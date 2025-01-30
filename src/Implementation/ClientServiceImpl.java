package Implementation;
import Entities.Reservation;
import Enumerations.ModePaiement;
import Enumerations.TypeOffre;
import Services.IService;
import Utils.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientServiceImpl implements IService<Reservation> {

    private Connection con = DataSource.getInstance().getConn();

    @Override
    public void ajouter(Reservation reservation) throws SQLException {
        String req = "INSERT INTO `Reservation` (`date`, `nbrParticipants`, `modePaiement`, `clientId`, `offreId`, `typeOffre`) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement pre = con.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
        pre.setDate(1, new Date(reservation.getDate().getTime()));
        pre.setInt(2, reservation.getNbrParticipants());
        pre.setString(3, reservation.getModePaiement().getValue()); 
        pre.setString(4, reservation.getClientId());
        pre.setInt(5, reservation.getOffreId());
        pre.setString(6, reservation.getTypeOffre().getValue()); 
        int rowsAffected = pre.executeUpdate();
        System.out.println("Réservation ajoutée avec succès ! Nombre de lignes affectées : " + rowsAffected);

        try (var generatedKeys = pre.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                reservation.setId(generatedId); 
                System.out.println("ID de la réservation générée : " + generatedId);
            }
        }
    }

    
    public List<Reservation> getByClientId(String id) throws SQLException {
        String req = "SELECT * FROM `Reservation` WHERE `clientId` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        List<Reservation> allreservations = new ArrayList<Reservation>();
        pre.setString(1, id);
        ResultSet rs = pre.executeQuery();

        while (rs.next()) {
             allreservations.add(new Reservation(
                rs.getInt("id"), 
                rs.getDate("date"), 
                rs.getInt("nbrParticipants"), 
                ModePaiement.fromString(rs.getString("modePaiement")), 
                rs.getString("clientId"),
                rs.getInt("offreId"), 
                TypeOffre.fromString(rs.getString("typeOffre")) 
            )) ;
        }
        return allreservations;
    }
    
    @Override
    public void supprimer(Reservation reservation) throws SQLException {
    	String req = "DELETE FROM `Reservation` WHERE `id` = ?";
        PreparedStatement pre = con.prepareStatement(req);

        pre.setInt(1, reservation.getId());

        int rowsAffected = pre.executeUpdate();

        if( rowsAffected > 0) {
            System.out.println("La réservation avec l'ID " + reservation.getId() + " a été supprimée avec succès !");
        }
        else {
            System.out.println("Aucune réservation trouvée avec l'ID " + reservation.getId() + ".");

        }
    }

    @Override
    public void update(Reservation reservation) throws SQLException {
    	 String req = "UPDATE `Reservation` SET `date` = ?, `nbrParticipants` = ?, `modePaiement` = ?, `clientId` = ?, `offreId` = ?, `typeOffre` = ? WHERE `id` = ?";
    	    PreparedStatement pre = con.prepareStatement(req);

    	    // Définir les paramètres pour la requête
    	    pre.setDate(1, new java.sql.Date(reservation.getDate().getTime()));
    	    pre.setInt(2, reservation.getNbrParticipants());
    	    pre.setString(3, reservation.getModePaiement().getValue());
    	    pre.setString(4, reservation.getClientId());
    	    pre.setInt(5, reservation.getOffreId());
    	    pre.setString(6, reservation.getTypeOffre().toString());
    	    pre.setInt(7, reservation.getId());

    	    int rowsAffected = pre.executeUpdate();
    	    if (rowsAffected > 0) {
                System.out.println("La réservation avec l'ID " + reservation.getId() + " a été mise à jour avec succès !");
            } else {
                System.out.println("Aucune réservation trouvée avec l'ID " + reservation.getId() + ".");
            }
    }

    @Override
    public Reservation getById(int id) throws SQLException {
        String req = "SELECT * FROM `Reservation` WHERE `id` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();

        if (rs.next()) {
            return new Reservation(
                rs.getInt("id"), 
                rs.getDate("date"), 
                rs.getInt("nbrParticipants"), 
                ModePaiement.fromString(rs.getString("modePaiement")), 
                rs.getString("clientId"),
                rs.getInt("offreId"), 
                TypeOffre.fromString(rs.getString("typeOffre")) 
            );
        }
        return null;
    }

	@Override
	public List<Reservation> getAll() throws SQLException {
		String req = "SELECT * FROM `Reservation`";
	    PreparedStatement pre = con.prepareStatement(req);

	    List<Reservation> allReservations = new ArrayList<>();
	    ResultSet rs = pre.executeQuery();

	    while (rs.next()) {
	        allReservations.add(new Reservation(
	            rs.getInt("id"), 
	            rs.getDate("date"), 
	            rs.getInt("nbrParticipants"), 
	            ModePaiement.fromString(rs.getString("modePaiement")), 
	            rs.getString("clientId"),
	            rs.getInt("offreId"), 
	            TypeOffre.valueOf(rs.getString("typeOffre").toUpperCase())
	        ));
	    }

	    return allReservations;
	}

	

}