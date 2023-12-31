package ma.emsi.Event.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
//@NoArgsConstructor
//@AllArgsConstructor


public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eventName;
    private String venue; // lieu de l'événement
    private LocalDateTime dateAndTime; // date et heure de l'événement
    private String description; // description de l'événement
    private Long organizerId; // organisateur de l'événement
    private Double ticketPrice; // prix du billet
    private int availableSeats; // nombre de sièges disponibles
    private Boolean isPublished;

    public Event() {

    }

    public Event(String eventName, String venue, LocalDateTime dateAndTime, String description, Long organizer, Double ticketPrice, int availableSeats, Boolean isPublished) {
        this.eventName = eventName;
        this.venue = venue;
        this.dateAndTime = dateAndTime;
        this.description = description;
        this.organizerId = organizer;
        this.ticketPrice = ticketPrice;
        this.availableSeats = availableSeats;
        this.isPublished = isPublished;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOrganizerId() {
        return organizerId;
    }

    public void setOrganizer(Long organizer) {
        this.organizerId = organizer;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }
}
