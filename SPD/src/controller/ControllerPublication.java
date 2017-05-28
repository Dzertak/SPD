package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import entities.Authors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class ControllerPublication implements Initializable{

	//private static ObservableList<Pu> publicationData = FXCollections.observableArrayList();
	
	@FXML
	private AnchorPane leftAnchor,authorsWindow,topAnchor;
	private Button btnMoreInfo,btnInfo;
	private TabPane tabPaneAuthorMain;
	@FXML private TabPane tabAuthor;
	
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
	}
}
