package demo.controllers;

import javax.servlet.http.HttpServletRequest;

import demo.Car;
import demo.CarRepository;
import demo.ErrorInfo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.HttpStatus;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.*;
import java.util.*;


@RestController
public class DemoController{
	List<String> strings = Arrays.asList("a1", "a2", "b1", "c2", "c1");

	@Autowired
	private CarRepository repo;

	@RequestMapping ("/sayHello")
	public String sayHello(){
		return "hello";
	}

	@RequestMapping("/findAll")
	public String findAll(){
		System.out.println("FINDALL");
		for(Car c : repo.findAll()){
			System.out.println(c);
		}

		System.out.println(repo.findByMake("Tesla"));
        System.out.println(repo.findByColor("Black"));
		
		repo.findAll().forEach(e -> System.out.println(e)); // Or simply: System.out::println

		return "OK";
	}

	// Find one car
	@RequestMapping("/findOne")
	public Car findOneCar(@RequestParam(value="name", required=true, defaultValue="Hyundai") String name){
		// Hard coded
		Car c = new Car(name, "Tiburon", "Silver");
		return c;
	}

	// Create one car
	@RequestMapping(value="/createOne", method=RequestMethod.POST)
	public Car createOne(@ModelAttribute Car newCar, Model model){
		// Hard coded
		System.out.println(newCar.toString());

		model.addAttribute("newCar", newCar);
        return (Car) repo.save(newCar);
	}

	@RequestMapping("/testStream")
	public String streamIt(){
		// Get only the items that start with C and display
		strings.stream()
			   .filter(f -> f.startsWith("c"))
			   .forEach(System.out::println);


		// Get the items that start with a save to a new list
		List<String> newList = 
				strings.stream()
			   .filter(f -> f.startsWith("a"))
			   .collect(Collectors.toList());

			   newList.forEach(System.out::println);


		// Get all items that have a 2 in it and append some string
		strings.stream()
			   .filter(f -> f.contains("2"))
			   .map(e -> e+="asdf")
			   .forEach(System.out::println);

		return "OK";
	}

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value=HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorInfo handleTypeMismatchException(HttpServletRequest req, Exception ex) {

        String url = req.getRequestURL().toString();
        System.out.println("Handling error, path: " + url);
        return new ErrorInfo(ex.getMessage());
    }


}