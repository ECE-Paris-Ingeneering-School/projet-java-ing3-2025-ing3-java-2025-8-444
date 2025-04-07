package modele;

public class RendezVous {
    private int id;
    private int patientId;
    private int specialisteId;
    private int disponibiliteId;
    private String note;

    public RendezVous(int id, int patientId, int specialisteId, int disponibiliteId, String note) {
        this.id = id;
        this.patientId = patientId;
        this.specialisteId = specialisteId;
        this.disponibiliteId = disponibiliteId;
        this.note = note;
    }

    public int getId() { return id; }
    public int getPatientId() { return patientId; }
    public int getSpecialisteId() { return specialisteId; }
    public int getDisponibiliteId() { return disponibiliteId; }
    public String getNote() { return note; }

    public void setId(int id) { this.id = id; }
    public void setPatientId(int patientId) { this.patientId = patientId; }
    public void setSpecialisteId(int specialisteId) { this.specialisteId = specialisteId; }
    public void setDisponibiliteId(int disponibiliteId) { this.disponibiliteId = disponibiliteId; }
    public void setNote(String note) { this.note = note; }
}

