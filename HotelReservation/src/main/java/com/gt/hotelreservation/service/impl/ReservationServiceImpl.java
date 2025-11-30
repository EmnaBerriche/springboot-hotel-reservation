package com.gt.hotelreservation.service.impl;

import com.gt.hotelreservation.model.Reservation;
import com.gt.hotelreservation.repository.ReservationRepository;
import com.gt.hotelreservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public Reservation annulerReservation(Long id) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(id);
        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();
            reservation.setAnnulee(true);
            return reservationRepository.save(reservation);
        }
        return null;
    }

    @Override
    public List<Reservation> getReservationsByChambreId(Long chambreId) {
        return reservationRepository.findByChambreId(chambreId);
    }

    @Override
    public List<Reservation> getReservationsByHotelId(Long hotelId) {
        return reservationRepository.findByHotelId(hotelId);
    }

    @Override
    public List<Reservation> getReservationsForPeriod(Long chambreId, LocalDate dateEntree, LocalDate dateSortie) {
        return reservationRepository.findReservationsForPeriod(chambreId, dateEntree, dateSortie);
    }

    @Override
    public boolean isChambreDisponible(Long chambreId, LocalDate dateEntree, LocalDate dateSortie) {
        List<Reservation> reservations = reservationRepository.findReservationsForPeriod(chambreId, dateEntree, dateSortie);
        return reservations.isEmpty();
    }
}
