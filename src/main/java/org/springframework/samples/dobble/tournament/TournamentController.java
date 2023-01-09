package org.springframework.samples.dobble.tournament;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Model;
import javax.resource.spi.IllegalStateException;
import javax.security.auth.message.AuthException;
import javax.validation.Valid;

import org.hibernate.hql.internal.HolderInstantiator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.dobble.game.Game;
import org.springframework.samples.dobble.game.GameMode;
import org.springframework.samples.dobble.game.GameService;
import org.springframework.samples.dobble.game.GameState;
import org.springframework.samples.dobble.game.GameUser;
import org.springframework.samples.dobble.game.GameUserService;
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
    private GameService gameService;
    private UserService userService;
    private TournamentService tournamentService;
    private GameUserService gameUserService;

    @Autowired
    public TournamentController(TournamentService tournamentService, UserService userService, GameUserService gameUserService, GameService gameService) {
        this.tournamentService = tournamentService;
        this.userService = userService;
        this.gameService = gameService;
        this.gameUserService = gameUserService;
    }

    @ModelAttribute("gamemodes")
    public List<GameMode> populateGameModes() {
        return List.of(GameMode.values());
    }

    @GetMapping
    public ModelAndView indexUnstartedTournaments() {
        ModelAndView mav = new ModelAndView(VIEW_INDEX_TOURNAMENTS);
        List<Tournament> tournaments = this.tournamentService.findAllUnstartedTournaments();
        List<Integer> numPartidas = new ArrayList<Integer>();
        for(Tournament t: tournaments){
            numPartidas.add(t.getGamemodes().size());
        }
        mav.addObject("tournaments", tournaments);
        mav.addObject("numpartidas", numPartidas);
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
		return result;
    }


    @PostMapping("/new")
    public String createTournament(Tournament tournament, BindingResult result) {
        User owner = userService.getLoggedUser();
        tournament.setOwner(owner);
        if (result.hasErrors())
            return VIEWS_TOURNAMENTS_CREATE_OR_UPDATE_FORM;
        tournament.setState(TournamentState.LOBBY);
        tournamentService.saveTournament(tournament);
        return "redirect:/tournaments/" + tournament.getId();
    }

    @GetMapping("/{tournamentId}/play")
    public String playTournament(@PathVariable("tournamentId") Long tournamentId) {
        Tournament tournament = this.tournamentService.findTournament(tournamentId);
        Game game = new Game();
        List<GameUser> gameusers = new ArrayList<>();
        game.setGamemode(tournament.getGamemodes().get(0));
        game.setOwner(tournament.getOwner());
        game.setMaxPlayers(tournament.getMaxPlayers());
        game.setWinner(null);
        game.setState(GameState.LOBBY);
        game.setUpdatedAt(null);
        for(User user: tournament.getUsers()){
                user.setCurrentGame(game);
                userService.setCurrentGame(user, game);
                GameUser gameUser = new GameUser(user,game);
                gameUserService.save(gameUser);
                gameusers.add(gameUser);
        }
        game.setGameUsers(gameusers);
        gameService.saveGame(game);
        List<Game> games = tournament.getGames();
        games.add(game);
        tournament.setGames(games);
        if(tournament.getGamemodes().size()>1){
            List<GameMode> mode = tournament.getGamemodes();
            mode.remove(0);
            tournament.setGamemodes(mode);
        }
        TournamentService.saveTournament(tournament);
		return "redirect:/games/"+game.getId()+"/lobby";	
    }

    @GetMapping("/{tournamentId}/lobby")
    public ModelAndView lobbyTournament(@PathVariable("tournamentId") Long tournamentId) {
        Tournament tournament = this.tournamentService.findTournament(tournamentId);
        Iterable<User> mazos=tournament.getUsers();
		ModelAndView result=new ModelAndView("tournaments/LobbyTournament");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        Boolean isOwner = tournament.getOwner().getUsername().compareTo(userId)==0;
		result.addObject("isowner", isOwner);
        result.addObject("users", mazos);
        result.addObject("tournament", tournament);
		return result;	
    }

    @PostMapping(path="/{id}/lobby")
	public String grabarParlamentario(@ModelAttribute("tournament")  Tournament tournamentForm, @PathVariable("id") long id) {
		Tournament tournament = this.tournamentService.findTournament(id);
        tournament.setOwner(tournamentForm.getOwner());
        tournament.setGamemodes(tournamentForm.getGamemodes());
        TournamentService.saveTournament(tournament);
		return "redirect:/tournaments/"+id+"/lobby";
    }

    @GetMapping("/{tournamentId}/edit")
    public ModelAndView editTournament(@PathVariable("tournamentId") Long tournamentId) {
        Tournament tournament = this.tournamentService.findTournament(tournamentId);
        Iterable<User> mazos=tournament.getUsers();
        List<String> users = new ArrayList<String>();
        for(User u:mazos){
            users.add(u.getUsername());
        }
		ModelAndView result=new ModelAndView("tournaments/createOrUpdateGameForm");
        result.addObject("users", users);
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
        return "redirect:/tournaments/{tournamentId}/lobby";

    }

    @GetMapping(path="/{tournamentId}/lobby/delete/{id}")
	public String DeleteUsersTournament(@PathVariable("tournamentId") Long tournamentId, @PathVariable("id") String id, RedirectAttributes redirAttrs) {
        try {
            Tournament tournament = this.tournamentService.findTournament(tournamentId);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userId = authentication.getName();
            if(tournament.getOwner().getUsername().compareTo(userId)==0 && tournament.getNumUsers()>1){
                tournamentService.deleteUserTournament(tournamentId, id);
            }else{
                return "redirect:/tournaments/"+tournamentId+"/lobby?error=You are not allowed to do this action";
            }
        } catch(Exception e) {
            return "redirect:/tournaments?error="+ e.getMessage();
        } 
        return "redirect:/tournaments/{tournamentId}/lobby";

    }

}
