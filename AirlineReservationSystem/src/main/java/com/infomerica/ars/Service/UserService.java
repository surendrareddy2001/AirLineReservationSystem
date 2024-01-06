package com.infomerica.ars.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.infomerica.ars.DAO.Flight;
import com.infomerica.ars.DAO.User;
import com.infomerica.ars.Repository.FlightRepository;
import com.infomerica.ars.Repository.UserRepository;
import com.infomerica.ars.constants.Constants;

@Service
public class UserService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private FlightRepository flightRepository;
	
	
	@Autowired
	private UserRepository userRepository;
	

	@Autowired
	private JavaMailSender javaMailSender;
	
	public List<String> boardingPoints() {
		
		return flightRepository.getDistinctBoardingPoint();
	}
	
	public List<String> destination() {
		
		return flightRepository.getDistinctDestination();
	}
	
	public List<String> dates() {
		
		return UserService.formatDates(flightRepository.getDistinctDates()) ;
	}
	
    public static List<String> formatDates(List<Date> dates) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dates.stream().map(date -> dateFormat.format(date)).collect(Collectors.toList());
    }

	public List<Flight> displayFlights(String boardingPoint, String destination, String dates) {

		return flightRepository.getFlights(boardingPoint, destination, dates);
		
	}

	public int calculatePayment(Flight flight,String seats) {
		
		int price = flight.getPrice()* Integer.parseInt(seats);
		flight.setPrice(price);
		return price;	
		
	}
	public void save_User(User user) {
		userRepository.save(user);
		logger.info("New user Registration with details:{} Date and Time:{}", user,LocalDateTime.now());
		
	}

	public String checkDetails(String email, String password) {
		User user=  userRepository.findById(email).get();
		if(user.getPassword().equals(password)) {
			logger.info("User login Successfull with email: {}  Date and Time:{}", email,LocalDateTime.now());
			return user.getFullName();
		}	
		else {
			logger.info("User login failed with email: {}Date and Time:{}", user,LocalDateTime.now());
			return "failed" ;	
		}
			
	}

	
	
	public void sendEmail(String email,String subject,String body) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(Constants.TO_MAIL);
		simpleMailMessage.setTo(email);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(body);
		javaMailSender.send(simpleMailMessage);
		logger.info("Email has been sent",LocalDateTime.now());
	}
	
	
    public  String setMessage(Flight flight, int price, Long phone, String fullName, String email) {
    
        return "Flight: " + flight.foralert() + "\n" +
                "Price: $" + price + "\n" +
        		"Phone: " + phone + "\n" +
                "Full Name: " + fullName + "\n" +
        		"Email: " + email + "\n";
    }


}
