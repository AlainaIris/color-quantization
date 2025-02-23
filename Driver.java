import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;

public class Driver {
	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.out.println("Please provide a file name and # of colors permitted");
		} else if (args.length == 1) {
			System.out.println("Please provide number of colors");
		}
		File file = new File(args[0]);
		int colors = Integer.parseInt(args[1]);
		BufferedImage img = ImageIO.read(file);
		int w = img.getWidth();
		int h = img.getHeight();
		ArrayList<Color> points = new ArrayList<Color>();
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				int clr = img.getRGB(i, j);
				double r = (double) ((clr & 0x00ff0000) >> 16);
				double g = (double) ((clr & 0x0000ff00) >> 8);
				double b = (double) (clr & 0x000000ff);
				points.add(new Color(r, g, b, i * h + j));
			}
		}
		Octree o = new Octree(new Color(127.5, 127.5, 127.5), 127.5, points);
		ArrayList<Octree> trees = new ArrayList<Octree>();
		trees.add(o);
		int size = 1;
		int max;
		int index;
		Octree tree;
		int numPoints;
		boolean splitting = true;
		HashSet<Octree> finalTrees = new HashSet<Octree>();
		while (splitting && size < colors) {
			max = 0;
			index = 0;
			int treesize = trees.size();
			for (int i = 0; i < treesize; i++) {
				tree = trees.get(i);
				numPoints = tree.numPoints();
				if (numPoints > max) {
					max = numPoints;
					o = tree;
					index = i;
				}
			}
			try {
				o.split();
				Octree[] children = o.getChildren();
				for (int i = 0; i < 8; i++) {
					if (children[i].numPoints() > 0) {
						trees.add(children[i]);
					}
				}
			} catch (RuntimeException e) {
				finalTrees.add(o);
			} finally {
				if (treesize == 0) {
					splitting = false;
				} else {
					trees.remove(index);
					size = trees.size() + finalTrees.size();
				}
			}
		}
		for (Octree t : trees) {
			finalTrees.add(t);
		}
		Color[] quantizedPoints = new Color[w * h];
		for (Octree t : finalTrees) {
			points = t.getPoints();
			numPoints = points.size();
			double r = 0;
			double g = 0;
			double b = 0;
			for (Color c : points) {
				double[] clr = c.getColor();
				r += clr[0];
				g += clr[1];
				b += clr[2];
			}
			r /= (double) numPoints;
			g /= (double) numPoints;
			b /= (double) numPoints;
			if (r % 1.0 < 0.5) {
				r = (double) (int) r;
			} else {
				r += 1.0 - r % 1.0;
			}
                        if (g % 1.0 < 0.5) {
                                g = (double) (int) g;
                        } else {
                                g += 1.0 - g % 1.0;
                        }
                        if (b % 1.0 < 0.5) {
                                b = (double) (int) b;
                        } else {
                                b += 1.0 - b % 1.0;
                        }
			for (Color c : points) {
				c.setColor(r,g,b);
				quantizedPoints[c.getIndex()] = c;
			}
		}
		BufferedImage newImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				Color c = quantizedPoints[i * h + j];
				double[] rgb = c.getColor();

				int hex = ((int) rgb[0]) << 16 | ((int) rgb[1]) << 8 | ((int) rgb[2]);
				newImg.setRGB(i, j, hex);
			}
		}

		File ofile = new File("o.png");
		try {
			ImageIO.write(newImg, "png", ofile);
		} catch (IOException e) {
			System.out.println("Failed to output " + e);
		}
	}
}
