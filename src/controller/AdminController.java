package controller;

import dao.DAOFactory;
import dao.SpecialisteDAO;
import dao.SpecialiteDAO;
import model.Specialiste;
import model.Specialite;

import java.util.List;

public class AdminController {
    private final SpecialisteDAO specialisteDAO;
    private final SpecialiteDAO specialiteDAO;

    public AdminController() {
        this.specialisteDAO = DAOFactory.getSpecialisteDAO();
        this.specialiteDAO = DAOFactory.getSpecialiteDAO();
    }

    public List<Specialite> getToutesSpecialites() {
        return specialiteDAO.getAll();
    }

    public boolean ajouterSpecialiste(String nom, String prenom, String email, String mdp, String qualification,
                                      String specialiteNom) {
        Specialite specialite = specialiteDAO.getByName(specialiteNom);
        if (specialite == null) return false;

        Specialiste nouveau = new Specialiste(0, nom, prenom, email, mdp, qualification, specialite);
        return specialisteDAO.save(nouveau);
    }
}
