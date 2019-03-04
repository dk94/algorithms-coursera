
public class BruteCollinearPoints {
    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        for(int i=0; i<points.length; i++) {
            Point firstPoint = points[i];
            for(int j=i+1; j<points.length; j++) {
                Point secondPoint = point[j];
                for(int k=j+1; k)
            }
        }
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments;
    }
}