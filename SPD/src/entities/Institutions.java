package entities;

public class Institutions {

	public int getId_institutions() {
		return id_institutions;
	}

	public void setId_institutions(int id_institutions) {
		this.id_institutions = id_institutions;
	}

	public int getId_scientific_institutions() {
		return id_scientific_institutions;
	}

	public void setId_scientific_institutions(int id_scientific_institutions) {
		this.id_scientific_institutions = id_scientific_institutions;
	}

	public String getName_scientific_institutions() {
		return name_scientific_institutions;
	}

	public void setName_scientific_institutions(String name_scientific_institutions) {
		this.name_scientific_institutions = name_scientific_institutions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private int id_institutions;
	private int id_scientific_institutions;
	private String name_scientific_institutions;
	private String name;
	private String adress;
	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	private String telephone;
	private String url;
	
	public Institutions(int id_institutions, int id_scientific_institutions, String name_scientific_institutions, String name, String adress, String telephone, String url){
		this.id_institutions = id_institutions;
		this.id_scientific_institutions = id_scientific_institutions;
		this.name_scientific_institutions = name_scientific_institutions;
		this.name = name;
		this.adress = adress;
		this.telephone = telephone;
		this.url = url;

	}
	
	public Institutions(int id_institutions, int id_scientific_institutions, String name, String telephone, String url){
		this.id_institutions = id_institutions;
		this.id_scientific_institutions = id_scientific_institutions;
		this.name = name;
		this.telephone = telephone;
		this.url = url;

	}
}
