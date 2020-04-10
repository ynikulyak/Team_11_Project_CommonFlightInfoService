package cst438.domain;

public class FlightInfo {

   long id;
   String flightCode;
   String fromAirportCode;
   String toAirportCode;
   String departure;
   String arrival;
   String airportTitleFrom;
   String airportLocationFrom;
   String airportTitleTo;
   String airportLocationTo;

   public FlightInfo(Flight flight, String airportTitleFrom, String airportLocationFrom, String airportTitleTo,
         String airportLocationTo) {
      this.id = flight.getId();
      this.flightCode = flight.getCode();
      this.fromAirportCode = flight.getFromAirportId();
      this.toAirportCode = flight.getToAirportId();
      this.departure = flight.getDeparture();
      this.arrival = flight.getArrival();
      this.airportLocationFrom = airportLocationFrom;
      this.airportTitleFrom = airportTitleFrom;
      this.airportLocationTo = airportLocationTo;
      this.airportTitleTo = airportTitleTo;
   }

   public FlightInfo(long id, String flightCode, String fromAirportCode, String toAirportCode, String departure,
         String arrival, String airportTitleFrom, String airportLocationFrom, String airportTitleTo,
         String airportLocationTo) {
      super();
      this.id = id;
      this.flightCode = flightCode;
      this.fromAirportCode = fromAirportCode;
      this.toAirportCode = toAirportCode;
      this.departure = departure;
      this.arrival = arrival;
      this.airportLocationFrom = airportLocationFrom;
      this.airportTitleFrom = airportTitleFrom;
      this.airportLocationTo = airportLocationTo;
      this.airportTitleTo = airportTitleTo;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      FlightInfo other = (FlightInfo) obj;
      if (flightCode == null) {
         if (other.flightCode != null)
            return false;
      } else if (!flightCode.equals(other.flightCode))
         return false;
      if (fromAirportCode == null) {
         if (other.fromAirportCode != null)
            return false;
      } else if (!fromAirportCode.equals(other.fromAirportCode))
         return false;
      if (toAirportCode == null) {
         if (other.toAirportCode != null)
            return false;
      } else if (!toAirportCode.equals(other.toAirportCode))
         return false;
      if (id != other.id)
         return false;
      if (departure == null) {
         if (other.departure != null)
            return false;
      } else if (!departure.equals(other.departure))
         return false;
      if (arrival == null) {
         if (other.arrival != null)
            return false;
      } else if (!arrival.equals(other.arrival))
         return false;
      if (airportLocationFrom == null) {
         if (other.airportLocationFrom != null)
            return false;
      } else if (!airportLocationFrom.equals(other.airportLocationFrom))
         return false;
      if (airportLocationTo == null) {
         if (other.airportLocationTo != null)
            return false;
      } else if (!airportLocationTo.equals(other.airportLocationTo))
         return false;
      if (airportTitleFrom == null) {
         if (other.airportTitleFrom != null)
            return false;
      } else if (!airportTitleFrom.equals(other.airportTitleFrom))
         return false;
      if (airportTitleTo == null) {
         if (other.airportTitleTo != null)
            return false;
      } else if (!airportTitleTo.equals(other.airportTitleTo))
         return false;
      return true;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getFlightCode() {
      return flightCode;
   }

   public void setFlightCode(String flightCode) {
      this.flightCode = flightCode;
   }

   public String getFromAirportCode() {
      return fromAirportCode;
   }

   public void setFromAirportCode(String fromAirportCode) {
      this.fromAirportCode = fromAirportCode;
   }

   public String getToAirportCode() {
      return toAirportCode;
   }

   public void setToAirportCode(String toAirportCode) {
      this.toAirportCode = toAirportCode;
   }

   public String getDeparture() {
      return departure;
   }

   public void setDeparture(String departure) {
      this.departure = departure;
   }

   public String getArrival() {
      return arrival;
   }

   public void setArrival(String arrival) {
      this.arrival = arrival;
   }

   public String getAirportTitleFrom() {
      return airportTitleFrom;
   }

   public void setAirportTitleFrom(String airportTitleFrom) {
      this.airportTitleFrom = airportTitleFrom;
   }

   public String getAirportLocationFrom() {
      return airportLocationFrom;
   }

   public void setAirportLocationFrom(String airportLocationFrom) {
      this.airportLocationFrom = airportLocationFrom;
   }

   public String getAirportLocationTo() {
      return airportLocationTo;
   }

   public void setairportLocationTo(String airportLocationTo) {
      this.airportLocationTo = airportLocationTo;
   }

   public String getAirportTitleTo() {
      return airportTitleTo;
   }

   public void setAirportTitleTo(String airportTitleTo) {
      this.airportTitleTo = airportTitleTo;
   }

   @Override
   public String toString() {
      return "FlightInfo [code=" + flightCode + ", fromAirportCode=" + fromAirportCode + ", toAirportCode="
            + toAirportCode + ", departure=" + departure + ", arrival=" + arrival + ", airportTitleFrom ="
            + airportTitleFrom + ", airportTitleTo=" + airportTitleTo + ", airportLocationFrom =" + airportLocationFrom
            + ", airportLocationTo = " + airportLocationTo + "]";
   }

}
