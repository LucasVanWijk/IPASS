import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class Main extends Application implements EventHandler<ActionEvent> {

    Button venus;
    Button mercury;
    Button self_made_planet;

    No_Moon_Planet to_calc_planet;
    Satellite sat;



    public static void main (String[] args) {

        Satellite sat1 = new Satellite();
        sat1.setMass(100);
        sat1.setOrbit_distance(450 * Math.pow(10,3));

        launch(args);

    }
    public void start(Stage primaryStage) throws Exception {

        Stage window;
        window = primaryStage;
        window.setTitle("James");


        //Left menu
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(15);


        //Creates all the buttons
        venus = new Button("Venus");
        venus.setMinSize(180,10);
        venus.setOnAction(this);


        mercury = new Button("Mercury");
        mercury.setMinSize(180,10);
        mercury.setOnAction(this);


        self_made_planet = new Button("Make your own planet");
        self_made_planet.setMinSize(180,10);
        self_made_planet.setOnAction(this);

        //Adds all buttons to the left menu
        vbox.getChildren().addAll(venus,mercury,self_made_planet);


        //Adds all menu's together
        BorderPane border = new BorderPane();
        //Sets the left menu to the left
        border.setLeft(vbox);


        Scene s = new Scene(border);
        primaryStage.setMaximized(true);
        window.setScene(s);
        window.show();

    }

    public void set_to_calc_planet() {


    }
    @Override
    public void handle(ActionEvent event){

        if (event.getSource() == venus){
            double Venus_mass;
            double Venus_radius;
            Venus_mass = 4.8675 * Math.pow(10,24);
            Venus_radius = 6051.8;
            to_calc_planet = new No_Moon_Planet(Venus_radius,Venus_mass);

        }

        else if (event.getSource() == mercury){
            double Mercury_mass;
            double Mercury_radius;
            Mercury_mass = 3.3011 * Math.pow(10,23);
            Mercury_radius = 2439.7;

            to_calc_planet = new No_Moon_Planet(Mercury_radius,Mercury_mass);

        }

        else if(event.getSource() == self_made_planet){
            System.out.println("works");
        }

    }
}
