package controller;

import dao.DAOFactory;
import dao.DisponibiliteDAO;
import dao.RendezVousDAO;
import exceptions.DaoOperationException;
import exceptions.EnvoiMailException;
import model.*;
import view.ResultatsRechercheView;

import javax.swing.*;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Contrôleur pour gérer la prise de rendez-vous.
 */
public class PriseRdvController {
    private DisponibiliteDAO disponibiliteDAO;
    private RendezVousDAO rendezVousDAO;
    private Mail mail;

    /**
     * Constructeur initialisant les DAO et l'objet mail.
     */
    public PriseRdvController() {
        this.disponibiliteDAO = DAOFactory.getDisponibiliteDAO();
        this.rendezVousDAO = DAOFactory.getRendezVousDAO();
        this.mail = new Mail();
    }

    /**
     * Récupère toutes les spécialités disponibles.
     *
     * @return Liste des noms de spécialités.
     */
    public List<String> getToutesLesSpecialites() {
        try {
            return DAOFactory.getSpecialiteDAO().getAll().stream()
                    .map(Specialite::getNom)
                    .collect(Collectors.toList());
        } catch (DaoOperationException e) {
            System.err.println("Erreur lors de la récupération des spécialités : " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Récupère les disponibilités filtrées par spécialité.
     *
     * @param specialiteNom Le nom de la spécialité.
     * @return Liste des disponibilités correspondantes.
     */
    public List<Disponibilite> getDisponibilitesParSpecialite(String specialiteNom) {
        try {
            return disponibiliteDAO.getAll().stream()
                    .filter(d -> d.isEstDisponible() &&
                            d.getSpecialiste().getSpecialite().getNom().equalsIgnoreCase(specialiteNom))
                    .collect(Collectors.toList());
        } catch (DaoOperationException e) {
            System.err.println("Erreur lors de la récupération des disponibilités : " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Réserve une disponibilité pour un patient et envoie un mail de confirmation.
     *
     * @param patient L'utilisateur patient.
     * @param dispo La disponibilité à réserver.
     * @return true si la réservation est réussie, false sinon.
     */
    public boolean reserverDispo(Utilisateur patient, Disponibilite dispo) {
        if (!(patient instanceof Patient)) return false;

        try {
            RendezVous rdv = new RendezVous(0, (Patient) patient, dispo.getSpecialiste(), dispo, "confirmé", "");
            boolean success = rendezVousDAO.save(rdv);
            if (success) {
                try {
                    mail.envoimail(patient, "Bonjour, \nVotre rendez-vous Doc N Roll est confirmé ! Merci pour votre confiance.\nL'équipe Doc N Roll.");
                } catch (EnvoiMailException e) {
                    System.err.println("Erreur lors de l'envoi du mail de confirmation : " + e.getMessage());
                }

                dispo.setEstDisponible(false);
                return disponibiliteDAO.update(dispo);
            }
        } catch (DaoOperationException e) {
            System.err.println("Erreur lors de la réservation du rendez-vous : " + e.getMessage());
        }

        return false;
    }

    /**
     * Recherche des disponibilités selon un critère donné.
     *
     * @param critere Mot-clé pour la recherche (nom, spécialité, lieu, etc.).
     * @return Liste des disponibilités trouvées.
     */
    public List<Disponibilite> rechercherDisponibilites(String critere) {
        try {
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
        } catch (DaoOperationException e) {
            System.err.println("Erreur lors de la recherche de disponibilités : " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Affiche les résultats de la recherche de disponibilités.
     *
     * @param critere Le critère de recherche.
     * @param user L'utilisateur effectuant la recherche.
     */
    public void afficherResultats(String critere, Utilisateur user) {
        List<Disponibilite> resultats = rechercherDisponibilites(critere);
        if (resultats.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucun créneau trouvé pour : " + critere);
        } else {
            new ResultatsRechercheView(resultats, user);
        }
    }
}
