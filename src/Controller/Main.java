package Controller;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import Entities.Reservation;
import Enumerations.ModePaiement;
import Enumerations.TypeOffre;
import Implementation.ClientServiceImpl;
import Utils.DataSource;
//ici main point entree
public class Main {

    public static void main(String[] args) throws SQLException {
        Connection con = DataSource.getInstance().getConn();
        Scanner scanner = new Scanner(System.in);
        int choixPrincipal;
        do {
            // Menu principal
            System.out.println("-------------------------!! Menu Principal !!------------------------------------");
            System.out.println("1. Ajouter une réservation");
            System.out.println("2. Afficher les réservations par client");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");
            while (!scanner.hasNextInt()) {
                System.out.println("Veuillez entrer un entier valide (0, 1 ou 2) :");
                scanner.next(); 
            }
            choixPrincipal = scanner.nextInt();
            scanner.nextLine(); 

            switch (choixPrincipal) {
                case 1:
                    ajouterReservation(scanner);
                    break;

                case 2:
                    afficherReservationsParClient(scanner);
                    break;

                case 0:
                    System.out.println("----------Merci d'avoir utilisé notre système de réservation ! À bientôt !--------");
                    break;

                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choixPrincipal != 0);

        scanner.close();
    }

	public static void ajouterReservation(Scanner scanner) {
		int continuer;

		do {
			try {
				System.out.println("----------------------! Ajout d'une Réservation !-----------------------------");
				System.out.print("Entrez la date de réservation (yyyy-MM-dd) : ");
				String dateString = scanner.nextLine();
				Date date = Date.valueOf(dateString);

				System.out.print("Entrez le nombre de participants : ");
				/*
				 * while (!scanner.hasNextInt()) { System.out.
				 * println("Veuillez entrer un entier valide pour le nombre de participants :");
				 * scanner.next(); }
				 */
				int nbrParticipants = scanner.nextInt();
				scanner.nextLine();
				while (nbrParticipants <= 0) {
					System.out.print("Veuillez entrer un entier valide pour le nombre de participants :");
					nbrParticipants = scanner.nextInt();
					scanner.nextLine();
				}
				System.out.print("Entrez le mode de paiement (EN_LIGNE / SUR_PLACE) : ");
				String modePaiementStr = scanner.nextLine();
				ModePaiement modePaiement = ModePaiement.valueOf(modePaiementStr.toUpperCase());

				System.out.print("Entrez l'ID du client : ");
				String clientId = scanner.nextLine();

				System.out.print("Entrez l'ID de l'offre : ");
				while (!scanner.hasNextInt()) {
					System.out.println("Veuillez entrer un entier valide pour l'ID de l'offre :");
					scanner.next();
				}
				int offreId = scanner.nextInt();
				scanner.nextLine();

				System.out.print("Entrez le type d'offre (VOL / SEJOUR_HOTEL / VOYAGE_ORGANISE) : ");
				String typeOffreStr = scanner.nextLine();
				TypeOffre typeOffre = TypeOffre.valueOf(typeOffreStr.toUpperCase());

				Reservation reservation = new Reservation(date, nbrParticipants, modePaiement, clientId, offreId,
						typeOffre);

				ClientServiceImpl reserver = new ClientServiceImpl();
				reserver.ajouter(reservation);
				System.out.println("Réservation ajoutée avec succès !");
			} catch (IllegalArgumentException e) {
				System.err.println("Erreur dans les données saisies : " + e.getMessage());
			} catch (SQLException e) {
				System.err.println("Erreur SQL : " + e.getMessage());
			}

			System.out.println("Voulez-vous ajouter une autre réservation ? [1 pour oui, 0 pour non]");
			while (!scanner.hasNextInt()) {
				System.out.println("Veuillez entrer un entier valide (1 ou 0) :");
				scanner.next();
			}
			continuer = scanner.nextInt();
			scanner.nextLine();

		} while (continuer == 1);
	}

    public static void afficherReservationsParClient(Scanner scanner) throws SQLException {
        int continuer;

        do {
            System.out.print("Entrez l'ID du client pour afficher ses réservations : ");
            String clientId = scanner.nextLine();

            ClientServiceImpl reserver = new ClientServiceImpl();
            List<Reservation> reservations = reserver.getByClientId(clientId);

            if (reservations.isEmpty()) {
                System.out.println("Aucune réservation trouvée pour ce client.");
            } else {
                System.out.println("Liste des réservations pour le client " + clientId + " :");
                for (Reservation res : reservations) {
                    System.out.println(res);
                }
            }

            System.out.println("Voulez-vous afficher les réservations pour un autre client ? [1 pour oui, 0 pour non]");
            while (!scanner.hasNextInt()) {
                System.out.println("Veuillez entrer un entier valide (1 ou 0) :");
                scanner.next();
            }
            continuer = scanner.nextInt();
            scanner.nextLine();

        } while (continuer == 1);
    }
   
}

