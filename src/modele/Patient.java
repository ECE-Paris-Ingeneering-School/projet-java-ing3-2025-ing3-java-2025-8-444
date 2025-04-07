package modele;

public class Patient {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String typePatient;

    public Patient(int id, String nom, String prenom, String email, String motDePasse, String typePatient) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.typePatient = typePatient;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getMotDePasse() { return motDePasse; }
    public String getTypePatient() { return typePatient; }

    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setEmail(String email) { this.email = email; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
    public void setTypePatient(String typePatient) { this.typePatient = typePatient; }
}
