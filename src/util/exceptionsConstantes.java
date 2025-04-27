package util;

/**
 * Classe contenant toutes les constantes de messages d'exception utilisées sur le site.
 */
public class exceptionsConstantes {

        /**
         * Message d'erreur affiché lorsqu'une spécialité demandée est introuvable.
         */
        public static final String SPECIALITE_NON_TROUVEE = "La spécialité demandée est introuvable.";

        /**
         * Message d'erreur affiché lors d'un échec de sauvegarde d'un spécialiste.
         */
        public static final String ERREUR_SAUVEGARDE_SPECIALISTE = "Erreur lors de la sauvegarde du spécialiste.";

        /**
         * Message d'erreur affiché si aucune spécialité n'est sélectionnée avant d'ajouter un spécialiste.
         */
        public static final String SPECIALITE_NON_SELECTIONNEE = "Veuillez sélectionner une spécialité avant d'ajouter un spécialiste.";

        /**
         * Message d'erreur affiché lorsqu'un créneau de disponibilité existe déjà pour ce spécialiste.
         */
        public static final String DISPONIBILITE_EXISTANTE = "Le créneau de disponibilité existe déjà pour ce spécialiste.";

        /**
         * Message d'erreur affiché lors d'un échec d'enregistrement de la disponibilité.
         */
        public static final String ERREUR_SAUVEGARDE_DISPONIBILITE = "Erreur lors de l'enregistrement de la disponibilité.";

        /**
         * Message d'erreur affiché lorsqu'aucun utilisateur n'est trouvé avec les identifiants fournis.
         */
        public static final String UTILISATEUR_NON_TROUVE = "Aucun utilisateur trouvé avec cet email et mot de passe.";

        /**
         * Message d'erreur affiché en cas de problème de connexion à la base de données.
         */
        public static final String ERREUR_CONNEXION_BDD = "Erreur lors de la connexion à la base de données.";

        /**
         * Message d'erreur affiché lors d'un problème de création d'un DAO.
         */
        public static final String ERREUR_CREATION_DAO = "Erreur lors de la création d'un DAO.";

        /**
         * Message d'erreur affiché lors d'une opération échouée sur l'entité Admin dans la base de données.
         */
        public static final String ERREUR_DAO_ADMIN = "Erreur lors d'une opération sur Admin dans la base de données.";

        /**
         * Message d'erreur affiché lors d'une opération échouée sur l'entité Disponibilité dans la base de données.
         */
        public static final String ERREUR_DAO_DISPONIBILITE = "Erreur lors d'une opération sur Disponibilité dans la base de données.";

        /**
         * Message d'erreur affiché lors d'une opération échouée sur l'entité Lieu dans la base de données.
         */
        public static final String ERREUR_DAO_LIEU = "Erreur lors d'une opération sur Lieu dans la base de données.";

        /**
         * Message d'erreur affiché lors d'une opération échouée sur l'entité Patient dans la base de données.
         */
        public static final String ERREUR_DAO_PATIENT = "Erreur lors d'une opération sur Patient dans la base de données.";

        /**
         * Message d'erreur affiché lors d'une opération échouée sur l'entité RendezVous dans la base de données.
         */
        public static final String ERREUR_DAO_RDV = "Erreur lors d'une opération sur RendezVous dans la base de données.";

        /**
         * Message d'erreur affiché lors d'une opération échouée sur l'entité Specialiste dans la base de données.
         */
        public static final String ERREUR_DAO_SPECIALISTE = "Erreur lors d'une opération sur Specialiste dans la base de données.";

        /**
         * Message d'erreur affiché lors d'une opération échouée sur l'entité Specialite dans la base de données.
         */
        public static final String ERREUR_DAO_SPECIALITE = "Erreur lors d'une opération sur Specialite dans la base de données.";

        /**
         * Message d'erreur affiché lors d'un échec d'envoi de mail.
         */
        public static final String ERREUR_ENVOI_MAIL = "Erreur lors de l'envoi du mail.";

        /**
         * Message d'erreur affiché si les identifiants fournis sont incorrects.
         */
        public static final String IDENTIFIANTS_INCORRECTS = "Identifiants incorrects.";

        /**
         * Message d'erreur affiché lors d'une erreur de mise à jour d'un rendez-vous.
         */
        public static final String ERREUR_MISE_A_JOUR_RDV = "Erreur lors de la mise à jour du rendez-vous.";

        /**
         * Message d'erreur affiché lors d'une erreur de suppression d'un rendez-vous.
         */
        public static final String ERREUR_SUPPRESSION_RDV = "Erreur lors de la suppression du rendez-vous.";

        /**
         * Message d'erreur affiché lors d'une erreur de chargement des rendez-vous.
         */
        public static final String ERREUR_CHARGEMENT_RDV = "Erreur lors du chargement des rendez-vous.";

        /**
         * Message d'erreur affiché lors d'une erreur de mise à jour des informations d'un utilisateur.
         */
        public static final String ERREUR_MISE_A_JOUR_UTILISATEUR = "Erreur lors de la modification de l'utilisateur.";

        /**
         * Message d'erreur affiché lors d'une erreur de suppression d'un utilisateur.
         */
        public static final String ERREUR_SUPPRESSION_UTILISATEUR = "Erreur lors de la suppression du compte utilisateur.";

        /**
         * Message d'erreur affiché lors d'une erreur de chargement des utilisateurs.
         */
        public static final String ERREUR_CHARGEMENT_UTILISATEUR = "Erreur lors du chargement des utilisateurs.";

        /**
         * Message d'erreur affiché si l'email est déjà utilisé ou qu'il y a un problème d'inscription d'un patient.
         */
        public static final String ERREUR_INSCRIPTION_PATIENT = "Erreur : Email déjà utilisé ou problème d'inscription.";

        /**
         * Message d'erreur affiché lors de la récupération du compte après inscription d'un patient.
         */
        public static final String ERREUR_RECUPERATION_PATIENT = "Erreur lors de la récupération du compte après inscription.";

        /**
         * Message d'erreur affiché lors de l'envoi de l'email de bienvenue à un patient.
         */
        public static final String ERREUR_ENVOI_EMAIL = "Erreur lors de l'envoi de l'email de bienvenue.";

        /**
         * Message d'erreur affiché lorsqu'il manque des champs à remplir dans un formulaire.
         */
        public static final String CHAMPS_INCOMPLETS = "Veuillez remplir tous les champs.";

        /**
         * Message d'erreur affiché lorsque l'email fourni est invalide.
         */
        public static final String EMAIL_INVALIDE = "Veuillez entrer une adresse email valide.";

        /**
         * Message d'erreur affiché lors du chargement des spécialités depuis la base de données.
         */
        public static final String ERREUR_CHARGEMENT_SPECIALITES = "Erreur lors du chargement des spécialités.";

        /**
         * Message d'erreur affiché lors du chargement des disponibilités depuis la base de données.
         */
        public static final String ERREUR_CHARGEMENT_DISPOS = "Erreur lors du chargement des disponibilités.";

        /**
         * Message d'erreur affiché lors de la réservation d'un rendez-vous.
         */
        public static final String ERREUR_RESERVATION_RDV = "Erreur lors de la réservation du rendez-vous.";

        /**
         * Message affiché lorsqu'aucun créneau n'a été sélectionné pour la réservation.
         */
        public static final String MESSAGE_SELECTION_CRENEAU = "Veuillez sélectionner un créneau.";

        /**
         * Message affiché lorsque la réservation d'un rendez-vous est réussie.
         */
        public static final String MESSAGE_RESERVATION_REUSSIE = "Rendez-vous réservé avec succès !";
}
