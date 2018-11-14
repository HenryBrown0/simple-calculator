import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class Main extends Application {
  private Stage window;
  private Display display;

  @Override
  public void start(Stage primaryStage) throws Exception {
	window = primaryStage;
	display = new Display();

	window.setTitle("Simple Calculator");
	paint();
  }

  private void paint() {
	VBox layout = new VBox();
	GridPane numberPad = addNumberButtons(display);

	layout.getChildren().addAll(display.getTotal(), display.getCurrent(), numberPad);

	window.setScene(new Scene(layout));

	window.show();
  }

  private GridPane addNumberButtons(Display display) {
	GridPane layout = new GridPane();
	Button[] btnArray = new Button[10];

	layout.setHgap(10);
	layout.setVgap(10);
	layout.setPadding(new Insets(0, 10, 0, 10));

	for(int i = 0; i <= 9; i++) {
	  int value = i;
	  btnArray[i] = new Button(Integer.toString(i));
	  btnArray[i].setOnAction(e -> {
		display.addNumber(value);
		this.paint();
	  });
	}

	layout.add(btnArray[7], 0, 1);
	layout.add(btnArray[8], 1, 1);
	layout.add(btnArray[9], 2, 1);
	layout.add(btnArray[4], 0, 2);
	layout.add(btnArray[5], 1, 2);
	layout.add(btnArray[6], 2, 2);
	layout.add(btnArray[1], 0, 3);
	layout.add(btnArray[2], 1, 3);
	layout.add(btnArray[3], 2, 3);
	layout.add(btnArray[0], 1, 4);

	Button divide = new Button("/");
	divide.setOnAction(e -> {
	  display.setMethod("/");
	  this.paint();
	});
	layout.add(divide, 1, 0);

	Button multiple = new Button("*");
	multiple.setOnAction(e -> {
	  display.setMethod("*");
	  this.paint();
	});
	layout.add(multiple, 2, 0);

	Button minus = new Button("-");
	minus.setOnAction(e -> {
	  display.setMethod("-");
	  this.paint();
	});
	layout.add(minus, 3, 1);

	Button add = new Button("+");
	add.setOnAction(e -> {
	  display.setMethod("+");
	  this.paint();
	});
	layout.add(add, 3, 2);

	Button clear = new Button("c");
	clear.setOnAction(e -> {
	  display.setMethod("c");
	  display.calculate();
	  this.paint();
	});
	layout.add(clear, 0, 0);

	Button equal = new Button("=");
	equal.setOnAction(e -> {
	  display.calculate();
	  this.paint();
	});
	layout.add(equal, 3, 4);


    return layout;
  }


  public static void main(String[] args) {
        launch(args);
    }
}
