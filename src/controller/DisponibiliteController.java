package controller;

import dao.DAOFactory;
import dao.DisponibiliteDAO;
import dao.LieuDAO;
import model.Disponibilite;
import model.Lieu;
import model.Specialiste;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class DisponibiliteController {
    private final LieuDAO lieuDAO;
    private final DisponibiliteDAO disponibiliteDAO;

    public DisponibiliteController() {
        this.lieuDAO = DAOFactory.getLieuDAO();
        this.disponibiliteDAO = DAOFactory.getDisponibiliteDAO();
    }

    public List<Lieu> getTousLieux() {
        return lieuDAO.getAll();
    }

    public boolean ajouterDisponibilite(Specialiste specialiste, LocalDate date, LocalTime debut, LocalTime fin, Lieu lieu) {
        // Vérification simple : pas de créneau identique déjà pris
        List<Disponibilite> existantes = disponibiliteDAO.getAllForSpecialiste(specialiste.getId());
        for (Disponibilite d : existantes) {
            if (d.getDate().equals(date) && d.getHeureDebut().equals(debut) && d.getHeureFin().equals(fin) && d.getLieu().getId() == lieu.getId()) {
                return false; // créneau déjà existant
            }
        }

        Disponibilite nouvelle = new Disponibilite(0, specialiste, date, debut, fin, lieu, true);
        return disponibiliteDAO.save(nouvelle);
    }
}
