package Frontend;

import Frontend.Events_and_reactions;
import javafx.scene.control.Button;

import java.util.HashMap;

public class Template_button {
    /**
     * Creates a basic button
     * @param name
     * @return A basic button
     */
    public  static Button create_basic_button(String name){


        Button x = new Button(name);
        x.setMinSize(180, 10);
        Events_and_reactions handler = new Events_and_reactions();
        x.setOnAction(handler);

        return x;
    }


    /**
     * Creates a button specif for the satellite configuration
     * @param name
     * @param dict
     * @return A button specific for the satellites
     */
    public static Button create_sat_button(String name, HashMap dict){


        Button x = new Button(name);
        x.setMinSize(180, 10);
        x.setOnAction(event -> {

            Object na = dict.get(name);
            String name_sat = na.toString();

            Main.mass.setText(name_sat);
            Main.sat_non_custom_name = name;

            Events_and_reactions.update_screen();

        });

        return x;
    }
}
