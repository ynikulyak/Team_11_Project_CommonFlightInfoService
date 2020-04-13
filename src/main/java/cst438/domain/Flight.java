package cst438.domain;

import javax.persistence.*;

@Entity
@Table(name="flight")
public class Flight {

   @Id
   private long id;
   @Column(name="short_code")
   private String code;
   private String fromAirportId;
   private String toAirportId;
   @Column(name="scheduled_departure_time")
   private String departureDateTime;
   @Column(name="scheduled_arrival_time")
   private String returnDateTime;
   
   public Flight() {
      this(0, "nul", "nul", "nul", "zero", "zero");
   }
   
   public Flight(long id, String code, String fromAirportId, String toAirportId, String departure, String arrival) {
      super();
      this.id = id;
      this.code = code;
      this.fromAirportId = fromAirportId;
      this.toAirportId = toAirportId;
      this.departureDateTime = departure;
      this.returnDateTime = arrival;
   }
   
   public long getId() {
      return id;
   }
   public void setId(long id) {
      this.id = id;
   }
   public String getCode() {
      return code;
   }
   public void setCode(String code) {
      this.code = code;
   }
   public String getFromAirportId() {
      return fromAirportId;
   }
   public void setFromAiportId(String fromAirportId) {
      this.fromAirportId = fromAirportId;
   }
   
   public String getToAirportId() {
      return toAirportId;
   }
   public void setToAirportId(String toAirportId) {
      this.toAirportId = toAirportId;
   }
   
   public String getDeparture() {
      return departureDateTime;
   }
   public void setDeparture(String departure) {
      this.departureDateTime = departure;
   }
   
   public String getArrival() {
      return returnDateTime;
   }
   public void setArrival(String arrival) {
      this.returnDateTime = arrival;
   }

   @Override
   public String toString() {
      return "Flight [code=" + code + ", airport from=" + fromAirportId + ", airport to=" + toAirportId+ ", departure = " + 
   departureDateTime + ", arrival = " + returnDateTime + "]";
   }
}


