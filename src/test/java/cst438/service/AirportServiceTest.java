package cst438.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import cst438.domain.Airport;
import cst438.domain.AirportRepository;

@SpringBootTest
public class AirportServiceTest {

   @MockBean
   private AirportRepository airportRepository;

   @Autowired
   private AirportService airportService;

   @Test
   public void testAirportInfoFound() throws Exception {
      
      Airport airport = new Airport("SJC", "San Jose International Airport", "San Jose, California, United States");
      //create all necessary mocks
      given(airportRepository.findById("SJC")).willReturn(Optional.of(airport));

      //call real service
      Optional<Airport> airportInfoOptional = airportService.getAirportInfo("SJC");
      
      // Test results.
      //check if object found
      assertThat(airportInfoOptional).isPresent();
      
      //verify that actual airport object is correct
      Airport realAirport = airportInfoOptional.get();
      
      assertThat(realAirport.getId()).isEqualTo("SJC");
      assertThat(realAirport.getTitle()).isEqualTo("San Jose International Airport");
      assertThat(realAirport.getLocation()).isEqualTo("San Jose, California, United States");
   }
   
   @Test
   public void testAllAirportsFoundByPrefix() throws Exception {
      
      List<Airport> airports = Arrays.asList( new Airport("SJC", "San Jose International Airport", "San Jose, California, United States"),
            new Airport("SAN", "San Diego International Airport", "San Diego, California, United States"),
            new Airport("SAT", "San Antonio International Airport", "San Antonio, Texas, United States"),
            new Airport("SFO", "San Francisco International Airport", "San Francisco, California, United States"));
      //create mocks
      given(airportRepository.findByTitleStartingWith("San")).willReturn(airports);
      
      //real
      List<Airport> realAirports = airportService.getAllAirports("San");      
      
      assertThat(realAirports).containsAll(airports);
   }

   @Test
   public void testAirportNotFound() {
      
      given(airportRepository.findById("AAAA")).willReturn(Optional.empty());
      
      Optional<Airport> airportInfoOptional = airportService.getAirportInfo("AAAA");
      
      assertThat(airportInfoOptional).isNotPresent(); 
   }

   @Test
   public void testAirportsNotFoundByPrefix() {
      
      given(airportRepository.findByTitleStartingWith("SSS")).willReturn(Collections.emptyList());
      
      List<Airport> airports = airportService.getAllAirports("SSS");
      
      assertThat(airports.isEmpty());
   }
}
