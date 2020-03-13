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

    // The main simulation method using instances of other classes.
    public static void main(String[] args) {

        //TODO: implement method.

        //One test case is given:
        //Results for the falling ball on the surface of earth are as follows:
        //Height 10m: 2 (sec = number of move(fx,fy,fz) calls)
        //Height 100m: 5 (sec = number of move(fx,fy,fz) calls)

        Body ball1 = new Body();
        ball1.setPosition(0, 0, 100); // 100m height.
        ball1.setVelocity(0, 0, 0);
        ball1.setMass(1); // 1 kg
        System.out.println(fallToGround(ball1)); // 5

        //Changing position
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
        spaceObject.setPosition(45, 23, -12);
        spaceObject.setVelocity(-1, 2, 3);
        spaceObject.setMass(1); // 1kg
        System.out.print("\nThe space objects new position is: ");
        flyingBody(spaceObject, 5); // time = 5sec

        Body rocket = new Body();
        rocket.setPosition(10, 0, 0);
        rocket.setVelocity(0, 0, 5e6);
        rocket.setMass(2800000); // Saturn V rocket 2800 tons
        System.out.print("\nThe rockets new position is: ");
        rocketVelocity(rocket, 10, 10000); // time = 5sec, fuelBurn = 10000 l/sec

        Body ball = new Body();
        int droppingHeight = 5000; // 5000m
        ball.setMass(100); // 100kg
        ball.setPosition(0, 0, droppingHeight);
        System.out.println("\nThe ball needs " + fallToGround(ball) + " seconds until it hits the ground");

        Body feather = new Body();
        feather.setPosition(0, 0, 0);
        feather.setVelocity(0, 0, 0);
        feather.setMass(0.1); // 100g (big feather, maybe from a ostrich)
        System.out.println("\nFeather-Simulation: ");
        featherSimulation(feather, 10); // time = 10sec
        /*
        Body zusatz = new Body();
        zusatz.setPosition(0,0,0);
        zusatz.setVelocity(0,0,0);
        zusatz.setMass(1);
        zusatz.move(5,0,0,1);
        zusatz.printPosition();
         */
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

    // prints the position of an flying body 'b' in space after, a specific 'time' in seconds,
    // by using the printPosition() method
    // the method terminates when the integer time, which gets decreased by one each method call, reaches the value 0
    private static void flyingBody(Body b, int time) {
        if (time > 0) {
            b.move();
            flyingBody(b, --time);
        } else {
            b.printPosition();
        }
    }

    // prints the position of an rocket 'b' in space,
    // after a specific number of turns = 'time' in seconds,
    // by using the printPosition() method
    // Extra: The mass of the body is reduced by the value of fuelBurn every method call
    private static void rocketVelocity(Body b, int time, int fuelBurn) {
        if (time > 0) {
            b.move(0, 0, b.getZVelocity());
            b.setMass(b.getMass() - fuelBurn);
            rocketVelocity(b, --time, fuelBurn);
        } else {
            b.printPosition();
        }
    }

    // Simulates a feather flying carried by the wind
    // the feathers external force (wind) is changing every call of the method and generated random
    // with values in a range from -2.5 to 2.5, the method reduces the time variable by one each method call
    // until time reaches 0 and the method terminates
    // By calling the printPosition() method, the position of the feather is printed to the console every
    // method call wich represents one second.
    private static void featherSimulation(Body b, int time) {
        if (time > 0) {
            double randX = (Math.random() * 5 - 2.5);
            double randY = (Math.random() * 5 - 2.5);
            double randZ = (Math.random() * 5 - 2.5);
            b.move(randX, randY, randZ);
            b.printPosition();
            featherSimulation(b, --time);
        }
    }
}

/*
    Wie könnte man move(seconds, fx,fy,fz) implementieren?
    Diese Nachricht soll bewirken, dass mehrere Bewegungsschritte durchgeführt werden.

    Antwort: In class Body:

    public void move(int seconds, double fx, double fy, double fz){
        if(seconds >= 1){
            move(fx,fy,fz);
            move(--seconds,fx,fy,fz);
        }
    }

 */

