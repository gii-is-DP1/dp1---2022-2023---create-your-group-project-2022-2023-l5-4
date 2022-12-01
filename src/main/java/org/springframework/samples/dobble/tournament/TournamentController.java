package org.springframework.samples.dobble.tournament;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.dobble.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/tournaments")
public class TournamentController<ParalamentarioSevice> {
	
	@Autowired
	TournamentService tournamentService;
	
	@Autowired
	UserService userService;
	
	private static final String PATHCARD = "tournaments/";
	@GetMapping
	public ModelAndView showTournaments(){
		Iterable<Tournament> mazos=tournamentService.findAll();
		ModelAndView result=new ModelAndView(PATHCARD+"TournamentsListing");
		result.addObject("tournaments", mazos);
		return result;
		
	}
	
	@GetMapping(path="/create")
	public ModelAndView crearteTournament(){		
		ModelAndView result=new ModelAndView(PATHCARD+"EditTournament");	
		result.addObject("tournament", new Tournament());
		result.addObject("allUsers", userService.findAll());
		return result;
	}


	@PostMapping(path="/create")
	public ModelAndView saveNewTournament(@ModelAttribute("tournament")  Tournament tournament) {
		tournamentService.save(tournament);
		ModelAndView result=showTournaments();	
		result.addObject("message", "Tournament created sucessfully!");
		result.addObject("messageType", "sucess");
		return result;
	}
	
	@GetMapping(path="/edit/{id}")
	public ModelAndView editarMazo(@PathVariable("id") long id){		
		ModelAndView result=new ModelAndView(PATHCARD+"EditTournament");	
		result.addObject("tournament", tournamentService.findById(id));
		result.addObject("allUsers", userService.findAll());
		return result;
	}
	
	@PostMapping(path="/edit/{id}")
	public ModelAndView grabarMazo(@ModelAttribute("tournament")  Tournament tournament, @PathVariable("id") long id) {
		tournamentService.save(tournament);
		ModelAndView result=showTournaments();	
		result.addObject("mesasge", "Tournament sucessfully updated");
		result.addObject("messageType", "sucess");
		return result;
	}
	
	@GetMapping(path="/delete/{id}")
	public ModelAndView borrarMazo(@PathVariable("id") long id){
		tournamentService.deleteById(id);
		ModelAndView result=showTournaments();	
		result.addObject("message", "Mazo borrado con Ã©xito");
		result.addObject("messageType", "sucess");
		return result;
	}
}
