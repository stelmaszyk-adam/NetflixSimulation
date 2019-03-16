package MainProgram;

import Controler.ControlApp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {


    public void start(Stage stage) throws Exception
    {
        ControlApp.startControlApp();
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("mainFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Couch Movies");
        stage.show();
    }

    public static void main(String[] args) {launch(args);}
}
