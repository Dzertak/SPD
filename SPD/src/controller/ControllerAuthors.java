package controller;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import application.ScientificInstitution;
import entities.Authors;
import entities.EmploymentHistory;
import entities.Identifiers;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
//import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;
import jxl.Workbook;
import jxl.biff.FontRecord;
import jxl.format.RGB;
import jxl.write.Colour;
import jxl.write.Font;
//import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class ControllerAuthors implements Initializable{

	public Object idAuthorTableValue, idAuthorTableField;
	public boolean flagChoseAuthor=false;
	public String tabTableName = "Authors";
	
	
	private static ObservableList<Authors> authorsData = FXCollections.observableArrayList();
	private static ObservableList<Identifiers> identifiersData = FXCollections.observableArrayList();
	private static ObservableList<EmploymentHistory> employmentHistoryData = FXCollections.observableArrayList();
	private static ObservableList<Authors> authorsForViewData = FXCollections.observableArrayList();
	private static ObservableList<Identifiers> identifiersForViewData = FXCollections.observableArrayList();
	private static ObservableList<EmploymentHistory> employmentHistoryForViewData = FXCollections.observableArrayList();
	
	@FXML private ImageView btnSearchForView, btnRefresh;
	@FXML private TextField edtSearchForView = new TextField("");
	@FXML private TableView tblIdentifiersForView,tblAuthorsForView,tblEmploymentHistoryForView;

	
	@FXML private AnchorPane leftAnchor,authorsWindow,topAnchor,anchorAddAuthors;
	@FXML private TabPane tabAuthor;
	@FXML private Tab tabAuthors,tabIdentifiers,tabEmployment_History;
	@FXML private ToolBar authorsToolBar;
	@FXML private Button btnShowScientificInstitution;
	
	//search 
	@FXML private TextField edtSearchAuthor,edtSearchIdentifiers,edtSearchEmploymentHistory;
	@FXML private ImageView btnSearchAuthors,btnSearchIdentifiers,btnSearchEmploymentHistory;
	//export 
	@FXML private Button btnExportExcelAuthors,btnExportExcelEmploymentHistory,btnExportExcelIdentifiers;	
	//delete 
	@FXML private Button btnDeleteAuthor,btnDeleteIdentifiers,btnDeleteEmploymentHiostory;
	//add 
	@FXML private TitledPane tltPaneAddAuthor,tltPaneAddIdentifier,tltPaneAddEmploymentHistory;
	@FXML private Button btnAddAuthor,btnAddIdentifier,btnAddEmploymentHistory;
	@FXML private TextField edtIdentifiers, edtFullNameEn = new TextField(""), edtFullNameRu= new TextField(""), edtFullNameUa= new TextField(""), edtScientificDegree = new TextField(""), edtDateOfBirth= new TextField(""), edtEmail= new TextField(""), edtTelephone= new TextField("");
	@FXML private TextField edtIdentifiers1, edtScousID = new TextField(""),edtOrcID = new TextField(""),edtResearcherID = new TextField("");
	@FXML private TextField edtIDAuthor,edtIDScientificInstitution,edtIDCathedra= new TextField(""),edtBeginningOfPeriod= new TextField(""),edtEndingOfPeriod= new TextField("");
	
	//Table for Authors
	@FXML private TableView tblAuthors,tblIdentifiers,tblEmploymentHistory;
	@FXML private TableColumn<Authors, Integer> id_authors;
	@FXML private TableColumn<Authors, Integer> id_identifiers;
	@FXML private TableColumn<Authors, String> full_name_en;
	@FXML private TableColumn<Authors, String> full_name_ru;
	@FXML private TableColumn<Authors, String> full_name_ua;
	@FXML private TableColumn<Authors, String> scientific_degree;
	@FXML private TableColumn<Authors, Date> date_of_birth;
	@FXML private TableColumn<Authors, String> email;
	@FXML private TableColumn<Authors, String> telephone;
	//Table for View
	@FXML private TableColumn<Authors, Integer> id_authors2;
	@FXML private TableColumn<Authors, Integer> id_identifiers2;
	@FXML private TableColumn<Authors, String> full_name_en1;
	@FXML private TableColumn<Authors, String> full_name_ru1;
	@FXML private TableColumn<Authors, String> full_name_ua1;
	@FXML private TableColumn<Authors, String> scientific_degree1;
	@FXML private TableColumn<Authors, Date> date_of_birth1;
	@FXML private TableColumn<Authors, String> email1;
	@FXML private TableColumn<Authors, String> telephone1;
	
	@FXML private TableColumn<Identifiers, Integer> id_identifiers1;
	@FXML private TableColumn<Identifiers, String> scopusid;
	@FXML private TableColumn<Identifiers, String> orcid;
	@FXML private TableColumn<Identifiers, String> researcherid;
	//Table for View
	@FXML private TableColumn<Identifiers, Integer> id_identifiers3;
	@FXML private TableColumn<Identifiers, String> scopusid1;
	@FXML private TableColumn<Identifiers, String> orcid1;
	@FXML private TableColumn<Identifiers, String> researcherid1;
	
	@FXML private TableColumn<EmploymentHistory, Integer>id_employment_history;
	@FXML private TableColumn<EmploymentHistory, Integer>id_authors1;
	@FXML private TableColumn<EmploymentHistory, Integer>id_cathedra;
	@FXML private TableColumn<EmploymentHistory, Integer>id_scientific_institutions;
	@FXML private TableColumn<EmploymentHistory, String> name_authors;
	@FXML private TableColumn<EmploymentHistory, String> name_scientific_institutions;
	@FXML private TableColumn<EmploymentHistory, String> name_cathedra;
	@FXML private TableColumn<EmploymentHistory, String> beginning_of_period;
	@FXML private TableColumn<EmploymentHistory, String> ending_of_period;
	//Table for View
	@FXML private TableColumn<EmploymentHistory, Integer>id_employment_history1;
	@FXML private TableColumn<EmploymentHistory, Integer>id_authors3;
	@FXML private TableColumn<EmploymentHistory, Integer>id_cathedra1;
	@FXML private TableColumn<EmploymentHistory, Integer>id_scientific_institutions1;
	@FXML private TableColumn<EmploymentHistory, String> name_authors1;
	@FXML private TableColumn<EmploymentHistory, String> name_scientific_institutions1;
	@FXML private TableColumn<EmploymentHistory, String> name_cathedra1;
	@FXML private TableColumn<EmploymentHistory, String> beginning_of_period1;
	@FXML private TableColumn<EmploymentHistory, String> ending_of_period1;
	
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
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		authorsWindow.setStyle("-fx-background-color: white");
		leftAnchor.setStyle("-fx-background-color: rgba(52,132,191,1);");
		topAnchor.setStyle("-fx-background-color: rgba(52,132,191,1);");
		tabAuthor.setStyle("-fx-background-color: white");
		tblAuthors.setStyle(" -fx-base: white; -fx-control-inner-background: white; -fx-table-cell-border-color: rgba(52,132,191,1); -fx-table-header-border-color: rgba(52,132,191,1); -fx-padding: 2;");
		tblIdentifiers.setStyle(" -fx-base: white; -fx-control-inner-background: white; -fx-table-cell-border-color: rgba(52,132,191,1); -fx-table-header-border-color: rgba(52,132,191,1); -fx-padding: 2;");
		tblEmploymentHistory.setStyle(" -fx-base: white; -fx-control-inner-background: white; -fx-table-cell-border-color: rgba(52,132,191,1); -fx-table-header-border-color: rgba(52,132,191,1); -fx-padding: 2;");
		Image imageInst = new Image(getClass().getResourceAsStream("/fxml/image/inst.png"));
		btnShowScientificInstitution.setGraphic(new ImageView(imageInst));
		Image imageDelete = new Image(getClass().getResourceAsStream("/fxml/image/delete author3.png"));
		btnDeleteAuthor.setGraphic(new ImageView(imageDelete));
		btnDeleteAuthor.setText("Delete");
		btnDeleteIdentifiers.setGraphic(new ImageView(imageDelete));
		btnDeleteIdentifiers.setText("Delete");
		btnDeleteEmploymentHiostory.setGraphic(new ImageView(imageDelete));
		btnDeleteEmploymentHiostory.setText("Delete");
		Image imageExcel = new Image(getClass().getResourceAsStream("/fxml/image/excel.png"));
		btnExportExcelAuthors.setGraphic(new ImageView(imageExcel));
		btnExportExcelAuthors.setText("Export");
		btnExportExcelIdentifiers.setGraphic(new ImageView(imageExcel));
		btnExportExcelIdentifiers.setText("Export");
		btnExportExcelEmploymentHistory.setGraphic(new ImageView(imageExcel));
		btnExportExcelEmploymentHistory.setText("Export");
		tblAuthorsForView.setPlaceholder(new Label("                 Hello!\n Use the search form at the top!"));
		
		btnSearchAuthors.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				search(edtSearchAuthor.getText());
			}
		});
		btnSearchIdentifiers.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				search(edtSearchIdentifiers.getText());
			}
		});
		btnSearchEmploymentHistory.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				searchEmploymentHistory(edtSearchEmploymentHistory.getText());
			}
		});
		
		tltPaneAddAuthor.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if(tltPaneAddAuthor.isExpanded()){
					tltPaneAddAuthor.setPrefHeight(295);
				}else{
					tltPaneAddAuthor.setPrefHeight(17);
				}
			}
		});
		
		tltPaneAddIdentifier.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if(tltPaneAddIdentifier.isExpanded()){
					tltPaneAddIdentifier.setPrefHeight(259);
				}else{
					tltPaneAddIdentifier.setPrefHeight(17);
				}
			}
		});
		
		tltPaneAddEmploymentHistory.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if(tltPaneAddEmploymentHistory.isExpanded()){
					tltPaneAddEmploymentHistory.setPrefHeight(295);
				}else{
					tltPaneAddEmploymentHistory.setPrefHeight(17);
				}
			}
		});
		
		tblAuthors.setOnMouseClicked(new EventHandler<Event>() {			
			@Override
			public void handle(Event event) {
				try{
				TablePosition pos = (TablePosition) tblAuthors.getSelectionModel().getSelectedCells().get(0);
				int row = pos.getRow();
				TableColumn col = pos.getTableColumn();
				// this gives the value in the selected cell:
				idAuthorTableValue = col.getCellObservableValue(tblAuthors.getItems().get(row)).getValue();
				
				idAuthorTableField = col.getId();
				flagChoseAuthor = true;
			}catch(Exception e){
			}
			}
		});
		tblIdentifiers.setOnMouseClicked(new EventHandler<Event>() {			
			@Override
			public void handle(Event event) {
				try{
				TablePosition pos = (TablePosition) tblIdentifiers.getSelectionModel().getSelectedCells().get(0);
				int row = pos.getRow();
				TableColumn col = pos.getTableColumn();
				// this gives the value in the selected cell:
				idAuthorTableValue = col.getCellObservableValue(tblIdentifiers.getItems().get(row)).getValue();
				if(col.getId().equals("id_identifiers1")){
					idAuthorTableField = "id_identifiers";
				}else{
				idAuthorTableField = col.getId();
				}
				flagChoseAuthor = true;
			}catch(Exception e){
			}
			}
		});
		tblEmploymentHistory.setOnMouseClicked(new EventHandler<Event>() {			
			@Override
			public void handle(Event event) {
				try{
				TablePosition pos = (TablePosition) tblEmploymentHistory.getSelectionModel().getSelectedCells().get(0);
				int row = pos.getRow();
				TableColumn col = pos.getTableColumn();
				// this gives the value in the selected cell:
				idAuthorTableValue = col.getCellObservableValue(tblEmploymentHistory.getItems().get(row)).getValue();
				if(col.getId().equals("id_authors1")){
					idAuthorTableField = "id_authors";
				}else{
				idAuthorTableField = col.getId();
				}
				flagChoseAuthor = true;
			}catch(Exception e){
			}
			}
		});
		
		tabAuthors.setOnSelectionChanged(new EventHandler<Event>() {			
			@Override
			public void handle(Event event) {
				tabTableName = tabAuthors.getId().substring(3);
				}
		});
		tabIdentifiers.setOnSelectionChanged(new EventHandler<Event>() {			
			@Override
			public void handle(Event event) {
				tabTableName = tabIdentifiers.getId().substring(3);
				}
		});
		tabEmployment_History.setOnSelectionChanged(new EventHandler<Event>() {			
			@Override
			public void handle(Event event) {
				tabTableName = tabEmployment_History.getId().substring(3);
				}
		});
	
		id_identifiers.setEditable(true);
		id_identifiers.setCellValueFactory(new Callback<CellDataFeatures<Authors, Integer>, ObservableValue<Integer>>(){
			 @Override
			    public ObservableValue<Integer> call(CellDataFeatures<Authors, Integer> p) {
			        return (ObservableValue) new SimpleIntegerProperty(p.getValue().getId_identifiers());
			} 
			});
		id_identifiers.setCellFactory(TextFieldTableCell.<Authors, Integer>forTableColumn(new IntegerStringConverter()));
		id_identifiers.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Authors,Integer>>(){
		public void handle(CellEditEvent<Authors, Integer> t) {
			if(fieldEditAuthors(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_authors(),tabTableName,"id_identifiers", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_identifiers()), String.valueOf(t.getNewValue()))){		
				((Authors) t.getTableView().getItems().get(t.getTablePosition().getRow())).setId_identifiers(t.getNewValue());
			} tblAuthors.refresh(); }});
		
		full_name_en.setEditable(true);
		full_name_en.setCellFactory(TextFieldTableCell.forTableColumn());
		full_name_en.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Authors,String>>() {		
			@Override
			public void handle(CellEditEvent<Authors, String> t) {
				fieldEditAuthors(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_authors(),tabTableName,"full_name_en", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getFull_name_en()), String.valueOf(t.getNewValue()));
                ((Authors) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFull_name_en(t.getNewValue());}});
		
		full_name_ru.setEditable(true);
		full_name_ru.setCellFactory(TextFieldTableCell.forTableColumn());
		full_name_ru.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Authors,String>>() {		
			@Override
			public void handle(CellEditEvent<Authors, String> t) {
				fieldEditAuthors(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_authors(),tabTableName,"full_name_ru", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getFull_name_ru()), String.valueOf(t.getNewValue()));
                ((Authors) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFull_name_ru(t.getNewValue());}});
		
		full_name_ua.setEditable(true);
		full_name_ua.setCellFactory(TextFieldTableCell.forTableColumn());
		full_name_ua.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Authors,String>>() {		
			@Override
			public void handle(CellEditEvent<Authors, String> t) {
				fieldEditAuthors(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_authors(),tabTableName,"full_name_ua", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getFull_name_ua()), String.valueOf(t.getNewValue()));
                ((Authors) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFull_name_ua(t.getNewValue());}});
		
		scientific_degree.setEditable(true);
		scientific_degree.setCellFactory(TextFieldTableCell.forTableColumn());
		scientific_degree.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Authors,String>>() {		
			@Override
			public void handle(CellEditEvent<Authors, String> t) {
				fieldEditAuthors(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_authors(),tabTableName,"scientific_degree", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getScientific_degree()), String.valueOf(t.getNewValue()));
                ((Authors) t.getTableView().getItems().get(t.getTablePosition().getRow())).setScientific_degree(t.getNewValue());}});
		

		date_of_birth.setEditable(true);
		/*date_of_birth.setCellValueFactory(new Callback<CellDataFeatures<Authors, Date>, ObservableValue<Date>>(){
			 @Override
			    public ObservableValue<Date> call(CellDataFeatures<Authors, Date> p) {
			        return (ObservableValue) new SimpleStringProperty(p.getValue().getDate_of_birth().toString());
			} 
			});*/
		date_of_birth.setCellFactory(TextFieldTableCell.<Authors, Date>forTableColumn(new DateStringConverter()));
		date_of_birth.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Authors,Date>>(){
		public void handle(CellEditEvent<Authors, Date> t) {
			
			if(fieldEditDateAuthors(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_authors(),"date_of_birth", t.getTableView().getItems().get(t.getTablePosition().getRow()).getDate_of_birth(), t.getNewValue())){		
				((Authors) t.getTableView().getItems().get(t.getTablePosition().getRow())).setDate_of_birth(t.getNewValue());
			} 
			tblAuthors.refresh(); }});

		email.setEditable(true);
		email.setCellFactory(TextFieldTableCell.forTableColumn());
		email.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Authors,String>>() {		
			@Override
			public void handle(CellEditEvent<Authors, String> t) {
				fieldEditAuthors(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_authors(),tabTableName,"email", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getEmail()), String.valueOf(t.getNewValue()));
                ((Authors) t.getTableView().getItems().get(t.getTablePosition().getRow())).setEmail(t.getNewValue());}});
		
		telephone.setEditable(true);
		telephone.setCellFactory(TextFieldTableCell.forTableColumn());
		telephone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Authors,String>>() {		
			@Override
			public void handle(CellEditEvent<Authors, String> t) {
				fieldEditAuthors(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_authors(),tabTableName,"telephone", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getTelephone()), String.valueOf(t.getNewValue()));
                ((Authors) t.getTableView().getItems().get(t.getTablePosition().getRow())).setTelephone(t.getNewValue());}});
	
		id_identifiers1.setEditable(true);
		id_identifiers1.setCellValueFactory(new Callback<CellDataFeatures<Identifiers, Integer>, ObservableValue<Integer>>(){
			 @Override
			    public ObservableValue<Integer> call(CellDataFeatures<Identifiers, Integer> p) {
			        return (ObservableValue) new SimpleIntegerProperty(p.getValue().getId_identifiers());
			} 
			});
		id_identifiers1.setCellFactory(TextFieldTableCell.<Identifiers, Integer>forTableColumn(new IntegerStringConverter()));
		id_identifiers1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Identifiers,Integer>>(){
		public void handle(CellEditEvent<Identifiers, Integer> t) {
			if(fieldEditIdentifiers(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_identifiers(),tabTableName,"id_identifiers", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_identifiers()), String.valueOf(t.getNewValue()))){		
				((Identifiers) t.getTableView().getItems().get(t.getTablePosition().getRow())).setId_identifiers(t.getNewValue());
			} tblIdentifiers.refresh(); }});
		
		scopusid.setEditable(true);
		scopusid.setCellFactory(TextFieldTableCell.forTableColumn());
		scopusid.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Identifiers,String>>() {		
			@Override
			public void handle(CellEditEvent<Identifiers, String> t) {
				fieldEditIdentifiers(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_identifiers(),tabTableName,"scopusid", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getScopusid()), String.valueOf(t.getNewValue()));
                ((Identifiers) t.getTableView().getItems().get(t.getTablePosition().getRow())).setScopusid(t.getNewValue());}});
		orcid.setEditable(true);
		orcid.setCellFactory(TextFieldTableCell.forTableColumn());
		orcid.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Identifiers,String>>() {		
			@Override
			public void handle(CellEditEvent<Identifiers, String> t) {
				fieldEditIdentifiers(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_identifiers(),tabTableName,"orcid", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getOrcid()), String.valueOf(t.getNewValue()));
                ((Identifiers) t.getTableView().getItems().get(t.getTablePosition().getRow())).setOrcid(t.getNewValue());}});
	
		researcherid.setEditable(true);
		researcherid.setCellFactory(TextFieldTableCell.forTableColumn());
		researcherid.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Identifiers,String>>() {		
			@Override
			public void handle(CellEditEvent<Identifiers, String> t) {
				fieldEditIdentifiers(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_identifiers(),tabTableName,"researcherid", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getResearcherid()), String.valueOf(t.getNewValue()));
                ((Identifiers) t.getTableView().getItems().get(t.getTablePosition().getRow())).setResearcherid(t.getNewValue());}});
	
		
		id_authors1.setEditable(true);
		id_authors1.setCellValueFactory(new Callback<CellDataFeatures<EmploymentHistory, Integer>, ObservableValue<Integer>>(){
			 @Override
			    public ObservableValue<Integer> call(CellDataFeatures<EmploymentHistory, Integer> p) {
			        return (ObservableValue) new SimpleIntegerProperty(p.getValue().getId_authors());
			} 
			});
		id_authors1.setCellFactory(TextFieldTableCell.<EmploymentHistory, Integer>forTableColumn(new IntegerStringConverter()));
		id_authors1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<EmploymentHistory,Integer>>(){
		public void handle(CellEditEvent<EmploymentHistory, Integer> t) {
			if(fieldEditEmploymentHistory(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_employment_history(),tabTableName,"id_authors", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_authors()), String.valueOf(t.getNewValue()))){		
				((EmploymentHistory) t.getTableView().getItems().get(t.getTablePosition().getRow())).setId_authors(t.getNewValue());
			} tblEmploymentHistory.refresh(); }});
		
		id_scientific_institutions.setEditable(true);
		id_scientific_institutions.setCellValueFactory(new Callback<CellDataFeatures<EmploymentHistory, Integer>, ObservableValue<Integer>>(){
			 @Override
			    public ObservableValue<Integer> call(CellDataFeatures<EmploymentHistory, Integer> p) {
			        return (ObservableValue) new SimpleIntegerProperty(p.getValue().getId_authors());
			} 
			});
		id_scientific_institutions.setCellFactory(TextFieldTableCell.<EmploymentHistory, Integer>forTableColumn(new IntegerStringConverter()));
		id_scientific_institutions.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<EmploymentHistory,Integer>>(){
		public void handle(CellEditEvent<EmploymentHistory, Integer> t) {
			if(fieldEditEmploymentHistory(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_employment_history(),tabTableName,"id_scientific_institutions", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_scientific_institutions()), String.valueOf(t.getNewValue()))){		
				((EmploymentHistory) t.getTableView().getItems().get(t.getTablePosition().getRow())).setId_scientific_institutions(t.getNewValue());
			} tblEmploymentHistory.refresh(); }});
		
		id_cathedra.setEditable(true);
		id_cathedra.setCellValueFactory(new Callback<CellDataFeatures<EmploymentHistory, Integer>, ObservableValue<Integer>>(){
			 @Override
			    public ObservableValue<Integer> call(CellDataFeatures<EmploymentHistory, Integer> p) {
			        return (ObservableValue) new SimpleIntegerProperty(p.getValue().getId_authors());
			} 
			});
		id_cathedra.setCellFactory(TextFieldTableCell.<EmploymentHistory, Integer>forTableColumn(new IntegerStringConverter()));
		id_cathedra.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<EmploymentHistory,Integer>>(){
		public void handle(CellEditEvent<EmploymentHistory, Integer> t) {
			if(fieldEditEmploymentHistory(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_employment_history(),tabTableName,"id_cathedra", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_cathedra()), String.valueOf(t.getNewValue()))){		
				((EmploymentHistory) t.getTableView().getItems().get(t.getTablePosition().getRow())).setId_cathedra(t.getNewValue());
			} tblEmploymentHistory.refresh(); }});
		
		beginning_of_period.setEditable(true);
		beginning_of_period.setCellFactory(TextFieldTableCell.forTableColumn());
		beginning_of_period.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<EmploymentHistory,String>>() {		
			@Override
			public void handle(CellEditEvent<EmploymentHistory, String> t) {
				fieldEditEmploymentHistory(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_employment_history(),tabTableName,"beginning_of_period", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getBeginning_of_period()), String.valueOf(t.getNewValue()));
                ((EmploymentHistory) t.getTableView().getItems().get(t.getTablePosition().getRow())).setBeginning_of_period(t.getNewValue());}});
		ending_of_period.setEditable(true);
		ending_of_period.setCellFactory(TextFieldTableCell.forTableColumn());
		ending_of_period.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<EmploymentHistory,String>>() {		
			@Override
			public void handle(CellEditEvent<EmploymentHistory, String> t) {
				fieldEditEmploymentHistory(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId_employment_history(),tabTableName,"ending_of_period", String.valueOf(t.getTableView().getItems().get(t.getTablePosition().getRow()).getEnding_of_period()), String.valueOf(t.getNewValue()));
                ((EmploymentHistory) t.getTableView().getItems().get(t.getTablePosition().getRow())).setEnding_of_period(t.getNewValue());}});
	
		
		try {
			refresTables();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	
	public boolean fieldEditAuthors(int id, String table, String field, Object oldValue, Object newValue){
		boolean flag =true;
		Connection conn = null;
		Statement stmt =null;
		ResultSet rs =  null;
		try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp","root","7525800");
		stmt=conn.createStatement();
		stmt.execute("UPDATE `"+table+"` SET `"+field+"`='"+newValue+"' where id_authors='"+id+"'");		
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
	
	public boolean fieldEditIdentifiers(int id, String table, String field, Object oldValue, Object newValue){
		boolean flag =true;
		Connection conn = null;
		Statement stmt =null;
		ResultSet rs =  null;
		try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp","root","7525800");
		stmt=conn.createStatement();
		stmt.execute("UPDATE `"+table+"` SET `"+field+"`='"+newValue+"' where id_identifiers='"+id+"'");		
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
	
	public boolean fieldEditEmploymentHistory(int id, String table, String field, Object oldValue, Object newValue){
		boolean flag =true;
		Connection conn = null;
		Statement stmt =null;
		ResultSet rs =  null;
		try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp","root","7525800");
		stmt=conn.createStatement();
		stmt.execute("UPDATE `"+table+"` SET `"+field+"`='"+newValue+"' where id_employment_history='"+id+"'");		
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
	
	public boolean fieldEditDateAuthors(int id, String field, Date oldValue, Date newValue){
		boolean flag =true;
		Connection conn = null;
		Statement stmt =null;
		try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp","root","7525800");
		stmt=conn.createStatement();		
		//facking format date!!!
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		stmt.execute("UPDATE `sdp`.`Authors` SET "+field+"='"+format1.format(newValue.getTime())+"' where `id_authors`="+id);		
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
					System.out.println(ex.getMessage());	
			
				}
			}
		}

	}
	
	private String modifyDateLayout(String inputDate){
	    try {
	        inputDate = inputDate.replace(" UTC", "");
	        Date date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(inputDate);
	        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	    } catch (ParseException e) {
	        e.printStackTrace();
	        return "1999.12.12";
	    }
	}
	public void connectToDB() throws SQLException{
		Connection conn = null;
		Statement stmt =null;
		ResultSet rs =null;
		try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp","root","7525800");
		stmt=conn.createStatement();
		rs=stmt.executeQuery("Select * From Authors");
		while(rs.next()){		
			authorsData.add(new Authors(rs.getInt("id_authors"), rs.getInt("id_identifiers"), rs.getString("full_name_en"), rs.getString("full_name_ru"), rs.getString("full_name_ua"), rs.getString("scientific_degree"), rs.getDate("date_of_birth"), rs.getString("email"),rs.getString("telephone")));
		}
		rs=stmt.executeQuery("Select * From Identifiers");
		while(rs.next()){		
			identifiersData.add(new Identifiers(rs.getInt("id_identifiers"), rs.getString("scopusid"), rs.getString("orcid"), rs.getString("researcherid")));
		}
		
		rs=stmt.executeQuery("Select emp.*,a.full_name_en AS name_authors, s.name AS name_scientific_institutions, c.name AS name_cathedra  "
				+ "From `Authors` a, `Scientific_institutions` s, `Cathedra` c, `employment_history` emp "
				+ "Where `emp`.id_authors=`a`.id_authors and `emp`.id_scientific_institutions=`s`.id_scientific_institutions or `emp`.id_cathedra=`c`.id_cathedra");
		while(rs.next()){	
			employmentHistoryData.add(new EmploymentHistory(rs.getInt("emp.id_employment_history"),rs.getInt("emp.id_scientific_institutions"), rs.getInt("emp.id_cathedra"), rs.getInt("emp.id_authors"), rs.getString("emp.beginning_of_period"), rs.getString("emp.ending_of_period"), rs.getString("name_scientific_institutions"), rs.getString("name_cathedra"),rs.getString("name_authors")));
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
	
	//String id_identifiers, String full_name_en, String full_name_ru, String full_name_ua, String scientific_degree, Date date_of_birth, String email, String telephone
	
	public void addAuthors() {
		if(edtIdentifiers.equals("")){
			JOptionPane.showMessageDialog(null, "Input date in field for identifiers");
		}
		else{
		Connection conn = null;
		Statement stmt =null;
		try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp","root","7525800");
		stmt=conn.createStatement();		
		stmt.execute("Insert into `sdp`.`Authors`(`id_identifiers`,`full_name_en`,`full_name_ru`,`full_name_ua`,`scientific_degree`,`date_of_birth`,`email`,`telephone`) "
				+ "Value('"+edtIdentifiers.getText()+"','"+edtFullNameEn.getText()+"','"+edtFullNameRu.getText()+"', '"+edtFullNameUa.getText()+"','"+edtScientificDegree.getText()+"', '"+edtDateOfBirth.getText()+"', '"+edtEmail.getText()+"', '"+edtTelephone.getText()+"')");		
		refresTables();
		edtIdentifiers.setText("");
		edtFullNameEn.setText("");
		edtFullNameRu.setText("");
		edtFullNameUa.setText("");
		edtScientificDegree.setText("");
		edtDateOfBirth.setText("");
		edtEmail.setText("");
		edtTelephone.setText("");
		tltPaneAddAuthor.setPrefHeight(17);
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "incorrect value!!! maybe in Identifier or Date");	
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
	}
	
	public void addIdentifiers() {
		Connection conn = null;
		Statement stmt =null;
		try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp","root","7525800");
		stmt=conn.createStatement();		
		stmt.execute("Insert into `sdp`.`Identifiers`(`id_identifiers`,`scopusid`,`orcid`,`researcherid`) "
				+ "Value('"+edtIdentifiers1.getText()+"','"+edtScousID.getText()+"','"+edtOrcID.getText()+"', '"+edtResearcherID.getText()+"')");		
		refresTables();
		edtIdentifiers1.setText("");
		edtScousID.setText("");
		edtOrcID.setText("");
		edtResearcherID.setText("");
		tltPaneAddIdentifier.setPrefHeight(17);
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error!!! maybe duplicate values");	
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
	
	public void addEmploymentHistory() {
		Connection conn = null;
		Statement stmt =null;
		try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp","root","7525800");
		stmt=conn.createStatement();		
		stmt.execute("Insert into `sdp`.`Employment_history`(`id_authors`,`id_scientific_institutions`,`id_cathedra`,`beginning_of_period`,`ending_of_period`) "
				+ "Value('"+edtIDAuthor.getText()+"','"+edtIDScientificInstitution.getText()+"','"+edtIDCathedra.getText()+"', '"+edtBeginningOfPeriod.getText()+"', '"+edtEndingOfPeriod.getText()+"')");		
		refresTables();
		edtIDAuthor.setText("");
		edtIDScientificInstitution.setText("");
		edtIDCathedra.setText("");
		edtBeginningOfPeriod.setText("");
		edtEndingOfPeriod.setText("");
		tltPaneAddEmploymentHistory.setPrefHeight(17);
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
	
	public void search(String edtSearch){
		Connection conn = null;
		Statement stmt =null;
		ResultSet rs =null;
		try{
		ArrayList<Object> listColumn = new ArrayList<>();
		String sqlString = "Select * from `"+tabTableName+"` where ";
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp","root","7525800");
		stmt=conn.createStatement();
		rs=stmt.executeQuery("SHOW COLUMNS FROM `"+tabTableName+"`");
		int i=0;
		while(rs.next()){	
			if(i==0){
				sqlString = sqlString.concat(" CONVERT(`"+rs.getObject(1)+"` USING 'utf8') LIKE '%"+edtSearch+"%'");
				i++;
			}else{
				sqlString = sqlString.concat(" or CONVERT(`"+rs.getObject(1)+"` USING 'utf8') LIKE '%"+edtSearch+"%'");
			}
				
		}
		rs=stmt.executeQuery(sqlString);
		
		if(tabTableName.equals("Authors")){		
			authorsData.clear();
			while(rs.next()){
			authorsData.add(new Authors(rs.getInt("id_authors"), rs.getInt("id_identifiers"), rs.getString("full_name_en"), rs.getString("full_name_ru"), rs.getString("full_name_ua"), rs.getString("scientific_degree"), rs.getDate("date_of_birth"), rs.getString("email"),rs.getString("telephone")));
			}
			tblAuthors.refresh();
		}
		
		if(tabTableName.equals("Identifiers")){
			identifiersData.clear();
			while(rs.next()){
			identifiersData.add(new Identifiers(rs.getInt("id_identifiers"), rs.getString("scopusid"), rs.getString("orcid"), rs.getString("researcherid")));

			}
			tblIdentifiers.refresh();
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
	
	public void searchEmploymentHistory(String edtSearch){
		Connection conn = null;
		Statement stmt =null;
		ResultSet rs =null;
		try{
		ArrayList<Object> listColumn = new ArrayList<>();
		String sqlString = "Select * from `Employment_history` where ";
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp","root","7525800");
		stmt=conn.createStatement();
		if(!edtSearch.equals("")){
		rs=stmt.executeQuery("Select emp.*,a.full_name_en AS name_authors, s.name AS name_scientific_institutions, c.name AS name_cathedra  "
				+ "From `Authors` a, `Scientific_institutions` s, `Cathedra` c, `employment_history` emp "
				+ "Where `emp`.id_authors=`a`.id_authors and `emp`.id_scientific_institutions=`s`.id_scientific_institutions or `emp`.id_cathedra=`c`.id_cathedra "
				+ "and (emp.id_authors=(Select `authors`.id_authors From `Authors` Where CONVERT(`authors`.full_name_en USING 'utf8') LIKE '%"+edtSearch+"%') "
						+ "or `emp`.id_scientific_institutions=(Select `scientific_institutions`.id_scientific_institutions From `scientific_institutions` Where CONVERT(`scientific_institutions`.name USING 'utf8') LIKE '%"+edtSearch+"%') "
						+ "or `emp`.id_cathedra=(Select `cathedra`.id_cathedra From `cathedra` Where CONVERT(`cathedra`.name USING 'utf8') LIKE '%"+edtSearch+"%') "
						+ "or CONVERT(`emp`.beginning_of_period USING 'utf8') LIKE '%"+edtSearch+"%'"
						+ "or CONVERT(`emp`.ending_of_period USING 'utf8') LIKE '%"+edtSearch+"%')");	
		}else{
			rs=stmt.executeQuery("Select emp.*,a.full_name_en AS name_authors, s.name AS name_scientific_institutions, c.name AS name_cathedra  "
					+ "From `Authors` a, `Scientific_institutions` s, `Cathedra` c, `employment_history` emp "
					+ "Where `emp`.id_authors=`a`.id_authors and `emp`.id_scientific_institutions=`s`.id_scientific_institutions or `emp`.id_cathedra=`c`.id_cathedra ");
		}
		
		employmentHistoryData.clear();
		while(rs.next()){	
				employmentHistoryData.add(new EmploymentHistory(rs.getInt("emp.id_employment_history"),rs.getInt("emp.id_scientific_institutions"), rs.getInt("emp.id_cathedra"), rs.getInt("emp.id_authors"), rs.getString("emp.beginning_of_period"), rs.getString("emp.ending_of_period"), rs.getString("name_scientific_institutions"), rs.getString("name_cathedra"),rs.getString("name_authors")));
			}
				tblEmploymentHistory.refresh();		
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
	
	public void refresh(){
		authorsForViewData.clear();
		identifiersForViewData.clear();
		employmentHistoryForViewData.clear();
		tblAuthorsForView.refresh();
		tblIdentifiersForView.refresh();
		tblEmploymentHistoryForView.refresh();	
	}
	
	public void searchForView(){
		Connection conn = null;
		Statement stmt =null;
		ResultSet rs =null;
		try{
			authorsForViewData.clear();
			identifiersForViewData.clear();
			employmentHistoryForViewData.clear();
		
		String sqlString = "Select * from `"+tabTableName+"` where ";
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp","root","7525800");
		stmt=conn.createStatement();
		rs=stmt.executeQuery("Select distinct a.* "
					+ "From `Authors` a "
					+ "Where CONVERT(a.id_authors USING utf8) LIKE '%"+edtSearchForView.getText()+"%' "
					+ "or CONVERT(`a`.id_identifiers USING utf8) LIKE '%"+edtSearchForView.getText()+"%' "
					+ "or CONVERT(`a`.full_name_en USING utf8) LIKE '%"+edtSearchForView.getText()+"%' or "
					+ "CONVERT(`a`.full_name_ru USING utf8) LIKE '%"+edtSearchForView.getText()+"%' "
					+ "or CONVERT(`a`.full_name_ua USING utf8) LIKE '%"+edtSearchForView.getText()+"%' "
					+ "or CONVERT(`a`.scientific_degree USING utf8) LIKE '%"+edtSearchForView.getText()+"%' or "
					+ "CONVERT(`a`.date_of_birth USING utf8) LIKE '%"+edtSearchForView.getText()+"%' "
					+ "or CONVERT(`a`.email USING utf8) LIKE '%"+edtSearchForView.getText()+"%' "
					+ "or CONVERT(`a`.telephone USING utf8) LIKE '%"+edtSearchForView.getText()+"%' ");
	
		while(rs.next()){
			authorsForViewData.add(new Authors(rs.getInt("a.id_authors"), rs.getInt("a.id_identifiers"), rs.getString("a.full_name_en"), rs.getString("a.full_name_ru"), rs.getString("a.full_name_ua"), rs.getString("a.scientific_degree"), rs.getDate("a.date_of_birth"), rs.getString("a.email"),rs.getString("a.telephone")));
			}
		rs=stmt.executeQuery("Select * From `Identifiers` i "
				+ "Where CONVERT(`i`.id_identifiers USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%'"
				+ "or CONVERT(`i`.scopusid USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%' "
				+ "or CONVERT(`i`.orcid USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%'"
				+ "or CONVERT(`i`.researcherid USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%' ");	
	while(rs.next()){
		identifiersForViewData.add(new Identifiers(rs.getInt("i.id_identifiers"), rs.getString("i.scopusid"), rs.getString("i.orcid"), rs.getString("i.researcherid")));		
		}
		
		if(!edtSearchForView.equals("")){
			rs=stmt.executeQuery("Select emp.*,a.full_name_en AS name_authors, s.name AS name_scientific_institutions, c.name AS name_cathedra  "
					+ "From `Authors` a, `Scientific_institutions` s, `Cathedra` c, `employment_history` emp "
					+ "Where `emp`.id_authors=`a`.id_authors and `emp`.id_scientific_institutions=`s`.id_scientific_institutions or `emp`.id_cathedra=`c`.id_cathedra "
					+ "and (emp.id_authors IN (Select `authors`.id_authors From `Authors` Where CONVERT(`authors`.full_name_en USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%') "
							+ "or `emp`.id_scientific_institutions IN (Select `scientific_institutions`.id_scientific_institutions From `scientific_institutions` Where CONVERT(`scientific_institutions`.name USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%') "
							+ "or `emp`.id_cathedra IN (Select `cathedra`.id_cathedra From `cathedra` Where CONVERT(`cathedra`.name USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%') "
							+ "or CONVERT(`emp`.beginning_of_period USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%'"
							+ "or CONVERT(`emp`.ending_of_period USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%')");	
			}else{
				rs=stmt.executeQuery("Select emp.*,a.full_name_en AS name_authors, s.name AS name_scientific_institutions, c.name AS name_cathedra  "
						+ "From `Authors` a, `Scientific_institutions` s, `Cathedra` c, `employment_history` emp "
						+ "Where `emp`.id_authors=`a`.id_authors and `emp`.id_scientific_institutions=`s`.id_scientific_institutions or `emp`.id_cathedra=`c`.id_cathedra ");
			}
		while(rs.next()){
			employmentHistoryForViewData.add(new EmploymentHistory(rs.getInt("emp.id_employment_history"),rs.getInt("emp.id_scientific_institutions"), rs.getInt("emp.id_cathedra"), rs.getInt("emp.id_authors"), rs.getString("emp.beginning_of_period"), rs.getString("emp.ending_of_period"), rs.getString("name_scientific_institutions"), rs.getString("name_cathedra"),rs.getString("name_authors")));			
		}
		tblAuthorsForView.refresh();
		tblIdentifiersForView.refresh();
		tblEmploymentHistoryForView.refresh();	
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
		
/*	public void searchForView(){
		Connection conn = null;
		Statement stmt =null;
		ResultSet rs =null;
		try{
			authorsForViewData.clear();
			identifiersForViewData.clear();
			employmentHistoryForViewData.clear();
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdp","root","7525800");
		stmt=conn.createStatement();
		rs=stmt.executeQuery("Select * From Authors");
		if(!rbForAll.isSelected()){
		rs=stmt.executeQuery("Select distinct e.*, a.*, i.*,a.full_name_en AS name_authors, s.name AS name_scientific_institutions, c.name AS name_cathedra "
				+ "From `Authors` a, `Identifiers` i, `Employment_history` e, `Scientific_institutions` s, `Cathedra` c "
				+ "Where `e`.id_authors=`a`.id_authors and `e`.id_scientific_institutions=`s`.id_scientific_institutions and `e`.id_cathedra=`c`.id_cathedra and a.id_identifiers=i.id_identifiers "
				+ "and (CONVERT(`a`.id_authors USING utf8) LIKE '%"+edtSearchForView.getText()+"%' "
				+ "or CONVERT(`a`.id_identifiers USING utf8) LIKE '%"+edtSearchForView.getText()+"%' "
				+ "or CONVERT(`a`.full_name_en USING utf8) LIKE '%"+edtSearchForView.getText()+"%' "
				+ "or CONVERT(`a`.full_name_ru USING utf8) LIKE '%"+edtSearchForView.getText()+"%' "
				+ "or CONVERT(`a`.full_name_ua USING utf8) LIKE '%"+edtSearchForView.getText()+"%' "
				+ "or CONVERT(`a`.scientific_degree USING utf8) LIKE '%"+edtSearchForView.getText()+"%' "
				+ "or CONVERT(`a`.date_of_birth USING utf8) LIKE '%"+edtSearchForView.getText()+"%' "
				+ "or CONVERT(`a`.email USING utf8) LIKE '%"+edtSearchForView.getText()+"%' "
				+ "or CONVERT(a.telephone USING utf8) LIKE '%"+edtSearchForView.getText()+"%' "
				+ "or `e`.id_authors=(Select `authors`.id_authors From `Authors` Where CONVERT(`authors`.full_name_en USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%') "
				+ "or `e`.id_scientific_institutions=(Select `scientific_institutions`.id_scientific_institutions From `scientific_institutions` Where CONVERT(`scientific_institutions`.name USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%') "
				+ "or `e`.id_cathedra=(Select `cathedra`.id_cathedra From `cathedra` Where CONVERT(`cathedra`.name USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%') "
				+ "or CONVERT(`e`.beginning_of_period USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%' "
				+ "or CONVERT(`e`.ending_of_period USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%' "
				+ "or CONVERT(`e`.id_authors USING 'utf8') LIKE '%na%' "
				+ "or CONVERT(`e`.id_scientific_institutions USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%' "
				+ "or CONVERT(`e`.id_cathedra USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%' "
				+ "or CONVERT(`i`.id_identifiers USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%' "
				+ "or CONVERT(`i`.scopusid USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%' "
				+ "or CONVERT(`i`.orcid USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%' "
				+ "or CONVERT(`i`.researcherid USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%') "
				+ "group by(`a`.id_authors);");
		while(rs.next()){
		authorsForViewData.add(new Authors(rs.getInt("a.id_authors"), rs.getInt("a.id_identifiers"), rs.getString("a.full_name_en"), rs.getString("a.full_name_ru"), rs.getString("a.full_name_ua"), rs.getString("a.scientific_degree"), rs.getDate("a.date_of_birth"), rs.getString("a.email"),rs.getString("a.telephone")));
		identifiersForViewData.add(new Identifiers(rs.getInt("i.id_identifiers"), rs.getString("i.scopusid"), rs.getString("i.orcid"), rs.getString("i.researcherid")));		
		employmentHistoryForViewData.add(new EmploymentHistory(rs.getInt("e.id_employment_history"),rs.getInt("e.id_scientific_institutions"), rs.getInt("e.id_cathedra"), rs.getInt("e.id_authors"), rs.getString("e.beginning_of_period"), rs.getString("e.ending_of_period"), rs.getString("name_scientific_institutions"), rs.getString("name_cathedra"),rs.getString("name_authors")));			
		}
		tblAuthorsForView.refresh();
		tblIdentifiersForView.refresh();
		tblEmploymentHistoryForView.refresh();	
		}
		else{
			rs=stmt.executeQuery("Select distinct a.* "
					+ "From `Authors` a "
					+ "Where CONVERT(a.id_authors USING utf8) LIKE '%"+edtSearchForView.getText()+"%' "
					+ "or CONVERT(`a`.id_identifiers USING utf8) LIKE '%"+edtSearchForView.getText()+"%' "
					+ "or CONVERT(`a`.full_name_en USING utf8) LIKE '%"+edtSearchForView.getText()+"%' or "
					+ "CONVERT(`a`.full_name_ru USING utf8) LIKE '%"+edtSearchForView.getText()+"%' "
					+ "or CONVERT(`a`.full_name_ua USING utf8) LIKE '%"+edtSearchForView.getText()+"%' "
					+ "or CONVERT(`a`.scientific_degree USING utf8) LIKE '%"+edtSearchForView.getText()+"%' or "
					+ "CONVERT(`a`.date_of_birth USING utf8) LIKE '%"+edtSearchForView.getText()+"%' "
					+ "or CONVERT(`a`.email USING utf8) LIKE '%"+edtSearchForView.getText()+"%' "
					+ "or CONVERT(`a`.telephone USING utf8) LIKE '%"+edtSearchForView.getText()+"%' ");
	
		while(rs.next()){
			authorsForViewData.add(new Authors(rs.getInt("a.id_authors"), rs.getInt("a.id_identifiers"), rs.getString("a.full_name_en"), rs.getString("a.full_name_ru"), rs.getString("a.full_name_ua"), rs.getString("a.scientific_degree"), rs.getDate("a.date_of_birth"), rs.getString("a.email"),rs.getString("a.telephone")));
			}
		rs=stmt.executeQuery("Select * From `Identifiers` i "
				+ "Where CONVERT(`i`.id_identifiers USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%'"
				+ "or CONVERT(`i`.scopusid USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%' "
				+ "or CONVERT(`i`.orcid USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%'"
				+ "or CONVERT(`i`.researcherid USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%' ");	
	while(rs.next()){
		identifiersForViewData.add(new Identifiers(rs.getInt("i.id_identifiers"), rs.getString("i.scopusid"), rs.getString("i.orcid"), rs.getString("i.researcherid")));		
		}
		
		if(!edtSearchForView.equals("")){
			rs=stmt.executeQuery("Select emp.*,a.full_name_en AS name_authors, s.name AS name_scientific_institutions, c.name AS name_cathedra  "
					+ "From `Authors` a, `Scientific_institutions` s, `Cathedra` c, `employment_history` emp "
					+ "Where `emp`.id_authors=`a`.id_authors and `emp`.id_scientific_institutions=`s`.id_scientific_institutions and `emp`.id_cathedra=`c`.id_cathedra "
					+ "and (emp.id_authors IN (Select `authors`.id_authors From `Authors` Where CONVERT(`authors`.full_name_en USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%') "
							+ "or `emp`.id_scientific_institutions IN (Select `scientific_institutions`.id_scientific_institutions From `scientific_institutions` Where CONVERT(`scientific_institutions`.name USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%') "
							+ "or `emp`.id_cathedra IN (Select `cathedra`.id_cathedra From `cathedra` Where CONVERT(`cathedra`.name USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%') "
							+ "or CONVERT(`emp`.beginning_of_period USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%'"
							+ "or CONVERT(`emp`.ending_of_period USING 'utf8') LIKE '%"+edtSearchForView.getText()+"%')");	
			}else{
				rs=stmt.executeQuery("Select emp.*,a.full_name_en AS name_authors, s.name AS name_scientific_institutions, c.name AS name_cathedra  "
						+ "From `Authors` a, `Scientific_institutions` s, `Cathedra` c, `employment_history` emp "
						+ "Where `emp`.id_authors=`a`.id_authors and `emp`.id_scientific_institutions=`s`.id_scientific_institutions and `emp`.id_cathedra=`c`.id_cathedra ");
			}
		while(rs.next()){
			employmentHistoryForViewData.add(new EmploymentHistory(rs.getInt("emp.id_employment_history"),rs.getInt("emp.id_scientific_institutions"), rs.getInt("emp.id_cathedra"), rs.getInt("emp.id_authors"), rs.getString("emp.beginning_of_period"), rs.getString("emp.ending_of_period"), rs.getString("name_scientific_institutions"), rs.getString("name_cathedra"),rs.getString("name_authors")));			
		}
		tblAuthorsForView.refresh();
		tblIdentifiersForView.refresh();
		tblEmploymentHistoryForView.refresh();
			
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
	*/
	public void exportToExcelAuthors(){
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.XLS","*.*");
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("Reports"));
		chooser.setFileFilter(filter);		
		if ( chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
	        try { 
	        	File f = new File(chooser.getSelectedFile().getAbsolutePath()+".xls");        	
	        		WritableWorkbook myExcel =  Workbook.createWorkbook(f);
	        		myExcel.setProtected(true);
	        		WritableSheet mySheet = myExcel.createSheet("mySheet", 0);
	     
	        		WritableCellFormat newFormat= new WritableCellFormat();	     
	        		newFormat.setBackground(jxl.format.Colour.LIGHT_BLUE);
	        		
	        		jxl.write.Label l = new jxl.write.Label(0,0,"ID",newFormat);
	        		jxl.write.Label l1 = new jxl.write.Label(1,0,"Identifier",newFormat);
	        		jxl.write.Label l2 = new jxl.write.Label(2,0,"Full Name",newFormat);
	        		jxl.write.Label l3 = new jxl.write.Label(3,0,"ФИО",newFormat);
	        		jxl.write.Label l4 = new jxl.write.Label(4,0,"ПІБ",newFormat);
	        		jxl.write.Label l5 = new jxl.write.Label(5,0,"Scientific degree",newFormat);
	        		jxl.write.Label l6 = new jxl.write.Label(6,0,"Date of Birth",newFormat);
	        		jxl.write.Label l7 = new jxl.write.Label(7,0,"E-mail",newFormat);
	        		jxl.write.Label l8 = new jxl.write.Label(8,0,"Telephone",newFormat);	        		
	        		mySheet.addCell(l);
	        		mySheet.addCell(l1);
	        		mySheet.addCell(l2);
	        		mySheet.addCell(l3);
	        		mySheet.addCell(l4);
	        		mySheet.addCell(l5);
	        		mySheet.addCell(l6);
	        		mySheet.addCell(l7);
	        		mySheet.addCell(l8);
	        		int i=1;
	        		for(Authors author: authorsData){
	        			mySheet.addCell(new jxl.write.Label(0, i, String.valueOf(author.getId_authors())));
	        			mySheet.addCell(new jxl.write.Label(1, i, String.valueOf(author.getId_identifiers())));
	        			mySheet.addCell(new jxl.write.Label(2, i, author.getFull_name_en()));
	        			mySheet.addCell(new jxl.write.Label(3, i, author.getFull_name_ru()));
	        			mySheet.addCell(new jxl.write.Label(4, i, author.getFull_name_ua()));
	        			mySheet.addCell(new jxl.write.Label(5, i, author.getScientific_degree()));
	        			mySheet.addCell(new jxl.write.Label(6, i, String.valueOf(author.getDate_of_birth())));
	        			mySheet.addCell(new jxl.write.Label(7, i, author.getEmail()));
	        			mySheet.addCell(new jxl.write.Label(8, i, author.getTelephone()));
	        			i++;
	        		}        		
	        		myExcel.write();
	        		myExcel.close();
	        }
	        catch ( Exception e ) {
	            System.out.println("Всё погибло!");
	        }
	    }
		
	}
	
	public void exportToExcelIdentifiers(){
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.XLS","*.*");
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("Reports"));
		chooser.setFileFilter(filter);		
		if ( chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
	        try { 
	        	File f = new File(chooser.getSelectedFile().getAbsolutePath()+".xls");        	
	        		WritableWorkbook myExcel =  Workbook.createWorkbook(f);
	        		myExcel.setProtected(true);
	        		WritableSheet mySheet = myExcel.createSheet("mySheet", 0);
	        		WritableCellFormat newFormat= new WritableCellFormat();	     
	        		newFormat.setBackground(jxl.format.Colour.LIGHT_BLUE);
	        		jxl.write.Label l = new jxl.write.Label(0,0,"ID",newFormat);
	        		jxl.write.Label l1 = new jxl.write.Label(1,0,"ScopusID",newFormat);
	        		jxl.write.Label l2 = new jxl.write.Label(2,0,"OrcID",newFormat);
	        		jxl.write.Label l3 = new jxl.write.Label(3,0,"ResearcherID",newFormat);
      		
	        		mySheet.addCell(l);
	        		mySheet.addCell(l1);
	        		mySheet.addCell(l2);
	        		mySheet.addCell(l3);

	        		int i=1;
	        		for(Identifiers identifier: identifiersData){
	        			mySheet.addCell(new jxl.write.Label(0, i, String.valueOf(identifier.getId_identifiers())));
	        			mySheet.addCell(new jxl.write.Label(1, i, identifier.getScopusid()));
	        			mySheet.addCell(new jxl.write.Label(2, i, identifier.getOrcid()));
	        			mySheet.addCell(new jxl.write.Label(3, i, identifier.getResearcherid()));
	        			i++;
	        		}        		
	        		myExcel.write();
	        		myExcel.close();
	        }
	        catch ( Exception e ) {
	            System.out.println("Всё погибло!");
	        }
	    }
		
	}
	
	public void exportToExcelEmploymentHistory(){
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.XLS","*.*");
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("Reports"));
		chooser.setFileFilter(filter);		
		if ( chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
	        try { 
	        	File f = new File(chooser.getSelectedFile().getAbsolutePath()+".xls");        	
	        		WritableWorkbook myExcel =  Workbook.createWorkbook(f);
	        		myExcel.setProtected(true);
	        		WritableSheet mySheet = myExcel.createSheet("mySheet", 0);
	        		WritableCellFormat newFormat= new WritableCellFormat();	     
	        		newFormat.setBackground(jxl.format.Colour.LIGHT_BLUE);
	        		jxl.write.Label l = new jxl.write.Label(0,0,"ID",newFormat);
	        		jxl.write.Label l1 = new jxl.write.Label(1,0,"ID Author",newFormat);
	        		jxl.write.Label l2 = new jxl.write.Label(2,0,"Author Name",newFormat);
	        		jxl.write.Label l3 = new jxl.write.Label(3,0,"Scientific Institution",newFormat);
	        		jxl.write.Label l4 = new jxl.write.Label(4,0,"Cathedra",newFormat);
	        		jxl.write.Label l5 = new jxl.write.Label(5,0,"Beginning_of period",newFormat);
	        		jxl.write.Label l6 = new jxl.write.Label(6,0,"Ending of period",newFormat);
        		
	        		mySheet.addCell(l);
	        		mySheet.addCell(l1);
	        		mySheet.addCell(l2);
	        		mySheet.addCell(l3);
	        		mySheet.addCell(l4);
	        		mySheet.addCell(l5);
	        		mySheet.addCell(l6);

	        		int i=1;
	        		for(EmploymentHistory employmentHistory: employmentHistoryData){
	        			mySheet.addCell(new jxl.write.Label(0, i, String.valueOf(employmentHistory.getId_employment_history())));
	        			mySheet.addCell(new jxl.write.Label(1, i, String.valueOf(employmentHistory.getId_authors())));
	        			mySheet.addCell(new jxl.write.Label(2, i, employmentHistory.getName_authors()));
	        			mySheet.addCell(new jxl.write.Label(3, i, employmentHistory.getName_scientific_institutions()));
	        			mySheet.addCell(new jxl.write.Label(4, i, employmentHistory.getName_cathedra()));
	        			mySheet.addCell(new jxl.write.Label(5, i, employmentHistory.getBeginning_of_period()));
	        			mySheet.addCell(new jxl.write.Label(6, i, employmentHistory.getEnding_of_period()));
	        			i++;
	        		}        		
	        		myExcel.write();
	        		myExcel.close();
	        }
	        catch ( Exception e ) {
	            System.out.println("Всё погибло!");
	        }
	    }
		
	}
	
	public void refresTables() throws SQLException{
		authorsData.clear();
		identifiersData.clear();
		employmentHistoryData.clear();
		connectToDB();		
		id_authors.setCellValueFactory((new PropertyValueFactory<Authors, Integer>("id_authors")));
		id_identifiers.setCellValueFactory((new PropertyValueFactory<Authors, Integer>("id_identifiers")));
		full_name_en.setCellValueFactory((new PropertyValueFactory<Authors, String>("full_name_en")));
		full_name_ru.setCellValueFactory((new PropertyValueFactory<Authors, String>("full_name_ru")));
		full_name_ua.setCellValueFactory((new PropertyValueFactory<Authors, String>("full_name_ua")));
		scientific_degree.setCellValueFactory((new PropertyValueFactory<Authors, String>("scientific_degree")));
		date_of_birth.setCellValueFactory((new PropertyValueFactory<Authors, Date>("date_of_birth")));
		email.setCellValueFactory((new PropertyValueFactory<Authors, String>("email")));
		telephone.setCellValueFactory((new PropertyValueFactory<Authors, String>("telephone")));	
		//for view
		id_authors2.setCellValueFactory((new PropertyValueFactory<Authors, Integer>("id_authors")));
		id_identifiers2.setCellValueFactory((new PropertyValueFactory<Authors, Integer>("id_identifiers")));
		full_name_en1.setCellValueFactory((new PropertyValueFactory<Authors, String>("full_name_en")));
		full_name_ru1.setCellValueFactory((new PropertyValueFactory<Authors, String>("full_name_ru")));
		full_name_ua1.setCellValueFactory((new PropertyValueFactory<Authors, String>("full_name_ua")));
		scientific_degree1.setCellValueFactory((new PropertyValueFactory<Authors, String>("scientific_degree")));
		date_of_birth1.setCellValueFactory((new PropertyValueFactory<Authors, Date>("date_of_birth")));
		email1.setCellValueFactory((new PropertyValueFactory<Authors, String>("email")));
		telephone1.setCellValueFactory((new PropertyValueFactory<Authors, String>("telephone")));
		
		
		id_identifiers1.setCellValueFactory((new PropertyValueFactory<Identifiers, Integer>("id_identifiers")));
		scopusid.setCellValueFactory((new PropertyValueFactory<Identifiers, String>("scopusid")));
		orcid.setCellValueFactory((new PropertyValueFactory<Identifiers, String>("orcid")));
		researcherid.setCellValueFactory((new PropertyValueFactory<Identifiers, String>("researcherid")));
		//for view
		id_identifiers3.setCellValueFactory((new PropertyValueFactory<Identifiers, Integer>("id_identifiers")));
		scopusid1.setCellValueFactory((new PropertyValueFactory<Identifiers, String>("scopusid")));
		orcid1.setCellValueFactory((new PropertyValueFactory<Identifiers, String>("orcid")));
		researcherid1.setCellValueFactory((new PropertyValueFactory<Identifiers, String>("researcherid")));

		id_employment_history.setCellValueFactory((new PropertyValueFactory<EmploymentHistory, Integer>("id_employment_history")));
		id_authors1.setCellValueFactory((new PropertyValueFactory<EmploymentHistory, Integer>("id_authors")));
		id_cathedra.setCellValueFactory((new PropertyValueFactory<EmploymentHistory, Integer>("id_cathedra")));
		id_scientific_institutions.setCellValueFactory((new PropertyValueFactory<EmploymentHistory, Integer>("id_scientific_institutions")));
		name_authors.setCellValueFactory((new PropertyValueFactory<EmploymentHistory, String>("name_authors")));
		name_scientific_institutions.setCellValueFactory((new PropertyValueFactory<EmploymentHistory, String>("name_scientific_institutions")));
		name_cathedra.setCellValueFactory((new PropertyValueFactory<EmploymentHistory, String>("name_cathedra")));
		beginning_of_period.setCellValueFactory((new PropertyValueFactory<EmploymentHistory, String>("beginning_of_period")));
		ending_of_period.setCellValueFactory((new PropertyValueFactory<EmploymentHistory, String>("ending_of_period")));
		//for view
		id_employment_history1.setCellValueFactory((new PropertyValueFactory<EmploymentHistory, Integer>("id_employment_history")));
		id_authors3.setCellValueFactory((new PropertyValueFactory<EmploymentHistory, Integer>("id_authors")));
		id_cathedra1.setCellValueFactory((new PropertyValueFactory<EmploymentHistory, Integer>("id_cathedra")));
		id_scientific_institutions1.setCellValueFactory((new PropertyValueFactory<EmploymentHistory, Integer>("id_scientific_institutions")));
		name_authors1.setCellValueFactory((new PropertyValueFactory<EmploymentHistory, String>("name_authors")));
		name_scientific_institutions1.setCellValueFactory((new PropertyValueFactory<EmploymentHistory, String>("name_scientific_institutions")));
		name_cathedra1.setCellValueFactory((new PropertyValueFactory<EmploymentHistory, String>("name_cathedra")));
		beginning_of_period1.setCellValueFactory((new PropertyValueFactory<EmploymentHistory, String>("beginning_of_period")));
		ending_of_period1.setCellValueFactory((new PropertyValueFactory<EmploymentHistory, String>("ending_of_period")));
		
		tblAuthors.setItems(authorsData);
		tblIdentifiers.setItems(identifiersData);
		tblEmploymentHistory.setItems(employmentHistoryData);
		tblAuthorsForView.setItems(authorsForViewData);
		tblIdentifiersForView.setItems(identifiersForViewData);
		tblEmploymentHistoryForView.setItems(employmentHistoryForViewData);
		
	}
	
	public void showScientificInstitutions() throws Exception{
		ScientificInstitution sc = new ScientificInstitution();
	}

}
