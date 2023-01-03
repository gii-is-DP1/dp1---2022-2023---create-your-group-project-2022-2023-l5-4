package org.springframework.samples.dobble.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.dobble.card.CardService;
import org.springframework.samples.dobble.symbol.Symbol;
import org.springframework.samples.dobble.symbol.SymbolService;
import org.springframework.samples.dobble.user.User;
import org.springframework.samples.dobble.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/games/{gameId}/play")
public class MatchController {

    
    private static final String VIEW_PLAY_GAME = "games/playGame";
    private String VIEW_SHOW_GAME = "games/gameDetails";
    private String VIEWS_GAMES_CREATE_OR_UPDATE_FORM = "games/createOrUpdateGameForm";
    private String VIEW_INDEX_GAMES = "games/gamesList";

    // Constructor
    private GameService gameService;
    private UserService userService;
    private CardService cardService;
    private GameUserService gameUserService;
    private SymbolService symbolService;

    @Autowired
    public MatchController(GameService gameService, UserService userService, CardService cardService,
            GameUserService gameUserService, SymbolService symbolService) {
        this.gameService = gameService;
        this.userService = userService;
        this.cardService = cardService;
        this.gameUserService = gameUserService;
        this.symbolService = symbolService;
    }

    @GetMapping
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


      
    @GetMapping(path = "/delete/{userId}")
    public String deleteUsersGame(@PathVariable("gameId") Long gameId,
            @PathVariable("userId") String userId, RedirectAttributes redirAttrs) {
        try {
            gameUserService.deleteGameUser(gameId, userId);
        } catch (Exception e) {
            return "redirect:/games?error=" + e.getMessage();
        }
        return "redirect:/games/{gameId}/play";
    }


    


    @PostMapping("/match")
    public String checkMatch(@PathVariable("gameId") Long gameId, @ModelAttribute("symbol") Symbol symbol,  @ModelAttribute("user") User user) {
        //This method steps are only made for testing at the moment. For the next sprint it will 
        //fully implement the required mehtoda
        Game game = gameService.findGame(gameId);
        Boolean symbolMatches = game.getHand().getCurrentCard().matches(symbol);
        Boolean userMatches = true;
        switch (game.getGamemode().getName()) {
            case "The Poisoned Gift":
                userMatches = user.getUsername()!=null && !user.getUsername().equals(userService.getSessionUser());
                break;  
            default:
                userMatches = user.getUsername()!=null && user.getUsername().equals(userService.getSessionUser());
                break;  
        }
        System.out.println("HEEEEEEEEEEEEERE");
        System.out.println(symbolMatches);
        System.out.println(userMatches);
        if (symbolMatches && userMatches) gameUserService.makePlay(game, user);
       
        if (game.getCentralDeck().size()==0) return "redirect:/games/{gameId}/play?NoMoreCardsInTheCenter";
        return "redirect:/games/{gameId}/play?" + userMatches;
    }
    
}
