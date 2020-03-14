import java.awt.*;

// This class represents vectors in a 3D vector space.
public class Vector3 {

    //TODO: change modifiers.
    private double x;
    private double y;
    private double z;

    //TODO: define constructor.
    public Vector3() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Returns the sum of this vector and vector 'v'.
    public Vector3 plus(Vector3 v) {
        return new Vector3(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    // Returns the product of this vector and 'd'.
    public Vector3 times(double d) {
        return new Vector3(this.x * d, this.y * d, this.z * d);
    }

    // Returns the sum of this vector and -1*v.
    public Vector3 minus(Vector3 v) {
        return new Vector3(this.x - v.x, this.y - v.y, this.z - v.z);
    }

    // Returns the Euclidean distance of this vector
    // to the specified vector 'v'.
    public double distanceTo(Vector3 v) {
        double dX = this.x - v.x;
        double dY = this.y - v.y;
        double dZ = this.z - v.z;

        return Math.sqrt(dX * dX + dY * dY + dZ * dZ);
    }

    // Returns the length (norm) of this vector.
    public double length() {
        return distanceTo(this);
    }

    // Normalizes this vector: changes the length of this vector such that it becomes one.
    // The direction and orientation of the vector is not affected.
    public void normalize() {
        double length = length();
        this.x /= length;
        this.y /= length;
        this.z /= length;
    }

    // Draws a filled circle with the center at (x,y) coordinates of this vector
    // in the existing StdDraw canvas. The z-coordinate is not used.
    public void drawAsDot(double radius, Color color) {

        //TODO: implement method.
    }

    // Returns the coordinates of this vector in brackets as a string
    // in the form "[x,y,z]", e.g., "[1.48E11,0.0,0.0]".
    public String toString() {
        return "[" + this.x + ',' + this.y + ',' + this.z + ']';
    }

    // Prints the coordinates of this vector in brackets to the console (without newline)
    // in the form [x,y,z], e.g.,
    // [1.48E11,0.0,0.0]
    public void print() {
        System.out.println(toString());
    }

}

