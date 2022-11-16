package org.springframework.samples.dobble.game;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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
	
	@Autowired
	private GameService gameService;
	
	@GetMapping
	public ModelAndView showGames(){		
		ModelAndView result=new ModelAndView("games/GamesListing");		
		result.addObject("games", gameService.findAll());
		return result;
	}
	
	
	@GetMapping(path="/edit/{id}")
	public ModelAndView editGame(@PathVariable("id") long id){		
		ModelAndView result=new ModelAndView("EditGame");	
		result.addObject("game", gameService.findById(id));		
		return result;
	}
	

}

