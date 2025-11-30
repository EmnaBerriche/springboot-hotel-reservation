package com.gt.hotelreservation.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

// Import explicite des classes utilisées
import com.gt.hotelreservation.model.Hotel;
import com.gt.hotelreservation.model.Reservation;

@Entity
public class Chambre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private String type;
    private double prix;
    private boolean disponible = true;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    
    @JsonIgnore
    @OneToMany(mappedBy = "chambre", cascade = CascadeType.ALL)
    private List<Reservation> reservations;
    
    public Chambre() {
    }
    
    public Chambre(Long id, String numero, String type, double prix, boolean disponible, Hotel hotel, List<Reservation> reservations) {
        this.id = id;
        this.numero = numero;
        this.type = type;
        this.prix = prix;
        this.disponible = disponible;
        this.hotel = hotel;
        this.reservations = reservations;
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
