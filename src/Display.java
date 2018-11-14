import javafx.scene.control.Label;

class Display {
  private long current;
  private long total;
  private String currentAsString;
  private int base;
  private boolean clear;
  private String method;

  public Display() {
    current = 0;
	total = 0;
    currentAsString = "0";
    base = 0;
	clear = false;
	method = "";
  }

  public void addNumber(int input) {
    current *= 10;
    current += input;

    if(base != 0) {
	  currentAsString += input;
	} else {
      currentAsString = Integer.toString(input);
	}
	base++;
  }

  public void setMethod(String method) {
    this.method = method;
    if(!clear) {
      total = current;
      clear = true;
	  resetCurrent();
	}
  }

  public void calculate() {
    switch (method) {
	  case "/" :
	    total = total / current;
		resetCurrent();
	    break;
	  case "*" :
		total = total * current;
		resetCurrent();
		break;
	  case "-" :
		total = total - current;
		resetCurrent();
		break;
	  case "+" :
		total = total + current;
		resetCurrent();
		break;
	  case "c" :
		reset();
		break;
	}
  }

  private void resetCurrent() {
	current = 0;
	base = 0;
	currentAsString = "0";
  }

  private void reset() {
	resetCurrent();
	total = 0;
	clear = false;
	method = "";
  }

  public Label getCurrent() {
	return new Label(currentAsString);
  }

  public Label getTotal() {
	return new Label(Long.toString(total));
  }
}
