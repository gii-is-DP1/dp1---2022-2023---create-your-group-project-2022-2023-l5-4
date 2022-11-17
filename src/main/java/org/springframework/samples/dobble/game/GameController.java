package org.springframework.samples.dobble.game;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.dobble.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/games")
public class GameController {

    private String VIEW_SHOW_GAME = "games/gameDetails";
    private String VIEWS_GAMES_CREATE_OR_UPDATE_FORM = "games/createOrUpdateGameForm";
    private String VIEW_INDEX_GAMES = "games/gamesList";

    
    private GameService gameService;
    
    @Autowired
    public GameController(GameService gameService){
        this.gameService=gameService;
    }
    
    @ModelAttribute("gamemodes")
	public List<GameMode> populateGameModes() {
		return this.gameService.findGameModes();
	}

    
    @GetMapping
    public ModelAndView indexGames(){
        ModelAndView mav = new ModelAndView(VIEW_INDEX_GAMES);
        List<Game> games = this.gameService.findAllGames();
        mav.addObject("games", games); 
        return mav;

    }
    @GetMapping("/{gameId}")
    public ModelAndView showGame(@PathVariable("gameId") Long gameId){
        System.out.println("yey");
        ModelAndView mav = new ModelAndView(VIEW_SHOW_GAME);
        Game game = this.gameService.findGame(gameId);
        mav.addObject(game);
        return mav;
        
    }
    @GetMapping("/new")
	public String initCreationForm(Map<String, Object> model) {
		Game game = new Game();
        User user = new User();
		model.put("game", game);
        model.put("user", user);
		return VIEWS_GAMES_CREATE_OR_UPDATE_FORM;
	}

    @PostMapping("/new")
    public String createGame(@Valid Game game, BindingResult result){
        System.out.println("yey2");
        if (result.hasErrors()) return VIEWS_GAMES_CREATE_OR_UPDATE_FORM;
        this.gameService.saveGame(game);
        return "redirect:/games/" + game.getId();
    }
}
