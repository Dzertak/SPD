package entities;

public class Cathedra {

	public int getId_cathedra() {
		return id_cathedra;
	}

	public void setId_cathedra(int id_cathedra) {
		this.id_cathedra = id_cathedra;
	}

	public int getId_faculties() {
		return id_faculties;
	}

	public void setId_faculties(int id_faculties) {
		this.id_faculties = id_faculties;
	}

	public String getName_faculties() {
		return name_faculties;
	}

	public void setName_faculties(String name_faculties) {
		this.name_faculties = name_faculties;
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

	private int id_cathedra;
	private int id_faculties;
	private String name_faculties;
	private String name;
	private String telephone;
	private String url;
	
	public Cathedra(int id_cathedra, int id_faculties, String name_faculties, String name, String telephone, String url){
		this.id_cathedra = id_cathedra;
		this.id_faculties = id_faculties;
		this.name_faculties = name_faculties;
		this.name = name;
		this.telephone = telephone;
		this.url = url;

	}
	
	public Cathedra(int id_cathedra, int id_faculties, String name, String telephone, String url){
		this.id_cathedra = id_cathedra;
		this.id_faculties = id_faculties;
		this.name = name;
		this.telephone = telephone;
		this.url = url;

	}
}
