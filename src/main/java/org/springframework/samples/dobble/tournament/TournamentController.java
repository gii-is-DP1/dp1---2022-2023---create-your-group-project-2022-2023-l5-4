package org.springframework.samples.dobble.tournament;

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
@RequestMapping("/tournaments")
public class TournamentController {

    public static final String TOURNAMENTS_LISTING="TournamentsListing";
    public static final String TOURNAMENT_EDIT="EditTournament";
    

    @Autowired
    TournamentService tournamentservice;

    @GetMapping
    public ModelAndView showTournamentsListing(){
        ModelAndView result = new ModelAndView(TOURNAMENTS_LISTING);
        result.addObject("tournament", tournamentservice.getAllTournaments());
        return result;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteTournament(@PathVariable("id") int id){
        tournamentservice.deleteTournament(id);
        ModelAndView result = showTournamentsListing();
        result.addObject("message", "Tournament removed succesfully");
        return result;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editTournament(@PathVariable("id") int id){
        ModelAndView result = new ModelAndView(TOURNAMENT_EDIT);
        Tournament tour = tournamentservice.getTournamentById(id);
        if(tour!=null){
            result.addObject("tournament", tour);
        }else{
            result = showTournamentsListing();
        }
        return result;
    }

    @PostMapping("/edit/{id}")
     public ModelAndView editTournament(@PathVariable("id") int id, @Valid Tournament tournament,BindingResult br) {        
         ModelAndView result=null;
         if(br.hasErrors()) {
             result=new ModelAndView(TOURNAMENT_EDIT);
             result.addAllObjects(br.getModel());         
         }else {
             Tournament tournamentToUpdate=tournamentservice.getTournamentById(id);
             
             if(tournamentToUpdate!=null) {
                 BeanUtils.copyProperties(tournament, tournamentToUpdate,"id");                 
                 tournamentservice.save(tournamentToUpdate);
                 result=showTournamentsListing();
                 result.addObject("message", "Tournament saved succesfully!");
             }else {
                 result=showTournamentsListing();             
                 result.addObject("message", "Tournament with id "+id+" not found!");
             }
         }                                                
         return result;
     }
     
     @GetMapping("/create")
     public ModelAndView createTournament() {
         ModelAndView result=new ModelAndView(TOURNAMENT_EDIT);
         Tournament tour=new Tournament();         
         result.addObject("tournament", tour);                                  
         return result;
     }
     
     
     @PostMapping("/create")
     public ModelAndView saveNewTournament(@Valid Tournament tour,BindingResult br) {        
         ModelAndView result=null;
         if(br.hasErrors()) {
             result=new ModelAndView(TOURNAMENT_EDIT);
             result.addAllObjects(br.getModel());         
         }else {                          
             tournamentservice.save(tour);
             result=showTournamentsListing();
             result.addObject("message", "Tournament saved succesfully!");             
         }                                                
         return result;
     }
}
