import java.util.Arrays;

public class FastCollinearPoints {

	private LineSegment[] segments;
	private int count = 0;

	public FastCollinearPoints(Point[] points) {
		segments = new LineSegment[0];
		if (points == null) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i < points.length; i++) {
			if (points[i] == null) {
				throw new IllegalArgumentException();
			}

			Point[] aux = new Point[points.length];
			for (int m = 0; m < points.length; m++) {
				aux[m] = points[m];
			}

			Arrays.sort(aux, points[i].slopeOrder());

			int countPoints = 1;
			for (int j = 2; j < aux.length; j++) {
				if (aux[j] == null || aux[j - 1] == null || points[i].slopeTo(aux[j]) == Double.NEGATIVE_INFINITY
						|| points[i].slopeTo(aux[j - 1]) == Double.NEGATIVE_INFINITY) {
					throw new IllegalArgumentException();
				}

				if (points[i].slopeTo(aux[j]) == points[i].slopeTo(aux[j - 1])) {
					if (countPoints == 1)
						countPoints += 2;
					else
						countPoints++;
				}

				if (points[i].slopeTo(aux[j]) != points[i].slopeTo(aux[j - 1]) | j == aux.length - 1) {
					if (countPoints >= 4) {
						Point[] selectedPoints = new Point[countPoints];

						selectedPoints[0] = points[i];
						int auxC;
						if (j == aux.length - 1 & points[i].slopeTo(aux[j]) == points[i].slopeTo(aux[j - 1]))
							auxC = j;
						else
							auxC = j - 1;
						while (countPoints > 1) {
							selectedPoints[countPoints - 1] = aux[auxC--];
							countPoints--;
						}

						Arrays.sort(selectedPoints);

						boolean found = false;
						LineSegment newLine = new LineSegment(selectedPoints[0],
								selectedPoints[selectedPoints.length - 1]);
						for (int l = 0; l < segments.length; l++) {
							if (segments[l].toString().equals(newLine.toString())) {
								found = true;
							}
						}

						if (!found) {

							if (segments.length == count) {
								LineSegment[] newSegment = new LineSegment[count + 1];
								for (int k = 0; k < segments.length; k++) {
									newSegment[k] = segments[k];
								}
								segments = newSegment;
							}
							segments[count++] = newLine;

						}

					}
					countPoints = 1;
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
