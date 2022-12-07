package org.springframework.samples.dobble.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/players")
public class PlayerController {
	
	@Autowired
	PlayerService playerService;
	
	@GetMapping
	public ModelAndView showPlayers(){
		Iterable<Player> players=playerService.findAll();
		ModelAndView result=new ModelAndView("PlayersListing");
		result.addObject("players", players);
		return result;
		
	}

	@PostMapping(path="/create")
	public ModelAndView saveNewPlayer(@ModelAttribute("player")  Player player) {
		playerService.save(player);
		ModelAndView result=showPlayers();	
		result.addObject("message", "Player created sucessfully!");
		result.addObject("messageType", "sucess");
		return result;
	}
	
}
