package Entities;

import java.util.Date;

public class Agent extends Visiteur {
    // Constructeur non paramétré
    public Agent() {}

    // Constructeur paramétré
    public Agent(String cin, String nom, String prenom, Date dateNaissance, String email, String telephone, String login, String motDePasse) {
        super(cin, nom, prenom, dateNaissance, email, telephone, login, motDePasse);
    }
}