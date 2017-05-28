package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import entities.Authors;
import entities.Cathedra;
import entities.EmploymentHistory;
import entities.Faculties;
import entities.Identifiers;
import entities.Institutions;
import entities.ScientificInstitutions;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

public class ControllerScientificInstitutions implements Initializable{
	
	
	private static ObservableList<ScientificInstitutions> scientificInstitutionsData = FXCollections.observableArrayList();
	private static ObservableList<Institutions> institutionsData = FXCollections.observableArrayList();
	private static ObservableList<Faculties> facultiesData = FXCollections.observableArrayList();
	private static ObservableList<Cathedra> cathedraData = FXCollections.observableArrayList();
	
	public Object idAuthorTableValue, idAuthorTableField;
	public boolean flagChoseAuthor=false;
	private String tabTableName = "Scientific_institutions";
	@FXML private Tab tabScientific_institutions,tabInstitutions,tabFaculties,tabCathedra;
	
	@FXML private TitledPane tltPaneAddScientificInstitutions,tltPaneAddInstitutions,tltPaneAddFaculties,tltPaneAddCathedra;
	@FXML private Button btnDeleteScientificInstitutions,btnDeleteInstitutions,btnDeleteFaculties,btnDeleteCathedra;
	@FXML private TextField edtSearchScietificInstitutions,edtSearchInstitutions,edtSearchFaculties,edtSearchCathedra;
	@FXML private ImageView btnSearchScientificInstitutions,btnSearchInstitutions,btnSearchFaculties,btnSearchCathedra;
	//add
	@FXML private TextField edtNameScientifiInstitutions = new TextField(""),edtAdressScientificInstitutions= new TextField(""),edtTelephoneScientificInstitutions= new TextField(""),edtURLScientificInstitutions= new TextField("");
	@FXML private TextField edtIDScientificInstitutions,edtNameInstitutions = new TextField(""),edtAdressInstitutions = new TextField(""),edtTelephoneInstitutions = new TextField(""),edtURLInstitutions = new TextField("");
	@FXML private TextField edtIDInstitutions,edtNameFaculties = new TextField(""),edtTelephoneFaculties = new TextField(""),edtURLFaculties = new TextField("");
	@FXML private TextField edtIDFaculties,edtNameCathedra,edtTelephoneCathedra,edtURLCathedra;
	@FXML private Button btnAddScientificInstitutions,btnAddInstitutions,btnAddFaculties, btnAddCathedra;
	
	@FXML private AnchorPane topAnchorScientificInstitution,scientificInstitutionsWindow,leftAnchor;
	@FXML private TabPane tabAuthor;
	@FXML private TableView tblScientificInstitution,tblInstitutions,tblFaculties,tblCathedra;
	
	//Table ScientificInstitution
	@FXML private TableColumn<ScientificInstitutions, Integer> id_scientific_institutions;
	@FXML private TableColumn<ScientificInstitutions, String> name;
	@FXML private TableColumn<ScientificInstitutions, String> adress;
	@FXML private TableColumn<ScientificInstitutions, String> url;
	@FXML private TableColumn<ScientificInstitutions, String> telephone;
	
	@FXML private TableColumn<Institutions, Integer> id_institutions;
	@FXML private TableColumn<Institutions, Integer> id_scientific_institutions1;
	@FXML private TableColumn<Institutions, String> name_scientific_institutions;
	@FXML private TableColumn<Institutions, String> name1;
	@FXML private TableColumn<Institutions, String> adress1;
	@FXML private TableColumn<Institutions, String> url1;
	@FXML private TableColumn<Institutions, String> telephone1;
	
	@FXML private TableColumn<Faculties, Integer> id_faculties;
	@FXML private TableColumn<Faculties, Integer> id_institutions1;
	@FXML private TableColumn<Faculties, String> name2;
	@FXML private TableColumn<Faculties, String> name_institutions;
	@FXML private TableColumn<Faculties, String> url2;
	@FXML private TableColumn<Faculties, String> telephone2;
	
	@FXML private TableColumn<Cathedra, Integer> id_cathedra;
	@FXML private TableColumn<Cathedra, Integer> id_faculties1;
	@FXML private TableColumn<Cathedra, String> name3;
	@FXML private TableColumn<Cathedra, String> name_faculties;
	@FXML private TableColumn<Cathedra, String> url3;
	@FXML private TableColumn<Cathedra, String> telephone3;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		scientificInstitutionsWindow.setStyle("-fx-background-color: white");
		leftAnchor.setStyle("-fx-background-color: rgba(52,132,191,1);");
		topAnchorScientificInstitution.setStyle("-fx-background-color: rgba(52,132,191,1);");
		tabAuthor.setStyle("-fx-background-color: white");
		tblScientificInstitution.setStyle(" -fx-base: white; -fx-control-inner-background: white; -fx-table-cell-border-color: rgba(52,132,191,1); -fx-table-header-border-color: rgba(52,132,191,1); -fx-padding: 2;");
		tblInstitutions.setStyle(" -fx-base: white; -fx-control-inner-background: white; -fx-table-cell-border-color: rgba(52,132,191,1); -fx-table-header-border-color: rgba(52,132,191,1); -fx-padding: 2;");
		tblFaculties.setStyle(" -fx-base: white; -fx-control-inner-background: white; -fx-table-cell-border-color: rgba(52,132,191,1); -fx-table-header-border-color: rgba(52,132,191,1); -fx-padding: 2;");
		tblCathedra.setStyle(" -fx-base: white; -fx-control-inner-background: white; -fx-table-cell-border-color: rgba(52,132,191,1); -fx-table-header-border-color: rgba(52,132,191,1); -fx-padding: 2;");
	
		Image imageDelete = new Image(getClass().getResourceAsStream("/fxml/image/delete author3.png"));
		btnDeleteScientificInstitutions.setGraphic(new ImageView(imageDelete));
		btnDeleteInstitutions.setGraphic(new ImageView(imageDelete));
		btnDeleteFaculties.setGraphic(new ImageView(imageDelete));
		btnDeleteCathedra.setGraphic(new ImageView(imageDelete));
		
		id_scientific_institutions.setEditable(true);
		id_scientific_institutions.setCellValueFactory(new Callback<CellDataFeatures<ScientificInstitutions, Integer>, ObservableValue<Integer>>(){
			 @Override
			    public ObservableValue<Integer> call(CellDataFeatures<ScientificInstitutions, Integer> p) {
			        return (ObservableValue) new SimpleIntegerProperty(p.getValue().getId_scientific_institutions());
			} 
			});
		id_scientific_institutions.setCellFactory(TextFieldTableCell.<ScientificInstitutions, Integer>forTableColumn(new IntegerStringConverter()));
		id_scientific_institutions.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ScientificInstitutions,Integer>>(){
		public void handle(CellEditEvent<ScientificInstitutions, Integer> t) {
			if(fieldEditScientificInstitutions(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_scientific_institutions(),tabTableName,"id_scientific_institutions", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_scientific_institutions()), String.valueOf(t.getNewValue()))){		
				((ScientificInstitutions) t.getTableView().getItems().get(t.getTablePosition().getRow())).setId_scientific_institutions(t.getNewValue());
			} tblScientificInstitution.refresh(); }});
		name.setEditable(true);
		name.setCellFactory(TextFieldTableCell.forTableColumn());	
		name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ScientificInstitutions,String>>(){
		public void handle(CellEditEvent<ScientificInstitutions, String> t) {
			if(fieldEditScientificInstitutions(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_scientific_institutions(),tabTableName,"name", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getName()), String.valueOf(t.getNewValue()))){		
				((ScientificInstitutions) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
			} tblScientificInstitution.refresh(); }});
		adress.setEditable(true);
		adress.setCellFactory(TextFieldTableCell.forTableColumn());	
		adress.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ScientificInstitutions,String>>(){
		public void handle(CellEditEvent<ScientificInstitutions, String> t) {
			if(fieldEditScientificInstitutions(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_scientific_institutions(),tabTableName,"adress", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getAdress()), String.valueOf(t.getNewValue()))){		
				((ScientificInstitutions) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAdress(t.getNewValue());
			} tblScientificInstitution.refresh(); }});
		telephone.setEditable(true);
		telephone.setCellFactory(TextFieldTableCell.forTableColumn());	
		telephone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ScientificInstitutions,String>>(){
		public void handle(CellEditEvent<ScientificInstitutions, String> t) {
			if(fieldEditScientificInstitutions(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_scientific_institutions(),tabTableName,"telephone", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getTelephone()), String.valueOf(t.getNewValue()))){		
				((ScientificInstitutions) t.getTableView().getItems().get(t.getTablePosition().getRow())).setTelephone(t.getNewValue());
			} tblScientificInstitution.refresh(); }});
		url.setEditable(true);
		url.setCellFactory(TextFieldTableCell.forTableColumn());	
		url.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ScientificInstitutions,String>>(){
		public void handle(CellEditEvent<ScientificInstitutions, String> t) {
			if(fieldEditScientificInstitutions(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_scientific_institutions(),tabTableName,"url", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getUrl()), String.valueOf(t.getNewValue()))){		
				((ScientificInstitutions) t.getTableView().getItems().get(t.getTablePosition().getRow())).setUrl(t.getNewValue());
			} tblScientificInstitution.refresh(); }});
		
		
		id_institutions.setEditable(true);
		id_institutions.setCellValueFactory(new Callback<CellDataFeatures<Institutions, Integer>, ObservableValue<Integer>>(){
			 @Override
			    public ObservableValue<Integer> call(CellDataFeatures<Institutions, Integer> p) {
			        return (ObservableValue) new SimpleIntegerProperty(p.getValue().getId_institutions());
			} 
			});
		id_institutions.setCellFactory(TextFieldTableCell.<Institutions, Integer>forTableColumn(new IntegerStringConverter()));
		id_institutions.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Institutions,Integer>>(){
		public void handle(CellEditEvent<Institutions, Integer> t) {
			if(fieldEditInstitutions(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_institutions(),tabTableName,"id_institutions", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_institutions()), String.valueOf(t.getNewValue()))){		
				((Institutions) t.getTableView().getItems().get(t.getTablePosition().getRow())).setId_institutions(t.getNewValue());
			} tblInstitutions.refresh(); }});
		id_scientific_institutions1.setEditable(true);
		id_scientific_institutions1.setCellValueFactory(new Callback<CellDataFeatures<Institutions, Integer>, ObservableValue<Integer>>(){
			 @Override
			    public ObservableValue<Integer> call(CellDataFeatures<Institutions, Integer> p) {
			        return (ObservableValue) new SimpleIntegerProperty(p.getValue().getId_scientific_institutions());
			} 
			});
		id_scientific_institutions1.setCellFactory(TextFieldTableCell.<Institutions, Integer>forTableColumn(new IntegerStringConverter()));
		id_scientific_institutions1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Institutions,Integer>>(){
		public void handle(CellEditEvent<Institutions, Integer> t) {
			if(fieldEditInstitutions(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_institutions(),tabTableName,"id_scientific_institutions", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_scientific_institutions()), String.valueOf(t.getNewValue()))){		
				((Institutions) t.getTableView().getItems().get(t.getTablePosition().getRow())).setId_scientific_institutions(t.getNewValue());
			} tblInstitutions.refresh(); }});
		
		name1.setEditable(true);
		name1.setCellFactory(TextFieldTableCell.forTableColumn());	
		name1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Institutions,String>>(){
		public void handle(CellEditEvent<Institutions, String> t) {
			if(fieldEditInstitutions(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_institutions(),tabTableName,"name", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getName()), String.valueOf(t.getNewValue()))){		
				((Institutions) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
			} tblInstitutions.refresh(); }});
		adress1.setEditable(true);
		adress1.setCellFactory(TextFieldTableCell.forTableColumn());	
		adress1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Institutions,String>>(){
		public void handle(CellEditEvent<Institutions, String> t) {
			if(fieldEditInstitutions(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_institutions(),tabTableName,"adress", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getAdress()), String.valueOf(t.getNewValue()))){		
				((Institutions) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAdress(t.getNewValue());
			} tblInstitutions.refresh(); }});
		telephone1.setEditable(true);
		telephone1.setCellFactory(TextFieldTableCell.forTableColumn());	
		telephone1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Institutions,String>>(){
		public void handle(CellEditEvent<Institutions, String> t) {
			if(fieldEditInstitutions(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_institutions(),tabTableName,"telephone", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getTelephone()), String.valueOf(t.getNewValue()))){		
				((Institutions) t.getTableView().getItems().get(t.getTablePosition().getRow())).setTelephone(t.getNewValue());
			} tblInstitutions.refresh(); }});
		url1.setEditable(true);
		url1.setCellFactory(TextFieldTableCell.forTableColumn());	
		url1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Institutions,String>>(){
		public void handle(CellEditEvent<Institutions, String> t) {
			if(fieldEditInstitutions(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_institutions(),tabTableName,"url", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getUrl()), String.valueOf(t.getNewValue()))){		
				((Institutions) t.getTableView().getItems().get(t.getTablePosition().getRow())).setUrl(t.getNewValue());
			} tblInstitutions.refresh(); }});
		
		
		id_faculties.setEditable(true);
		id_faculties.setCellValueFactory(new Callback<CellDataFeatures<Faculties, Integer>, ObservableValue<Integer>>(){
			 @Override
			    public ObservableValue<Integer> call(CellDataFeatures<Faculties, Integer> p) {
			        return (ObservableValue) new SimpleIntegerProperty(p.getValue().getId_faculties());
			} 
			});
		id_faculties.setCellFactory(TextFieldTableCell.<Faculties, Integer>forTableColumn(new IntegerStringConverter()));
		id_faculties.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Faculties,Integer>>(){
		public void handle(CellEditEvent<Faculties, Integer> t) {
			if(fieldEditFaculties(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_faculties(),tabTableName,"id_faculties", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_faculties()), String.valueOf(t.getNewValue()))){		
				((Faculties) t.getTableView().getItems().get(t.getTablePosition().getRow())).setId_faculties(t.getNewValue());
			} tblFaculties.refresh(); }});
		id_institutions1.setEditable(true);
		id_institutions1.setCellValueFactory(new Callback<CellDataFeatures<Faculties, Integer>, ObservableValue<Integer>>(){
			 @Override
			    public ObservableValue<Integer> call(CellDataFeatures<Faculties, Integer> p) {
			        return (ObservableValue) new SimpleIntegerProperty(p.getValue().getId_institutions());
			} 
			});
		id_institutions1.setCellFactory(TextFieldTableCell.<Faculties, Integer>forTableColumn(new IntegerStringConverter()));
		id_institutions1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Faculties,Integer>>(){
		public void handle(CellEditEvent<Faculties, Integer> t) {
			if(fieldEditFaculties(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_faculties(),tabTableName,"id_institutions", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_institutions()), String.valueOf(t.getNewValue()))){		
				((Faculties) t.getTableView().getItems().get(t.getTablePosition().getRow())).setId_institutions(t.getNewValue());
			} tblFaculties.refresh(); }});
		name2.setEditable(true);
		name2.setCellFactory(TextFieldTableCell.forTableColumn());	
		name2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Faculties,String>>(){
		public void handle(CellEditEvent<Faculties, String> t) {
			if(fieldEditFaculties(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_faculties(),tabTableName,"name", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getName()), String.valueOf(t.getNewValue()))){		
				((Faculties) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
			} tblFaculties.refresh(); }});
		telephone2.setEditable(true);
		telephone2.setCellFactory(TextFieldTableCell.forTableColumn());	
		telephone2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Faculties,String>>(){
		public void handle(CellEditEvent<Faculties, String> t) {
			if(fieldEditFaculties(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_faculties(),tabTableName,"telephone", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getTelephone()), String.valueOf(t.getNewValue()))){		
				((Faculties) t.getTableView().getItems().get(t.getTablePosition().getRow())).setTelephone(t.getNewValue());
			} tblFaculties.refresh(); }});
		url2.setEditable(true);
		url2.setCellFactory(TextFieldTableCell.forTableColumn());	
		url2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Faculties,String>>(){
		public void handle(CellEditEvent<Faculties, String> t) {
			if(fieldEditFaculties(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_faculties(),tabTableName,"url", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getUrl()), String.valueOf(t.getNewValue()))){		
				((Faculties) t.getTableView().getItems().get(t.getTablePosition().getRow())).setUrl(t.getNewValue());
			} tblFaculties.refresh(); }});
		
		id_cathedra.setEditable(true);
		id_cathedra.setCellValueFactory(new Callback<CellDataFeatures<Cathedra, Integer>, ObservableValue<Integer>>(){
			 @Override
			    public ObservableValue<Integer> call(CellDataFeatures<Cathedra, Integer> p) {
			        return (ObservableValue) new SimpleIntegerProperty(p.getValue().getId_cathedra());
			} 
			});
		id_cathedra.setCellFactory(TextFieldTableCell.<Cathedra, Integer>forTableColumn(new IntegerStringConverter()));
		id_cathedra.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Cathedra,Integer>>(){
		public void handle(CellEditEvent<Cathedra, Integer> t) {
			if(fieldEditCathedra(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_cathedra(),tabTableName,"id_cathedra", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_cathedra()), String.valueOf(t.getNewValue()))){		
				((Cathedra) t.getTableView().getItems().get(t.getTablePosition().getRow())).setId_cathedra(t.getNewValue());
			} tblCathedra.refresh(); }});
		id_faculties1.setEditable(true);
		id_faculties1.setCellValueFactory(new Callback<CellDataFeatures<Cathedra, Integer>, ObservableValue<Integer>>(){
			 @Override
			    public ObservableValue<Integer> call(CellDataFeatures<Cathedra, Integer> p) {
			        return (ObservableValue) new SimpleIntegerProperty(p.getValue().getId_faculties());
			} 
			});
		id_faculties1.setCellFactory(TextFieldTableCell.<Cathedra, Integer>forTableColumn(new IntegerStringConverter()));
		id_faculties1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Cathedra,Integer>>(){
		public void handle(CellEditEvent<Cathedra, Integer> t) {
			if(fieldEditCathedra(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_cathedra(),tabTableName,"id_faculties", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_faculties()), String.valueOf(t.getNewValue()))){		
				((Cathedra) t.getTableView().getItems().get(t.getTablePosition().getRow())).setId_faculties(t.getNewValue());
			} tblCathedra.refresh(); }});
		
		name3.setEditable(true);
		name3.setCellFactory(TextFieldTableCell.forTableColumn());	
		name3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Cathedra,String>>(){
		public void handle(CellEditEvent<Cathedra, String> t) {
			if(fieldEditCathedra(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_cathedra(),tabTableName,"name", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getName()), String.valueOf(t.getNewValue()))){		
				((Cathedra) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
			} tblCathedra.refresh(); }});
		telephone3.setEditable(true);
		telephone3.setCellFactory(TextFieldTableCell.forTableColumn());	
		telephone3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Cathedra,String>>(){
		public void handle(CellEditEvent<Cathedra, String> t) {
			if(fieldEditCathedra(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_cathedra(),tabTableName,"telephone", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getTelephone()), String.valueOf(t.getNewValue()))){		
				((Cathedra) t.getTableView().getItems().get(t.getTablePosition().getRow())).setTelephone(t.getNewValue());
			} tblCathedra.refresh(); }});
		url3.setEditable(true);
		url3.setCellFactory(TextFieldTableCell.forTableColumn());	
		url3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Cathedra,String>>(){
		public void handle(CellEditEvent<Cathedra, String> t) {
			if(fieldEditCathedra(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_cathedra(),tabTableName,"url", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getUrl()), String.valueOf(t.getNewValue()))){		
				((Cathedra) t.getTableView().getItems().get(t.getTablePosition().getRow())).setUrl(t.getNewValue());
			} tblCathedra.refresh(); }});
		
		tblScientificInstitution.setOnMouseClicked(new EventHandler<Event>() {			
			@Override
			public void handle(Event event) {
				try{
				TablePosition pos = (TablePosition) tblScientificInstitution.getSelectionModel().getSelectedCells().get(0);
				int row = pos.getRow();
				TableColumn col = pos.getTableColumn();
				// this gives the value in the selected cell:
				idAuthorTableValue = col.getCellObservableValue(tblScientificInstitution.getItems().get(row)).getValue();				
				idAuthorTableField = col.getId();
				flagChoseAuthor = true;
			}catch(Exception e){
			}
			}
		});
		tblInstitutions.setOnMouseClicked(new EventHandler<Event>() {			
			@Override
			public void handle(Event event) {
				try{
				TablePosition pos = (TablePosition) tblInstitutions.getSelectionModel().getSelectedCells().get(0);
				int row = pos.getRow();
				TableColumn col = pos.getTableColumn();
				// this gives the value in the selected cell:
				idAuthorTableValue = col.getCellObservableValue(tblInstitutions.getItems().get(row)).getValue();				
				idAuthorTableField = col.getId();
				flagChoseAuthor = true;
			}catch(Exception e){
			}
			}
		});
		tblFaculties.setOnMouseClicked(new EventHandler<Event>() {			
			@Override
			public void handle(Event event) {
				try{
				TablePosition pos = (TablePosition) tblFaculties.getSelectionModel().getSelectedCells().get(0);
				int row = pos.getRow();
				TableColumn col = pos.getTableColumn();
				// this gives the value in the selected cell:
				idAuthorTableValue = col.getCellObservableValue(tblFaculties.getItems().get(row)).getValue();				
				idAuthorTableField = col.getId();
				flagChoseAuthor = true;
			}catch(Exception e){
			}
			}
		});
		tblCathedra.setOnMouseClicked(new EventHandler<Event>() {			
			@Override
			public void handle(Event event) {
				try{
				TablePosition pos = (TablePosition) tblCathedra.getSelectionModel().getSelectedCells().get(0);
				int row = pos.getRow();
				TableColumn col = pos.getTableColumn();
				// this gives the value in the selected cell:
				idAuthorTableValue = col.getCellObservableValue(tblCathedra.getItems().get(row)).getValue();				
				idAuthorTableField = col.getId();
				flagChoseAuthor = true;
			}catch(Exception e){
			}
			}
		});
		
		
		tltPaneAddScientificInstitutions.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if(tltPaneAddScientificInstitutions.isExpanded()){
					tltPaneAddScientificInstitutions.setPrefHeight(263);
				}else{
					tltPaneAddScientificInstitutions.setPrefHeight(17);
				}
			}
		});
		tltPaneAddInstitutions.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if(tltPaneAddInstitutions.isExpanded()){
					tltPaneAddInstitutions.setPrefHeight(296);
				}else{
					tltPaneAddInstitutions.setPrefHeight(17);
				}
			}
		});
		tltPaneAddFaculties.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if(tltPaneAddFaculties.isExpanded()){
					tltPaneAddFaculties.setPrefHeight(268);
				}else{
					tltPaneAddFaculties.setPrefHeight(17);
				}
			}
		});
		tltPaneAddCathedra.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if(tltPaneAddCathedra.isExpanded()){
					tltPaneAddCathedra.setPrefHeight(272);
				}else{
					tltPaneAddCathedra.setPrefHeight(17);
				}
			}
		});
		
		tabScientific_institutions.setOnSelectionChanged(new EventHandler<Event>() {			
			@Override
			public void handle(Event event) {
				tabTableName = tabScientific_institutions.getId().substring(3);
				}
		});
		tabInstitutions.setOnSelectionChanged(new EventHandler<Event>() {			
			@Override
			public void handle(Event event) {
				tabTableName = tabInstitutions.getId().substring(3);
				}
		});
		tabFaculties.setOnSelectionChanged(new EventHandler<Event>() {			
			@Override
			public void handle(Event event) {
				tabTableName = tabFaculties.getId().substring(3);
				}
		});
		tabCathedra.setOnSelectionChanged(new EventHandler<Event>() {			
			@Override
			public void handle(Event event) {
				tabTableName = tabCathedra.getId().substring(3);
				}
		});
		
		try {
			refresTables();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void connectToDB() throws SQLException{
		Connection conn = null;
		Statement stmt =null;
		ResultSet rs =null;
		try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp","root","7525800");
		stmt=conn.createStatement();
		rs=stmt.executeQuery("Select * From Scientific_institutions");
		while(rs.next()){		
			scientificInstitutionsData.add(new ScientificInstitutions(rs.getInt("id_scientific_institutions"), rs.getString("name"), rs.getString("adress"), rs.getString("telephone"), rs.getString("url")));
		}
		rs=stmt.executeQuery("Select i.*,s.name AS name_scientific_institutions  "
				+ "From `Institutions` i, `Scientific_institutions` s "
				+ "Where `s`.id_scientific_institutions=`i`.id_scientific_institutions");
		while(rs.next()){		
			institutionsData.add(new Institutions(rs.getInt("i.id_institutions"), rs.getInt("i.id_scientific_institutions"), rs.getString("name_scientific_institutions"), rs.getString("i.name"), rs.getString("i.adress"), rs.getString("i.telephone"), rs.getString("i.url")));
		}
		
		rs=stmt.executeQuery("Select f.*,i.name AS name_institutions  "
				+ "From `Institutions` i, `Faculties` f "
				+ "Where `f`.id_institutions=`i`.id_institutions");
		while(rs.next()){		
			facultiesData.add(new Faculties(rs.getInt("f.id_faculties"), rs.getInt("f.id_institutions"), rs.getString("name_institutions"), rs.getString("f.name"), rs.getString("f.telephone"), rs.getString("f.url")));
		}
		rs=stmt.executeQuery("Select c.*,f.name AS name_faculties  "
				+ "From `Cathedra` c, `Faculties` f "
				+ "Where `f`.id_faculties=`c`.id_faculties");
		while(rs.next()){		
			cathedraData.add(new Cathedra(rs.getInt("c.id_cathedra"), rs.getInt("c.id_faculties"), rs.getString("name_faculties"), rs.getString("c.name"), rs.getString("c.telephone"), rs.getString("c.url")));
		}
	
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		} finally{
			if (conn!=null){
				try{
					conn.close();
					stmt.close();
					rs.close();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());			}
			}
		}
	}
	
	public boolean fieldEditScientificInstitutions(int id, String table, String field, Object oldValue, Object newValue){
		Connection conn = null;
		Statement stmt =null;
		ResultSet rs =  null;
		try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp","root","7525800");
		stmt=conn.createStatement();
		stmt.execute("UPDATE `"+table+"` SET `"+field+"`='"+newValue+"' where id_scientific_institutions='"+id+"'");		
		return true;
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "'"+newValue+"' incorrect value!!!");
			return false;		
		} finally{
			if (conn!=null){
				try{
					conn.close();
					stmt.close();
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "'"+newValue+"' incorrect value!!!");	
			
				}
			}
		}

	}
	
	public boolean fieldEditInstitutions(int id, String table, String field, Object oldValue, Object newValue){
		Connection conn = null;
		Statement stmt =null;
		ResultSet rs =  null;
		try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp","root","7525800");
		stmt=conn.createStatement();
		stmt.execute("UPDATE `"+table+"` SET `"+field+"`='"+newValue+"' where id_institutions='"+id+"'");		
		return true;
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "'"+newValue+"' incorrect value!!!");
			return false;		
		} finally{
			if (conn!=null){
				try{
					conn.close();
					stmt.close();
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "'"+newValue+"' incorrect value!!!");	
			
				}
			}
		}

	}
	
	public boolean fieldEditFaculties(int id, String table, String field, Object oldValue, Object newValue){
		Connection conn = null;
		Statement stmt =null;
		ResultSet rs =  null;
		try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp","root","7525800");
		stmt=conn.createStatement();
		stmt.execute("UPDATE `"+table+"` SET `"+field+"`='"+newValue+"' where id_faculties='"+id+"'");		
		return true;
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "'"+newValue+"' incorrect value!!!");
			return false;		
		} finally{
			if (conn!=null){
				try{
					conn.close();
					stmt.close();
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "'"+newValue+"' incorrect value!!!");	
			
				}
			}
		}

	}
	
	public boolean fieldEditCathedra(int id, String table, String field, Object oldValue, Object newValue){
		Connection conn = null;
		Statement stmt =null;
		ResultSet rs =  null;
		try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp","root","7525800");
		stmt=conn.createStatement();
		stmt.execute("UPDATE `"+table+"` SET `"+field+"`='"+newValue+"' where id_cathedra='"+id+"'");		
		return true;
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "'"+newValue+"' incorrect value!!!");
			return false;		
		} finally{
			if (conn!=null){
				try{
					conn.close();
					stmt.close();
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "'"+newValue+"' incorrect value!!!");	
			
				}
			}
		}

	}
	
	public void delete() {
		if(flagChoseAuthor){
		int result = JOptionPane.showConfirmDialog(null,
				"you really want to delete the data?",
				"Select answer",
			    JOptionPane.YES_NO_OPTION);
		switch (result) {
		case JOptionPane.YES_OPTION:
		{
			Connection conn = null;
			Statement stmt =null;
			try{
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp","root","7525800");
			stmt=conn.createStatement();	
			stmt.execute("DELETE FROM `"+tabTableName+"` where `"+idAuthorTableField+"`='"+idAuthorTableValue+"'");
			refresTables();
			JOptionPane.showMessageDialog(null, "Data delete!");
			flagChoseAuthor=false;
			}catch(SQLException ex){
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "incorrect value! Click in ID Column");	
			} finally{
				if (conn!=null){
					try{
						conn.close();
						stmt.close();
					}catch(Exception ex){
						System.out.println(ex.getMessage());	
				
					}
				}
			}

			}
			break;

		 case JOptionPane.NO_OPTION:
			 JOptionPane.showMessageDialog(null, "I thought so..."); break;
		default:
			break;
		}
		}else{
			JOptionPane.showMessageDialog(null, "Choose Row in Table!");
		}
	}
	
	public void addScientificInstitutions() {
		Connection conn = null;
		Statement stmt =null;
		try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp","root","7525800");
		stmt=conn.createStatement();		
		stmt.execute("Insert into `sdp`.`Scientific_institutions`(`name`,`adress`,`telephone`,`url`) "
				+ "Value('"+edtNameScientifiInstitutions.getText()+"','"+edtAdressScientificInstitutions.getText()+"','"+edtTelephoneScientificInstitutions.getText()+"', '"+edtURLScientificInstitutions.getText()+"')");		
		refresTables();
		edtNameScientifiInstitutions.setText("");
		edtAdressScientificInstitutions.setText("");
		edtTelephoneScientificInstitutions.setText("");
		edtURLScientificInstitutions.setText("");
		tltPaneAddScientificInstitutions.setPrefHeight(17);
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "incorrect value!!! Perhaps this value does not exist");	
		} finally{
			if (conn!=null){
				try{
					conn.close();
					stmt.close();
				}catch(Exception ex){
					System.out.println(ex.getMessage());	
			
				}
			}
		}	
	}
	
	public void addInstitutions() {
		Connection conn = null;
		Statement stmt =null;
		try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp","root","7525800");
		stmt=conn.createStatement();		
		stmt.execute("Insert into `sdp`.`Institutions`(`id_scientific_institutions`,`name`,`adress`,`telephone`,`url`) "
				+ "Value('"+edtIDScientificInstitutions.getText()+"','"+edtNameInstitutions.getText()+"','"+edtAdressInstitutions.getText()+"', '"+edtTelephoneInstitutions.getText()+"', '"+edtURLInstitutions.getText()+"')");		
		refresTables();
		edtIDScientificInstitutions.setText("");
		edtNameInstitutions.setText("");
		edtAdressInstitutions.setText("");
		edtTelephoneInstitutions.setText("");
		edtURLInstitutions.setText("");
		tltPaneAddInstitutions.setPrefHeight(17);
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "incorrect value!!! Perhaps this value does not exist");	
		} finally{
			if (conn!=null){
				try{
					conn.close();
					stmt.close();
				}catch(Exception ex){
					System.out.println(ex.getMessage());	
			
				}
			}
		}		
	}
	
	public void addFaculties() {
		Connection conn = null;
		Statement stmt =null;
		try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp","root","7525800");
		stmt=conn.createStatement();		
		stmt.execute("Insert into `sdp`.`Faculties`(`id_institutions`,`name`,`telephone`,`url`) "
				+ "Value('"+edtIDInstitutions.getText()+"','"+edtNameFaculties.getText()+"','"+edtTelephoneFaculties.getText()+"', '"+edtURLFaculties.getText()+"')");		
		refresTables();
		edtIDInstitutions.setText("");
		edtNameFaculties.setText("");
		edtTelephoneFaculties.setText("");
		edtURLFaculties.setText("");
		tltPaneAddFaculties.setPrefHeight(17);
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "incorrect value!!! Perhaps this value does not exist");	
		} finally{
			if (conn!=null){
				try{
					conn.close();
					stmt.close();
				}catch(Exception ex){
					System.out.println(ex.getMessage());	
			
				}
			}
		}	
	}
	
	public void addCathedra() {
		Connection conn = null;
		Statement stmt =null;
		try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp","root","7525800");
		stmt=conn.createStatement();		
		stmt.execute("Insert into `sdp`.`Cathedra`(`id_faculties`,`name`,`telephone`,`url`) "
				+ "Value('"+edtIDFaculties.getText()+"','"+edtNameCathedra.getText()+"','"+edtTelephoneCathedra.getText()+"', '"+edtURLCathedra.getText()+"')");		
		refresTables();
		edtIDFaculties.setText("");
		edtNameCathedra.setText("");
		edtTelephoneCathedra.setText("");
		edtURLCathedra.setText("");
		tltPaneAddCathedra.setPrefHeight(17);
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "incorrect value!!! Perhaps this value does not exist");	
		} finally{
			if (conn!=null){
				try{
					conn.close();
					stmt.close();
				}catch(Exception ex){
					System.out.println(ex.getMessage());	
			
				}
			}
		}	
	}
	
	public void searchScientificInstitutions(){
		Connection conn = null;
		Statement stmt =null;
		ResultSet rs =null;
		try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp","root","7525800");
		stmt=conn.createStatement();
		rs=stmt.executeQuery("Select * From `Scientific_institutions` s "
						+ "Where CONVERT(`s`.id_scientific_institutions USING 'utf8') LIKE '%"+edtSearchScietificInstitutions.getText()+"%' "
						+ "or CONVERT(`s`.name USING 'utf8') LIKE '%"+edtSearchScietificInstitutions.getText()+"%' "
						+ "or CONVERT(`s`.adress USING 'utf8') LIKE '%"+edtSearchScietificInstitutions.getText()+"%' "
						+ "or CONVERT(`s`.url USING 'utf8') LIKE '%"+edtSearchScietificInstitutions.getText()+"%' "
						+ "or CONVERT(`s`.telephone USING 'utf8') LIKE '%"+edtSearchScietificInstitutions.getText()+"%'");			
		scientificInstitutionsData.clear();
		while(rs.next()){	
			scientificInstitutionsData.add(new ScientificInstitutions(rs.getInt("id_scientific_institutions"), rs.getString("name"), rs.getString("adress"), rs.getString("telephone"), rs.getString("url")));
			}
				tblScientificInstitution.refresh();		
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		} finally{
			if (conn!=null){
				try{
					conn.close();
					stmt.close();
					rs.close();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());			}
			}
		}
	}
	
	public void searchInstitutions(){
		Connection conn = null;
		Statement stmt =null;
		ResultSet rs =null;
		try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp","root","7525800");
		stmt=conn.createStatement();
		if(!edtSearchInstitutions.equals("")){
		rs=stmt.executeQuery("Select i.*,s.name AS name_scientific_institutions  "
				+ "From `Institutions` i, `Scientific_institutions` s "
				+ "Where `i`.id_scientific_institutions=`s`.id_scientific_institutions "
				+ "and (i.id_scientific_institutions IN (Select `Scientific_institutions`.id_scientific_institutions From `Scientific_institutions` Where CONVERT(`Scientific_institutions`.name USING 'utf8') LIKE '%"+edtSearchInstitutions.getText()+"%') "
				+ "or CONVERT(`i`.id_institutions USING 'utf8') LIKE '%"+edtSearchInstitutions.getText()+"%' "				
				+ "or CONVERT(`i`.name USING 'utf8') LIKE '%"+edtSearchInstitutions.getText()+"%' "	
				+ "or CONVERT(`i`.adress USING 'utf8') LIKE '%"+edtSearchInstitutions.getText()+"%' "
				+ "or CONVERT(`i`.telephone USING 'utf8') LIKE '%"+edtSearchInstitutions.getText()+"%' "	
				+ "or CONVERT(`i`.url USING 'utf8') LIKE '%"+edtSearchInstitutions.getText()+"%')");	
		}else{
			rs=stmt.executeQuery("Select s.*,i.name AS name_institutions  "
					+ "From `Institutions` i, `Scientific_institutions` s "
					+ "Where `i`.id_scientific_institutions=`s`.id_scientific_institutions ");
				}
		
		institutionsData.clear();
		while(rs.next()){	
			institutionsData.add(new Institutions(rs.getInt("i.id_institutions"), rs.getInt("i.id_scientific_institutions"), rs.getString("name_scientific_institutions"), rs.getString("i.name"), rs.getString("i.adress"), rs.getString("i.telephone"), rs.getString("i.url")));
			}
				tblInstitutions.refresh();		
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		} finally{
			if (conn!=null){
				try{
					conn.close();
					stmt.close();
					rs.close();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());			}
			}
		}
	}
	
	public void searchFaculties(){
		Connection conn = null;
		Statement stmt =null;
		ResultSet rs =null;
		try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp","root","7525800");
		stmt=conn.createStatement();
		if(!edtSearchFaculties.equals("")){
		rs=stmt.executeQuery("Select f.*,i.name AS name_institutions  "
				+ "From `Institutions` i, `Faculties` f "
				+ "Where `i`.id_institutions=`f`.id_institutions "
				+ "and (f.id_institutions IN (Select `Institutions`.id_institutions From `Institutions` Where CONVERT(`Institutions`.name USING 'utf8') LIKE '%"+edtSearchFaculties.getText()+"%') "
				+ "or CONVERT(`f`.id_faculties USING 'utf8') LIKE '%"+edtSearchFaculties.getText()+"%' "				
				+ "or CONVERT(`f`.name USING 'utf8') LIKE '%"+edtSearchFaculties.getText()+"%' "	
				+ "or CONVERT(`f`.telephone USING 'utf8') LIKE '%"+edtSearchFaculties.getText()+"%' "	
				+ "or CONVERT(`f`.url USING 'utf8') LIKE '%"+edtSearchFaculties.getText()+"%')");	
		}else{
			rs=stmt.executeQuery("Select f.*,i.name AS name_institutions  "
					+ "From `Institutions` i, `Faculties` f "
					+ "Where `i`.id_institutions=`f`.id_institutions ");
				}
		
		facultiesData.clear();
		while(rs.next()){	
			facultiesData.add(new Faculties(rs.getInt("f.id_faculties"), rs.getInt("f.id_institutions"), rs.getString("name_institutions"), rs.getString("f.name"), rs.getString("f.telephone"), rs.getString("f.url")));
			}
				tblFaculties.refresh();		
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		} finally{
			if (conn!=null){
				try{
					conn.close();
					stmt.close();
					rs.close();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());			}
			}
		}
	}
	
	public void searchCathedra(){
		Connection conn = null;
		Statement stmt =null;
		ResultSet rs =null;
		try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp","root","7525800");
		stmt=conn.createStatement();
		if(!edtSearchCathedra.equals("")){
		rs=stmt.executeQuery("Select c.*,f.name AS name_faculties  "
				+ "From `Cathedra` c, `Faculties` f "
				+ "Where `c`.id_faculties=`f`.id_faculties "
				+ "and (c.id_faculties IN (Select `Faculties`.id_faculties From `Faculties` Where CONVERT(`Faculties`.name USING 'utf8') LIKE '%"+edtSearchCathedra.getText()+"%') "
				+ "or CONVERT(`c`.id_cathedra USING 'utf8') LIKE '%"+edtSearchCathedra.getText()+"%' "				
				+ "or CONVERT(`c`.name USING 'utf8') LIKE '%"+edtSearchCathedra.getText()+"%' "	
				+ "or CONVERT(`c`.telephone USING 'utf8') LIKE '%"+edtSearchCathedra.getText()+"%' "	
				+ "or CONVERT(`c`.url USING 'utf8') LIKE '%"+edtSearchCathedra.getText()+"%')");	
		}else{
			rs=stmt.executeQuery("Select c.*,f.name AS name_faculties  "
					+ "From `Cathedra` c, `Faculties` f "
					+ "Where `c`.id_faculties=`f`.id_faculties ");
		}
		
		cathedraData.clear();
		while(rs.next()){	
			cathedraData.add(new Cathedra(rs.getInt("c.id_cathedra"), rs.getInt("c.id_faculties"), rs.getString("name_faculties"), rs.getString("c.name"), rs.getString("c.telephone"), rs.getString("c.url")));
			}
				tblCathedra.refresh();		
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		} finally{
			if (conn!=null){
				try{
					conn.close();
					stmt.close();
					rs.close();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());			}
			}
		}
	}
	
	public void refresTables() throws SQLException{
		scientificInstitutionsData.clear();
		institutionsData.clear();
		facultiesData.clear();
		cathedraData.clear();
		connectToDB();		
		
		id_scientific_institutions.setCellValueFactory((new PropertyValueFactory<ScientificInstitutions, Integer>("id_scientific_institutions")));
		name.setCellValueFactory((new PropertyValueFactory<ScientificInstitutions, String>("name")));
		adress.setCellValueFactory((new PropertyValueFactory<ScientificInstitutions, String>("adress")));
		telephone.setCellValueFactory((new PropertyValueFactory<ScientificInstitutions, String>("telephone")));
		url.setCellValueFactory((new PropertyValueFactory<ScientificInstitutions, String>("url")));
	
		id_institutions.setCellValueFactory((new PropertyValueFactory<Institutions, Integer>("id_institutions")));
		id_scientific_institutions1.setCellValueFactory((new PropertyValueFactory<Institutions, Integer>("id_scientific_institutions")));
		name_scientific_institutions.setCellValueFactory((new PropertyValueFactory<Institutions, String>("name_scientific_institutions")));
		name1.setCellValueFactory((new PropertyValueFactory<Institutions, String>("name")));
		adress1.setCellValueFactory((new PropertyValueFactory<Institutions, String>("adress")));
		telephone1.setCellValueFactory((new PropertyValueFactory<Institutions, String>("telephone")));
		url1.setCellValueFactory((new PropertyValueFactory<Institutions, String>("url")));

		id_faculties.setCellValueFactory((new PropertyValueFactory<Faculties, Integer>("id_faculties")));
		id_institutions1.setCellValueFactory((new PropertyValueFactory<Faculties, Integer>("id_institutions")));
		name_institutions.setCellValueFactory((new PropertyValueFactory<Faculties, String>("name_institutions")));
		name2.setCellValueFactory((new PropertyValueFactory<Faculties, String>("name")));
		telephone2.setCellValueFactory((new PropertyValueFactory<Faculties, String>("telephone")));
		url2.setCellValueFactory((new PropertyValueFactory<Faculties, String>("url")));
		
		id_cathedra.setCellValueFactory((new PropertyValueFactory<Cathedra, Integer>("id_cathedra")));
		id_faculties1.setCellValueFactory((new PropertyValueFactory<Cathedra, Integer>("id_faculties")));
		name_faculties.setCellValueFactory((new PropertyValueFactory<Cathedra, String>("name_faculties")));
		name3.setCellValueFactory((new PropertyValueFactory<Cathedra, String>("name")));
		telephone3.setCellValueFactory((new PropertyValueFactory<Cathedra, String>("telephone")));
		url3.setCellValueFactory((new PropertyValueFactory<Cathedra, String>("url")));
		
		tblScientificInstitution.setItems(scientificInstitutionsData);
		tblInstitutions.setItems(institutionsData);
		tblFaculties.setItems(facultiesData);
		tblCathedra.setItems(cathedraData);

		
	}
	public void changeLanguageUA() throws IOException{
		JOptionPane.showMessageDialog(null, "This is demo-version");
	}

	public void changeLanguageUK(){
		JOptionPane.showMessageDialog(null, "This is demo-version");
	}

	public void changeLanguageRU(){
		JOptionPane.showMessageDialog(null, "This is demo-version");
	}
	
	public void showHelpWindow(){
	
	}
}
