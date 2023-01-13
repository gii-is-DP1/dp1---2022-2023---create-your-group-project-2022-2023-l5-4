package org.springframework.samples.dobble.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.dobble.user.User;
import org.springframework.samples.dobble.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/games/{gameId}/lobby")
public class LobbyController {

     // Views declaration

     private static final String VIEW_LOBBY_GAME = "games/LobbyGame";
     
 
     // Constructor
     private GameService gameService;
     private GameUserService gameUserService;
     private UserService userService;
     
 
     @Autowired
     public LobbyController(GameService gameService, GameUserService gameUserService, UserService userService) {
         this.gameService = gameService;
         this.gameUserService = gameUserService;
         this.userService = userService;
     }
 
    @GetMapping
    public ModelAndView lobbyGame(@PathVariable("gameId") Long gameId, @ModelAttribute("error") String error) {
        Game game = this.gameService.findGame(gameId);
        if (game.isOnPlay()) return new ModelAndView("redirect:/games/{gameId}/play");
        if (game.isFinished()) return new ModelAndView("redirect:/games/{gameId}");
        List<GameUser> gameUsers = game.getGameUsers();
		ModelAndView result = new ModelAndView(VIEW_LOBBY_GAME);
		result.addObject("gameUsers", gameUsers);
        result.addObject("game", game);
        result.addObject("user", userService.getLoggedUser());
        result.addObject("error",error);
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
