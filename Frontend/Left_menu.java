package Frontend;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Left_menu {
    /**
     * Returns a menu which will be displayed on the left of the screen.
     * <P>
     *     This menu will contain 3 buttons to select a planet and 3 images to represent the planets
     *     The elements will be stacked vertically. (VBox stacks it children vertically)
     * </P>
     *
     * @return A VBox containing all elements for the left menu
     */
    public static VBox create_left_menu() {


        //Left menu
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(15);

        //Projects images above the buttons
        Image venus_image = new Image("Jpg/Venus.JPG", 0, 200, true, false);
        ImageView venus_imageView = new ImageView(venus_image);

        Image mercury_image = new Image("Jpg/Mercury.JPG", 0, 200, true, false);
        ImageView mercury_imageView = new ImageView(mercury_image);

        Image self_image = new Image("Jpg/Own_planet.JPG", 0, 200, true, false);
        ImageView self_imageView = new ImageView(self_image);

        //Creates all the buttons
        Main.venus = Template_button.create_basic_button("Venus");
        Main.mercury = Template_button.create_basic_button("Mercury");
        Main.self_made_planet = Template_button.create_basic_button("Make your own planet");

        //Adds all buttons to the left menu
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(venus_imageView, Main.venus, mercury_imageView, Main.mercury, self_imageView, Main.self_made_planet);

        return vbox;


    }
}
