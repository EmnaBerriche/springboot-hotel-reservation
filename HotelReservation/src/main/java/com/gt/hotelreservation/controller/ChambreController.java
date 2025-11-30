package com.gt.hotelreservation.controller;

import com.gt.hotelreservation.model.Chambre;
import com.gt.hotelreservation.service.ChambreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chambres")
@CrossOrigin
public class ChambreController {

    @Autowired
    ChambreService chambreService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Chambre> getAllChambres() {
        return chambreService.getAllChambres();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Chambre> getChambreById(@PathVariable("id") Long id) {
        Optional<Chambre> chambre = chambreService.getChambreById(id);
        return chambre.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value="/hotel/{hotelId}", method = RequestMethod.GET)
    public List<Chambre> getChambresByHotelId(@PathVariable("hotelId") Long hotelId) {
        return chambreService.getChambresByHotelId(hotelId);
    }

    @RequestMapping(value="/type/{type}", method = RequestMethod.GET)
    public List<Chambre> getChambresByType(@PathVariable("type") String type) {
        return chambreService.getChambresByType(type);
    }

    @RequestMapping(value="/prix", method = RequestMethod.GET)
    public List<Chambre> getChambresByPrixMax(@RequestParam("prixMax") double prixMax) {
        return chambreService.getChambresByPrixMax(prixMax);
    }

    @RequestMapping(value="/disponibles", method = RequestMethod.GET)
    public List<Chambre> getChambresDisponibles(
            @RequestParam("hotelId") Long hotelId,
            @RequestParam("dateEntree") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEntree,
            @RequestParam("dateSortie") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateSortie) {
        return chambreService.getChambresDisponibles(hotelId, dateEntree, dateSortie);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Chambre createChambre(@RequestBody Chambre chambre) {
        return chambreService.saveChambre(chambre);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Chambre> updateChambre(@PathVariable("id") Long id, @RequestBody Chambre chambre) {
        Optional<Chambre> existingChambre = chambreService.getChambreById(id);
        if (existingChambre.isPresent()) {
            chambre.setId(id);
            Chambre updatedChambre = chambreService.saveChambre(chambre);
            return new ResponseEntity<>(updatedChambre, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteChambre(@PathVariable("id") Long id) {
        Optional<Chambre> existingChambre = chambreService.getChambreById(id);
        if (existingChambre.isPresent()) {
            chambreService.deleteChambre(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
