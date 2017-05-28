package application;
	

import java.awt.BorderLayout;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		/*	Stage stage = new Stage();
			Pane tmpWindow = new Pane();
			Scene scene1 = new Scene(tmpWindow,200,200);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(scene1);
		//	stage.show();

*/		
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainFXML.fxml"));				
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			primaryStage.setTitle("SPD");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/fxml/image/penBlue.png")));
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//Author author = new Author();
			//Publication publication = new Publication();


		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
