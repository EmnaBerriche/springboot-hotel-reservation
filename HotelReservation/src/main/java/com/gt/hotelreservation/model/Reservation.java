package com.gt.hotelreservation.model;

import jakarta.persistence.*;
import java.time.LocalDate;

// Import explicite de la classe utilisée
import com.gt.hotelreservation.model.Chambre;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateEntree;
    private LocalDate dateSortie;
    private boolean annulee = false;
    private String nomClient;
    private String emailClient;

    @ManyToOne
    @JoinColumn(name = "chambre_id")
    private Chambre chambre;
    
    public Reservation() {
    }
    
    public Reservation(Long id, LocalDate dateEntree, LocalDate dateSortie, boolean annulee, 
                      String nomClient, String emailClient, Chambre chambre) {
        this.id = id;
        this.dateEntree = dateEntree;
        this.dateSortie = dateSortie;
        this.annulee = annulee;
        this.nomClient = nomClient;
        this.emailClient = emailClient;
        this.chambre = chambre;
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(LocalDate dateEntree) {
        this.dateEntree = dateEntree;
    }

    public LocalDate getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(LocalDate dateSortie) {
        this.dateSortie = dateSortie;
    }

    public boolean isAnnulee() {
        return annulee;
    }

    public void setAnnulee(boolean annulee) {
        this.annulee = annulee;
    }
    
    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }
}
