
// Body represents a body or object in space with 3D-position, velocity and mass.
// This class will be used by the executable class 'Space'. A body moves through space according to
// its inertia and - if specified - an additional force exerted on it (for example the
// gravitational force of another mass).
public class Body {
    //TODO: class definition.

    private int mass;
    private double[] position = new double[3];
    private double[] velocityVector = new double[3];


    public void move() {
        position[0] = position[0] + velocityVector[0];
        position[1] = position[1] + velocityVector[1];
        position[2] = position[2] + velocityVector[2];
    }

    public void move(double fx, double fy, double fz) {
        velocityVector[0] = velocityVector[0] + fx / mass;
        velocityVector[1] = velocityVector[1] + fy / mass;
        velocityVector[2] = velocityVector[2] + fz / mass;

        position[0] = position[0] + velocityVector[0];
        position[1] = position[1] + velocityVector[1];
        position[2] = position[2] + velocityVector[2];
    }


    public void setPosition(double x, double y, double z) {
        position[0] = x;
        position[1] = y;
        position[2] = z;
    }

    public double getXPosition(){
        return position[0];
    }

    public double getYPosition(){
        return position[1];
    }

    public double getZPosition(){
        return position[2];
    }

    public double getXVelocity(){
        return velocityVector[0];
    }

    public double getYVelocity(){
        return velocityVector[1];
    }

    public double getZVelocity(){
        return velocityVector[2];
    }

    public void printPosition() {
        System.out.println("x: " + position[0] + "\ty: " + position[1] + "\tz: " + position[2]);
    }

    public void setVelocity(double vx, double vy, double vz) {
        velocityVector[0] = vx;
        velocityVector[1] = vy;
        velocityVector[2] = vz;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public int getMass() {
        return mass;
    }
}
