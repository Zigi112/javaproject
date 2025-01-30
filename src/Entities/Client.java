package Entities;

import java.util.Date;

public class Client extends Visiteur {
    // Constructeur non paramétré
    public Client() {}

    // Constructeur paramétré
    public Client(String cin, String nom, String prenom, Date dateNaissance, String email, String telephone, String login, String motDePasse) {
        super(cin, nom, prenom, dateNaissance, email, telephone, login, motDePasse);
    }
}