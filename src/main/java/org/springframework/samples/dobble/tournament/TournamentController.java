package org.springframework.samples.dobble.tournament;

import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Model;
import javax.resource.spi.IllegalStateException;
import javax.security.auth.message.AuthException;
import javax.validation.Valid;

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

    private static final String VIEW_PLAY_TOURNAMENT = "tournaments/playTournament";
    private String VIEW_SHOW_TOURNAMENT = "tournaments/tournamentDetails";
    private String VIEWS_TOURNAMENTS_CREATE_OR_UPDATE_FORM = "tournaments/createOrUpdateTournamentForm";
    private String VIEW_INDEX_TOURNAMENTS = "tournaments/tournamentsList";

    private TournamentService tournamentService;
    private UserService userService;


    @ModelAttribute("tournamentmodes")
    public Iterable<TournamentMode> populateTournamentModes() {
        return this.tournamentService.findTournamentModes();
    }
    @Autowired
    public TournamentController(TournamentService tournamentService, UserService userService) {
        this.tournamentService = tournamentService;
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView indexUnstartedTournaments() {
        ModelAndView mav = new ModelAndView(VIEW_INDEX_TOURNAMENTS);
        List<Tournament> tournaments = this.tournamentService.findAllUnstartedTournaments();
        mav.addObject("tournaments", tournaments);
        return mav;

    }

    @GetMapping("/{tournamentId}")
    public ModelAndView showTournament(@PathVariable("tournamentId") Long tournamentId) {
        ModelAndView mav = new ModelAndView(VIEW_SHOW_TOURNAMENT);
        Tournament tournament = this.tournamentService.findTournament(tournamentId);
        mav.addObject(tournament);
        return mav;

    }

    @GetMapping("/new")
    public ModelAndView initCreationForm() {
        ModelAndView result=new ModelAndView(VIEWS_TOURNAMENTS_CREATE_OR_UPDATE_FORM);	
		result.addObject("tournament", new Tournament());
		result.addObject("allTournamentmodes", populateTournamentModes());
		return result;
    }


    @PostMapping("/new")
    public String createTournament(Tournament tournament, BindingResult result) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        User owner = userService.findUser(userId);
        tournament.setOwner(owner);
        if (result.hasErrors())
            return VIEWS_TOURNAMENTS_CREATE_OR_UPDATE_FORM;
        tournament.setState(TournamentState.LOBBY);
        this.tournamentService.saveTournament(tournament);
        return "redirect:/tournaments/" + tournament.getId();
    }

    @GetMapping("/{tournamentId}/play")
    public ModelAndView playTournament(@PathVariable("tournamentId") Long tournamentId) {
        Tournament tournament = this.tournamentService.findTournament(tournamentId);
        Iterable<User> mazos=tournament.getUsers();
		ModelAndView result=new ModelAndView("tournaments/LobbyTournament");
		result.addObject("users", mazos);
        result.addObject("tournament", tournament);
		return result;	
    }

    @PostMapping("/{tournamentId}/join")
    public String joinTournament(@PathVariable("tournamentId") Long tournamentId, @ModelAttribute("accessCode") String accessCode, RedirectAttributes redirAttrs) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userId = authentication.getName();
            tournamentService.addUserTournament(tournamentId, userId, accessCode);
        } catch(Exception e) {
            return "redirect:/tournaments?error="+ e.getMessage();
        } 
        return "redirect:/tournaments/{tournamentId}/play";

    }

    @GetMapping(path="/{tournamentId}/play/delete/{id}")
	public String DeleteUsersTournament(@PathVariable("tournamentId") Long tournamentId, @PathVariable("id") String id, RedirectAttributes redirAttrs) {
        try {
            tournamentService.deleteUserTournament(tournamentId, id);
        } catch(Exception e) {
            return "redirect:/tournaments?error="+ e.getMessage();
        } 
        return "redirect:/tournaments/{tournamentId}/play";

    }

}
