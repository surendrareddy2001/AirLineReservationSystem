package com.infomerica.ars.DAO;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FLIGHTS")
public class Flight {
	


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "ID")
	private int id;
	@Column(name = "FLIGHTNUMBER")
	private String flightNumber;
	@Column(name = "BOARDINGPOINT")
	private String boardingPoint;
	@Column(name = "DESTINATION")
	private String destination;
	@Column(name = "STARTDATE")
	private LocalDate startDate;
	@Column(name = "STARTTIME")
	private String startTime;
	@Column(name = "ENDDATE")
	private LocalDate endDate;
	@Column(name = "ENDTIME")
	private String endTime;
	@Column(name = "PRICE")
	private int price;
	
	public Flight() {

	}

	public Flight(String flightNumber, String boardingPoint, String destination, LocalDate startDate,
			String startTime, LocalDate endDate, String endTime, int price) {
		super();
		this.flightNumber = flightNumber;
		this.boardingPoint = boardingPoint;
		this.destination = destination;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getBoardingPoint() {
		return boardingPoint;
	}

	public void setBoardingPoint(String boardingPoint) {
		this.boardingPoint = boardingPoint;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Flight [id=" + id + ", flightNumber=" + flightNumber + ", boardingPoint=" + boardingPoint
				+ ", destination=" + destination + ", startDate=" + startDate + ", startTime=" + startTime
				+ ", endDate=" + endDate + ", endTime=" + endTime + ", price=" + price + "]";
	}

	public String foralert() {
	    return String.format("ID=%s,%n  FlightNumber=%s,%n  BoardingPoint=%s,%n  Destination=%s,%n  StartDate=%s,%n  StartTime=%s,%n  EndDate=%s,%n  EndTime=%s,%n  Price=%s%n]",
	            id, flightNumber, boardingPoint, destination, startDate, startTime, endDate, endTime, price);
	}


}
