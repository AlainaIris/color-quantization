public class Color {
	double r;
	double g;
	double b;
	int index;
	public Color(double r, double g, double b) {
		setColor(r,g,b);
	}

	public Color(double r, double g, double b, int index) {
		setColor(r,g,b);
		this.index = index;
	}

	public void setColor(double r, double g, double b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public double[] getColor() {
		return new double[] {r, g, b};
	}

	public int getIndex() {
		return index;
	}

	public String toString() {
		return "R: " + r + " G: " + g + " B: " + b;
	}
}
