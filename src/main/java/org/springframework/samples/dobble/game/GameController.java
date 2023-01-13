package org.springframework.samples.dobble.game;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.Map;

import javax.resource.spi.IllegalStateException;
import javax.security.auth.message.AuthException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.dobble.card.CardService;
import org.springframework.samples.dobble.card.Deck;
import org.springframework.samples.dobble.user.User;
import org.springframework.samples.dobble.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/games")
public class GameController {

    // Views declaration

    private String VIEW_SHOW_GAME = "games/gameDetails";
    private String VIEWS_GAMES_CREATE_OR_UPDATE_FORM = "games/createOrUpdateGameForm";
    private String VIEW_INDEX_GAMES = "games/gamesList";

    // Constructor
    private GameService gameService;
    private UserService userService;
    private CardService cardService;
    private GameUserService gameUserService;

    @Autowired
    public GameController(GameService gameService, UserService userService, CardService cardService,
            GameUserService gameUserService) {
        this.gameService = gameService;
        this.userService = userService;
        this.cardService = cardService;
        this.gameUserService = gameUserService;
    }

    // Game entity related actions
    @ModelAttribute("gamemodes")
    public List<GameMode> populateGameModes() {
        return List.of(GameMode.values());
    }

    @GetMapping
    public ModelAndView indexUnstartedGames(@ModelAttribute("error") String error) {
        ModelAndView mav = new ModelAndView(VIEW_INDEX_GAMES);
        List<Game> games = this.gameService.findAllUnstartedGames();
        User user = userService.getLoggedUser();
        mav.addObject("games", games);
        mav.addObject("user", user);
        mav.addObject("error",error);
        return mav;

    }

    @GetMapping("/{gameId}")
    public ModelAndView showGame(@PathVariable("gameId") Long gameId) {
        ModelAndView mav = new ModelAndView(VIEW_SHOW_GAME);
        Game game = this.gameService.findGame(gameId);
        mav.addObject(game);
        return mav;

    }

    @GetMapping("/new")
    public String initCreationForm(Map<String, Object> model) {
        try {
            Game game = new Game();
            model.put("game", game);
        } catch (Error err) {
        }
        return VIEWS_GAMES_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String createGame(Game game, BindingResult result) throws AuthException, NullPointerException, IllegalStateException {
        if (result.hasErrors())
            return VIEWS_GAMES_CREATE_OR_UPDATE_FORM;
        User owner = userService.getLoggedUser();
        game.setOwner(owner);
        game.setState(GameState.LOBBY);
        this.gameService.saveGame(game);
        gameUserService.addGameUser(game.getId(), owner.getUsername(), game.getAccessCode());
        return "redirect:/games/" + game.getId() + "/lobby";
    }

    // Before starting

    @PostMapping("/{gameId}/join")
    public RedirectView joinGame(@PathVariable("gameId") Long gameId, @ModelAttribute("accessCode") String accessCode, RedirectAttributes attributes) {
        Game game = gameService.findGame(gameId);
        String userId = userService.getLoggedUser().getUsername();
        try {
            gameUserService.addGameUser(gameId, userId, accessCode);
        } catch(Exception e) {
            attributes.addFlashAttribute("error",e.getMessage());
            return new RedirectView("/games");
        } 
        if (game.hasStarted()) return new RedirectView("/games/{gameId}/play");
        return new RedirectView("/games/{gameId}/lobby");

    }


    @PostMapping("/{gameId}/start")
    public RedirectView startGame(@PathVariable("gameId") Long gameId, RedirectAttributes attributes) {
        Game game = gameService.findGame(gameId);
        User user = userService.getLoggedUser();
        
        if (!user.equals(game.getOwner())) {
            attributes.addFlashAttribute("error","Only the game owner can start the game.");
            return new RedirectView("lobby");
        }
        
        List<GameUser> gameUsers = game.getGameUsers();
        if (gameUsers.size()<2) {
            attributes.addFlashAttribute("error","There is no enough users to start the game (min. 2 players are required)");
            return new RedirectView("lobby");
        }

        Deck cards = Deck.of(cardService.findAll());
        
        Map<GameUser, Deck> deal = cards.deal(gameUsers, game.getGamemode());
        Deck centralDeck = cards.getLeftForCenter();
        game.setCards(centralDeck);
        gameUsers.forEach(gameUser -> {
            gameUser.setCards(deal.get(gameUser));
            gameUserService.save(gameUser);
        });
        game.setState(GameState.ON_PLAY);
        game.setStartedAt(LocalDateTime.now());
        
        gameService.saveGame(game);
        return new RedirectView("play");
    }



    // In-game related actions

}
