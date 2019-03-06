import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
public class BruteCollinearPoints {
    private LineSegment[] segments;
    private int count = 0;

    public BruteCollinearPoints(Point[] points) {
    	if(points == null) {
    		throw new IllegalArgumentException();
    	}
        segments = new LineSegment[points.length];
        for(int i=0; i<points.length; i++) {
            Point firstPoint = points[i];
            if(firstPoint == null) throw new IllegalArgumentException();
            for(int j=i+1; j<points.length; j++) {
                Point secondPoint = points[j];
                double slopeFirstSecond = firstPoint.slopeTo(secondPoint);
                if(secondPoint == null | slopeFirstSecond == Double.NEGATIVE_INFINITY ) throw new IllegalArgumentException();
                for(int k=j+1; k<points.length; k++) {
                    Point thirdPoint = points[k];
                    double slopeSecondThird = secondPoint.slopeTo(thirdPoint);
                    if(thirdPoint == null | slopeSecondThird == Double.NEGATIVE_INFINITY ) throw new IllegalArgumentException();
                    if(slopeFirstSecond == slopeSecondThird) {
                        for(int l=k+1; l<points.length; l++) {
                            Point fourthPoint = points[l];
                            double slopeThirdFourth = thirdPoint.slopeTo(fourthPoint);
                            if(fourthPoint == null | slopeThirdFourth == Double.NEGATIVE_INFINITY ) throw new IllegalArgumentException();
                            if(slopeSecondThird == slopeThirdFourth) {
                               Point[] selectedPoints = new Point[]{firstPoint, secondPoint, thirdPoint, fourthPoint};
                                Arrays.sort(selectedPoints);
                                segments[count++] = new LineSegment(selectedPoints[0], selectedPoints[3]);
                            }
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return count;
    }

    public LineSegment[] segments() {
        return segments;
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
        
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
              StdOut.println(segment);
              if(segment != null) {
                  StdDraw.line(segment.p.x, segment.p.y, segment.q.x, segment.q.y);
              }
        }
        StdDraw.show();
    }
}