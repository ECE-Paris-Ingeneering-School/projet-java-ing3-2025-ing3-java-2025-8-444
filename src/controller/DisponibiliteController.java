package controller;

import dao.DAOFactory;
import dao.DisponibiliteDAO;
import dao.LieuDAO;
import exceptions.DaoOperationException;
import exceptions.DisponibiliteExistanteException;
import exceptions.DisponibiliteSaveException;
import model.Disponibilite;
import model.Lieu;
import model.Specialiste;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static util.exceptionsConstantes.DISPONIBILITE_EXISTANTE;
import static util.exceptionsConstantes.ERREUR_SAUVEGARDE_DISPONIBILITE;

public class DisponibiliteController {
    private final LieuDAO lieuDAO;
    private final DisponibiliteDAO disponibiliteDAO;

    public DisponibiliteController() {
        this.lieuDAO = DAOFactory.getLieuDAO();
        this.disponibiliteDAO = DAOFactory.getDisponibiliteDAO();
    }

    public List<Lieu> getTousLieux() {
        try {
            return lieuDAO.getAll();
        } catch (DaoOperationException e) {
            System.err.println("Erreur lors de la récupération des lieux : " + e.getMessage());
            return List.of();
        }
    }

    public boolean ajouterDisponibilite(Specialiste specialiste, LocalDate date, LocalTime debut, LocalTime fin, Lieu lieu) {
        try {
            List<Disponibilite> existantes = disponibiliteDAO.getAllForSpecialiste(specialiste.getId());
            for (Disponibilite d : existantes) {
                if (d.getDate().equals(date) && d.getHeureDebut().equals(debut) && d.getHeureFin().equals(fin) && d.getLieu().getId() == lieu.getId()) {
                    throw new DisponibiliteExistanteException(DISPONIBILITE_EXISTANTE);
                }
            }

            Disponibilite nouvelle = new Disponibilite(0, specialiste, date, debut, fin, lieu, true);
            boolean saved = disponibiliteDAO.save(nouvelle);
            if (!saved) {
                throw new DisponibiliteSaveException(ERREUR_SAUVEGARDE_DISPONIBILITE);
            }

            return true;
        } catch (DisponibiliteExistanteException | DisponibiliteSaveException | DaoOperationException e) {
            System.err.println("Erreur lors de l'ajout de la disponibilité : " + e.getMessage());
            return false;
        }
    }
}
