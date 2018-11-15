import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;

/**
 * Creates a simple calculator.
 * Build for Kent Computer Society JavaFx workshop.
 * @author HenryBrown0
 */
public class Main extends Application {
  private Stage window;
  private Display display;

  public static void main(String[] args) {
	launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
	window = primaryStage;
	display = new Display();

	window.setTitle("Simple Calculator");
	paint();
	window.show();
  }

  /**
   * Paints the new scene to the stage
   */
  private void paint() {
	VBox layout = new VBox();
	GridPane numberPad = addNumberButtons();

	layout.setPadding(new Insets(5, 0, 0, 0));
	layout.getChildren().addAll(display.getTotal(), display.getCurrent(), numberPad);

	VBox.setVgrow(numberPad, Priority.ALWAYS);

	Scene scene = new Scene(layout, 300, 500);
	scene.getStylesheets().add("main.css");

	window.setScene(scene);
  }

  /**
   * Creates the number pad for the calculator
   * @return GridPane A GridPane with the number pad
   */
  private GridPane addNumberButtons() {
	GridPane layout = new GridPane();
	Button[] btnArray = new Button[10];
	RowConstraints[] rowConst = new RowConstraints[5];
	ColumnConstraints[] colConst = new ColumnConstraints[4];

	// Set row constraints making rows responsive
	for(int i = 0; i <= 4; i ++) {
	  rowConst[i] = new RowConstraints();
	  rowConst[i].setPercentHeight(20);
	}
	layout.getRowConstraints().addAll(rowConst[0], rowConst[1], rowConst[2], rowConst[3], rowConst[4]);

	// Set column constraints making columns responsive
	for(int i = 0; i <= 3; i ++) {
	  colConst[i] = new ColumnConstraints();
	  colConst[i].setPercentWidth(25);
	}
	layout.getColumnConstraints().addAll(colConst[0], colConst[1], colConst[2], colConst[3]);

	// Create 0-9 buttons
	for(int i = 0; i <= 9; i++) {
	  int value = i;
	  btnArray[i] = new Button(Integer.toString(i));
	  btnArray[i].setMaxWidth(Double.MAX_VALUE);
	  btnArray[i].setMaxHeight(Double.MAX_VALUE);
	  btnArray[i].setOnAction(e -> {
		display.addNumber(value);
		this.paint();
	  });
	}

	// Place 0-9 buttons
	layout.add(btnArray[7], 0, 1);
	layout.add(btnArray[8], 1, 1);
	layout.add(btnArray[9], 2, 1);
	layout.add(btnArray[4], 0, 2);
	layout.add(btnArray[5], 1, 2);
	layout.add(btnArray[6], 2, 2);
	layout.add(btnArray[1], 0, 3);
	layout.add(btnArray[2], 1, 3);
	layout.add(btnArray[3], 2, 3);
	layout.add(btnArray[0], 0, 4);
	GridPane.setColumnSpan(btnArray[0], 3);

	// Divide button
	Button divide = new Button("/");
	divide.setMaxWidth(Double.MAX_VALUE);
	divide.setMaxHeight(Double.MAX_VALUE);
	divide.setOnAction(e -> {
	  display.setMethod("/");
	  this.paint();
	});
	layout.add(divide, 1, 0);

	// Multiple button
	Button multiple = new Button("*");
	multiple.setMaxWidth(Double.MAX_VALUE);
	multiple.setMaxHeight(Double.MAX_VALUE);
	multiple.setOnAction(e -> {
	  display.setMethod("*");
	  this.paint();
	});
	layout.add(multiple, 2, 0);

	// Minus button
	Button minus = new Button("-");
	minus.setMaxWidth(Double.MAX_VALUE);
	minus.setMaxHeight(Double.MAX_VALUE);
	minus.setOnAction(e -> {
	  display.setMethod("-");
	  this.paint();
	});
	layout.add(minus, 3, 1);

	// Add button
	Button add = new Button("+");
	add.setMaxWidth(Double.MAX_VALUE);
	add.setMaxHeight(Double.MAX_VALUE);
	add.setOnAction(e -> {
	  display.setMethod("+");
	  this.paint();
	});
	layout.add(add, 3, 0);

	// Clear button
	Button clear = new Button("c");
	clear.setMaxWidth(Double.MAX_VALUE);
	clear.setMaxHeight(Double.MAX_VALUE);
	clear.setOnAction(e -> {
	  display.setMethod("c");
	  display.calculate();
	  this.paint();
	});
	layout.add(clear, 0, 0);

	// Equals button
	Button equal = new Button("=");
	equal.setMaxWidth(Double.MAX_VALUE);
	equal.setMaxHeight(Double.MAX_VALUE);
	equal.setOnAction(e -> {
	  display.calculate();
	  this.paint();
	});
	layout.add(equal, 3, 1);
	GridPane.setRowSpan(equal, 4);

    return layout;
  }
}
