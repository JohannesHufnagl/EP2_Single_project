// Body represents a body or object in space with 3D-position, velocity and mass.
// This class will be used by the executable class 'Space'. A body moves through space according to
// its inertia and - if specified - an additional force exerted on it (for example the
// gravitational force of another mass).
public class Body {
    //TODO: class definition.
    /******************************************************
     class Body: Object in space with a 3D-position, velocity and mass
     private variables:
        double mass;
        double[] position;
        double[] velocityVector;
     public methods:
        void move() ;
        void move(double fx, double fy, double fz);
        void setPosition(double x, double y, double z);
        double getZPosition();
        double getZVelocity();
        void printPosition();
        setVelocity(double vx, double vy, double vz);
        setMass(double mass);
        double getMass();
     ******************************************************/

    private double mass;
    private double[] position = new double[3];
    private double[] velocityVector = new double[3];

    // Sets the new position of the object
    // New position is the current position plus the velocity coordinates
    public void move() {
        position[0] = position[0] + velocityVector[0];
        position[1] = position[1] + velocityVector[1];
        position[2] = position[2] + velocityVector[2];
    }

    // Moves the object, but with considering external forces
    // First the velocity vector is being calculated
    // With the newly calculated velocity the move() method gets called
    public void move(double fx, double fy, double fz) {
        velocityVector[0] = velocityVector[0] + fx / mass;
        velocityVector[1] = velocityVector[1] + fy / mass;
        velocityVector[2] = velocityVector[2] + fz / mass;
        move();
    }

    // Method to set the 3-D (starting) position of an object
    // Parameters are x, y and z
    public void setPosition(double x, double y, double z) {
        position[0] = x;
        position[1] = y;
        position[2] = z;
    }

    // returns the position of the z-coordinate from the object
    public double getZPosition() {
        return position[2];
    }

    // returns the velocity of the z-coordinate from the object
    public double getZVelocity() {
        return velocityVector[2];
    }

    // Method to print out the current position of the object to the console
    public void printPosition() {
        System.out.println("x: " + position[0] + "\ty: " + position[1] + "\tz: " + position[2]);
    }

    // Method to set a specific velocity for an object
    // vx, vy and vz are the 3-D velocities
    public void setVelocity(double vx, double vy, double vz) {
        velocityVector[0] = vx;
        velocityVector[1] = vy;
        velocityVector[2] = vz;
    }

    // sets the mass of an object
    public void setMass(double mass) {
        this.mass = mass;
    }

    // returns the mass of an object
    public double getMass() {
        return mass;
    }
}
