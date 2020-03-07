import java.util.Arrays;
import java.util.Random;

// Space is the actual program (executable class) using objects of class 'Body'.
public class Space {

    // Some constants helpful for the simulation (particularly the 'falling ball' example).
    public static final double G = 6.6743e-11; // gravitational constant
    public static final double MASS_OF_EARTH = 5.972e24; // kg
    public static final double MASS_OF_BALL = 1; // kg
    public static final double RADIUS_OF_EARTH = 6.371e6; // m (meters)

    // On the surface of earth the gravitational force on the ball weighing 1kg is
    // approximately as follows:
    public static final double GRAVITATIONAL_FORCE_ON_BALL =
            G * MASS_OF_EARTH * MASS_OF_BALL / (RADIUS_OF_EARTH * RADIUS_OF_EARTH); // kg*m/sec² ... F = mass * acc
    // This means each second its speed increases about 9.82 meters per second.

    //TODO: further variables, if needed.
    private static int seconds = 0;

    // The main simulation method using instances of other classes.
    public static void main(String[] args) {

        //TODO: implement method.

        //One test case is given:
        //Results for the falling ball on the surface of earth are as follows:
        //Height 10m: 2 (sec = number of move(fx,fy,fz) calls)
        //Height 100m: 5 (sec = number of move(fx,fy,fz) calls)

        Body ball1 = new Body();
        ball1.setPosition(0, 0, 100); // 100m height.
        ball1.setVelocity(0, 0, -1);
        ball1.setMass(1); // 1 kg
        System.out.println(fallToGround(ball1)); // 5


        ball1.setPosition(0, 0, 10); // 10m height.
        ball1.setVelocity(0, 0, 0);
        System.out.println(fallToGround(ball1)); // 2

        Body ball2 = new Body();
        ball2.setPosition(0, 0, 100); // 100m height.
        ball2.setVelocity(0, 0, 0);
        ball2.setMass(15); // 15 kg
        System.out.println(fallToGround(ball2)); // 5


        //Further examples are to be tested (body in empty space, rocket, feather).

        Body spaceObject = new Body();
        spaceObject.setPosition(0, 0, 0);
        spaceObject.setVelocity(-1, 2, 3);
        spaceObject.setMass(1);
        flyingBody(spaceObject, 5);

        Body rocket = new Body();
        rocket.setPosition(10, 0, 0);
        rocket.setVelocity(0, 0, 5e6);
        spaceObject.setMass(2800);
        rocketVelocity(rocket, 10, 1);

        Body ball = new Body();
        int droppingHeight = 50;
        ball.setMass(1);
        ball.setPosition(0, 0, droppingHeight);
        System.out.println("The ball needs " + fallToGround(ball) + " seconds until it hits the ground");

        Body feather = new Body();
        feather.setPosition(0,0,0);
        feather.setVelocity(0,0,0);
        featherSimulation(feather,10);
    }

    // Returns the number of move(fx,fy,fz) calls needed for 'b' hitting the ground, i.e.,
    // the method reduces the z-coordinate of 'b' until it becomes 0 or negative.
    public static int fallToGround(Body b) {
        if (b.getZPosition() >= 0.0) {
            b.move(0, 0, -GRAVITATIONAL_FORCE_ON_BALL * b.getMass());
            return fallToGround(b) + 1;
        }
        return 0;
    }

    //TODO: Define further methods as needed.

    public static void flyingBody(Body b, int time) {
        if (time > 0) {
            b.move();
            flyingBody(b, --time);
        } else {
            b.printPosition();
        }
    }

    public static void rocketVelocity(Body b, int time, int fuelBurn) {
        if (time > 0) {
            b.move(0, 0, b.getZVelocity());
            b.setMass(b.getMass() - fuelBurn);
            rocketVelocity(b, --time, fuelBurn);
        } else {
            b.printPosition();
        }
    }

    public static void featherSimulation(Body b, int time) {
        if (time > 0) {
            Random rand = new Random();
            int randomNumX = rand.nextInt((100) + 1);
            int randomNumY = rand.nextInt((100) + 1);
            int randomNumZ = rand.nextInt((100) + 1);

            b.move(randomNumX, randomNumY, randomNumZ);
            b.printPosition();
            featherSimulation(b, --time);
        }
    }
}



