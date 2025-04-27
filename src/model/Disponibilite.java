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


    /**
     * Retourne l'identifiant de la disponibilité.
     * Cet identifiant est unique pour chaque disponibilité dans la base de données.
     * @return l'identifiant de la disponibilité
     */
    public int getId() { return id; }

    /**
     * Retourne le spécialiste concerné par cette disponibilité.
     * Cela permet de savoir à quel spécialiste appartient cette disponibilité.
     * @return le spécialiste concerné
     */
    public Specialiste getSpecialiste() { return specialiste; }

    /**
     * Retourne la date de la disponibilité.
     * Cette date représente le jour où la disponibilité est définie.
     * @return la date de la disponibilité
     */
    public LocalDate getDate() { return date; }

    /**
     * Retourne l'heure de début de la disponibilité.
     * Cela représente le moment où la disponibilité commence.
     * @return l'heure de début
     */
    public LocalTime getHeureDebut() { return heureDebut; }

    /**
     * Retourne l'heure de fin de la disponibilité.
     * Cela représente le moment où la disponibilité se termine.
     * @return l'heure de fin
     */
    public LocalTime getHeureFin() { return heureFin; }

    /**
     * Retourne le lieu de la disponibilité.
     * Cela permet de connaître l'endroit où la disponibilité a lieu.
     * @return le lieu de la disponibilité
     */
    public Lieu getLieu() { return lieu; }

    /**
     * Retourne si la disponibilité est libre ou non.
     * @return true si la disponibilité est libre, false si elle est occupée
     */
    public boolean isEstDisponible() { return estDisponible; }


    /**
     * Définit l'identifiant de la disponibilité
     * @param id l'identifiant de la disponibilité
     */
    public void setId(int id) { this.id = id; }

    /**
     * Définit le spécialiste concerné
     * @param specialiste le spécialiste qui est concerné par cette disponibilité
     */
    public void setSpecialiste(Specialiste specialiste) { this.specialiste = specialiste; }

    /**
     * Définit la date de disponibilité
     * @param date la date de la disponibilité
     */
    public void setDate(LocalDate date) { this.date = date; }

    /**
     * Définit l'heure de début
     * @param heureDebut l'heure de début de la disponibilité
     */
    public void setHeureDebut(LocalTime heureDebut) { this.heureDebut = heureDebut; }

    /**
     * Définit l'heure de fin
     * @param heureFin l'heure de fin de la disponibilité
     */
    public void setHeureFin(LocalTime heureFin) { this.heureFin = heureFin; }

    /**
     * Définit le lieu de la disponibilité
     * @param lieu le lieu où la disponibilité a lieu
     */
    public void setLieu(Lieu lieu) { this.lieu = lieu; }

    /**
     * Définit si la disponibilité est libre ou occupée
     * @param estDisponible true si la disponibilité est libre, false si elle est occupée
     */
    public void setEstDisponible(boolean estDisponible) { this.estDisponible = estDisponible; }
}
