package controller;

import dao.DAOFactory;
import dao.DisponibiliteDAO;
import dao.RendezVousDAO;
import model.*;

import java.util.List;
import java.util.stream.Collectors;

public class PriseRdvController {
    private DisponibiliteDAO disponibiliteDAO;
    private RendezVousDAO rendezVousDAO;

    public PriseRdvController() {
        this.disponibiliteDAO = DAOFactory.getDisponibiliteDAO();
        this.rendezVousDAO = DAOFactory.getRendezVousDAO();
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
            dispo.setEstDisponible(false);
            return disponibiliteDAO.update(dispo);
        }
        return false;
    }
}