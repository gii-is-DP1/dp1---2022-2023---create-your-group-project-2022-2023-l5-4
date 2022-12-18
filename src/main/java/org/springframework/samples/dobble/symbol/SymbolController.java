package org.springframework.samples.dobble.symbol;

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
		Iterable<Symbol> symbols=symbolService.findAll();
		ModelAndView result=new ModelAndView("SymbolsListing");
		result.addObject("symbols", symbols);
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

	// @GetMapping(path="{symbolId}/variants/{symbolSetId}")
	// public String getSymbolVariant(@PathVariable("symbolId") Long symbolId, @PathVariable("symbolSetId") Long symbolSetId){
	// 	SymbolVariant symbol = symbolService.findSymbolVariant(symbolSetId, symbolId);
	// 	SymbolSet symbolSet = symbol.getSymbolSet();
	// 	return "redirect:/%s/%s".formatted(symbolSet.getPath(), symbol.getFileName());
	// }
	
}
