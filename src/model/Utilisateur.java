package model;

public abstract class Utilisateur {
    protected int id;
    protected String nom;
    protected String prenom;
    protected String email;
    protected String motDePasse;

    public Utilisateur(int id, String nom, String prenom, String email, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getMotDePasse() { return motDePasse; }

    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setEmail(String email) { this.email = email; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    @Override
    public String toString() {
        return nom + " " + prenom + " (" + email + ")";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Specialiste)) return false;
        Specialiste s = (Specialiste) o;
        return this.getId() == s.getId(); // ou .equals() si ID est un objet
    }
}
