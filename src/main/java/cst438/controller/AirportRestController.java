package cst438.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import cst438.domain.Airport;
import cst438.service.AirportService;

/*
 * Rest controller to return airport object in form of json if found or
 * throws exception if not found
 */

@RestController
public class AirportRestController {

   @Autowired
   private AirportService airportService;

   @GetMapping("/api/airport/v1/{airport}")
   public Airport getAirport(@PathVariable("airport") String code) {

      Optional<Airport> airportInfo = airportService.getAirportInfo(code);
      // return airportInfo object in form of json if object present
      if (airportInfo.isPresent()) {
         return airportInfo.get();
      }
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
   }
   
   @GetMapping("/api/airports/v1/{prefix}")
   public List<Airport> getAllAirports(@PathVariable("prefix") String prefix) {

      List<Airport> airports = airportService.getAllAirports(prefix);
      
      return airports;
   }
}
