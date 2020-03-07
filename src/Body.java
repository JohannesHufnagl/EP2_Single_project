
// Body represents a body or object in space with 3D-position, velocity and mass.
// This class will be used by the executable class 'Space'. A body moves through space according to
// its inertia and - if specified - an additional force exerted on it (for example the
// gravitational force of another mass).
public class Body {
    //TODO: class definition.

    private int mass;
    private double[] position = new double[3];
    private double[] VelocityVector = new double[3];


    public void move() {
        position[0] = position[0] + VelocityVector[0];
        position[1] = position[1] + VelocityVector[1];
        position[2] = position[2] + VelocityVector[2];
    }

    public void move(double fx, double fy, double fz) {
        position[0] = (VelocityVector[0] + fx) / mass;
        position[1] = (VelocityVector[1] + fy) / mass;
        position[2] = (VelocityVector[2] + fz) / mass;
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

    public void printPosition() {
        System.out.println("x: " + position[0] + "\ty: " + position[1] + "\tz: " + position[2]);
    }

    public void setVelocity(double vx, double vy, double vz) {
        VelocityVector[0] = vx;
        VelocityVector[1] = vy;
        VelocityVector[2] = vz;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }
}
