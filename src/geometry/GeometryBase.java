package geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This is a base class for competitive programming
 */
public class GeometryBase {
    public static final double EPS = 0.000000001;
    /*
    Point is also Vector, this is the approach you take during contests to avoid
    explicit transitions from one to another
     */
    public static class Point implements Comparable<Point> {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(Point p) {
            int xcompare = Integer.compare(x, p.x);
            int ycompare = Integer.compare(y, p.y);
            return xcompare != 0 ? xcompare : ycompare;
        }
    }

    public static class DPoint {
        double x;
        double y;
        public DPoint(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Segment {
        Point a;
        Point b;
        public Segment(Point a, Point b) {
            this.a = a;
            this.b = b;
        }
    }

    public static class Line {
        // Represent line as a set of coefficients satisfying Ax + B + C = 0 equation
        double A;
        double B;
        double C;

        public Line(Point p0, Point b) {
            Point d = getVector(p0, b);
            A = d.y;
            B = -d.x;
            C = -p0.x * d.y + p0.y * d.x;
        }
    }

    // |a| * |b| * cos L
    // dotProduct = 0 => L = 90
    // dotProduct < 0 => L > 90
    // dotProduct > 0 => L < 90
    public static int dotProduct(Point a, Point b) {
        return a.x * b.x + a.y * b.y;
    }

    // |a| * |b| * sin L
    // crossProduct = 0 => a and b are collinear
    public static int crossProduct(Point a, Point b) {
        return a.x * b.y - b.x * a.y;
    }

    public static Point getVector(Point p1, Point p2) {
        return new Point(p2.x - p1.x, p2.y - p1.y);
    }

    // Returning doubles instead of Point since this is the time we have to loose precision of integers
    public static DPoint getLineIntersection(Line l1, Line l2) {
        // Cramer's rule, very specific and hardcoded version
        double[] coordinates = new double[2];
        coordinates[0] = (- l1.C * l2.B + l1.B * l2.C)/(l1.A * l2.B - l1.B * l2.A);
        coordinates[1] = (- l1.A * l2.C + l1.C * l2.A)/(l1.A * l2.B - l1.B * l2.A);
        return new DPoint(coordinates[0], coordinates[1]);
    }

    public static boolean belongsToSegment(Segment s, DPoint p) {
        Line l = new Line(s.a, s.b);
        if(Math.abs(l.A * p.x + l.B * p.y + l.C) < EPS) {
            if(p.x + EPS > Math.min(s.a.x, s.b.x)
                    && Math.max(s.a.x, s.b.x) + EPS > p.x
                    && p.y + EPS > Math.min(s.a.y, s.b.y)
                    && Math.max(s.a.y, s.b.y) + EPS > p.y) {
                return true;
            }
        }
        return false;
    }

    public static DPoint[] getSegmentIntersection(Segment s1, Segment s2) {
        // First consider special cases when either one or two segments are degenerate
        if(s1.a.compareTo(s1.b) == 0 && s2.a.compareTo(s2.b) == 0) {
            if(s1.a.compareTo(s2.a) == 0) {
                return new DPoint[]{new DPoint(s1.a.x, s1.a.y)};
            } else {
                return new DPoint[]{};
            }
        } else if(s1.a.compareTo(s1.b) == 0){
            DPoint p = new DPoint(s1.a.x, s1.a.y);
            if(belongsToSegment(s2, p)) {
                return new DPoint[]{p};
            } else {
                return new DPoint[]{};
            }
        } else if(s2.a.compareTo(s2.b) == 0) {
            DPoint p = new DPoint(s2.a.x, s2.a.y);
            if(belongsToSegment(s1, p)) {
                return new DPoint[]{p};
            } else {
                return new DPoint[]{};
            }
        }

        // Check for collinearity
        Point vector1 = getVector(s1.a, s1.b);
        Point vector2 = getVector(s2.a, s2.b);
        int crossProduct = crossProduct(vector1, vector2);

        if(crossProduct == 0) {
            // Those two are collinear, that's a special case
            // Let's check if these two segments sit on the same line or on a different line
            Point vector12 = getVector(s2.a, s1.a);
            crossProduct = crossProduct(vector12, vector2);
            if(crossProduct != 0) {
                // These 2 segments don't sit on the same line
                return new DPoint[]{};
            } else {
                Point s1beg = s1.a.compareTo(s1.b) < 0 ? s1.a : s1.b;
                Point s1end = s1.a.compareTo(s1.b) < 0 ? s1.b : s1.a;
                Point s2beg = s2.a.compareTo(s2.b) < 0 ? s2.a : s2.b;
                Point s2end = s2.a.compareTo(s2.b) < 0 ? s2.b : s2.a;

                Point maxbeg = s1beg.compareTo(s2beg) < 0 ? s2beg : s1beg;
                Point minend = s1end.compareTo(s2end) < 0 ? s1end : s2end;

                if(minend.compareTo(maxbeg) > 0) {
                    return new DPoint[]{new DPoint(maxbeg.x, maxbeg.y), new DPoint(minend.x, minend.y)};
                } else if(minend.compareTo(maxbeg) == 0) {
                    return new DPoint[]{new DPoint(minend.x, minend.y)};
                } else {
                    return new DPoint[]{};
                }
            }
        } else {
            Line l1 = new Line(s1.a, s1.b);
            Line l2 = new Line(s2.a, s2.b);
            DPoint possibleIntersection = getLineIntersection(l1, l2);
            // This point sits on the lines intersection, but does it sit within segments?
            if(belongsToSegment(s1, possibleIntersection) && belongsToSegment(s2, possibleIntersection)) {
                return new DPoint[]{possibleIntersection};
            } else {
                return new DPoint[]{};
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] A = br.readLine().split(" ");
        String[] B = br.readLine().split(" ");
        String[] C = br.readLine().split(" ");
        String[] D = br.readLine().split(" ");
        Point Ap = new Point(Integer.parseInt(A[0]), Integer.parseInt(A[1]));
        Point Bp = new Point(Integer.parseInt(B[0]), Integer.parseInt(B[1]));
        Point Cp = new Point(Integer.parseInt(C[0]), Integer.parseInt(C[1]));
        Point Dp = new Point(Integer.parseInt(D[0]), Integer.parseInt(D[1]));
        DPoint[] res = getSegmentIntersection(new Segment(Ap, Bp), new Segment(Cp, Dp));
        if(res.length == 0) {
            System.out.println("Empty");
        } else if(res.length == 2) {
            System.out.println(res[0].x + " " + res[0].y);
            System.out.println(res[1].x + " " + res[1].y);
        } else {
            System.out.println(res[0].x + " " + res[0].y);
        }
    }
}
