public class Simulation {

    // gravitational constant
    public static final double G = 6.6743e-11;

    // one astronomical unit (AU) is the average distance of earth to the sun.
    public static final double AU = 150e9;

    // all quantities are based on units of kilogram respectively second and meter.

    // The main simulation method using instances of other classes.
    public static void main(String[] args) {

        CelestialBody sun = new CelestialBody(
                "Sol",
                1.989e30,
                696340e3,
                new Vector3(0,0,0),
                new Vector3(0,0,0),
                StdDraw.YELLOW);

        CelestialBody earth = new CelestialBody(
                "Earth",
                5.972e24,
                6371e3,
                new Vector3(148e9, 0, 0),
                new Vector3(0, 29.29e3, 0),
                StdDraw.BLUE);

        CelestialBody mercury = new CelestialBody(
                "Mercury",
                3.301e23,
                2.4397e3,
                new Vector3(-46.0e9, 0, 0),
                new Vector3(0, -47.87e3, 0),
                StdDraw.RED);

        CelestialBody[] bodies = new CelestialBody[]{earth, sun, mercury};
        Vector3[] forceOnBody = new Vector3[bodies.length];

        StdDraw.setCanvasSize(500, 500);
        StdDraw.setXscale(-2 * AU, 2 * AU);
        StdDraw.setYscale(-2 * AU, 2 * AU);
        double pixelWidth = 4 * AU / 500;
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BLACK);

        double seconds = 0;

        // simulation loop
        while (true) {

            seconds++; // each iteration computes the movement of the celestial bodies within one second.

            // for each body (with index i): compute the total force exerted on it.
            for (int i = 0; i < bodies.length; i++) {
                forceOnBody[i] = new Vector3(); // begin with zero
                for (int j = 0; j < bodies.length; j++) {
                    if (i == j) continue;
                    Vector3 forceToAdd = bodies[i].gravitationalForce(bodies[j]);
                    forceOnBody[i] = forceOnBody[i].plus(forceToAdd);
                }
            }
            // now forceOnBody[i] holds the force vector exerted on body with index i.

            // for each body (with index i): move it according to the total force exerted on it.
            for (int i = 0; i < bodies.length; i++) {
                bodies[i].move(forceOnBody[i]);
            }

            // show all movements in StdDraw canvas only every 3 hours (to speed up the simulation)
            if (seconds % (3 * 3600) == 0) {
                // clear old positions (exclude the following line if you want to draw orbits).
                StdDraw.clear(StdDraw.BLACK);

                // draw new positions
                for (int i = 0; i < bodies.length; i++) {
                    bodies[i].draw();
                }

                // show new positions
                StdDraw.show();
            }

        }

    }
}

//TODO: answer additional questions of 'Aufgabenblatt2'.


