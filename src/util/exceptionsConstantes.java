package util;

/**
 * Classe contenant toutes les constantes de messages d'exception utilisées sur le site
 */

public class exceptionsConstantes {

        public static final String SPECIALITE_NON_TROUVEE = "La spécialité demandée est introuvable.";
        public static final String ERREUR_SAUVEGARDE_SPECIALISTE = "Erreur lors de la sauvegarde du spécialiste.";
        public static final String SPECIALITE_NON_SELECTIONNEE = "Veuillez sélectionner une spécialité avant d'ajouter un spécialiste.";

        public static final String DISPONIBILITE_EXISTANTE = "Le créneau de disponibilité existe déjà pour ce spécialiste.";
        public static final String ERREUR_SAUVEGARDE_DISPONIBILITE = "Erreur lors de l'enregistrement de la disponibilité.";

        public static final String UTILISATEUR_NON_TROUVE = "Aucun utilisateur trouvé avec cet email et mot de passe.";

        public static final String ERREUR_CONNEXION_BDD = "Erreur lors de la connexion à la base de données.";
        public static final String ERREUR_CREATION_DAO = "Erreur lors de la création d'un DAO.";

        public static final String ERREUR_DAO_ADMIN = "Erreur lors d'une opération sur Admin dans la base de données.";
        public static final String ERREUR_DAO_DISPONIBILITE = "Erreur lors d'une opération sur Disponibilité dans la base de données.";
        public static final String ERREUR_DAO_LIEU = "Erreur lors d'une opération sur Lieu dans la base de données.";
        public static final String ERREUR_DAO_PATIENT = "Erreur lors d'une opération sur Patient dans la base de données.";
        public static final String ERREUR_DAO_RDV = "Erreur lors d'une opération sur RendezVous dans la base de données.";
        public static final String ERREUR_DAO_SPECIALISTE = "Erreur lors d'une opération sur Specialiste dans la base de données.";
        public static final String ERREUR_DAO_SPECIALITE = "Erreur lors d'une opération sur Specialite dans la base de données.";

        public static final String ERREUR_ENVOI_MAIL = "Erreur lors de l'envoi du mail.";

        public static final String IDENTIFIANTS_INCORRECTS = "Identifiants incorrects.";
        public static final String ERREUR_MISE_A_JOUR_RDV = "Erreur lors de la mise à jour du rendez-vous.";
        public static final String ERREUR_SUPPRESSION_RDV = "Erreur lors de la suppression du rendez-vous.";
        public static final String ERREUR_CHARGEMENT_RDV = "Erreur lors du chargement des rendez-vous.";

        public static final String ERREUR_MISE_A_JOUR_UTILISATEUR = "Erreur lors de la modification de l'utilisateur.";
        public static final String ERREUR_SUPPRESSION_UTILISATEUR = "Erreur lors de la suppression du compte utilisateur.";
        public static final String ERREUR_CHARGEMENT_UTILISATEUR = "Erreur lors du chargement des utilisateurs.";

        public static final String ERREUR_INSCRIPTION_PATIENT = "Erreur : Email déjà utilisé ou problème d'inscription.";
        public static final String ERREUR_RECUPERATION_PATIENT = "Erreur lors de la récupération du compte après inscription.";
        public static final String ERREUR_ENVOI_EMAIL = "Erreur lors de l'envoi de l'email de bienvenue.";
        public static final String CHAMPS_INCOMPLETS = "Veuillez remplir tous les champs.";
        public static final String EMAIL_INVALIDE = "Veuillez entrer une adresse email valide.";

        public static final String ERREUR_CHARGEMENT_SPECIALITES = "Erreur lors du chargement des spécialités.";
        public static final String ERREUR_CHARGEMENT_DISPOS = "Erreur lors du chargement des disponibilités.";
        public static final String ERREUR_RESERVATION_RDV = "Erreur lors de la réservation du rendez-vous.";
        public static final String MESSAGE_SELECTION_CRENEAU = "Veuillez sélectionner un créneau.";
        public static final String MESSAGE_RESERVATION_REUSSIE = "Rendez-vous réservé avec succès !";

}


