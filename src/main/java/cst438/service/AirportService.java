package cst438.service;

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

@Service
public class AirportService {

   private static final Logger log = LoggerFactory.getLogger(AirportService.class);

   @Autowired
   private AirportRepository airportRepository;

  // @Autowired
  // private RabbitTemplate rabbitTemplate;

  // @Autowired
 //  private FanoutExchange fanout;

   public Optional<Airport> getAirportInfo(String id) {

      // find airport by code from db from table airport
      Optional<Airport> airportOptional = airportRepository.findById(id);
      // if no airport was found
      if (!airportOptional.isPresent()) {
         log.warn("Airport for {} id was not found", id);
         return Optional.empty();
      }

      // take airport object
      Airport airport = airportOptional.get();

      String airportName = airport.getTitle();
      String airportLocation = airport.getLocation();

      return Optional.of(new Airport(id, airportName, airportLocation));
   }

   public List<Airport> getAllAirports(String prefix) {
      // return list of airports from db from table airport that match prefix
      List<Airport> airports = airportRepository.findByTitleStartingWith(prefix);
      // take first city from the list
      // if list is empty
      if (airports.isEmpty()) {
         log.warn("Airport for {} prefix was not found", prefix);
      }

      return airports;
   }

   // methods that will write a message to an exchange using the RabbitTemplate
   // class provided by Spring
  /* public void requestReservation(String airportId, String airportName, String airportLocation) {
      String msg = "{\"airportId\": \"" + airportId + "\" \"airportName\": \"" + airportName + "\" \"airportLocation\": \"" + airportLocation + "\"}";
      System.out.println("Sending message:" + msg);
      rabbitTemplate.convertSendAndReceive(fanout.getName(), "", // routing key none.
            msg);
   }*/
}
