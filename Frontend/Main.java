package Frontend;

import Backend.No_Moon_Planet;
import Backend.Satellite;
import Frontend.Center_menu;
import Frontend.Left_menu;
import Frontend.Top_menu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class Main extends Application {

    public static Button venus;
    public static Button mercury;
    public static Button self_made_planet;

    public static TextField mass = new TextField();

    public static String sat_non_custom_name = "default";

    public static No_Moon_Planet to_calc_planet = new No_Moon_Planet(1,1,"default");
    public static Satellite to_calc_sat = new Satellite(0,0,0,"default");

    public static Stage window;
    public static BorderPane border;

    public static void main(String[] args) {
        launch(args);

    }

    public void start(Stage primaryStage) throws Exception {

        primaryStage.setMaximized(true);
        window = primaryStage;
        window.setTitle("James");

        //Adds all menu's together
        border = new BorderPane();
        border.setLeft(Left_menu.create_left_menu());
        border.setTop(Top_menu.create_top_menu());
        border.setCenter(Center_menu.create_center_menu());


        Scene s = new Scene(border);

        window.setScene(s);
        window.show();

    }



}



