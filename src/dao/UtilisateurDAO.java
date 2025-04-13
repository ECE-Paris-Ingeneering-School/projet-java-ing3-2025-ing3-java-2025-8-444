package dao;

import model.Utilisateur;
import java.sql.*;
import java.util.*;

public class UtilisateurDAO implements DAO<Utilisateur> {
    protected Connection connection;

    public UtilisateurDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Utilisateur get(int id) {
        throw new UnsupportedOperationException("Méthode get() à implémenter selon le type concret de l'utilisateur");
    }

    @Override
    public List<Utilisateur> getAll() {
        return new ArrayList<>();
    }

    @Override
    public boolean save(Utilisateur obj) {
        throw new UnsupportedOperationException("Utiliser un DAO spécifique (Patient, Admin, etc.)");
    }

    @Override
    public boolean update(Utilisateur obj) {
        throw new UnsupportedOperationException("Utiliser un DAO spécifique (Patient, Admin, etc.)");
    }

    @Override
    public boolean delete(Utilisateur obj) {
        throw new UnsupportedOperationException("Utiliser un DAO spécifique (Patient, Admin, etc.)");
    }
}