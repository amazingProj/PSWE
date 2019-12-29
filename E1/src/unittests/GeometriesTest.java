
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.*;
import primitives.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeometriesTest {
    @Test
    public void testFindIntersections() {
        Triangle shape1 = new Triangle(new Point3D(0, 0, 0), new Point3D(0, 3, 0), new Point3D(4, 0, 0));
        Plane shape2 = new Plane(new Point3D(0, 0, 1), new Point3D(0, 3, 1), new Point3D(4, 0, 1));
        Triangle shape3 = new Triangle(new Point3D(0, 0, 2), new Point3D(0, 3, 2), new Point3D(4, 0, 2));
        Ray ray;
        Geometries geometries = new Geometries(shape1, shape2, shape3);
        List<Point3D> intersection = new ArrayList<Point3D>();

        intersection.add(new Point3D(1, 1, 1));
        intersection.add(new Point3D(1, 1, 2));
        ray = new Ray(new Point3D(1, 1, 0.5), new Vector(0, 0, 1));
        assertEquals(geometries.findIntersections(ray), intersection); //EP ray intersects with some of the shapes (more than one, less than all of the shapes)

        ray = new Ray(new Point3D(1, 1, 3), new Vector(0, 0, -1));
        intersection.clear();
        intersection.add(new Point3D(1, 1, 0));
        intersection.add(new Point3D(1, 1, 1));
        intersection.add(new Point3D(1, 1, 2));

        assertEquals(intersection, geometries.findIntersections(ray)); //BVA ray intersects with all the shapes

        ray = new Ray(new Point3D(-1, -1, 3), new Vector(0, 0, -1));
        intersection.clear();
        intersection.add(new Point3D(-1, -1, 1));
        assertEquals(intersection, geometries.findIntersections(ray)); //BVA ray intersects with only one of the shapes

        ray = new Ray(new Point3D(1, 1, 3), new Vector(0, 0, 1));
        intersection.clear();
        assertEquals(intersection, geometries.findIntersections(ray)); //BVA ray does not intersect with any of the shapes
    }
}