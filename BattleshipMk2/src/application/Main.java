package application;
	

import javafx.scene.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {	
		Parent root = FXMLLoader.load(getClass().getResource("FXML_Example.fxml"));
		
		Scene scene = new Scene(root, 300, 500);
		
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}



//try {
//	BorderPane root = new BorderPane();
//	Scene scene = new Scene(root,400,400);
//	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//	primaryStage.setScene(scene);
//	primaryStage.show();
//} catch(Exception e) {
//	e.printStackTrace();
//}