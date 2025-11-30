package com.gt.hotelreservation.repository;

import com.gt.hotelreservation.model.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {
    
    List<Chambre> findByHotelId(Long hotelId);
    
    List<Chambre> findByType(String type);
    
    List<Chambre> findByPrixLessThanEqual(double prix);
    
    @Query("SELECT c FROM Chambre c WHERE c.hotel.id = :hotelId AND c.id NOT IN " +
           "(SELECT r.chambre.id FROM Reservation r WHERE " +
           "((r.dateEntree <= :dateSortie AND r.dateSortie >= :dateEntree) AND r.annulee = false))")
    List<Chambre> findChambresDisponibles(
            @Param("hotelId") Long hotelId,
            @Param("dateEntree") LocalDate dateEntree,
            @Param("dateSortie") LocalDate dateSortie);
}
