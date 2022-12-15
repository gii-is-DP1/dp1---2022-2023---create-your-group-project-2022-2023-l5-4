package org.springframework.samples.dobble.game;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.enterprise.inject.Model;
import javax.resource.spi.IllegalStateException;
import javax.security.auth.message.AuthException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.dobble.card.Card;
import org.springframework.samples.dobble.card.CardService;
import org.springframework.samples.dobble.card.Deck;
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
        return this.gameService.findGameModes();
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
    public String createGame(Game game, BindingResult result) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        User owner = userService.findUser(userId);
        game.setOwner(owner);
        if (result.hasErrors())
            return VIEWS_GAMES_CREATE_OR_UPDATE_FORM;
        game.setState(GameState.LOBBY);
        this.gameService.saveGame(game);
        return "redirect:/games/" + game.getId();
    }

    // Before starting

    @PostMapping("/{gameId}/join")
    public String joinGame(@PathVariable("gameId") Long gameId, @ModelAttribute("accessCode") String accessCode) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userId = authentication.getName();
            gameService.addGameUser(gameId, userId, accessCode);
        } catch(Exception e) {
            return "redirect:/games?error="+ e.getMessage();
        } 
        return "redirect:/games/{gameId}/lobby";

    }
  
    @GetMapping(path = "/{gameId}/play/delete/{userId}")
    public String deleteUsersGame(@PathVariable("gameId") Long gameId,
            @PathVariable("userId") String userId, RedirectAttributes redirAttrs) {
        try {
            gameService.deleteGameUser(gameId, userId);
        } catch (Exception e) {
            return "redirect:/games?error=" + e.getMessage();
        }
        return "redirect:/games/{gameId}/play";
    }

    @GetMapping("/{gameId}/start")
    public String startGame(@PathVariable("gameId") Long gameId) {
        Game game = gameService.findGame(gameId);
        List<GameUser> users = game.getUsers();
        Deck cards = Deck.of(cardService.findAll());
        
        Map<GameUser, Deck> deal = cards.deal(users, game.getGamemode());
        Deck centralDeck = cards.getLeftForCenter();
        game.setCentralDeck(centralDeck);
        users.forEach(user -> {
            user.setCards(deal.get(user));
            gameUserService.saveGameUser(user);
        });
        game.setState(GameState.STARTED);
        gameService.saveGame(game);
        return "redirect:play";
    }


    // In-game related actions

    @GetMapping("/{gameId}/play")
    public ModelAndView playGame(@PathVariable("gameId") Long gameId) {
        ModelAndView mav = new ModelAndView(VIEW_PLAY_GAME);
        Game game = this.gameService.findGame(gameId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User mainUser = userService.findUser(username);
        GameUser mainPlayer = gameUserService.findGameUser(GameUserPk.of(mainUser , game));
        List<GameUser> players = game.getUsers();
        players.removeIf(player -> player.equals(mainPlayer));
        mav.addObject("mainPlayer", mainPlayer);
        mav.addObject("players", players);
        mav.addObject("game", game);
        mav.addObject(game);
        return mav;

    }
    @GetMapping("/{gameId}/lobby")
    public ModelAndView lobbyGame(@PathVariable("gameId") Long gameId) {
        Game game = this.gameService.findGame(gameId);
        Iterable<GameUser> mazos=game.getUsers();
		ModelAndView result=new ModelAndView("games/LobbyGame");
		result.addObject("users", mazos);
        result.addObject("game", game);
		return result;	
    }


    @PostMapping("/{gameId}/match")
    public String checkMatch(@PathVariable("gameId") Long gameId, @ModelAttribute("symbol") String symbol) {
        System.out.println("MATCH");
        Game game = gameService.findGame(gameId);
        game.getCentralDeck().remove(0);
        gameService.saveGame(game);
        return "redirect:play?" + symbol;
    }

    @GetMapping(path="/{gameId}/lobby/delete/{id}")
	public String DeleteUsersGame(@PathVariable("gameId") Long gameId, @PathVariable("id") String id, RedirectAttributes redirAttrs) {
       try {
            gameService.deleteGameUser(gameId, id);
       } catch(Exception e) {
           return "redirect:/games?error="+ e.getMessage();
        } 
        return "redirect:/games/{gameId}/lobby";
    }


}
