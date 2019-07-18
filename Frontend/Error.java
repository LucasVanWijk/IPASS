package Frontend;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class Error {

    public static void number_unparasble() {
        String title = "Number unrecognisable";
        String message = "The program could not recognise the number you typed in.\n" +
                " Please refreain from using characters\n" +
                "And when using decimal numbers write them with a period like so 16.34";

        create_window(title,message);

    }

    public static void non_zero_values(){
        String title = "Unable to recognise desired value";
        String message = "The program is unable to recognise which value you want to calculate\n " +
                "Please fill in a zero for the value you want to calculate";
        create_window(title,message);
    }

    public static void zero_mass(){
        String title = "Zero mass";
        String message = "Your mass is zero." +
                " However mass is not something that can be calculated. \n" +
                " because it is on no affect of the force pulling it to the planet. \n" +
                "You can test this by filling in a very high mass and a very low mass \n " +
                "the result will be the same ";

        create_window(title,message);
    }

    public static void two_zero(){
        String title = "Two zero's";
        String message = "You filled in two zero's. \n" +
                "One can not be calculated without the other so this is impossible. \n" +
                "please fill in one of the values.";

        create_window(title,message);
    }

    private static void create_window(String title, String message){
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(window.getWidth()   + 200);
        window.setMinHeight(window.getHeight() + 200);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Close this window");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

}