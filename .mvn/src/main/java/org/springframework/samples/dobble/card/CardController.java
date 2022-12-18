package org.springframework.samples.dobble.card;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.dobble.symbol.SymbolService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/cards")
public class CardController<ParalamentarioSevice> {
	
	@Autowired
	CardService cardService;
	
	@Autowired
	SymbolService symbolService;
	
	private static final String PATHCARD = "cards/";
	@GetMapping
	public ModelAndView showCards(){
		Iterable<Card> mazos=cardService.findAll();
		ModelAndView result=new ModelAndView(PATHCARD+"CardsListing");
		result.addObject("cards", mazos);
		return result;
		
	}
	
	@GetMapping(path="/create")
	public ModelAndView crearteCard(){		
		ModelAndView result=new ModelAndView(PATHCARD+"EditCard");	
		result.addObject("card", new Card());
		result.addObject("allSymbols", symbolService.findAll());
		return result;
	}


	@PostMapping(path="/create")
	public ModelAndView saveNewCard(@ModelAttribute("card")  Card card) {
		cardService.save(card);
		ModelAndView result=showCards();	
		result.addObject("message", "Card created sucessfully!");
		result.addObject("messageType", "sucess");
		return result;
	}
	
	@GetMapping(path="/edit/{id}")
	public ModelAndView editarMazo(@PathVariable("id") long id){		
		ModelAndView result=new ModelAndView(PATHCARD+"EditCard");	
		result.addObject("card", cardService.findById(id));
		result.addObject("allSymbols", symbolService.findAll());
		return result;
	}
	
	@PostMapping(path="/edit/{id}")
	public ModelAndView grabarMazo(@ModelAttribute("card")  Card card, @PathVariable("id") long id) {
		cardService.save(card);
		ModelAndView result=showCards();	
		result.addObject("mesasge", "Card sucessfully updated");
		result.addObject("messageType", "sucess");
		return result;
	}
	
	@GetMapping(path="/delete/{id}")
	public ModelAndView borrarMazo(@PathVariable("id") long id){
		cardService.deleteById(id);
		ModelAndView result=showCards();	
		result.addObject("message", "Mazo borrado con Ã©xito");
		result.addObject("messageType", "sucess");
		return result;
	}
}
