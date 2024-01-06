package com.infomerica.ars.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.infomerica.ars.DAO.Flight;
import com.infomerica.ars.DAO.User;
import com.infomerica.ars.Service.UserService;



@Controller
public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	private String username;
	
	@GetMapping("/home")
	public String userController(Model model) {
		logger.info("Home page is accessed :{}",LocalDateTime.now());
		model.addAttribute("boardingPoints",userService.boardingPoints());
		model.addAttribute("destination",userService.destination());
		model.addAttribute("dates",userService.dates());
		return "SearchFlight";
	}
	@GetMapping("/displayflights")
	public String displayFlights(@RequestParam String boardingPoint,@RequestParam String destination,@RequestParam String dates,Model model) {
		logger.info("DisplayFlights page is accessed :{}",LocalDateTime.now());
		List<Flight> flight =  userService.displayFlights(boardingPoint,destination,dates);	
		model.addAttribute("flights",flight);	
		return "displayFlights";
	}
	
	@GetMapping("/displayticket")
	public String displayTicket(@RequestParam String flightNumber,@RequestParam String boardingPoint,@RequestParam String destination,@RequestParam LocalDate startDate,@RequestParam String startTime,@RequestParam LocalDate endDate,@RequestParam String endTime,@RequestParam int price,Model model) {
		logger.info("Home page is accessed :{}",LocalDateTime.now());
		Flight flight = new Flight(flightNumber,boardingPoint,destination,startDate,startTime,endDate,endTime,price);
		model.addAttribute("flights",flight);
		logger.info("Ticket Displayed with details :{}",flight);
		return "seatUserDetailsSelection";
	}
	@GetMapping("/seatconformation")
	public String seatSelection(@RequestParam String flightNumber,@RequestParam String boardingPoint,@RequestParam String destination,@RequestParam LocalDate startDate,@RequestParam String startTime,@RequestParam LocalDate endDate,@RequestParam String endTime,@RequestParam int price,@RequestParam String fullName,@RequestParam String email,@RequestParam Long phone,@RequestParam String seats,Model model) {
		logger.info("seatSelection page is accessed :{}",LocalDateTime.now());
		Flight flight = new Flight(flightNumber,boardingPoint,destination,startDate,startTime,endDate,endTime,price);
		int totalPrice = userService.calculatePayment(flight, seats);
		model.addAttribute("flight",flight);
		model.addAttribute("price",totalPrice);
		model.addAttribute("fullName",fullName);
		model.addAttribute("phone",phone);
		model.addAttribute("seats",seats);
		model.addAttribute("email",email);
		logger.info("Ticket Displayed with details: flight={}, totalPrice={}, phone={}, seats={}, email={}", flight, totalPrice, phone, seats, email);
		return "displayTickets";
	}
	
	@GetMapping("/payment")
	public String payment(@RequestParam String flightNumber,@RequestParam String boardingPoint,@RequestParam String destination,@RequestParam LocalDate startDate,@RequestParam String startTime,@RequestParam LocalDate endDate,@RequestParam String endTime,@RequestParam int price,@RequestParam String fullName,@RequestParam String email,@RequestParam Long phone,@RequestParam String seats,Model model) {
		logger.info("Payment page is accessed :{}",LocalDateTime.now());
		Flight flight = new Flight(flightNumber,boardingPoint,destination,startDate,startTime,endDate,endTime,price);
		model.addAttribute("flight",flight);
		model.addAttribute("price",price);
		model.addAttribute("phone",phone);
		model.addAttribute("fullName",fullName);
		model.addAttribute("email", email);
	return "payment";
		
	}
	
	@GetMapping("/paymentdone")
	public String paymentDone(@RequestParam String flightNumber,@RequestParam String boardingPoint,@RequestParam String destination,@RequestParam LocalDate startDate,@RequestParam String startTime,@RequestParam LocalDate endDate,@RequestParam String endTime,@RequestParam int price,@RequestParam String fullName,@RequestParam String email,@RequestParam Long phone,@RequestParam String seats,Model model) {
		logger.info("paymentDone page is accessed :{}",LocalDateTime.now());
		Flight flight = new Flight(flightNumber,boardingPoint,destination,startDate,startTime,endDate,endTime,price);
		userService.sendEmail(email, "Your Ticket Has Been Successfully Booked",userService.setMessage(flight,price,phone,fullName,email) );
		model.addAttribute("flight",flight);
		model.addAttribute("price",price);
		model.addAttribute("phone",phone);
		model.addAttribute("fullName",fullName);
		model.addAttribute("email", email);
		logger.info("Payment done with details: flight={}, totalPrice={}, phone={}, fullName={}, email={}", flight, price, phone, fullName, email);
		return "bookingConfomation";
	}
	
	@GetMapping("/registrationPage")
	public String registrationPage( ) {
		logger.info("registrationPage is accessed :{}",LocalDateTime.now());
		return "registration";
	}

	@GetMapping("/registration")
	public String registration(@RequestParam String email,@RequestParam String fullName, @RequestParam String userName,@RequestParam Long phoneNumber,@RequestParam String password ) {
		User user = new User(fullName,userName,email,password,phoneNumber);
		System.out.println(user);
		userService.save_User(user);
		return "login";
	}
	
	
	@GetMapping("/loginpage")
	public String loginPage() {
		logger.info("Login is accessed :{}",LocalDateTime.now());
		return "login";
	}
	
	@GetMapping("/loginDetails")
	public String login(@RequestParam String email,@RequestParam String password ,Model model) {
		
		if(email.equalsIgnoreCase("admin@gmail.com") && password.equalsIgnoreCase("admin"))
			return "addFlight";
		if(username==null)
			username =userService.checkDetails(email,password);
		if(!username.equals("failed")) {
			model.addAttribute("boardingPoints",userService.boardingPoints());
			model.addAttribute("destination",userService.destination());
			model.addAttribute("dates",userService.dates());
			model.addAttribute("username",username);
			return "SearchFlight";
		}
		else 
			return "error";
	}
}
