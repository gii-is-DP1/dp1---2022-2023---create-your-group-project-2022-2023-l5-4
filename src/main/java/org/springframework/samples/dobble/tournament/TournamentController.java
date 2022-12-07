package org.springframework.samples.dobble.tournament;


import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/tournaments")
public class TournamentController {
	

	private TournamentService tournamentService;
	private UserService userService;

	@Autowired
    public TournamentController(TournamentService tournamentService, UserService userService) {
        this.tournamentService = tournamentService;
        this.userService = userService;
    }

	private static final String PATHCARD = "tournaments/";


	@GetMapping
	public ModelAndView showTournaments(){
		Iterable<Tournament> tours=tournamentService.findAll();
		ModelAndView result=new ModelAndView(PATHCARD+"TournamentsListing");
		result.addObject("tournaments", tours);
		return result;
		
	}
	
	@GetMapping(path="/create")
	public ModelAndView createTournament(){		
		ModelAndView result=new ModelAndView(PATHCARD+"EditTournament");	
		result.addObject("tournament", new Tournament());
		return result;
	}



	@PostMapping("/create")
    public String createTournament(Tournament torneo, BindingResult result) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        User owner = userService.findUser(userId);
        torneo.setOwner(owner);
        if (result.hasErrors())
            return "tournaments/EditTournament";
        this.tournamentService.save(torneo);
        return "tournaments/TournamentsListing";
    }
	
	@GetMapping(path="/edit/{id}")
	public ModelAndView editarMazo(@PathVariable("id") long id){		
		ModelAndView result=new ModelAndView(PATHCARD+"EditTournament");	
		result.addObject("tournament", tournamentService.findById(id));
		result.addObject("allPlayers", userService.getUsers());
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
		result.addObject("message", "Delete success");
		result.addObject("messageType", "Success");
		return result;
	}


	@PostMapping(path="/{tournamentId}/join")
    public String joinTournament(@PathVariable("tournamentId") Long tournamentId, @ModelAttribute("contraseña") String constraseña, RedirectAttributes redirAttrs) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userId = authentication.getName();
            tournamentService.addUserTournament(tournamentId, userId, constraseña);
        } catch(Exception e) {
            return "redirect:/tournaments?error="+ e.getMessage();
        } 
        return "redirect:/tournaments/{tournamentId}/play";

    }
}
