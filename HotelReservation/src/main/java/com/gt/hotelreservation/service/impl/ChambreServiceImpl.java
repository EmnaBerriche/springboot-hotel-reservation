package com.gt.hotelreservation.service.impl;

import com.gt.hotelreservation.model.Chambre;
import com.gt.hotelreservation.repository.ChambreRepository;
import com.gt.hotelreservation.service.ChambreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChambreServiceImpl implements ChambreService {

    private final ChambreRepository chambreRepository;

    @Autowired
    public ChambreServiceImpl(ChambreRepository chambreRepository) {
        this.chambreRepository = chambreRepository;
    }

    @Override
    public List<Chambre> getAllChambres() {
        return chambreRepository.findAll();
    }

    @Override
    public Optional<Chambre> getChambreById(Long id) {
        return chambreRepository.findById(id);
    }

    @Override
    public Chambre saveChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public void deleteChambre(Long id) {
        chambreRepository.deleteById(id);
    }

    @Override
    public List<Chambre> getChambresByHotelId(Long hotelId) {
        return chambreRepository.findByHotelId(hotelId);
    }

    @Override
    public List<Chambre> getChambresByType(String type) {
        return chambreRepository.findByType(type);
    }

    @Override
    public List<Chambre> getChambresByPrixMax(double prix) {
        return chambreRepository.findByPrixLessThanEqual(prix);
    }

    @Override
    public List<Chambre> getChambresDisponibles(Long hotelId, LocalDate dateEntree, LocalDate dateSortie) {
        return chambreRepository.findChambresDisponibles(hotelId, dateEntree, dateSortie);
    }
}
