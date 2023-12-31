package ma.emsi.Reservation.services;

import ma.emsi.Reservation.entities.Event;
import ma.emsi.Reservation.entities.Reservation;
import ma.emsi.Reservation.entities.User;
import ma.emsi.Reservation.models.ReservationResponse;
import ma.emsi.Reservation.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Console;
import java.util.Arrays;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String URL1 = "http://localhost:8888/SERVICE-EVENT";
    private final String URL2 = "http://localhost:8888/SERVICE-USER";

    public List<ReservationResponse> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        ResponseEntity<Event[]> responseEvent = restTemplate.getForEntity(URL1 + "/api/event", Event[].class);
        ResponseEntity<User[]> responseUser = restTemplate.getForEntity(URL2 + "/api/user", User[].class);
        Event[] events = responseEvent.getBody();
        User[] users = responseUser.getBody();
        return reservations.stream()
                .map(reservation -> mapToReservationResponse(reservation, events, users))
                .toList();
    }

    public ReservationResponse getReservationById(Long reservationId) throws Exception {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new Exception("Invalid Reservation Id"));

        Event event = restTemplate.getForObject(URL1 + "/api/event/" + reservation.getEvent(), Event.class);
        User user = restTemplate.getForObject(URL2 + "/api/user/" + reservation.getUserId(), User.class);

        return mapToReservationResponse(reservation, new Event[]{event}, new User[]{user});
    }

    public ReservationResponse createReservation(ReservationResponse reservationResponse) {
        Reservation reservation = mapToReservationEntity(reservationResponse);
        Reservation savedReservation = reservationRepository.save(reservation);
        return mapToReservationResponse(savedReservation, new Event[]{}, new User[]{});
    }

    private ReservationResponse mapToReservationResponse(Reservation reservation, Event[] events, User[] users) {
        Event foundEvent = Arrays.stream(events)
                .filter(event -> event.getId().equals(reservation.getEvent()))
                .findFirst()
                .orElse(null);

        User foundUser = Arrays.stream(users)
                .filter(user -> user.getId().equals(reservation.getUserId()))
                .findFirst()
                .orElse(null);

        return ReservationResponse.builder()
                .id(reservation.getId())
                .event(foundEvent)
                .user(foundUser)
                .numberOfSeats(reservation.getNumberOfSeats())
                .build();
    }
    public ReservationResponse getReservationByEventId(Long eventId) throws Exception {

        Reservation reservation = reservationRepository.findByEventId(eventId);

        Event event = restTemplate.getForObject(URL1 + "/api/event/" + eventId, Event.class);
        User user = restTemplate.getForObject(URL2 + "/api/user/" + reservation.getUserId(), User.class);

        return mapToReservationResponse(reservation, new Event[]{event}, new User[]{user});
    }
    private Reservation mapToReservationEntity(ReservationResponse reservationResponse) {
        Reservation reservation = new Reservation();
        reservation.setEvent(reservationResponse.getEvent().getId());
        reservation.setUserId(reservationResponse.getUser().getId());
        reservation.setNumberOfSeats(reservationResponse.getNumberOfSeats());
        return reservation;
    }

    public void deleteReservation(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }
}

