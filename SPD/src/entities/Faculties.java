package entities;

public class Faculties {

	public int getId_faculties() {
		return id_faculties;
	}

	public void setId_faculties(int id_faculties) {
		this.id_faculties = id_faculties;
	}

	public int getId_institutions() {
		return id_institutions;
	}

	public void setId_institutions(int id_institutions) {
		this.id_institutions = id_institutions;
	}

	public String getName_institutions() {
		return name_institutions;
	}

	public void setName_institutions(String name_institutions) {
		this.name_institutions = name_institutions;
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

	private int id_faculties;
	private int id_institutions;
	private String name_institutions;
	private String name;
	private String telephone;
	private String url;
	
	public Faculties(int id_faculties, int id_institutions, String name_institutions, String name, String telephone, String url){
		this.id_faculties = id_faculties;
		this.id_institutions = id_institutions;
		this.name_institutions = name_institutions;
		this.name = name;
		this.telephone = telephone;
		this.url = url;

	}
	
	public Faculties(int id_faculties, int id_institutions, String name, String telephone, String url){
		this.id_faculties = id_faculties;
		this.id_institutions = id_institutions;
		this.name = name;
		this.telephone = telephone;
		this.url = url;

	}
}
