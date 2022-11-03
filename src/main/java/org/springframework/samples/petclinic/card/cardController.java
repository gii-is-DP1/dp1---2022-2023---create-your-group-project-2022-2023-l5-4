package org.springframework.samples.petclinic.card;

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
@RequestMapping("/cards")
public class cardController {
    public static final String CARD_LISTING="cardListing";
    @Autowired
    cardService service;

    @GetMapping()
    public ModelAndView showAllCards(){
        ModelAndView result = new ModelAndView(CARD_LISTING);
        result.addObject("cards", service.getCards());
        return result;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteCard(@PathVariable("id") long id){
        service.deleteCard(id);
        return showAllCards();
    }
    @GetMapping("/edit/{id}")
    public ModelAndView editCard(@PathVariable("id") long id){
        ModelAndView result = new ModelAndView("EditCard");
        Optional<card> card=service.getCardById(id);
        if(card.isPresent()){
            result.addObject("card", card.get());
        }else{
            result=showAllCards();
            result.addObject("message", "Card with id " + id + " not found!");
        }
        return result;
    }
    @PostMapping("/edit/{id}")
    public ModelAndView saveEditedCard(card card, BindingResult br,@PathVariable("id") long id){
        ModelAndView result=new ModelAndView();
        if(!br.hasErrors()){
            service.save(card);
        }
        return result; 
    }
    
}
