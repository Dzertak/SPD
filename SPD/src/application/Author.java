package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Author {
	

	
	public Author() throws IOException {
		Stage primaryStage = new Stage();
		Parent root1 = FXMLLoader.load(getClass().getResource("/fxml/AuthorsFXML.fxml"));	
		Scene sceneS = new Scene(root1);
		sceneS.getStylesheets().add(getClass().getResource("/css/authors.css").toExternalForm());
		primaryStage.setTitle("SPD.Authors");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/fxml/image/penBlue.png")));
		primaryStage.setScene(sceneS);
		primaryStage.setMinWidth(667);
		primaryStage.setMinHeight(457);
		primaryStage.show();
	}
	

}
