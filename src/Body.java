
// Body represents a body or object in space with 3D-position, velocity and mass.
// This class will be used by the executable class 'Space'. A body moves through space according to
// its inertia and - if specified - an additional force exerted on it (for example the
// gravitational force of another mass).
public class Body {
    //TODO: class definition.

    private int mass;
    private double x, y, z;
    private double[] VelocityVector;


    public void move() {
        x = x + VelocityVector[0];
        y = y + VelocityVector[1];
        z = z + VelocityVector[2];
    }

    public void move(double fx, double fy, double fz) {
        VelocityVector[0] = (VelocityVector[0] + fx) / mass;
        VelocityVector[1] = (VelocityVector[1] + fy) / mass;
        VelocityVector[2] = (VelocityVector[2] + fz) / mass;
        
    }


    public void setPosition(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
