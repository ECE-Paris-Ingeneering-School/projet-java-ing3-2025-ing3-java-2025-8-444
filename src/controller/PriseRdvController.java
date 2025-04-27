package controller;

import dao.DAOFactory;
import dao.DisponibiliteDAO;
import dao.RendezVousDAO;
import model.*;
import view.ResultatsRechercheView;
import controller.mail;

import javax.swing.*;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class PriseRdvController {
    private DisponibiliteDAO disponibiliteDAO;
    private RendezVousDAO rendezVousDAO;
    private mail mail;

    public PriseRdvController() {
        this.disponibiliteDAO = DAOFactory.getDisponibiliteDAO();
        this.rendezVousDAO = DAOFactory.getRendezVousDAO();
        this.mail = new mail();
    }

    public List<String> getToutesLesSpecialites() {
        return DAOFactory.getSpecialiteDAO().getAll().stream()
                .map(Specialite::getNom)
                .collect(Collectors.toList());
    }

    public List<Disponibilite> getDisponibilitesParSpecialite(String specialiteNom) {
        return disponibiliteDAO.getAll().stream()
                .filter(d -> d.isEstDisponible() &&
                        d.getSpecialiste().getSpecialite().getNom().equalsIgnoreCase(specialiteNom))
                .collect(Collectors.toList());
    }

    public boolean reserverDispo(Utilisateur patient, Disponibilite dispo) {
        if (!(patient instanceof Patient)) return false;

        RendezVous rdv = new RendezVous(0, (Patient) patient, dispo.getSpecialiste(), dispo, "confirmé", "");
        boolean success = rendezVousDAO.save(rdv);
        if (success) {
            mail.envoimail(patient, "Bonjour, \nVotre rendez vous Doc N Roll est confirmé ! Merci pour votre confiance.\nL'équipe Doc N Roll.");
            dispo.setEstDisponible(false);
            return disponibiliteDAO.update(dispo);
        }
        return false;
    }

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

    public void afficherResultats(String critere) {
        List<Disponibilite> resultats = rechercherDisponibilites(critere);
        if (resultats.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucun créneau trouvé pour : " + critere);
        } else {
            new ResultatsRechercheView(resultats);
        }
    }
}

