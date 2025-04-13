package model;

public class Patient extends Utilisateur {
    public Patient(int id, String nom, String prenom, String email, String motDePasse) {
        super(id, nom, prenom, email, motDePasse);
    }
}
