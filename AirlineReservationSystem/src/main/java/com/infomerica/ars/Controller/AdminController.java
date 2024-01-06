package com.infomerica.ars.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.infomerica.ars.DAO.Flight;
import com.infomerica.ars.Service.AdminService;

@Controller
public class AdminController {
	private static Logger logger = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	AdminService adminService;
	
	@GetMapping("/admin")
	public String adminController() {
		logger.info("Admin portal is Accessed : {}",LocalDateTime.now());
		return "addFlight";
	}
	
	@GetMapping("/saveflight")
	public String saveFlight(@RequestParam String flightNumber,@RequestParam String boardingPoint,@RequestParam String destination,@RequestParam LocalDate startDate,@RequestParam String startTime,@RequestParam LocalDate endDate,@RequestParam String endTime,@RequestParam int price) {
		Flight flight = new Flight(flightNumber,boardingPoint,destination,startDate,startTime,endDate,endTime,price);
		adminService.saveFLight(flight);
		adminService.sendAlert(flight);
		return "addFlight";
	}
	
//	@GetMapping("/publishmessage")
//	public String publishMessage() {
//		PublishRequest publishRequest = new PublishRequest("arn:aws:sns:us-east-1:578453012097:Info_SNS", message(), "Test mails from me");
//		client.publish(publishRequest);
//		return "Notification sent";
//	}
}
