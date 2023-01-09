package org.springframework.samples.dobble.game;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.enterprise.inject.Model;
import javax.resource.spi.IllegalStateException;
import javax.security.auth.message.AuthException;
import javax.validation.Valid;
import javax.ws.rs.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.dobble.card.Card;
import org.springframework.samples.dobble.card.CardService;
import org.springframework.samples.dobble.card.Deck;
import org.springframework.samples.dobble.comment.Comment;
import org.springframework.samples.dobble.forum.ForumService;
import org.springframework.samples.dobble.symbol.Symbol;
import org.springframework.samples.dobble.symbol.SymbolService;
import org.springframework.samples.dobble.user.User;
import org.springframework.samples.dobble.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/games")
public class GameController {

    // Views declaration

    private static final String VIEW_PLAY_GAME = "games/playGame";
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
    public ModelAndView indexUnstartedGames() {
        ModelAndView mav = new ModelAndView(VIEW_INDEX_GAMES);
        List<Game> games = this.gameService.findAllUnstartedGames();
        mav.addObject("games", games);
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
    public String joinGame(@PathVariable("gameId") Long gameId, @ModelAttribute("accessCode") String accessCode) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userId = authentication.getName();
            gameUserService.addGameUser(gameId, userId, accessCode);
        } catch(Exception e) {
            return "redirect:/games?error="+ e.getMessage();
        } 
        return "redirect:/games/{gameId}/lobby";

    }


    @GetMapping("/{gameId}/start")
    public String startGame(@PathVariable("gameId") Long gameId) {
        Game game = gameService.findGame(gameId);
        List<GameUser> gameUsers = game.getGameUsers();
        Deck cards = Deck.of(cardService.findAll());
        
        Map<GameUser, Deck> deal = cards.deal(gameUsers, game.getGamemode());
        Deck centralDeck = cards.getLeftForCenter();
        game.setCards(centralDeck);
        gameUsers.forEach(gameUser -> {
            gameUser.setCards(deal.get(gameUser));
            gameUserService.save(gameUser);
        });
        game.setState(GameState.ON_PLAY);
        gameService.saveGame(game);
        return "redirect:play";
    }



    // In-game related actions



}
