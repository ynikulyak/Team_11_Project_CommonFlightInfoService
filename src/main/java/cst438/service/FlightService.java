package cst438.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cst438.domain.Airport;
import cst438.domain.AirportRepository;
import cst438.domain.Flight;
import cst438.domain.FlightInfo;
import cst438.domain.FlightRepository;

@Service
public class FlightService {

   private static final Logger log = LoggerFactory.getLogger(FlightService.class);

   @Autowired
   private FlightRepository flightRepository;
   
   @Autowired
   private AirportRepository airportRepository;

   // @Autowired
   // private RabbitTemplate rabbitTemplate;

   // @Autowired
   // private FanoutExchange fanout;
   
   public Optional<FlightInfo> getFlightByCode(String code) {
      
      // find flight by code from db from table flight
      Optional<Flight> flightOptional = flightRepository.findByCode(code);
      
      // if no flight was found
      if (!flightOptional.isPresent()) {
         log.warn("Flight for {} code was not found", code);
         return Optional.empty();
      }

      // take flight object
      Flight flight = flightOptional.get();
      
      long id = flight.getId();
      String fromAirportId = flight.getFromAirportId();
      String toAirportId = flight.getToAirportId();
      String departure = flight.getDeparture();
      String arrival = flight.getArrival();
      
      Optional<Airport> airportOptional = airportRepository.findById(fromAirportId);
      
      //if no airport was found
      if(!airportOptional.isPresent()) {
         log.warn("Airport by {} id was not found", fromAirportId);
         return Optional.empty();
      }
      
      Airport fromAirport = airportOptional.get();
      String fromAirportTitle = fromAirport.getTitle();
      String fromAirportLocation = fromAirport.getLocation();
      
      Optional<Airport> airportToOptional = airportRepository.findById(toAirportId);
      
      //if no airport was found
      if(!airportToOptional.isPresent()) {
         log.warn("Airport by {} id was nor found", toAirportId);
         return Optional.empty();
      }
      
      Airport airportTo = airportToOptional.get();
      
      String toAirportTitle = airportTo.getTitle();
      String toAirportLocation = airportTo.getLocation();

      return Optional.of(new FlightInfo(id, code, fromAirportId, toAirportId, departure,
            arrival, fromAirportTitle, fromAirportLocation, toAirportTitle,
            toAirportLocation));
   }

   public List<FlightInfo> getAllFlights(String fromAirport, String toAirport) {
      Optional<Airport> airportOptional = airportRepository.findById(fromAirport);
      
      //if no airport was found
      if(!airportOptional.isPresent()) {
         log.warn("Airport by {} id was not found", fromAirport);
         return Collections.EMPTY_LIST;
      }
      
      Airport airportFrom = airportOptional.get();
      
      String fromAirportTitle = airportFrom.getTitle();
      String fromAirportLocation = airportFrom.getLocation();
      
      Optional<Airport> airportToOptional = airportRepository.findById(toAirport);
      
      //if no airport was found
      if(!airportToOptional.isPresent()) {
         log.warn("Airport by {} id was not found", toAirport);
         return Collections.EMPTY_LIST;
      }
      
   // return list of flights from db from table flight that match 
      List<Flight> flights = flightRepository.findByFromAirportId(fromAirport, toAirport);
      // if list is empty
      if (flights.isEmpty()) {
         log.warn("Flights from {} to {} were not found", fromAirport, toAirport);
         return Collections.EMPTY_LIST;
      }
      
      
      Airport airportTo = airportToOptional.get();
      String toAirportTitle = airportTo.getTitle();
      String toAirportLocation = airportTo.getLocation();
      
      List<FlightInfo> flightsInfo = new ArrayList<>();
      
      for(Flight f : flights) {
         long id = f.getId();
         String code = f.getCode();
         String fromAirportId = f.getFromAirportId();
         String toAirportId = f.getToAirportId();
         String departure = f.getDeparture();
         String arrival = f.getArrival(); 
         
         FlightInfo info = new FlightInfo(id, code, fromAirportId, toAirportId, departure,
               arrival, fromAirportTitle, fromAirportLocation, toAirportTitle,
               toAirportLocation);
         flightsInfo.add(info);
      }

      return flightsInfo;
   }

   // methods that will write a message to an exchange using the RabbitTemplate
   // class provided by Spring
   /*
    * public void requestReservation(String airportId, String airportName, String
    * airportLocation) { String msg = "{\"airportId\": \"" + airportId +
    * "\" \"airportName\": \"" + airportName + "\" \"airportLocation\": \"" +
    * airportLocation + "\"}"; System.out.println("Sending message:" + msg);
    * rabbitTemplate.convertSendAndReceive(fanout.getName(), "", // routing key
    * none. msg); }
    */
}
