import javafx.scene.control.Label;

/**
 * Creates a display for the calculator.
 * Build for Kent Computer Society JavaFx workshop.
 * @author HenryBrown0
 */
class Display {
  private long current;
  private long total;
  private String method;

  /**
   * Create the display
   */
  public Display() {
    current = 0;
	total = 0;
	method = "";
  }

  /**
   * Adds a number to the screen and current
   * @param input The input from the number pad
   */
  public void addNumber(int input) {
    current *= 10;
    current += input;
  }

  /**
   * Sets the new method
   * @param method The method from the number pad
   */
  public void setMethod(String method) {
    this.method = method;
    if(total == 0)
		total = current;
	current = 0;
  }

  /**
   * Calculates the query and then resets the current value
   */
  public void calculate() {
    switch (method) {
	  case "/" :
	    total = total / current;
		current = 0;
	    break;
	  case "*" :
		total = total * current;
		current = 0;
		break;
	  case "-" :
		total = total - current;
		current = 0;
		break;
	  case "+" :
		total = total + current;
		current = 0;
		break;
	  case "c" :
		reset();
		break;
	}
  }

  /**
   * Resets the Calculator
   */
  private void reset() {
	current = 0;
	total = 0;
	method = "";
  }

  /**
   * Gets the current input as a Label
   * @return Label The current input
   */
  public Label getCurrent() {
	return new Label(Long.toString(current));
  }

  /**
   * Gets the current total as a Label
   * @return Label The total input
   */
  public Label getTotal() {
	return new Label(Long.toString(total));
  }
}
