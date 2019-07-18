package Frontend;

import Backend.No_Moon_Planet;
import Backend.Satellite;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Events_and_reactions implements EventHandler<ActionEvent> {

    /**
     * Handles events from button click
     * @param event
     */
    public void handle(ActionEvent event) {

        if (event.getSource() == Main.venus) {
            double Venus_mass;
            double Venus_radius;
            Venus_mass = 4.8675 * Math.pow(10, 24);
            Venus_radius = 6051.8 * Math.pow(10,3);
            Main.to_calc_planet = new No_Moon_Planet(Venus_radius, Venus_mass,"Venus");

        } else if (event.getSource() == Main.mercury) {
            double Mercury_mass;
            double Mercury_radius;
            Mercury_mass = 3.3011 * Math.pow(10, 23);
            Mercury_radius = 2439.7 * Math.pow(10,3);

            Main.to_calc_planet = new No_Moon_Planet(Mercury_radius, Mercury_mass,"Mercury");

        } else if (event.getSource() == Main.self_made_planet) {
            Main.to_calc_planet = new No_Moon_Planet("Your own");
        }

        update_screen();
    }


    /**
     * Updates the screen
     * Used when a button is pressed to update the information on screen for it might have changed
     */
    public static void update_screen(){
        Main.border.setCenter(Center_menu.create_center_menu());
        Main.window.show();
    }


    /**
     * Changes the parameters of the satellite to the parameters given by the user
     * <p>
     *     Changes the values of the satellite which needs to be calculated to the parameters given by the user
     *     if unable to do so gives a error. If a parameter which te user wants to calculate has not been given.
     *     Gives a error. If with the given parameters calculations can not be made gives a error
     * </p>
     * @param mass_text_field
     * @param velocity_text_field
     * @param distance_text_field
     */
    public static void set_values_to_calc_sat(TextField mass_text_field, TextField velocity_text_field, TextField distance_text_field) {
        try {
            double mass = Double.parseDouble(mass_text_field.getText());
            double velocity = (Double.parseDouble(velocity_text_field.getText()) / 3.6 );
            double distance = (Double.parseDouble(distance_text_field.getText()) * Math.pow(10,3));

            if(mass == 0){
                Error.zero_mass();
            }

            //Checks if user has given a parameter which they want to calculate
            if (velocity * distance != 0) {
                Error.non_zero_values();
                Main.to_calc_sat = new Satellite(0,0,0,"default");
            }

            //Checks if the user has given a 0 to both velocity and distance if so gives a error because calculations
            //can not bet made
            else if((velocity + distance) == 0 ){
                Error.two_zero();
                Main.to_calc_sat = new Satellite (0,0,0,"default");
            }

            else {
                Main.to_calc_sat = new Satellite(mass, velocity, distance,"Self made");
            }

        } catch (NumberFormatException e) {
            Error.number_unparasble();
        }

    }


    /**
     * Checks which planet has been chosen and what needs to be displayed on the center screen.
     * <p>
     *     Checks which planet has been chosen and what needs to be displayed on the center screen. If it is just text
     *     returns the text. If it is text + text fields returns text + textfields
     * </p>
     * @return
     */
    public static VBox get_planet_info() {
        VBox v = new VBox();
        Label planet_info = new Label();
        planet_info.setFont(new Font(17.0));
        planet_info.setMaxWidth(1000);
        planet_info.setWrapText(true);
        planet_info.setText("Could not find information about the planet you chose.");

        if (Main.to_calc_planet.getName().equals("default")){
            planet_info.setText("Welcome to Orbit simulator\n\n" +
                    "This program will calculate de desired value needed for stabel orbit\n" +
                    "please read the instructions above the input fields. This simulator is only able" +
                    " to simulate planet's without a moon. This is because a simulation with a moon would be" +
                    " outside the scope of this project and my current abilities." +
                    " Only one value can be calculated at the time.");
            planet_info.setFont(new Font(20));
            v.getChildren().add(planet_info);
        }

        if (Main.to_calc_planet.getName().equals("Venus")){
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

        if (Main.to_calc_planet.getName().equals("Mercury")){
            String content =  "Mercury \n" +
                    "Mass   : 3.3011 * 10^24 Kg\n" +
                    "Radius : 2439.7 km \n \n" +

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

        if (Main.to_calc_planet.getName().equals("Your own")){

            Label mass_label =   new Label("Mass   : ");
            Label radius_label = new Label("Radius : ");
            Label kg = new Label("Kg");
            Label m = new Label("km");
            TextField mass_imput_field = new TextField();
            TextField radius_imput_field = new TextField();

            //Sets the text of the self made planet if it has already been made once
            //This ensures that the user doesn't have to keep giving it in
            mass_imput_field.setText(Double.toString(Main.to_calc_planet.getMass()));
            radius_imput_field.setText(Double.toString(Main.to_calc_planet.getRadius() / Math.pow(10,3)));

            Button save = new Button("save");
            save.setOnAction(event -> {
                try {
                    double mass_selfmade_planet = Double.parseDouble(mass_imput_field.getText());
                    double radius_selfmade_planet = Double.parseDouble(radius_imput_field.getText());

                    Main.to_calc_planet.setMass(mass_selfmade_planet);
                    Main.to_calc_planet.setRadius(radius_selfmade_planet *  Math.pow(10,3));


                } catch (NumberFormatException e) {
                    Error.number_unparasble();
                }

                update_screen();

            });

            HBox mass_Hbox = new HBox(mass_label,mass_imput_field,kg);
            mass_Hbox.setSpacing(10);
            HBox radius_Hbox = new HBox(radius_label,radius_imput_field,m,save);
            radius_Hbox.setSpacing(10);

            //If self made planter has already been made onces gives those values below the texfields
            if ((Main.to_calc_planet.getRadius() != 0) && (Main.to_calc_planet.getMass() != 0 )){
                String info = "The mass of your self made planet is " +
                        Round.round(Main.to_calc_planet.getMass()) + "Kg \n" + "The radius of your planet is "
                        + Round.round(Main.to_calc_planet.getRadius() / Math.pow(10,3)) + " km'\n ";

                planet_info.setText(info);

            }

            v.getChildren().addAll(mass_Hbox,radius_Hbox,planet_info);
            v.setSpacing(10);
        }


        return v;

    }


    /**
     * Check which satellite has been chosen and returns corresponding information
     * @return corresponding information about chosen satellite
     */
    protected static Label get_sat_info(){
        Label sat_info = new Label();
        sat_info.setFont(new Font(17.0));
        sat_info.setMaxWidth(1000);
        sat_info.setWrapText(true);
        sat_info.setText("Could not find information about the satellite you chose.");

        if(Main.sat_non_custom_name.equals("Mariner 5")){
            String info = "Mariner 5\n\n" +
                    "Liftoff took place on June 14, 1967 and carried a complement of experiments to probe Venus.\n\n" +
                    "Its goals were to measure interplanetary and Venusian magnetic fields," +
                    " charged particles, plasma, radio activity and UV emissions of the Venusian atmosphere.\n" +
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

        if(Main.sat_non_custom_name.equals("BepiColombo")){
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

        if(Main.sat_non_custom_name.equals("Messenger")){
            String info = "Messenger \n\n" +
                    " Messenger a backronym for \" MErcury Surface, Space ENvironment, GEochemistry, and Ranging\";" +
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

        if(Main.sat_non_custom_name.equals("New Horizons")){
            String info = "New Horizons \n\n" +
                    "the spacecraft was launched in 2006" +
                    " with the primary mission to perform a flyby study of the Pluto system in 2015 " +
                    " and a secondary mission to fly by and study one or more other Kuiper belt objects" +
                    " in the decade to follow," +
                    " It is the fifth space probe to achieve the escape velocity needed to leave the Solar System." +
                    "It's mission goals are to \n" +
                    "    map the surface composition of Pluto and Charon\n" +
                    "    characterize the geology and morphology of Pluto and Charon\n" +
                    "    characterize the neutral atmosphere of Pluto and its escape rate\n" +
                    "    search for an atmosphere around Charon\n" +
                    "    map surface temperatures on Pluto and Charon\n" +
                    "    search for rings and additional satellites around Pluto\n" +
                    "    conduct similar investigations of one or more Kuiper belt objects";
            sat_info.setText(info);

        }

        if(Main.sat_non_custom_name.equals("Voyager")){
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

        if(Main.sat_non_custom_name.equals("Pioneer 11")){
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

        if(Main.sat_non_custom_name.equals("Falcon Heavy")){
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

        if(Main.sat_non_custom_name.equals("Falcon 9")){
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
}
