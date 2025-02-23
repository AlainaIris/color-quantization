import java.util.ArrayList;

public class Octree {
	Color center;
	double range;
	Octree[] children;
	ArrayList<Color> points;
	final static int[][] splits = {
		{1, 1, 1},
		{1, 1, -1},
		{1, -1, 1},
		{1, -1, -1},
		{-1, 1, 1},
		{-1, 1, -1},
		{-1, -1, 1},
		{-1, -1, -1}
	};
	public Octree(Color center, double range) {
		this.center = center;
		this.range = range;
		children = new Octree[8];
		points = new ArrayList<Color>();
	}

	public Octree(Color center, double range, ArrayList<Color> points) {
		this.center = center;
		this.range = range;
		this.points = points;
		children = new Octree[8];
	}

	public ArrayList<Color> getPoints() {
		return points;
	}

	public Octree[] getChildren() {
		return children;
	}

	public double getRange() {
		return range;
	}

	public void split() {
		if (range < 0.25) {
			throw new RuntimeException();
		} else {
			ArrayList<ArrayList<Color>> cubes = new ArrayList<ArrayList<Color>>();
			cubes.add(new ArrayList<Color>());
                        cubes.add(new ArrayList<Color>());
                        cubes.add(new ArrayList<Color>());
                        cubes.add(new ArrayList<Color>());
                        cubes.add(new ArrayList<Color>());
                        cubes.add(new ArrayList<Color>());
                        cubes.add(new ArrayList<Color>());
                        cubes.add(new ArrayList<Color>());
			double[] centerC = center.getColor();
			for (Color c : points) {
				double[] rgb = c.getColor();
				boolean largeR = rgb[0] > centerC[0];
				boolean largeG = rgb[1] > centerC[1];
				boolean largeB = rgb[2] > centerC[2];
				if  (largeR) {
					if (largeG) {
						if (largeB) {
							cubes.get(0).add(c);
						} else {
							cubes.get(1).add(c);
						}
					} else {
						if (largeB) {
							cubes.get(2).add(c);
						} else {
							cubes.get(3).add(c);
						}
					}
				} else {
					if (largeG) {
						if (largeB) {
							cubes.get(4).add(c);
						} else {
							cubes.get(5).add(c);
						}
					} else {
						if (largeB) {
							cubes.get(6).add(c);
						} else {
							cubes.get(7).add(c);
						}
					}
				}
			}
			for (int i = 0; i < 8; i++) {
				double[] pos = center.getColor();
				for (int j = 0; j < 3; j++) {
					pos[j] += splits[i][j] * range / 4.0;
				}

				children[i] = new Octree(new Color(pos[0], pos[1], pos[2]), range / 2, cubes.get(i));
			}
		}
	}

	public int numPoints() {
		return points.size();
	}
}
