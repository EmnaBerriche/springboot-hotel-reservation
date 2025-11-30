package com.gt.hotelreservation.service;

import com.gt.hotelreservation.model.Hotel;
import java.util.List;
import java.util.Optional;

public interface HotelService {
    List<Hotel> getAllHotels();
    Optional<Hotel> getHotelById(Long id);
    Hotel saveHotel(Hotel hotel);
    void deleteHotel(Long id);
}
