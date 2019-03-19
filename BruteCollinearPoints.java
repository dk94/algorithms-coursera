import java.util.Arrays;

public class BruteCollinearPoints {
	private LineSegment[] segments;
	private int count = 0;

	public BruteCollinearPoints(Point[] points) {
		if (points == null) {
			throw new IllegalArgumentException();
		}
		segments = new LineSegment[0];
		for (int i = 0; i < points.length; i++) {
			Point firstPoint = points[i];
			if (firstPoint == null)
				throw new IllegalArgumentException();
			for (int j = i + 1; j < points.length; j++) {
				Point secondPoint = points[j];
				double slopeFirstSecond = firstPoint.slopeTo(secondPoint);
				if (secondPoint == null | slopeFirstSecond == Double.NEGATIVE_INFINITY)
					throw new IllegalArgumentException();
				for (int k = j + 1; k < points.length; k++) {
					Point thirdPoint = points[k];
					double slopeSecondThird = secondPoint.slopeTo(thirdPoint);
					if (thirdPoint == null | slopeSecondThird == Double.NEGATIVE_INFINITY)
						throw new IllegalArgumentException();
					if (slopeFirstSecond == slopeSecondThird) {
						for (int l = k + 1; l < points.length; l++) {
							Point fourthPoint = points[l];
							double slopeThirdFourth = thirdPoint.slopeTo(fourthPoint);
							if (fourthPoint == null | slopeThirdFourth == Double.NEGATIVE_INFINITY)
								throw new IllegalArgumentException();
							if (slopeSecondThird == slopeThirdFourth) {
								Point[] selectedPoints = new Point[] { firstPoint, secondPoint, thirdPoint,
										fourthPoint };
								Arrays.sort(selectedPoints);
								if (segments.length == count) {
	                                LineSegment[] newSegment = new LineSegment[count + 1];
	                                for (int p = 0; p < segments.length; p++) {
	                                    newSegment[p] = segments[p];
	                                }
	                                segments = newSegment;
	                            }
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
    }
}