package com.gt.hotelreservation.service;

import com.gt.hotelreservation.model.Reservation;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<Reservation> getAllReservations();
    Optional<Reservation> getReservationById(Long id);
    Reservation saveReservation(Reservation reservation);
    void deleteReservation(Long id);
    Reservation annulerReservation(Long id);
    List<Reservation> getReservationsByChambreId(Long chambreId);
    List<Reservation> getReservationsByHotelId(Long hotelId);
    List<Reservation> getReservationsForPeriod(Long chambreId, LocalDate dateEntree, LocalDate dateSortie);
    boolean isChambreDisponible(Long chambreId, LocalDate dateEntree, LocalDate dateSortie);
}
