package Entities;

import java.sql.Date;
import Enumerations.ModePaiement;
import Enumerations.TypeOffre;


public class Reservation {

	private int id;

	private Date date;

	private int nbrParticipants;

	private ModePaiement modePaiement;

	private String clientId;

	private int offreId;

	private TypeOffre typeOffre;
	
	public Reservation( Date date, int nbrParticipants, ModePaiement modePaiement, String clientId,
			int offreId, TypeOffre typeOffre) {
		super();
		//this.id = id;
		this.date = date;
		this.nbrParticipants = nbrParticipants;
		this.modePaiement = modePaiement;
		this.clientId = clientId;
		this.offreId = offreId;
		this.typeOffre = typeOffre;
	}
	public Reservation( int id,Date date, int nbrParticipants, ModePaiement modePaiement, String clientId,
			int offreId, TypeOffre typeOffre) {
		super();
		this.id = id;
		this.date = date;
		this.nbrParticipants = nbrParticipants;
		this.modePaiement = modePaiement;
		this.clientId = clientId;
		this.offreId = offreId;
		this.typeOffre = typeOffre;
	}
	
	public Reservation() {

	}
	
	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getNbrParticipants() {
		return nbrParticipants;
	}

	public void setNbrParticipants(int nbrParticipants) {
		this.nbrParticipants = nbrParticipants;
	}

	public ModePaiement getModePaiement() {
		return modePaiement;
	}

	public void setModePaiement(ModePaiement modePaiement) {
		this.modePaiement = modePaiement;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public int getOffreId() {
		return offreId;
	}

	public void setOffreId(int offreId) {
		this.offreId = offreId;
	}

	public TypeOffre getTypeOffre() {
		return typeOffre;
	}

	public void setTypeOffre(TypeOffre typeOffre) {
		this.typeOffre = typeOffre;
	}
	
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", date=" + date + ", nbrParticipants=" + nbrParticipants + ", modePaiement="
				+ modePaiement + ", clientId=" + clientId + ", offreId=" + offreId + ", typeOffre=" + typeOffre + "]";
	}
}
