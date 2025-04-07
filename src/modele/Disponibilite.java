package modele;

import java.time.LocalDateTime;

public class Disponibilite {
    private int id;
    private int specialisteId;
    private LocalDateTime horaire;
    private boolean disponible;

    public Disponibilite(int id, int specialisteId, LocalDateTime horaire, boolean disponible) {
        this.id = id;
        this.specialisteId = specialisteId;
        this.horaire = horaire;
        this.disponible = disponible;
    }

    public int getId() { return id; }
    public int getSpecialisteId() { return specialisteId; }
    public LocalDateTime getHoraire() { return horaire; }
    public boolean isDisponible() { return disponible; }

    public void setId(int id) { this.id = id; }
    public void setSpecialisteId(int specialisteId) { this.specialisteId = specialisteId; }
    public void setHoraire(LocalDateTime horaire) { this.horaire = horaire; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}