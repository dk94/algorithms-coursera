import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	public FastCollinearPoints(Point[] points) {
		if(points == null) {
    		throw new IllegalArgumentException();
    	}
		
		Point base = points[0];
	
		Arrays.sort(points, base.slopeOrder());
		
		
		for(int i =1; i<points.length; i++) {
			StdOut.println(points[i] +" " +base.slopeTo(points[i]));
		}
			
	}
	
	public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(0.007);
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        
        for (Point p : points) {
            StdOut.println(p);
            StdDraw.point(p.x, p.y);
        }
        StdDraw.show();
        
        FastCollinearPoints collinear = new FastCollinearPoints(points);
//        for (LineSegment segment : collinear.segments()) {
//              StdOut.println(segment);
//              StdDraw.line(segment.p.x, segment.p.y, segment.q.x, segment.q.y);
//        }
//        StdDraw.show();
    }
}
