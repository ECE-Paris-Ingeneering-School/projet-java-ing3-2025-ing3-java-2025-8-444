package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Disponibilite {
    private int id;
    private Specialiste specialiste;
    private LocalDate date;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private Lieu lieu;
    private boolean estDisponible;

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

    public int getId() { return id; }
    public Specialiste getSpecialiste() { return specialiste; }
    public LocalDate getDate() { return date; }
    public LocalTime getHeureDebut() { return heureDebut; }
    public LocalTime getHeureFin() { return heureFin; }
    public Lieu getLieu() { return lieu; }
    public boolean isEstDisponible() { return estDisponible; }

    public void setId(int id) { this.id = id; }
    public void setSpecialiste(Specialiste specialiste) { this.specialiste = specialiste; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setHeureDebut(LocalTime heureDebut) { this.heureDebut = heureDebut; }
    public void setHeureFin(LocalTime heureFin) { this.heureFin = heureFin; }
    public void setLieu(Lieu lieu) { this.lieu = lieu; }
    public void setEstDisponible(boolean estDisponible) { this.estDisponible = estDisponible; }
}
