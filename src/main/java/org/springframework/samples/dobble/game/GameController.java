package org.springframework.samples.dobble.game;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/games")
public class GameController {

    private String VIEW_SHOW_GAME = "games/gameDetails";

    @Autowired
    private GameService gameService;

    public GameController(GameService gameService){
        this.gameService=gameService;
    }

    @GetMapping("/{gameId}")
    public ModelAndView showGame(@PathVariable("gameId") Long gameId){
        System.out.println("yey");
        ModelAndView mav = new ModelAndView(VIEW_SHOW_GAME);
        Game game = this.gameService.findGame(gameId);
        mav.addObject(game);
        return mav;
        
    }

    @PostMapping
    public Game createGame(Game game, BindingResult result){
        if (result.hasErrors()) return null;
        return this.gameService.saveGame(game);
    }
}
