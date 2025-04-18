package controller;

import dao.DAOFactory;
import dao.DisponibiliteDAO;
import dao.RendezVousDAO;
import exception.ReservationInvalideException;
import exception.ResultatVideException;
import model.*;
import view.ResultatsRechercheView;

import javax.swing.*;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class PriseRdvController {
    //attributs
    private DisponibiliteDAO disponibiliteDAO;
    private RendezVousDAO rendezVousDAO;

    //constructeur
    public PriseRdvController() {
        this.disponibiliteDAO = DAOFactory.getDisponibiliteDAO();
        this.rendezVousDAO = DAOFactory.getRendezVousDAO();
    }

    //recupératon specialite
    public List<String> getToutesLesSpecialites() {
        return DAOFactory.getSpecialiteDAO().getAll().stream()
                .map(Specialite::getNom)
                .collect(Collectors.toList());
    }
    //recupératon dispo par specialite
    public List<Disponibilite> getDisponibilitesParSpecialite(String specialiteNom) {
        return disponibiliteDAO.getAll().stream()
                .filter(d -> d.isEstDisponible() &&
                        d.getSpecialiste().getSpecialite().getNom().equalsIgnoreCase(specialiteNom))
                .collect(Collectors.toList());
    }

    //reservation d'un creneau
    public boolean reserverDispo(Utilisateur patient, Disponibilite dispo) {
        try {
            if (!(patient instanceof Patient)) {
                throw new ReservationInvalideException("Seuls les patients peuvent réserver un rendez-vous.");
            }

            RendezVous rdv = new RendezVous(0, (Patient) patient, dispo.getSpecialiste(), dispo, "confirmé", "");
            boolean success = rendezVousDAO.save(rdv);
            if (success) {
                dispo.setEstDisponible(false);
                return disponibiliteDAO.update(dispo);
            }
            return false;
        } catch (ReservationInvalideException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur de réservation", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }


    //recherche de disponibilité
    public List<Disponibilite> rechercherDisponibilites(String critere) {
        String keyword = critere.toLowerCase(Locale.ROOT);
        return disponibiliteDAO.getAll().stream()
                .filter(d -> d.isEstDisponible() && (
                        d.getSpecialiste().getNom().toLowerCase().contains(keyword) ||
                                d.getSpecialiste().getPrenom().toLowerCase().contains(keyword) ||
                                d.getSpecialiste().getSpecialite().getNom().toLowerCase().contains(keyword) ||
                                d.getLieu().getNom().toLowerCase().contains(keyword) ||
                                d.getLieu().getVille().toLowerCase().contains(keyword)
                ))
                .collect(Collectors.toList());
    }

    //afficher les resultat de la recherche
    public void afficherResultats(String critere) {
        try {
            List<Disponibilite> resultats = rechercherDisponibilites(critere);
            if (resultats.isEmpty()) {
                throw new ResultatVideException("Aucun créneau trouvé pour : " + critere);
            } else {
                new ResultatsRechercheView(resultats);
            }
        } catch (ResultatVideException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Recherche", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}

