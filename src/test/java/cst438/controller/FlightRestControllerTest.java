package cst438.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import cst438.domain.FlightInfo;
import cst438.service.FlightService;

@WebMvcTest(FlightRestController.class)
public class FlightRestControllerTest {

   @MockBean
   private FlightService flightService;

   @Autowired
   private MockMvc mvc;

   // This object will be magically initialized by the initFields method below.
   private JacksonTester<FlightInfo> json;
   private JacksonTester<List<FlightInfo>> listJson;

   @BeforeEach
   public void setup() {
      JacksonTester.initFields(this, new ObjectMapper());
   }

   @Test
   public void getFlightInfo() throws Exception {

      // expected flight object
      FlightInfo flightInfo = new FlightInfo(1, "AS1922", "SFO", "LAX", "2020-05-01 08:30:00", "2020-05-01 10:30:00",
            "San Francisco International Airport", "San Francisco, California, United States",
            "Los Angeles International Airport", "Los Angeles, California, United States");

      // stub out Flight Service class
      given(flightService.getFlightByCode("AS1922")).willReturn(Optional.of(flightInfo));

      // when
      MockHttpServletResponse response = mvc.perform(get("/api/flight/v1/AS1922")
            .contentType(MediaType.APPLICATION_JSON).content(json.write(flightInfo).getJson())).andReturn()
            .getResponse();

      // then
      assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
      assertThat(response.getContentAsString()).isEqualTo(json.write(flightInfo).getJson());
   }

   @Test
   public void getFlightInfoNotFound() throws Exception {

      given(flightService.getFlightByCode("AAA")).willReturn(Optional.empty());

      ResultActions result = mvc.perform(get("/api/flight/v1/AS1922"));

      assertThat(result.andReturn().getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
   }

   @Test
   public void getAllFlightsInfo() throws Exception {

      List<FlightInfo> flights = Arrays.asList(
            new FlightInfo(1, "AS1922", "SFO", "LAX", "2020-05-01 08:30:00", "2020-05-01 10:30:00",
                  "San Francisco International Airport", "San Francisco, California, United States",
                  "Los Angeles International Airport", "Los Angeles, California, United States"),
            new FlightInfo(2, "AS1921", "SFO", "LAX", "2020-06-01 10:30:00", "2020-06-01 12:30:00",
                  "San Francisco International Airport", "San Francisco, California, United States",
                  "Los Angeles International Airport", "Los Angeles, California, United States"),
            new FlightInfo(3, "AS1920", "SFO", "LAX", "2020-10-01 18:30:00", "2020-10-01 20:30:00",
                  "San Francisco International Airport", "San Francisco, California, United States",
                  "Los Angeles International Airport", "Los Angeles, California, United States"));

      // stub out Flight Service class
      given(flightService.getAllFlights("SFO", "LAX", "2020-06-01")).willReturn(flights);
      given(flightService.getAllFlights("LAX", "SFO", "2020-10-01")).willReturn(Collections.emptyList());

      // when
      MockHttpServletResponse response = mvc
            .perform(get("/api/flights/v1/SFO/LAX?departure=2020-06-01&return=2020-10-01")
                  .contentType(MediaType.APPLICATION_JSON).content(listJson.write(flights).getJson()))
            .andReturn().getResponse();

      // then
      assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
      assertThat(response.getContentAsString()).isEqualTo(listJson.write(flights).getJson());
   }

   @Test
   public void getAllFlightsInfoNotFoundAndReturnEmptyList() throws Exception {

      // stub out the City Service class
      given(flightService.getAllFlights("AAA", "BBB", "2020-06-01")).willReturn(Collections.emptyList());
      given(flightService.getAllFlights("BBB", "AAA", "2020-10-01")).willReturn(Collections.emptyList());

      // when
      ResultActions response = mvc.perform(get("/api/flights/v1/AAA/BBB?departure=2020-06-01&return=2020-10-01"));

      // then
      assertThat(response.andReturn().getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
   }
}
