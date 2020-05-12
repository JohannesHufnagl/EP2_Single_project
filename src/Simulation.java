import java.awt.*;

public class Simulation {

    // one astronomical unit (AU) is the average distance of earth to the sun.
    private static final double AU = 150e9;

    // all quantities are based on units of kilogram respectively second and meter.

    // The main simulation method using instances of other classes.
    public static void main(String[] args) {

        CelestialSystem solarsystem = ReadDataUtil.initialize(60);
        ComplexCelestialSystem universe = new ComplexCelestialSystem("Universe");
        universe.add(solarsystem);

        CelestialSystem solarsystem2 = ReadDataUtil.initialize(40);

        CelestialSystem otherSystem = new CelestialSystem("emtpy system");
        CelestialBody Io = new CelestialBody("Io", 1, 1, new Vector3(1, 1, 1), new Vector3(1, 1, 1), StdDraw.BLACK);
        otherSystem.add(Io);

        System.out.println(solarsystem.equals(solarsystem)); // true
        System.out.println(solarsystem.equals(solarsystem2)); // true
        System.out.println(solarsystem.equals(otherSystem)); // false

        CelestialSystemIndexMap hashmap = new CelestialSystemIndexMap();
        CelestialSystemIndexMap hashmap2 = new CelestialSystemIndexMap();

        System.out.println(hashmap.add(solarsystem));
        System.out.println(hashmap.add(solarsystem2));

        System.out.println(hashmap2.add(solarsystem));
        System.out.println(hashmap2.add(solarsystem2));

        System.out.println(hashmap.contains(Io));
        System.out.println(hashmap.get(Io));

        System.out.println(hashmap);
        System.out.println(hashmap2);

        for (int i = 0; i < 100; i++) {
            CelestialBody test = new CelestialBody("test" + i, Math.random() * 1e25, Math.random() * 1e3,
                    new Vector3((Math.random() * 4 * AU),
                            (Math.random() * 4 * AU),
                            (Math.random() * 4 * AU)),
                    new Vector3(0, 0, 0),
                    new Color((int) (Math.random() * 256 - 100) + 100,
                            (int) (Math.random() * 256 - 100) + 100,
                            (int) (Math.random() * 256 - 100) + 100));
            otherSystem.add(test);
        }
        System.out.println(hashmap.contains(Io));

        System.out.println(hashmap.add(otherSystem));
        System.out.println(hashmap2.add(otherSystem.reverse()));
        System.out.println(hashmap.contains(Io));
        System.out.println(hashmap);
        System.out.println(hashmap2);

        System.out.println(hashmap.equals(hashmap2));

        System.out.println(hashmap.hashCode());
        System.out.println(hashmap2.hashCode());


        Vector3[] forceOnBody = new Vector3[solarsystem.size()];

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
            for (int i = 0; i < solarsystem.size(); i++) {
                forceOnBody[i] = new Vector3(); // begin with zero
                for (int j = 0; j < solarsystem.size(); j++) {
                    if (i == j) continue;
                    Vector3 forceToAdd = solarsystem.get(i).gravitationalForce(solarsystem.get(j));
                    forceOnBody[i] = forceOnBody[i].plus(forceToAdd);
                }
            }
            // now forceOnBody[i] holds the force vector exerted on body with index i.

            // for each body (with index i): move it according to the total force exerted on it.
            for (int i = 0; i < solarsystem.size(); i++) {
                solarsystem.get(i).move(forceOnBody[i]);
            }

            // show all movements in StdDraw canvas only every 3 hours (to speed up the simulation)
            if (seconds % (3 * 3600) == 0) {
                // clear old positions (exclude the following line if you want to draw orbits).
                StdDraw.clear(StdDraw.BLACK);

                // draw new positions
                for (int i = 0; i < solarsystem.size(); i++) {
                    solarsystem.get(i).draw();
                }

                // show new positions
                StdDraw.show();
            }

        }

    }
}