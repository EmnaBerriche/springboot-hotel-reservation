package com.gt.hotelreservation.controller;

import com.gt.hotelreservation.model.Hotel;
import com.gt.hotelreservation.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hotels")
@CrossOrigin
public class HotelController {

    @Autowired
    HotelService hotelService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Hotel> getHotelById(@PathVariable("id") Long id) {
        Optional<Hotel> hotel = hotelService.getHotelById(id);
        return hotel.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Hotel createHotel(@RequestBody Hotel hotel) {
        return hotelService.saveHotel(hotel);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Hotel> updateHotel(@PathVariable("id") Long id, @RequestBody Hotel hotel) {
        Optional<Hotel> existingHotel = hotelService.getHotelById(id);
        if (existingHotel.isPresent()) {
            hotel.setId(id);
            Hotel updatedHotel = hotelService.saveHotel(hotel);
            return new ResponseEntity<>(updatedHotel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteHotel(@PathVariable("id") Long id) {
        Optional<Hotel> existingHotel = hotelService.getHotelById(id);
        if (existingHotel.isPresent()) {
            hotelService.deleteHotel(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
