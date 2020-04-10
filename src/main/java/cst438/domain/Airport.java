package cst438.domain;

import javax.persistence.*;

@Entity
@Table(name="airport")
public class Airport {
   
   @Id
   private String id;
   private String title;
   private String location;
   
   public Airport() {
      this("nul", "title", "location");
   }
   
   public Airport(String id, String title, String location) {
      super();
      this.id = id;
      this.title = title;
      this.location = location;
   }
   
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getTitle() {
      return title;
   }
   public void setTitle(String title) {
      this.title = title;
   }
   public String getLocation() {
      return location;
   }
   public void setLocation(String location) {
      this.location = location;
   }

   @Override
   public String toString() {
      return "Airport [id=" + id + ", title=" + title + ", location=" + location+"]";
   }
}
