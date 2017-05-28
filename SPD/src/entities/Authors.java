package entities;

import java.util.Date;

import com.mysql.jdbc.Blob;

public class Authors {

	public int getId_authors() {
		return id_authors;
	}

	public void setId_authors(int id_authors) {
		this.id_authors = id_authors;
	}

	public int getId_identifiers() {
		return id_identifiers;
	}

	public void setId_identifiers(int id_identifiers) {
		this.id_identifiers = id_identifiers;
	}

	public String getFull_name_en() {
		return full_name_en;
	}

	public void setFull_name_en(String full_name_en) {
		this.full_name_en = full_name_en;
	}

	public String getFull_name_ru() {
		return full_name_ru;
	}

	public void setFull_name_ru(String full_name_ru) {
		this.full_name_ru = full_name_ru;
	}

	public String getFull_name_ua() {
		return full_name_ua;
	}

	public void setFull_name_ua(String full_name_ua) {
		this.full_name_ua = full_name_ua;
	}

	public String getScientific_degree() {
		return scientific_degree;
	}

	public void setScientific_degree(String scientific_degree) {
		this.scientific_degree = scientific_degree;
	}

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}



	private int id_authors;
	private int id_identifiers;
	private String full_name_en;
	private String full_name_ru;
	private String full_name_ua;
	private String scientific_degree;
	private Date date_of_birth;
	private String email;
	private String telephone;

	
	public Authors(int id_authors, int id_identifiers, String full_name_en, String full_name_ru, String full_name_ua, String scientific_degree, Date date_of_birth, String email, String telephone){
		this.id_authors = id_authors;
		this.id_identifiers = id_identifiers;
		this.full_name_en = full_name_en;
		this.full_name_ru = full_name_ru;
		this.full_name_ua = full_name_ua;
		this.scientific_degree = scientific_degree;
		this.date_of_birth = date_of_birth;
		this.email = email;
		this.telephone = telephone;

	}
}
