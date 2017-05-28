package entities;

public class EmploymentHistory {

	public int getId_employment_history() {
		return id_employment_history;
	}

	public void setId_employment_history(int id_employment_history) {
		this.id_employment_history = id_employment_history;
	}

	public int getId_scientific_institutions() {
		return id_scientific_institutions;
	}

	public void setId_scientific_institutions(int id_scientific_institutions) {
		this.id_scientific_institutions = id_scientific_institutions;
	}

	public int getId_cathedra() {
		return id_cathedra;
	}

	public void setId_cathedra(int id_cathedra) {
		this.id_cathedra = id_cathedra;
	}

	public int getId_authors() {
		return id_authors;
	}

	public void setId_authors(int id_authors) {
		this.id_authors = id_authors;
	}

	public String getBeginning_of_period() {
		return beginning_of_period;
	}

	public void setBeginning_of_period(String beginning_of_period) {
		this.beginning_of_period = beginning_of_period;
	}

	public String getEnding_of_period() {
		return ending_of_period;
	}

	public void setEnding_of_period(String ending_of_period) {
		this.ending_of_period = ending_of_period;
	}

	public String getName_scientific_institutions() {
		return name_scientific_institutions;
	}

	public void setName_scientific_institutions(String name_scientific_institutions) {
		this.name_scientific_institutions = name_scientific_institutions;
	}

	public String getName_cathedra() {
		return name_cathedra;
	}

	public void setName_cathedra(String name_cathedra) {
		this.name_cathedra = name_cathedra;
	}

	public String getName_authors() {
		return name_authors;
	}

	public void setName_authors(String name_authors) {
		this.name_authors = name_authors;
	}

	private int id_employment_history;
	private int id_scientific_institutions;
	private int id_cathedra;
	private int id_authors;
	private String beginning_of_period;
	private String ending_of_period;
	private String name_scientific_institutions;
	private String name_cathedra;
	private String name_authors;
	
	public EmploymentHistory(int id_employment_history, int id_scientific_institutions, int id_cathedra, int id_authors, String beginning_of_period, String ending_of_period, String name_scientific_institutions, String name_cathedra, String name_authors){
		this.id_employment_history = id_employment_history;
		this.id_scientific_institutions = id_scientific_institutions;
		this.id_cathedra = id_cathedra;
		this.id_authors = id_authors;
		this.beginning_of_period = beginning_of_period;
		this.ending_of_period = ending_of_period;
		this.name_scientific_institutions = name_scientific_institutions;
		this.name_cathedra = name_cathedra;
		this.name_authors = name_authors;

	}
	
	public EmploymentHistory(int id_employment_history, int id_scientific_institutions, int id_cathedra, int id_authors, String beginning_of_period, String ending_of_period){
		this.id_employment_history = id_employment_history;
		this.id_scientific_institutions = id_scientific_institutions;
		this.id_cathedra = id_cathedra;
		this.id_authors = id_authors;
		this.beginning_of_period = beginning_of_period;
		this.ending_of_period = ending_of_period;
	}
}
