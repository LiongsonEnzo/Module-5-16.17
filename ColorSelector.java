import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ColorSelector extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a Text object
        Text text = new Text("Colorful Text");
        text.setFont(Font.font(40)); // Set font size

        // Create sliders for Red, Green, Blue, and Opacity
        Slider redSlider = new Slider(0, 255, 0);
        Slider greenSlider = new Slider(0, 255, 0);
        Slider blueSlider = new Slider(0, 255, 0);
        Slider opacitySlider = new Slider(0, 1, 1);

        // Set the slider tick marks and labels
        configureSlider(redSlider, "Red");
        configureSlider(greenSlider, "Green");
        configureSlider(blueSlider, "Blue");
        configureOpacitySlider(opacitySlider, "Opacity");

        // Add listeners to update the text color dynamically
        ChangeListener<Number> colorChangeListener = (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            updateTextColor(text, redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue(), opacitySlider.getValue());
        };

        redSlider.valueProperty().addListener(colorChangeListener);
        greenSlider.valueProperty().addListener(colorChangeListener);
        blueSlider.valueProperty().addListener(colorChangeListener);
        opacitySlider.valueProperty().addListener(colorChangeListener);

        // Layout to organize the sliders and labels
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(
                new Label("Red"), redSlider,
                new Label("Green"), greenSlider,
                new Label("Blue"), blueSlider,
                new Label("Opacity"), opacitySlider,
                text
        );

        // Create a scene and place it on the stage
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setTitle("Use Scrollbars/Sliders to Select Text Color");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper method to configure the RGB sliders
    private void configureSlider(Slider slider, String label) {
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(10);
    }

    // Helper method to configure the opacity slider
    private void configureOpacitySlider(Slider slider, String label) {
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(0.2);
        slider.setMinorTickCount(4);
        slider.setBlockIncrement(0.05);
    }

    // Update the color of the text based on slider values
    private void updateTextColor(Text text, double red, double green, double blue, double opacity) {
        Color color = Color.rgb((int) red, (int) green, (int) blue, opacity);
        text.setFill(color);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
