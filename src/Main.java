/*
 * @author Talha Guzel 041802046
 * @since 18.11.2020
 *
 * Create random points (10-20-500) and find 
 * the smallest convex polygon that encloses these points.
 * */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Stack;

public class Main {



	public static Ball[] CreatePoints(int size) {//Creating points
		Ball[] point = new Ball[size] ;
		for(int i=0; i< size ;i++) { // Creates a random individual
			double randomX,randomY;
			randomX= 1 * new Random().nextDouble();//creating random x coordinates
			randomY= 1 * new Random().nextDouble();//creating random y coordinates
			point[i] = new Ball(randomX,randomY,i);

		}
		return point;

	}
	public static void drawPoints(Ball[] point) {//Draw points
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);//set pencil color
		StdDraw.setPenRadius(0.02);//set pencil border
		for(int i =0;i<point.length;i++) {
			StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
			StdDraw.point(point[i].GetX(), point[i].GetY());//draw points
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(point[i].GetX(), point[i].GetY(),point[i].name+"");
		}

	}

	public static void main(String[] args) {
		//canvas definition
		int canvas_width = 600;// area size
		int canvas_height = 600; // area size
		StdDraw.setCanvasSize(canvas_width, canvas_height);
		StdDraw.setXscale(0, 1); 
		StdDraw.setYscale(0, 1); 

		Ball[] point = CreatePoints(10) ;//create an array for points
		
		drawPoints(point);
		StdDraw.show();
		SortPoints(point);
		
		Ball[] sortedangularly = SortPointAngularly(point);
	
		Stack<Ball> a = intothestack(sortedangularly);


		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.setPenRadius(0.02);
	
		for(int i=0;i<point.length-1;i++) {
			Ball t = a.lastElement(); 
			a.pop();
			Ball second = a.lastElement(); 
			a.push(t);

			StdDraw.line(t.GetX(), t.GetY(), second.GetX(), second.GetY());
		}
	}

public static double calculateAngle(Ball a, Ball b) {//calculate the angel between points
		double xDiff = b.GetX() - a.GetX();
		double  yDiff = b.GetY() - a.GetY();
		return Math.toDegrees(Math.atan2(yDiff, xDiff));

	}

/*
 * Take the lowest point and rank the others by angle
 */
	public static Ball[] SortPointAngularly(Ball[] p ) {
		Ball[] angularly = new Ball[p.length];
		ArrayList<Ball> list = new ArrayList<Ball>();
		for(int i=0;i<p.length;i++) {
			list.add(p[i]);
		}
		
		Ball e = list.get(0);
		list.remove(0);
		 double minimumangle =360;
		 Ball item=null;
		 for (int i = 0; i < p.length-1; i++) {
			 	if (calculateAngle(e,list.get(i))<minimumangle) {
		                	minimumangle =calculateAngle(e,list.get(i));
		                	item = list.get(i);
		                }
		        }
		        return angularly;
		    }
	
		
	
	
// Method push first three element of array to Stack
	public static Stack<Ball> intothestack(Ball[] p){
		Stack<Ball> H = new Stack<Ball>();
		H.push(p[0]);
		H.push(p[1]);
		H.push(p[2]);
	//Push element according to position with others
		int i = 3;
		while (i < p.length) {
			Ball t1 = H.peek(); 
			H.pop();
			Ball t2 = H.peek(); 
			H.push(t1);

			if (((t2.GetX()-t1.GetX())*(p[i].GetY()-t1.GetY())-((p[i].GetX()-t1.GetX())*(t2.GetY()-t1.GetY())))>=0) {
				H.push(p[i]);
				i++;
			}else {
				H.pop();
			}
		}
		return H;
	}


/*
 * Sort by y values from small to large to find the bottom
 */
	public static void SortPoints(Ball[] p) {

		Arrays.sort(p, new Comparator<Ball>() {
			@Override
			public int compare(Ball emp1, Ball emp2) {
				return Double.compare(emp1.GetY(), emp2.GetY());
			}
		});
	}
}
