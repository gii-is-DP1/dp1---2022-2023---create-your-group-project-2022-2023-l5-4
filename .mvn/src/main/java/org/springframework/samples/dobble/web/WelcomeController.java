package org.springframework.samples.dobble.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.samples.dobble.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {
		List<Person> persons = new ArrayList<Person>();
		Person person = new Person();
		person.setFirstName("Antonio");
		person.setLastName("Barea");
		persons.add(person);
		Person person2 = new Person();
		person2.setFirstName("Pablo");
		person2.setLastName("Cuenca");
		persons.add(person2);
		Person person3 = new Person();
		person3.setFirstName("Adrian");
		person3.setLastName("Garcia-Baquero");
		persons.add(person3);
		Person person4 = new Person();
		person4.setFirstName("Antonio Jose");
		person4.setLastName("Lopez");
		persons.add(person4);
		Person person5 = new Person();
		person5.setFirstName("Eduardo");
		person5.setLastName("Robles");
		persons.add(person5);
		Person person6 = new Person();
		person6.setFirstName("Maria de los Remedios");
		person6.setLastName("Zoido");
		persons.add(person6);
		model.put("persons", persons);
		model.put("title", "Dobble");
		model.put("group", "Developers");
		
			    

	    return "welcome";
	  }
}
