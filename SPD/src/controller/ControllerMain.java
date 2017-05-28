package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.Author;
import application.Publication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class ControllerMain implements Initializable {
	@FXML
	private AnchorPane mainWindow;
	private ImageView ru,uk,ua,help,publication,authors;
	private Button button;
	

	public void changeLanguageUA() throws IOException{
		
		JOptionPane.showMessageDialog(null, "This is demo-version");
		/*Stage dialog = new Stage();
		dialog.initStyle(StageStyle.UTILITY);
		Label lbl = new Label("This is demo-version");
		lbl.setFont(new Font(20));
		Pane root = new Pane();
		root.getChildren().add(lbl);
		Scene scene = new Scene(root,50,50,Color.WHITE);
		dialog.setTitle("I am sorry :(");
		dialog.setResizable(false);
		dialog.setScene(scene);
		dialog.show();*/
	}
	
	public void changeLanguageUK(){
		JOptionPane.showMessageDialog(null, "This is demo-version");
	}
	
	public void changeLanguageRU(){
		JOptionPane.showMessageDialog(null, "This is demo-version");
	}
	
	public void showHelpWindow(){
		
	}
	
	public void showPublication() throws IOException{
		Publication publication = new Publication();
	}
	
	public void showAuthors() throws IOException{
		Author author = new Author();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		mainWindow.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	

}
