package Frontend;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.HashMap;

public class Top_menu {
    public static VBox create_top_menu() {
        /*
        "warning" Displays a waring massage in the hopes of preventing the user from filling in incompatible variables.
        The message has to be displayed above the textfield for aesthetic reasons.
        This is also the reason the new VBox is created and the text is given to it first.
        This ensures that the message is displayed above the text fields.
         */

        Text warning = new Text("Please fill in the values as a number." +
                " Scientific units are not necessary and will result in a error." +
                " Decimal numbers should be typed with a dot like so 2.0" +
                " Mass should always be filled in because it can't be calculated");

        warning.setFont(new Font(20.0)); // set to Label
        warning.setTextAlignment(TextAlignment.CENTER);
        VBox top_menu = new VBox();
        top_menu.setAlignment(Pos.CENTER);

        top_menu.setSpacing(4);
        top_menu.setStyle("-fx-background-color: #336699;");

        //HBox lays out its children in a single horizontal row
        HBox input_fields = new HBox();

        //Padding makes sure that the box does not end immediately below the text fields.
        //That would look very ugly

        input_fields.setSpacing(10);


        //The user will fill in the variables of the satellite in these fields
        Main.mass = new TextField();
        TextField velocity = new TextField();
        TextField distance = new TextField();
        Main.mass.setPromptText("Mass of the satellite");
        velocity.setPromptText("Velocity of the satellite");
        distance.setPromptText("distance from the planet");

        //This will put text with the scientific notation after the input fields
        Font size_20 = new Font(20);

        Label ms = new Label("km/h");
        ms.setFont(size_20);

        Label kg = new Label("Kg");
        kg.setFont(size_20);

        Label m  = new Label("km");
        m.setFont(size_20);

        Button save_sat = new Button("save satellite configuration");
        save_sat.setOnAction(event -> {
            Events_and_reactions.set_values_to_calc_sat(Main.mass, velocity, distance);
            Events_and_reactions.update_screen();
        });

        input_fields.setAlignment(Pos.CENTER);
        input_fields.getChildren().addAll(Main.mass,kg, velocity,ms, distance,m, save_sat);

        //HashMap will create a link between the name of a satellite and the weight of that satellite.
        HashMap<String, String> mass_sat_dict = new HashMap<>();
        mass_sat_dict.put("Mariner 5", "244.9");
        mass_sat_dict.put("BepiColombo", "4100");
        mass_sat_dict.put("Messenger","1107.9");
        mass_sat_dict.put("New Horizons","478");
        mass_sat_dict.put("Voyager","825.5");
        mass_sat_dict.put("Pioneer 11","259");
        mass_sat_dict.put("Falcon Heavy","1420788");
        mass_sat_dict.put("Falcon 9","549054");

        //This creates the buttons for al the pre made satellite so that the can be selected
        Button mariner_5 = Template_button.create_sat_button("Mariner 5",mass_sat_dict);
        Button bepicolombo = Template_button.create_sat_button("BepiColombo",mass_sat_dict);
        Button messenger = Template_button.create_sat_button("Messenger",mass_sat_dict);
        Button new_horizons = Template_button.create_sat_button("New Horizons",mass_sat_dict);
        Button voyager = Template_button.create_sat_button("Voyager",mass_sat_dict);
        Button pioneer_11 = Template_button.create_sat_button("Pioneer 11",mass_sat_dict);
        Button falcon_heavy = Template_button.create_sat_button("Falcon Heavy",mass_sat_dict);
        Button falcon_9 = Template_button.create_sat_button("Falcon 9",mass_sat_dict);

        //Puts all the buttons in a horizontal layout
        HBox buttons = new HBox();
        buttons.getChildren().addAll(mariner_5,bepicolombo, messenger,new_horizons,voyager,pioneer_11,falcon_heavy,falcon_9);
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(15, 15, 15, 15));


        top_menu.getChildren().addAll(warning, input_fields,buttons);

        return top_menu;
    }
}
