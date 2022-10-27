package org.springframework.samples.petclinic.symbol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/symbols")
public class SymbolController {
	
	@Autowired
	SymbolService symbolService;
	
	@GetMapping
	public ModelAndView showSymbols(){
		Iterable<Symbol> parlamentarios=symbolService.findAll();
		ModelAndView result=new ModelAndView("SymbolsListing");
		result.addObject("symbols", parlamentarios);
		return result;
		
	}

	@PostMapping(path="/create")
	public ModelAndView saveNewSymbol(@ModelAttribute("symbol")  Symbol symbol) {
		symbolService.save(symbol);
		ModelAndView result=showSymbols();	
		result.addObject("message", "Symbol created sucessfully!");
		result.addObject("messageType", "sucess");
		return result;
	}
	
	@PostMapping(path="/edit/{id}")
	public ModelAndView grabarParlamentario(@ModelAttribute("symbol")  Symbol symbol, @PathVariable("id") long id) {
		symbolService.save(symbol);
		ModelAndView result=showSymbols();	
		result.addObject("mesasge", "Symbol sucessfully updated");
		result.addObject("messageType", "sucess");
		return result;
	}
	
	@GetMapping(path="/delete/{id}")
	public ModelAndView borrarParlamentario(@PathVariable("id") long id){
		symbolService.deleteById(id);
		ModelAndView result=showSymbols();	
		result.addObject("message", "Parlamentario borrado con éxito");
		result.addObject("messageType", "sucess");
		return result;
	}
}
