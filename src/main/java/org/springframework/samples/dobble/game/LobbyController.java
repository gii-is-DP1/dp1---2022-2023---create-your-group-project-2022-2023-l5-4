package org.springframework.samples.dobble.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.dobble.user.User;
import org.springframework.samples.dobble.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/games/{gameId}/lobby")
public class LobbyController {

     // Views declaration

     private static final String VIEW_PLAY_GAME = "games/playGame";
     private String VIEW_SHOW_GAME = "games/gameDetails";
     private String VIEWS_GAMES_CREATE_OR_UPDATE_FORM = "games/createOrUpdateGameForm";
     private String VIEW_INDEX_GAMES = "games/gamesList";
 
     // Constructor
     private GameService gameService;
     private GameUserService gameUserService;
     private UserService userService;
     
 
     @Autowired
     public LobbyController(GameService gameService, UserService userService,
             GameUserService gameUserService) {
         this.gameService = gameService;
         this.gameUserService = gameUserService;
         this.userService = userService;
     }
 
    @GetMapping
    public ModelAndView lobbyGame(@PathVariable("gameId") Long gameId) {
        Game game = this.gameService.findGame(gameId);
        List<GameUser> gameUsers = game.getGameUsers();
		ModelAndView result = new ModelAndView("games/LobbyGame");
		result.addObject("gameUsers", gameUsers);
        result.addObject("game", game);
		return result;	
    }

    @GetMapping(path="/delete/{id}")
	public String DeleteUsersGame(@PathVariable("gameId") Long gameId, @PathVariable("id") String id, RedirectAttributes redirAttrs) {
       try {
            gameUserService.deleteGameUser(gameId, id);
       } catch(Exception e) {
           return "redirect:/games?error="+ e.getMessage();
        } 
        return "redirect:/games/{gameId}/lobby";
    }
    
}
