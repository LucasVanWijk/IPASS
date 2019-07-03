import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;


public class Main extends Application implements EventHandler<ActionEvent> {

    Button venus;
    Button mercury;
    Button self_made_planet;

    Button  mariner_5;
    Button  bepicolombo;
    Button  messenger;
    Button  new_horizons;
    Button  voyager;
    Button  pioneer_11;
    Button  falcon_heavy;
    Button  falcon_9;

    TextField mass = new TextField();
//    TextField mass_selfmade_planet = new TextField();
//    TextField radius_selfmade_planet;

    String sat_non_custom_name = "default";


    No_Moon_Planet to_calc_planet = new No_Moon_Planet(1,1,"default");
    Satellite to_calc_sat = new Satellite(0,0,0,"default");

    Stage window;
    BorderPane border;

    public static void main(String[] args) {
        launch(args);


    }

    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setTitle("James");

        //Adds all menu's together
        border = new BorderPane();
        border.setLeft(create_left_menu());
        border.setTop(create_top_menu());
        border.setCenter(create_center_menu());


        Scene s = new Scene(border);
        primaryStage.setMaximized(true);
        window.setScene(s);
        window.show();

    }


    private VBox create_top_menu() {


        /*
        "warning" Displays a waring massage in the hopes of preventing the user from filling in incompatible variables.
        The message has to be displayed above the textfield for aesthetic reasons.
        This is also the reason the new VBox is created and the text is given to it first.
        This ensures that the message is displayed above the textfields.
         */

        Text warning = new Text("Please fill in the values as a number." +
                " Scientific units are not necessary and will result in a error." +
                " Decimal numbers should be typed with a dot like so 2.0" +
                " Mass should alway be filled in because it cannot be calculated");

        warning.setFont(new Font(20.0)); // set to Label
        warning.setTextAlignment(TextAlignment.CENTER);
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        vbox.setSpacing(10);
        vbox.setStyle("-fx-background-color: #336699;");

        //Hbox is a GUI elements which stacks its children horizontally
        HBox imput_fields = new HBox();

        //Padding makes sure that the box does not end immediately below the textfields.
        //That would look very ugly

        imput_fields.setSpacing(10);


        //The user will fill in the variables of the satellite in these fields
        mass = new TextField();
        TextField velocity = new TextField();
        TextField distance = new TextField();
        mass.setPromptText("Mass of the satellite");
        velocity.setPromptText("Velocity of the satellite");
        distance.setPromptText("distance from the planet");

        //This will put text with the sintific notation after the imput fields
        Font size_20 = new Font(20);

        Label ms = new Label("m/s");
        ms.setFont(size_20);

        Label kg = new Label("Kg");
        kg.setFont(size_20);

        Label m  = new Label("m");
        m.setFont(size_20);

        Button save_sat = new Button("save satellite configuration");
        save_sat.setOnAction(event -> {
            sat_event(mass, velocity, distance);
            update_screen();
        });

        imput_fields.setAlignment(Pos.CENTER);
        imput_fields.getChildren().addAll(mass,kg, velocity,ms, distance,m, save_sat);

        HashMap<String, String> mass_sat_dict = new HashMap<>();
        mass_sat_dict.put("Mariner 5", "244.9");
        mass_sat_dict.put("BepiColombo", "4100");
        mass_sat_dict.put("Messenger","1107.9");
        mass_sat_dict.put("New Horizons","478");
        mass_sat_dict.put("Voyager","825.5");
        mass_sat_dict.put("Pioneer 11","259");
        mass_sat_dict.put("Falcon Heavy","1420788");
        mass_sat_dict.put("Falcon 9","549054");

        mariner_5 = create_sat_button("Mariner 5",mass_sat_dict);
        bepicolombo = create_sat_button("BepiColombo",mass_sat_dict);
        messenger = create_sat_button("Messenger",mass_sat_dict);
        new_horizons = create_sat_button("New Horizons",mass_sat_dict);
        voyager = create_sat_button("Voyager",mass_sat_dict);
        pioneer_11 = create_sat_button("Pioneer 11",mass_sat_dict);
        falcon_heavy = create_sat_button("Falcon Heavy",mass_sat_dict);
        falcon_9 = create_sat_button("Falcon 9",mass_sat_dict);

        HBox buttons = new HBox();
        buttons.getChildren().addAll(mariner_5,bepicolombo, messenger,new_horizons,voyager,pioneer_11,falcon_heavy,falcon_9);
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(15, 15, 15, 15));



        vbox.getChildren().addAll(warning, imput_fields,buttons);

        return vbox;
    }


    private VBox create_left_menu() {
        //Left menu
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(15);

        //Projects images above the buttons
        Image venus_image = new Image("Venus.JPG", 0, 200, true, false);
        ImageView venus_imageVieuw = new ImageView(venus_image);

        Image mercury_image = new Image("Mercury.JPG", 0, 200, true, false);
        ImageView mercury_imageVieuw = new ImageView(mercury_image);

        Image self_image = new Image("Own_planet.JPG", 0, 200, true, false);
        ImageView self_imageVieuw = new ImageView(self_image);

        //Creates all the buttons
        venus = create_basic_button("Venus");
        mercury = create_basic_button("Mercury");
        self_made_planet = create_basic_button("Make your own planet");

        //Adds all buttons to the left menu
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(venus_imageVieuw, venus, mercury_imageVieuw, mercury, self_imageVieuw, self_made_planet);

        return vbox;


    }

    private Button create_basic_button(String name){
        Button x = new Button(name);
        x.setMinSize(180, 10);
        x.setOnAction(this);

        return x;
    }

    private Button create_sat_button(String name, HashMap dict){
        Button x = new Button(name);
        x.setMinSize(180, 10);
        x.setOnAction(event -> {

            Object na = dict.get(name);
            String name_sat = na.toString();

            mass.setText(name_sat);
            sat_non_custom_name = name;

            update_screen();

        });

        return x;
    }


    private HBox create_center_menu() {
        HBox hbox = new HBox();

        //Padding makes sure that the box does not end immediately below the textfields.
        //That would like very ugly
        hbox.setPadding(new Insets(15, 15, 15, 15));
        hbox.setSpacing(10);



        VBox left_box = create_center_left();
        VBox right_box = create_center_right();


        hbox.getChildren().addAll(left_box,right_box);
        return hbox;

    }


    private VBox create_center_left(){

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(15);


        Label info_sat = get_sat_info();
        VBox info_planet = get_planetinfo();

        vbox.getChildren().addAll(info_planet,info_sat);
        return vbox;

    }


    private VBox create_center_right(){
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(15);

        if ((to_calc_planet.getName().equals("default") == false) && to_calc_sat.getName().equals("default") == false ) {

            to_calc_planet.orbit_sim(to_calc_sat);
            double velocity = to_calc_sat.getVelocity();
            double mass = to_calc_sat.getMass();
            double distance_core = to_calc_sat.getOrbit_distance();
            double distance_surface = (distance_core - to_calc_planet.getDiameter());
            double time_seconds = Laws.calc_orbit_time(distance_core, velocity);
            double time_hours = time_seconds/60/60;

            Label calculations = new Label();

            if (velocity > 0 && distance_core > 0 && distance_surface > 0 && time_seconds > 0) {
                String content = "The velocity or speed of the craft is " + round(velocity) + " m/s \n " +
                        "The mass of the craft is " + round(mass) + " Kg \n" +
                        "The distance from its surface is " + round(distance_surface) + " m" +
                        " and from its core " + round(distance_core) + "m \n" +
                        "It takes " + round(time_seconds) +
                        " seconds to compleet a orbit or " + round(time_hours) + " Hours";
                calculations.setText(content);


            }
            else{
                String content = "It is impossible to calculate the desired variable with the variables given." +
                        " You would get the folowing which can never be true \n\n " +
                        "The velocity or speed of the craft is " + round(velocity) + " \n " +
                        "The mass of the craft is " + round(mass) + " \n" +
                        "The distance from its surface is " + round(distance_surface) +
                        " and from its core " + round(distance_core) + "\n" +
                        "It takes " + round(time_seconds) +
                        " seconds to compleet a orbit or " + round(time_hours) + " Hours";

                calculations.setText(content);
            }


            vbox.getChildren().add(calculations);

        }
        
        return vbox;


    }


    @Override
    public void handle(ActionEvent event) {

        if (event.getSource() == venus) {
            double Venus_mass;
            double Venus_radius;
            Venus_mass = 4.8675 * Math.pow(10, 24);
            Venus_radius = 6051.8 * Math.pow(10,3);
            to_calc_planet = new No_Moon_Planet(Venus_radius, Venus_mass,"Venus");

        } else if (event.getSource() == mercury) {
            double Mercury_mass;
            double Mercury_radius;
            Mercury_mass = 3.3011 * Math.pow(10, 23);
            Mercury_radius = 2439.7 * Math.pow(10,3);

            to_calc_planet = new No_Moon_Planet(Mercury_radius, Mercury_mass,"Mercury");

        } else if (event.getSource() == self_made_planet) {
            to_calc_planet = new No_Moon_Planet("Your own");
        }

        update_screen();
    }


    private void update_screen(){
        border.setCenter(create_center_menu());
        window.show();
    }


    private void sat_event(TextField mass_text_field, TextField velocity_text_field, TextField distance_text_field) {
        try {
            double mass = Double.parseDouble(mass_text_field.getText());
            double velocity = Double.parseDouble(velocity_text_field.getText());
            double distance = Double.parseDouble(distance_text_field.getText());

            if (mass * velocity * distance != 0) {
                System.out.println("Error non of your values are 0 which means it can not be determend." +
                        " which variable you want to calculate");
                to_calc_sat = new Satellite (0,0,0,"default");
            } else {
                to_calc_sat = new Satellite(mass, velocity, distance,"Self made");

            }

        } catch (NumberFormatException e) {
            System.out.println("works not which means it work");
        }

    }


    private VBox get_planetinfo() {
        VBox v = new VBox();
        Label planet_info = new Label();
        planet_info.setFont(new Font(17.0));
        planet_info.setMaxWidth(1000);
        planet_info.setWrapText(true);
        planet_info.setText("Could not find information about the planet you chose.");

        if (to_calc_planet.getName().equals("default")){
            planet_info.setText("Welcome to Orbit simulator\n\n" +
                    "This program will calculate de desired value needed for stabel orbit\n" +
                    "please read the instructions above the input fields. This simulator is only able" +
                    " to simulate planet's without a moon. This is because a simulation with a moon would be" +
                    " outside the scope of this project and my current abilities." +
                    " Only one value can be calculated at the time.");
            planet_info.setFont(new Font(20));
            v.getChildren().add(planet_info);
        }

        if (to_calc_planet.getName().equals("Venus")){
            String content = "Venus \n" +
                    "Mass    : 4.8675 * 10^24 Kg  \n" +
                    "Radius  : 6051 Km \n \n" +

                    "Venus is the second planet from the Sun, orbiting it every 224.7 Earth days." +
                    " It has the longest rotation period of any planet in the Solar System" +
                    " and rotates in the opposite direction" +
                    " opposite direction to most other planets" +
                    " (meaning the Sun rises in the west and sets in the east)." +
                    " It does not have any natural satellites." +
                    " It is named after the Roman goddess of love and beauty." +
                    " It is the second-brightest natural object in the night sky after the Moon, \n \n" +
                    "Venus is a terrestrial planet and is sometimes called Earth's \"sister planet\"" +
                    " Because of there similarities"+
                    " It is radically different from Earth in other respects." +
                    " It has the densest atmosphere of the four terrestrial planets," +
                    " consisting of more than 96% carbon dioxide." +
                    " The atmospheric pressure at the planet's surface is 92 times that of Earth," +
                    " Venus is by far the hottest planet in the Solar System, with a surface temperature of 462 °C" +
                    " (863 °F), even though Mercury is closer to the Sun." +
                    " Venus is shrouded by an opaque layer of highly reflective clouds of sulfuric acid," +
                    " It may have had water oceans in the past," +
                    " but these would have vaporized as the temperature rose due to a runaway greenhouse effect." +
                    " Venus's surface is a dry desertscape" +
                    " interspersed with slab-like rocks and is periodically resurfaced by volcanism.";
            planet_info.setText(content);
            v.getChildren().add(planet_info);
        }

        if (to_calc_planet.getName().equals("Mercury")){
            String content =  "Mercury \n" +
                    "Mass   : 3.3011 * 10^24 \n" +
                    "Radius : 2439.7 Km \n \n" +

                    "Mercury is the smallest and innermost planet in the Solar System." +
                    " Its orbital period around the Sun of 87.97 days" +
                    " is the shortest of all the planets in the Solar System." +
                    " It is named after the Roman deity Mercury, the messenger of the gods." +
                    "Mercury's surface appears heavily cratered and is similar in appearance to the Moon's," +
                    " indicating that it has been geologically inactive for billions of years." +
                    " Having almost no atmosphere to retain heat," +
                    " it has surface temperatures that vary diurnally more than on any other planet in the Solar System," +
                    " ranging from −173 °C (−280 °F) at night to 427 °C (800 °F) during the day" +
                    " across the equatorial regions." +
                    " The planet has no known natural satellites.\n" +
                    "\n" +
                    "Two spacecraft have visited Mercury:" +
                    " Mariner 10 flew by in 1974 and 1975;" +
                    " and MESSENGER, launched in 2004, orbited Mercury over 4,000 times in four years" +
                    " before exhausting its fuel and crashing into the planet's surface on April 30, 2015. " +
                    "The BepiColombo spacecraft is planned to arrive at Mercury in 2025.  ";
            planet_info.setText(content);
            v.getChildren().add(planet_info);
        }

        if (to_calc_planet.getName().equals("Your own")){

            Label mass_label =   new Label("Mass   : ");
            Label radius_label = new Label("Radius : ");
            Label kg = new Label("Kg");
            Label m = new Label("m");
            TextField mass_imput_field = new TextField();
            TextField radius_imput_field = new TextField();
            Button save = new Button("save");
            save.setOnAction(event -> {
                try {
                    double mass_selfmade_planet = Double.parseDouble(mass_imput_field.getText());
                    double radius_selfmade_planet = Double.parseDouble(radius_imput_field.getText());

                    to_calc_planet.setMass(mass_selfmade_planet);
                    to_calc_planet.setDiameter(radius_selfmade_planet);


                } catch (NumberFormatException e) {
                    System.out.println("Error when creating planet");
                }

            });

            HBox mass_Hbox = new HBox(mass_label,mass_imput_field,kg);
            mass_Hbox.setSpacing(10);
            HBox radius_Hbox = new HBox(radius_label,radius_imput_field,m,save);
            radius_Hbox.setSpacing(10);

            v.getChildren().addAll(mass_Hbox,radius_Hbox);
            v.setSpacing(10);


        }


        return v;

    }


    private Label get_sat_info(){
        Label sat_info = new Label();
        sat_info.setFont(new Font(17.0));
        sat_info.setMaxWidth(1000);
        sat_info.setWrapText(true);
        sat_info.setText("Could not find information about the satellite you chose.");

        if(sat_non_custom_name.equals("Mariner 5")){
            String info = "Mariner 5\n\n" +
                    "Liftoff took place on June 14, 1967 and carried a complement of experiments to probe Venus.\n\n" +
                    "Its goals were to measure interplanetary and Venusian magnetic fields," +
                    " charged particles, plasma, radio refractivity and UV emissions of the Venusian atmosphere.\n" +
                    "Mariner 5 was actually built as a backup to Mariner 4, " +
                    "but after the success of the Mariner 4 mission, " +
                    "it was modified for the Venus mission " +
                    "data from Mariner 5 " +
                    " helped to understand the temperature and pressure data returned by the Venera 4 "
                    + " lander, which arrived at Venus shortly before it. After these missions, " +
                    " it was clear that Venus had a very hot surface and an atmosphere even denser than expected.\n" +
                    " The operations of Mariner 5 ended in November 1967";
            sat_info.setText(info);
        }

        if(sat_non_custom_name.equals("BepiColombo")){
            String info = "BepiColomb \n\n" +
                    "BepiColombo is a joint mission of the European Space Agency (ESA)" +
                    " and the Japan Aerospace Exploration Agency (JAXA) to the planet Mercury." +
                    " The mission comprises of two satellites launched together." +
                    " The mission will perform a comprehensive study of Mercury," +
                    " including characterization of its magnetic field, magnetosphere, \n\n" +
                    " and both interior and surface structure. It was launched on  20 October 2018, with an arrival at Mercury planned for December 2025," +
                    " after a flyby of Earth, two flybys of Venus, and six flybys of Mercury";

            sat_info.setText(info);

        }

        if(sat_non_custom_name.equals("Messenger")){
            String info = "Messenger \n\n" +
                    " Messenger a backronym for \"MErcury Surface, Space ENvironment, GEochemistry, and Ranging\";" +
                    " (the name being reference to the messenger deity Mercury from Roman mythology)" +
                    " was a NASA robotic spacecraft that orbited the planet Mercury between 2011 and 2015." +
                    " The probe was launched in August 2004 to study Mercury's" +
                    " chemical composition, geology, and magnetic field." +
                    " MESSENGER entered orbit around Mercury on March 18, 2011, becoming the first spacecraft to do so."+
                    " It successfully completed its primary mission in 2012. Following two mission extensions," +
                    " the MESSENGER spacecraft used the last of its maneuvering propellant and deorbited as planned," +
                    " impacting the surface of Mercury on April 30, 2015.";
            sat_info.setText(info);
        }

        if(sat_non_custom_name.equals("New Horizons")){
            String info = "New Horizons \n\n" +
                    "the spacecraft was launched in 2006" +
                    " with the primary mission to perform a flyby study of the Pluto system in 2015," +
                    " and a secondary mission to fly by and study one or more other Kuiper belt objects" +
                    " in the decade to follow," +
                    " It is the fifth space probe to achieve the escape velocity needed to leave the Solar System." +
                    "It's misson goals are to \n" +
                    "    map the surface composition of Pluto and Charon\n" +
                    "    characterize the geology and morphology of Pluto and Charon\n" +
                    "    characterize the neutral atmosphere of Pluto and its escape rate\n" +
                    "    search for an atmosphere around Charon\n" +
                    "    map surface temperatures on Pluto and Charon\n" +
                    "    search for rings and additional satellites around Pluto\n" +
                    "    conduct similar investigations of one or more Kuiper belt objects";
            sat_info.setText(info);

        }

        if(sat_non_custom_name.equals("Voyager")){
            String info = "Voyager program\n\n" +
                    " The Voyager program is an American scientific program that employs two robotic probes," +
                    " Voyager 1 and Voyager 2, to study the outer Solar System.The probes were launched in 1977" +
                    " to take advantage of a favorable alignment of the planets." +
                    " Although their original mission was to study only the planetary systems of Jupiter and Saturn," +
                    " Voyager 2 continued on to Uranus and Neptune." +
                    " The Voyagers now explore the outer boundary of the heliosphere in interstellar space;" +
                    " their mission has been extended three times and they continue to transmit useful scientific data." +
                    " Neither Uranus nor Neptune has been visited by a probe other than Voyager 2. " +
                    " On 25 August 2012, data from Voyager 1 indicated that it had become the first human-made object" +
                    " to enter interstellar space, traveling \"further than anyone, or anything, in history\"." +
                    " Golden record\n " +
                    "Both spacecraft carry with them a 12-inch (30 cm) golden phonograph record" +
                    " that contains pictures and sounds of Earth," +
                    " along with symbolic directions on the cover for playing the record" +
                    " and data detailing the location of Earth." +
                    " The record is intended as a combination of a time capsule and an interstellar message" +
                    " to any civilization, alien or far-future human," +
                    " that may recover either of the Voyagers.";
            sat_info.setText(info);
        }

        if(sat_non_custom_name.equals("Pioneer 11")){
            String info = "Pioneer 11 \n\n" +
                    "Pioneer 11 (also known as Pioneer G) robotic space probe launched by NASA on April 6, 1973" +
                    " to study the asteroid belt, the environment around Jupiter and Saturn," +
                    " solar wind and cosmic rays." +
                    " It was the first probe to encounter Saturn and the second to fly through the asteroid belt" +
                    " and by Jupiter. Thereafter," +
                    " Pioneer 11 became the second of five artificial objects to achieve the escape velocity" +
                    " that will allow them to leave the Solar System." +
                    " Due to power constraints and the vast distance to the probe," +
                    " the last routine contact with the spacecraft was on September 30, 1995," +
                    " and the last good engineering data was received on November 24, 1995.";
            sat_info.setText(info);
        }

        if(sat_non_custom_name.equals("Falcon Heavy")){
            String info = "Falcon Heavy\n\n" +
                    "The Falcon Heavy is a partially reusable heavy-lift launch vehicle" +
                    " designed and manufactured by SpaceX." +
                    " It is derived from the Falcon 9 vehicle and consists of a strengthened Falcon 9 first stage " +
                    "as the center core with two additional first stages as strap-on boosters." +
                    " The Falcon Heavy has the highest payload capacity of any currently operational launch vehicle," +
                    " the second-highest capacity of any rocket ever to reach orbit," +
                    " trailing the Saturn V, and the third-highest capacity of any orbital-class rocket ever launched." +
                    " SpaceX conducted the Falcon Heavy's maiden launch on February 6, 2018 \n" +
                    "The Falcon Heavy and Falcon 9 will be replaced by the Starship and Super Heavy launch system.";
            sat_info.setText(info);

        }

        if(sat_non_custom_name.equals("Falcon 9")){
            String info = "Falcon 9\n\n" +
                    "Falcon 9 is a launch vehicle designed and manufactured by SpaceX" +
                    "Its name is from the Millennium Falcon and the nine engines of the rocket's first stage." +
                    " Unlike most rockets, which are expendable launch systems," +
                    " Falcon 9 is partially reusable, with the first stage capable of re-entering the atmosphere" +
                    " and landing back vertically after separating from the second stage." +
                    " This feat was achieved for the first time on flight 20 with the v1.2 version in December 2015. ";
            sat_info.setText(info);
        }
        return sat_info;

    }

    private static String round(double value) {

        String textfrom = Double.toString(value);
        String[] decimal_split = textfrom.split("\\.");
        String before_decimal = decimal_split[0];

        int length = before_decimal.length();

        if (length>5) {
            double a = Math.pow(10, (length - 1));
            double new_value = value / a;
            return String.format("%.3f", new_value) + " * 10^" + (length - 1);

        }
        else if (length == 1){

            String[] new_text = textfrom.split("E");

            if(new_text.length != 1) {

                String number = new_text[0];
                String power = new_text[1];

                //Todo make this more efficient
                double a = Double.parseDouble(number);

                return number.format("%.3f", a) + " * 10^" + power;
            }

            else{

                return String.format("%.3f", value);

            }
        }

        else{

            return String.format("%.3f", value) ;
        }

    }
}



