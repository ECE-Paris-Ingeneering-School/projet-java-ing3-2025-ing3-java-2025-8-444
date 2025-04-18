package model;

public class RendezVous {
    private int id;
    private Patient patient;
    private Specialiste specialiste;
    private Disponibilite disponibilite;
    private String statut;
    private String notes;

    public RendezVous(int id, model.Patient patient, Specialiste specialiste,
                      model.Disponibilite disponibilite, String statut, String notes) {
        this.id = id;
        this.patient = patient;
        this.specialiste = specialiste;
        this.disponibilite = disponibilite;
        this.statut = statut;
        this.notes = notes;
    }

    public model.Lieu getLieu() {
        return disponibilite.getLieu();
    }
    public int getId() { return id; }
    public model.Patient getPatient() { return patient; }
    public Specialiste getSpecialiste() { return specialiste; }
    public model.Disponibilite getDisponibilite() { return disponibilite; }
    public String getStatut() { return statut; }
    public String getNotes() { return notes; }

    public void setId(int id) { this.id = id; }
    public void setPatient(model.Patient patient) { this.patient = patient; }
    public void setSpecialiste(Specialiste specialiste) { this.specialiste = specialiste; }
    public void setDisponibilite(model.Disponibilite disponibilite) { this.disponibilite = disponibilite; }
    public void setStatut(String statut) { this.statut = statut; }
    public void setNotes(String notes) { this.notes = notes; }
}
