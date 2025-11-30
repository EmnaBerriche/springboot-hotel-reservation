package com.gt.hotelreservation.controller;

import com.gt.hotelreservation.model.Reservation;
import com.gt.hotelreservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Reservation> getReservationById(@PathVariable("id") Long id) {
        Optional<Reservation> reservation = reservationService.getReservationById(id);
        return reservation.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value="/chambre/{chambreId}", method = RequestMethod.GET)
    public List<Reservation> getReservationsByChambreId(@PathVariable("chambreId") Long chambreId) {
        return reservationService.getReservationsByChambreId(chambreId);
    }

    @RequestMapping(value="/hotel/{hotelId}", method = RequestMethod.GET)
    public List<Reservation> getReservationsByHotelId(@PathVariable("hotelId") Long hotelId) {
        return reservationService.getReservationsByHotelId(hotelId);
    }

    @RequestMapping(value="/periode", method = RequestMethod.GET)
    public List<Reservation> getReservationsForPeriod(
            @RequestParam("chambreId") Long chambreId,
            @RequestParam("dateEntree") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEntree,
            @RequestParam("dateSortie") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateSortie) {
        return reservationService.getReservationsForPeriod(chambreId, dateEntree, dateSortie);
    }

    @RequestMapping(value="/disponibilite", method = RequestMethod.GET)
    public ResponseEntity<Boolean> checkChambreDisponible(
            @RequestParam("chambreId") Long chambreId,
            @RequestParam("dateEntree") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEntree,
            @RequestParam("dateSortie") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateSortie) {
        boolean disponible = reservationService.isChambreDisponible(chambreId, dateEntree, dateSortie);
        return new ResponseEntity<>(disponible, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        // Vérifier la disponibilité avant de créer la réservation
        boolean disponible = reservationService.isChambreDisponible(
                reservation.getChambre().getId(), 
                reservation.getDateEntree(), 
                reservation.getDateSortie());
        
        if (!disponible) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        
        Reservation savedReservation = reservationService.saveReservation(reservation);
        return new ResponseEntity<>(savedReservation, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Reservation> updateReservation(@PathVariable("id") Long id, @RequestBody Reservation reservation) {
        Optional<Reservation> existingReservation = reservationService.getReservationById(id);
        if (existingReservation.isPresent()) {
            reservation.setId(id);
            Reservation updatedReservation = reservationService.saveReservation(reservation);
            return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{id}/annuler", method = RequestMethod.PUT)
    public ResponseEntity<Reservation> annulerReservation(@PathVariable("id") Long id) {
        Reservation reservation = reservationService.annulerReservation(id);
        if (reservation != null) {
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") Long id) {
        Optional<Reservation> existingReservation = reservationService.getReservationById(id);
        if (existingReservation.isPresent()) {
            reservationService.deleteReservation(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
