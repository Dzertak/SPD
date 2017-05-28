package entities;

public class Identifiers {

	private int id_identifiers;
	public int getId_identifiers() {
		return id_identifiers;
	}

	public void setId_identifiers(int id_identifiers) {
		this.id_identifiers = id_identifiers;
	}

	public String getScopusid() {
		return scopusid;
	}

	public void setScopusid(String scopusid) {
		this.scopusid = scopusid;
	}

	public String getOrcid() {
		return orcid;
	}

	public void setOrcid(String orcid) {
		this.orcid = orcid;
	}

	public String getResearcherid() {
		return researcherid;
	}

	public void setResearcherid(String researcherid) {
		this.researcherid = researcherid;
	}

	private String scopusid;
	private String orcid;
	private String researcherid;
	
	public Identifiers(int id_identifiers, String scopusid, String orcid, String researcherid){
		this.id_identifiers = id_identifiers;
		this.scopusid = scopusid;
		this.orcid = orcid;
		this.researcherid = researcherid;

	}
}
