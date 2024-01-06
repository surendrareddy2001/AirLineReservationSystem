package com.infomerica.ars.Service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.infomerica.ars.DAO.Flight;
import com.infomerica.ars.Repository.FlightRepository;
import com.infomerica.ars.constants.Constants;


@Service
public class AdminService {
	private static Logger logger = LoggerFactory.getLogger(AdminService.class);
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	AmazonSNSClient amazonSNSClient;
	
	public String saveFLight(Flight flight) {
		
		flightRepository.save(flight);
		logger.info("New flight was Added : {}",flight);
		return "Flight data inserted";
	}
	
	
	public String sendAlert(Flight flight) {
		logger.info("Alert sent to admin: {}",LocalDateTime.now());
		String message = "Admin added flight with details\n" + flight.foralert();
		PublishRequest publishRequest = new PublishRequest("arn:aws:sns:us-east-1:578453012097:Info_SNS",message, Constants.SNS_MAIL_ALRET);
		amazonSNSClient.publish(publishRequest);
		return "Notification sent";
	}
	

}
