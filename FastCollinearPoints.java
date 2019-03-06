import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

    private LineSegment[] segments;
    private int count = 0;

    public FastCollinearPoints(Point[] points) {
        segments = new LineSegment[points.length];
        if (points == null) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }

            Point[] aux = new Point[points.length - 1 - i];
            int newCount = 0;
            for (int m = i + 1; m < points.length; m++) {
                aux[newCount] = points[m];
                newCount++;
            }

            Arrays.sort(aux, points[i].slopeOrder());
            for (int w = 0; w < aux.length; w++) {
                StdOut.println(aux[w] + " " + points[i]);
            }

            StdOut.println();
            int countPoints = 1;
            for (int j = 1; j < aux.length; j++) {
                if (aux[j] == null || aux[j - 1] == null || points[i].slopeTo(aux[j]) == Double.NEGATIVE_INFINITY
                        || points[i].slopeTo(aux[j - 1]) == Double.NEGATIVE_INFINITY) {
                    throw new IllegalArgumentException();
                }

                if (points[i].slopeTo(aux[j]) == points[i].slopeTo(aux[j - 1])) {

                    if (countPoints == 1)
                        countPoints += 2;
                    else
                        countPoints++;
                    if (i == 4) {
                        StdOut.println(countPoints);
                    }
                }

                if (points[i].slopeTo(aux[j]) != points[i].slopeTo(aux[j - 1]) | j == aux.length - 1) {
                    if (countPoints >= 4) {
                        StdOut.println("AAA");
                        Point[] selectedPoints = new Point[countPoints];
                        selectedPoints[0] = points[i];
                        int auxC;
                        if (j == aux.length - 1)
                            auxC = j;
                        else
                            auxC = j - 1;
                        while (countPoints > 1) {
                            StdOut.println(auxC + " " + countPoints);
                            selectedPoints[countPoints - 1] = aux[auxC--];
                            countPoints--;
                        }

                        StdOut.println(selectedPoints.length);

                        Arrays.sort(selectedPoints);

                        if (count < segments.length) {
                            segments[count++] = new LineSegment(selectedPoints[0],
                                    selectedPoints[selectedPoints.length - 1]);

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
