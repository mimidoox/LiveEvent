package ma.emsi.Reservation.entities;

import jakarta.persistence.*;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eventId;
    private Long userId;
    private int numberOfSeats;


    public Reservation(Long eventId, Long userId, int numberOfSeats) {
        this.eventId = eventId;
        this.userId = userId;
        this.numberOfSeats = numberOfSeats;
    }

    public Reservation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEvent() {
        return eventId;
    }

    public void setEvent(Long eventId) {
        this.eventId = eventId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", event=" + eventId +
                ", userId=" + userId +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }
}
