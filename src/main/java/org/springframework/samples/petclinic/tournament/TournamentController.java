package org.springframework.samples.petclinic.tournament;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tournaments")
public class TournamentController {
    

    @Autowired
    private TournamentService tournamentService;

    @GetMapping
    public ModelAndView listadoTorneos(){
        ModelAndView result = new ModelAndView("TournamentListing");
        result.addObject("tournaments", tournamentService.getTorneos());
        return result;
    }


     
     @GetMapping(path="/create")
     public ModelAndView createTournament() {
         ModelAndView result=new ModelAndView("EditTour");        
         result.addObject("tournaments", new Tournament());                                  
         return result;
     }
     
     
     @PostMapping(path="/save")
 		public ModelAndView salvarTorneo(@ModelAttribute("tour")  Tournament torneo){		
 		tournamentService.save(torneo);
 		ModelAndView result=listadoTorneos();		
 		result.addObject("mensaje", "Tournament saved sucessfully!");		
 		result.addObject("tipomensaje", "success");
 		return result;
 	}
}
