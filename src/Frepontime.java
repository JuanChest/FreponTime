import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Frepontime extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        File file = new File("src/presentacion/views/login.fxml");
        FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}