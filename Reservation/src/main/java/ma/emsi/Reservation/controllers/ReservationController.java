package ma.emsi.Reservation.controllers;

import ma.emsi.Reservation.ReservationApplication;
import ma.emsi.Reservation.entities.Event;
import ma.emsi.Reservation.entities.Reservation;
import ma.emsi.Reservation.models.ReservationResponse;
import ma.emsi.Reservation.models.ReservationWithEventResponse;
import ma.emsi.Reservation.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    ReservationApplication.EventService eventService;

    @GetMapping
    public ResponseEntity<List<ReservationResponse>> getAllReservations() {
        List<ReservationResponse> reservations = reservationService.getAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationResponse> getReservationById(@PathVariable Long reservationId) {
        try {
            ReservationResponse reservation = reservationService.getReservationById(reservationId);
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody ReservationResponse reservationResponse) {
        try {
            ReservationResponse createdReservation = reservationService.createReservation(reservationResponse);
            return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/event/{eventId}")
    public ResponseEntity<ReservationWithEventResponse> getReservationWithEventById(@PathVariable Long eventId) {
        try {
            ReservationResponse reservation = reservationService.getReservationByEventId(eventId);
            Event event = eventService.getEventById(eventId);

            // Create a combined response object or modify as per your requirements
            ReservationWithEventResponse response = new ReservationWithEventResponse(reservation, event);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long reservationId) {
        reservationService.deleteReservation(reservationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
