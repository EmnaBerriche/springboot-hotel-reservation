package com.gt.hotelreservation.repository;

import com.gt.hotelreservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
    List<Reservation> findByChambreId(Long chambreId);
    
    List<Reservation> findByDateEntreeGreaterThanEqualAndDateSortieLessThanEqual(
            LocalDate dateDebut, LocalDate dateFin);
    
    @Query("SELECT r FROM Reservation r WHERE r.chambre.hotel.id = :hotelId AND r.annulee = false")
    List<Reservation> findByHotelId(@Param("hotelId") Long hotelId);
    
    @Query("SELECT r FROM Reservation r WHERE r.chambre.id = :chambreId " +
           "AND ((r.dateEntree <= :dateSortie AND r.dateSortie >= :dateEntree) AND r.annulee = false)")
    List<Reservation> findReservationsForPeriod(
            @Param("chambreId") Long chambreId,
            @Param("dateEntree") LocalDate dateEntree,
            @Param("dateSortie") LocalDate dateSortie);
}
