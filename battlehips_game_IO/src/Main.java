import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/menuScreen.fxml"));
        Parent pane =(Parent) fxmlLoader.load();
        stage.setTitle("MENU");
        stage.setResizable(false);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        String css = this.getClass().getResource("/css/aplication.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
