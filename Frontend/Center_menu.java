package Frontend;

import Backend.Laws;
import javafx.animation.PathTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

class Center_menu {
    private static double scale_in_km;
    private static int pixel_width;
    private static int pixel_height;
    private static double diameter_screen;


    /**
     * Returns what will be displayed on the center of the screen.
     * <P>
     *     This menu will contain information about the chosen planet, information about the chosen satellite,
     *     the results of the calculations and a to scale animation representing the orbit of the chosen satellite.
     * </P>
     ** @return A menu displaying elements on the center of the screen
     */
    static HBox create_center_menu() {

        HBox hbox = new HBox();

        //Padding makes sure that the box does not end immediately below the textfield.
        hbox.setPadding(new Insets(15, 15, 15, 15));
        hbox.setSpacing(10);


        VBox left_box = create_center_left();
        VBox right_box = create_center_right();


        hbox.getChildren().addAll(left_box,right_box);
        return hbox;

    }


    /**
     * Returns what will be displayed on the left of the center of the screen.
     * <P>
     *     This menu will contain information about the chosen planet and information about the chosen satellite.
     * </P>
     *
     * @return A menu displaying elements on the left center of the screen
     */
    private static VBox create_center_left(){


        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(15);

        //Creates a text with information about the satellite
        Label info_sat = Events_and_reactions.get_sat_info();

        //creates a or text with information about the planet. Or a menu to create your own planet
        //This is also why it is a VBox and not a label because it might not only contain a label but also 2 text fields.
        VBox info_planet = Events_and_reactions.get_planet_info();

        vbox.getChildren().addAll(info_planet,info_sat);
        return vbox;

    }


    /**
     * Returns what will be displayed on the center right of the screen.
     * <P>
     *     This menu will contain the results of the calculations
     *     and a to scale animation of the orbit of the chosen satellite.
     * </P>
     *
     * @return A menu displaying elements on the center right of the screen
     */
    private static VBox create_center_right(){

        VBox vbox = new VBox();

        //Evaluates if the satellite and the planet have both been filled in
        //If so it will calculate the request parameters and so information about the orbit of the satellite

        if ((Main.to_calc_planet.getName().equals("default") == false) && Main.to_calc_sat.getName().equals("default") == false ) {

            //Calculates/receives the parameters of the orbit
            Main.to_calc_planet.calc_sat_parameters(Main.to_calc_sat);
            double velocity = Main.to_calc_sat.getVelocity() / Math.pow(10,3);
            double velocity_kmh = velocity * 3600;
            double mass = Main.to_calc_sat.getMass();
            double distance_core = Main.to_calc_sat.getOrbit_distance() / Math.pow(10,3);
            double distance_surface = (distance_core - (Main.to_calc_planet.getRadius() / Math.pow(10,3)));
            double time_seconds = Laws.calc_orbit_time((distance_core * Math.pow(10,3)), (velocity* Math.pow(10,3)));
            double time_hours = time_seconds/60/60;

            Label calculations = new Label();

            /*
            Evaluates if the results from the calculations would be possible in reality.
            If so displays the variables rounded in text form to the screen
            If not displays a text saying the results would be impossible in reality and the displays the results
            of the calculations in text form to the screen.
            */

            if (velocity > 0 && distance_core > 0 && distance_surface > 0 && time_seconds > 0) {
                String content = "The velocity or speed of the craft is " + Round.round(velocity) + " km/s " +
                        " which is " + Round.round(velocity_kmh) + " km/h. \n" +
                        "The mass of the craft is " + Round.round(mass) + " Kg. \n" +
                        "The distance from its surface is " + Round.round(distance_surface) + " km" +
                        " and from its core " + Round.round(distance_core) + "km. \n" +
                        "It takes " + Round.round(time_seconds) +
                        " seconds to complete a orbit or " + Round.round(time_hours) + " Hours \n ";
                calculations.setText(content);

            }
            else{
                String content = "These values would result in impossible outcomes \n" +
                        " You would get the following values. which can never be true \n\n " +
                        "The velocity or speed of the craft is " + Round.round(velocity) + " km/s \n " +
                        " which is " + Round.round(velocity_kmh) + " km/h. " +
                        "The mass of the craft is " + Round.round(mass) + " kg \n" +
                        "The distance from its surface is " + Round.round(distance_surface) +
                        " km and from its core " + Round.round(distance_core) + " km \n" +
                        "It takes " + Round.round(time_seconds) +
                        " seconds to complete a orbit or " + Round.round(time_hours) + " Hours\n ";

                calculations.setText(content);
            }

            //Ads both the result of the calculation and the to scale animation to the menu
            vbox.getChildren().addAll(calculations,animation());

        }
        return vbox;
    }


    /**
     * Creates a to scale animation of the orbit
     * <p>
     *     Creates a to scale animation of the orbit of the satellite around the planet. With the given parameters
     *     of both
     * </p>
     *
     * @return a to scale animation of the orbit
     */
    private static VBox animation(){

        //Creates a button and a circle which will represent the satellite and planet in the animation.
        Button sat = new Button();
        Circle planet = new Circle();

        /*
        Creates a box where you need to fill in the size and resolution of your screen
        This is needed so that a scale can be determent for how many pixels is 1 centimeter.
        This is needed because for the animation de distance of the satellite needs to be given in pixels.
        While the distance in reality is in kilometers.
        */
        HBox settings_box = create_setting_box();

        //Creates a box in which the animation will be displayed
        HBox animation_box = create_animation_box(planet,sat);

        transition(sat,planet);
        VBox return_box = new VBox();

        return_box.getChildren().addAll(settings_box,animation_box);
        return_box.setSpacing(10);

        return return_box;
    }


    /**
     * Creates a box in which the animation will be displayed
     * @param planet the circle which will represent the planet
     * @param sat the button which will represent the planet
     * @return A box for the animation to be displayed
     */
    private static HBox create_animation_box(Circle planet, Button sat){

        HBox animation_box = new HBox();
        animation_box.setAlignment(Pos.CENTER);
        double ppi = calc_ppi(pixel_width, pixel_height, diameter_screen);
        double amount_pixels_for_11_cm = (ppi / 2.54) * 11;
        //Sets the width and the height to 5 centimeters
        animation_box.setMaxSize(amount_pixels_for_11_cm,amount_pixels_for_11_cm);
        animation_box.setMinSize(amount_pixels_for_11_cm,amount_pixels_for_11_cm);


        double distance = Main.to_calc_planet.getRadius() / Math.pow(10,3);

        double radius = distance_to_pixels(distance);

        //Sets the maximum size of the planet to 580 pixels in diameter
        //So that the planet will not take up the whole screen
        if (radius > 600){
            radius = 290;
        }

        planet.setRadius(radius);

        //Sets the image of the planet to the circle
        if(Main.to_calc_planet.getName().equals("Mercury")){
            Image a = new Image("Jpg/Mercury.JPG");
            planet.setFill(new ImagePattern(a));
        }
        else if(Main.to_calc_planet.getName().equals("Venus")){
            Image a = new Image("Jpg/Venus.JPG");
            planet.setFill(new ImagePattern(a));
        }
        else {
            Image a = new Image("Jpg/Earth.JPG");
            planet.setFill(new ImagePattern(a));
        }



        animation_box.setStyle("-fx-background-image: url('Jpg/Space.JPG');" );
        animation_box.getChildren().addAll(planet ,sat);

        return animation_box;
    }


    /**
     * Creates a box where you need to fill in the size and resolution of your screen
     * <p>
     *     Creates a box where you need to fill in the size and resolution of your screen
     *     This is needed so that a scale can be determent for how many pixels is 1 centimeter.
     *     This is needed because for the animation de distance of the satellite needs to be given in pixels.
     *     While the distance in reality is in kilometers.
     * </p>
     * @return a box to fill in information about your screen
     */
    private static HBox create_setting_box(){


        //Creates a dropdown with the most common resolutions
        ChoiceBox<String> resolution_menu = create_Resolution_dropdown();
        Label resolution_menu_description = new Label("Resolution");
        VBox res = new VBox(resolution_menu_description,resolution_menu);

        //Creates a textfield to fill in you screen size (diameter across) and a label above it.
        TextField screen_size_input_field = new TextField("in inches");
        screen_size_input_field.setMaxWidth(80);
        screen_size_input_field.setText("15.6");
        Label screen_size_description = new Label("Screen size");
        VBox screen = new VBox(screen_size_description,screen_size_input_field);


        Label scale_description = new Label("Scale");
        HBox scale_setter = new HBox();

        double distance_core = Main.to_calc_sat.getOrbit_distance() / Math.pow(10,3);

        double kilometers_in_reality = get_scale(distance_core);
        Label text = new Label("1 centimeter on screen is " + Round.round(kilometers_in_reality)+ "km in reality");



        scale_setter.getChildren().addAll(text);
        scale_setter.setSpacing(3);

        VBox scale = new VBox(scale_description,scale_setter);

        HBox setting_box = new HBox();
        setting_box.setSpacing(25);
        setting_box.getChildren().addAll(res,screen,scale);

        set_global_animation_settings(kilometers_in_reality,resolution_menu,screen_size_input_field);

        return setting_box;
    }


    /**
     *Creates a scale
     *<p>
     *Creates a scale by dividing a number by 10 until it is les than 10.
     *After that it will check if the number is greater than 5 if so it will multiply it by 5.
     *
     *It does this so that distance/scale will never be greater than 5
     *This is because distance/scale will result in the amount of centimeters between the satellite and
     *the planet and this can not be greater than the size of the animation box (11 centimeters)
     *
     *
     *</p>
     * @param number_to_get_scale the number for which a relevant scale needs to be found
     * @return a scale
     */
    private static double get_scale(double number_to_get_scale){


        double new_number = number_to_get_scale;
        double scale = 1;

        while (new_number > 10) {
            new_number = new_number / 10;
            scale = scale * 10;
        }

        if (new_number > 5)
            scale = scale*5;


        else if (new_number < 2.5)
            scale = scale/2;

        return scale;

    }


    /**
     * Sets values needed for the animation global
     * @param kilometers_in_reality the scale which will be used
     * @param resolution the resolution of the screen
     * @param screen_size the size of the screen
     */
    private static void set_global_animation_settings(double kilometers_in_reality,
                                               ChoiceBox<String> resolution, TextField screen_size){

        scale_in_km = kilometers_in_reality;

        String string_resolution = resolution.getValue();

        String[] resolution_split = string_resolution.split("x");
        String pixel_width_string = resolution_split[0];
        String pixel_height_string = resolution_split[1];

        //Removes the spaces from the string
        pixel_width_string = pixel_width_string.substring(0,(pixel_width_string.length()-1));
        pixel_height_string = pixel_height_string.substring(1);

        //Converts the string to int
        pixel_width = Integer.parseInt(pixel_width_string);
        pixel_height = Integer.parseInt(pixel_height_string);


        try{
            String screen_size_value = screen_size.getText();
            diameter_screen = Double.parseDouble(screen_size_value);
        }
        catch (NumberFormatException e){
            Error.number_unparasble();
        }


    }

    /**
     * Creates a dropdown menu
     * @return A dropdown menu
     */
    private static ChoiceBox<String> create_Resolution_dropdown(){

        ChoiceBox<String> resolution_menu = new ChoiceBox<>();
        resolution_menu.getItems().add("1280 x 1024");
        resolution_menu.getItems().add("1366 x 768");
        resolution_menu.getItems().add("1600 x 900");
        resolution_menu.getItems().add("1920 x 1080");
        resolution_menu.getItems().add("1920 x 1200");
        resolution_menu.getItems().add("2560 x 1440");
        resolution_menu.getItems().add("3440 x 1440");
        resolution_menu.getItems().add("3840 x 2160");
        resolution_menu.setValue("1920 x 1080");

        return resolution_menu;
    }


    /**
     * Calculates ppi (Pixels per inch)
     * @param with_pixels the width of the screen in pixels
     * @param height_pixels the height of the screen in pixels
     * @param screen_size the size of the screen in diameter across
     * @return ppi
     */
    private static double calc_ppi(int with_pixels, int height_pixels, double screen_size){

        double pixels_diagonal_squared = Math.pow(with_pixels,2) + Math.pow(height_pixels,2);
        double pixels_diagonal = Math.pow(pixels_diagonal_squared,0.5);

        return pixels_diagonal / screen_size;
    }


    /**
     * Converts a given distance (in km) to a distance in pixels between elements
     * @param distance_in_km the distance in kilometers between two objects
     * @return the distance in pixels between two objects
     */
    private static double distance_to_pixels(double distance_in_km){

        double ppi = calc_ppi(pixel_width, pixel_height, diameter_screen);
        double pixels_per_cm = ppi / 2.54;


        double distance_in_cm = distance_in_km / scale_in_km;
        return distance_in_cm * pixels_per_cm;
    }

    /**
     * Makes object (sat) move around object (planet) in a circle
     * <p>
     *     It receives a button and based on the distance in reality, scale and ppi(pixels per inch of the screen)
     *     Makes this button move in a circle around the Circle on the screen. The radius of the motion of the satellite
     *     is based on the distance in reality, scale and ppi.
     * </p>
     * @param sat the button which wil represent the satellite
     * @param planet the circle which will represent the planet
     */
    private static void transition(Button sat, Circle planet){

        double distance_in_pixels = distance_to_pixels(Main.to_calc_sat.getOrbit_distance()/Math.pow(10,3));

        //Create new path transition
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(10000));

        //Set node to be animated
        pathTransition.setNode(sat);

        /*
        For some reason the Button (sat) chooses the right end point of the circle as the position to move around.
        This should be the center this code removes the radius on time from the default position (the right side of
        circle ) to get the center of the circle
        */
        double x = planet.getLayoutY() -  planet.getRadius();

        //Sets the path for the button to take
        pathTransition.setPath(new Circle(x,planet.getLayoutY() ,distance_in_pixels));

        pathTransition.setCycleCount(30);

        pathTransition.play();
        Main.window.show();
    }
}
