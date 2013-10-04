package apples.au.pivotal.controllers;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import apples.au.pivotal.People;
import apples.au.pivotal.Person;

@Controller
public class PersonController 
{

	private static List<Person> personList;

	static
	{
		personList = 
				Arrays.asList(new Person[] 
				  { new Person(1, "Pas", "Apicella"),
			        new Person(2, "Lucia", "Apicella"),
			        new Person(3, "Lucas", "Apicella"),
			        new Person(4, "Siena", "Apicella")
			      }); 		
	}

    @RequestMapping(value="/people", 
    		        method = RequestMethod.GET, 
    		        produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody People listWithJSON() 
    {
    	return new People(personList);

    }
    
    // View-based method
    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public String listWithView(Model model, HttpServletResponse response, HttpServletRequest request) 
    {
			      
    	// Call RESTful method to avoid repeating code
    	model.addAttribute("peopleList", listWithJSON().getPeople());

    	// Return the view to use for rendering the response
    	return "people";
    }
}
