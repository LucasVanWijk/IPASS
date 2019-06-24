import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.omg.PortableServer.POA;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


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

        //Adds all menu's together
        BorderPane border = new BorderPane();
        //Sets the left menu to the left
        border.setLeft(createLeftMenu());
        border.setTop(createTopMenu());


        Scene s = new Scene(border);
        primaryStage.setMaximized(true);
        window.setScene(s);
        window.show();

    }

    public HBox createTopMenu(){
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        TextField mass = new TextField();
        TextField velocity = new TextField();
        TextField distance = new TextField();
        mass.setPromptText("Mass of the satellite");
        velocity.setPromptText("Velocity of the satellite");
        distance.setPromptText("distance from the planet");

        Button save_sat = new Button("save satellite configuration");

        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(mass,velocity,distance,save_sat);

        return hbox;
    }


    public VBox createLeftMenu() {
        //Left menu
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(15);

        //Projects images above the buttons
        Image venus_image = new Image("Venus.JPG",0, 200, true, false);
        ImageView venus_imageVieuw = new ImageView(venus_image);

        Image mercury_image = new Image("Mercury.JPG",0, 200, true, false);
        ImageView mercury_imageVieuw = new ImageView(mercury_image);

        Image self_image = new Image("Own_planet.JPG",0, 200, true, false);
        ImageView self_imageVieuw = new ImageView(self_image);

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
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(venus_imageVieuw, venus, mercury_imageVieuw ,mercury, self_imageVieuw ,self_made_planet);

        return vbox;


    }

    public ImageView createImage(String filepath) throws FileNotFoundException{


        //Creating an image
        Image image = new Image(new FileInputStream(filepath));

        //Setting the image view
        ImageView imageView = new ImageView(image);

        //Setting the position of the image
        imageView.setX(50);
        imageView.setY(25);

        //setting the fit height and width of the image view
        imageView.setFitHeight(455);
        imageView.setFitWidth(500);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);

        return imageView;

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
