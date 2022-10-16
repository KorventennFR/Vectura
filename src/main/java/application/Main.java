package application;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;
import view.ViewController;

public class Main extends Application {
	
	static final int WINDOW_WIDTH = 1200;
	static final int WINDOW_HEIGHT = 600;
	static ViewController view;
	
	
    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {
    	FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/main.fxml"));
    	stage.setTitle("Vectura");
    	Parent root = loader.load();
    	view = loader.getController();
        stage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
        stage.show();
        
        ObjectMapper objectMapper = new ObjectMapper();
    	URI uri = Main.class.getResource( "/content/content.json" ).toURI();
		File file = new File(uri);
		Model model = objectMapper.readValue(file, Model.class);
		model.init();
		view.setModel(model);
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        launch();
    }

}