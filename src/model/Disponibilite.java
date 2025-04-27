package model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Classe qui designe une disponibilité horaire pour un  spécialiste
 * Une disponibilité peut être réservée par un patient pour un rendez-vous
 * et peut etre crée par un admin ou un spécialiste
 *
 */

public class Disponibilite {
    private int id;
    private Specialiste specialiste;
    private LocalDate date;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private Lieu lieu;
    private boolean estDisponible;

    /**
     * Constructeur pour créer une disponibilité.
     *
     * @param id Identifiant unique
     * @param specialiste Spécialiste concerné
     * @param date Date de la disponibilité
     * @param heureDebut Heure de début
     * @param heureFin Heure de fin
     * @param lieu Lieu du rendez-vous
     * @param estDisponible Disponibilité (true = libre, false = occupé)
     */
    public Disponibilite(int id, Specialiste specialiste, LocalDate date,
                         LocalTime heureDebut, LocalTime heureFin,
                         Lieu lieu, boolean estDisponible) {
        this.id = id;
        this.specialiste = specialiste;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.lieu = lieu;
        this.estDisponible = estDisponible;
    }


    /** @return l'identifiant de la disponibilité */
    public int getId() { return id; }

    /** @return le spécialiste concerné */
    public Specialiste getSpecialiste() { return specialiste; }

    /** @return la date de la disponibilité */
    public LocalDate getDate() { return date; }

    /** @return l'heure de début */
    public LocalTime getHeureDebut() { return heureDebut; }

    /** @return l'heure de fin */
    public LocalTime getHeureFin() { return heureFin; }

    /** @return le lieu de la disponibilité */
    public Lieu getLieu() { return lieu; }

    /** @return true si la disponibilité est libre */
    public boolean isEstDisponible() { return estDisponible; }


    /** @param id définit l'identifiant de la disponibilité */
    public void setId(int id) { this.id = id; }

    /** @param specialiste définit le spécialiste concerné */
    public void setSpecialiste(Specialiste specialiste) { this.specialiste = specialiste; }

    /** @param date définit la date de disponibilité */
    public void setDate(LocalDate date) { this.date = date; }

    /** @param heureDebut définit l'heure de début */
    public void setHeureDebut(LocalTime heureDebut) { this.heureDebut = heureDebut; }

    /** @param heureFin définit l'heure de fin */
    public void setHeureFin(LocalTime heureFin) { this.heureFin = heureFin; }

    /** @param lieu définit le lieu de la disponibilité */
    public void setLieu(Lieu lieu) { this.lieu = lieu; }

    /** @param estDisponible définit si la disponibilité est libre ou occupée */
    public void setEstDisponible(boolean estDisponible) { this.estDisponible = estDisponible; }
}
