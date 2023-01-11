package org.springframework.samples.dobble.card;


import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.dobble.game.Game;
import org.springframework.samples.dobble.symbol.SymbolService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/cards")
public class CardController{
	
	@Autowired
	CardService cardService;
	
	@Autowired
	SymbolService symbolService;
	
	private static final String CARD_LIST_VIEW = "cards/cardsListing";

	@GetMapping
	public ModelAndView indexCards(){
		List<Card> cards=cardService.findAll();
		Game mockGame = new Game();
		Collections.reverse(cards);
		mockGame.setCards(cards);
		ModelAndView result=new ModelAndView(CARD_LIST_VIEW);
		result.addObject("mockGame", mockGame);
		return result;
		
	}
	
	

}
