package com.gt.hotelreservation.service;

import com.gt.hotelreservation.model.Chambre;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ChambreService {
    List<Chambre> getAllChambres();
    Optional<Chambre> getChambreById(Long id);
    Chambre saveChambre(Chambre chambre);
    void deleteChambre(Long id);
    List<Chambre> getChambresByHotelId(Long hotelId);
    List<Chambre> getChambresByType(String type);
    List<Chambre> getChambresByPrixMax(double prix);
    List<Chambre> getChambresDisponibles(Long hotelId, LocalDate dateEntree, LocalDate dateSortie);
}
