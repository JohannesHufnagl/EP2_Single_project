import java.util.Iterator;

public class Simulation {

    // one astronomical unit (AU) is the average distance of earth to the sun.
    private static final double AU = 150e9;

    // all quantities are based on units of kilogram respectively second and meter.

    // The main simulation method using instances of other classes.
    public static void main(String[] args) {
        CelestialSystem solarsystem = ReadDataUtil.initialize(60);

        // Testing the Iterator of CelestialSystem
        System.out.println();
        for (Iterator i = solarsystem.iterator(); i.hasNext(); ) {
            System.out.println(i.next() + " ,");
        }

        CelestialBodyNameComparator comparator = new CelestialBodyNameComparator();
        CelestialSystemIndexTreeVariantC treeVariantC = new CelestialSystemIndexTreeVariantC(comparator);

        // --- Testing the class CelestialSystemIndex ---
        System.out.println("--- Testing the class CelestialSystemIndex ---");
        // CelestialSystemIndexTreeVariantC: Dwarf-Planets (only the names of the bodies are relevant to test the CelestialSystemIndex-Class
        CelestialBody ceres = new CelestialBody("Ceres", 1, 1, new Vector3(0, 0, 0), new Vector3(0, 0, 0), StdDraw.BLACK);
        CelestialBody pluto = new CelestialBody("Pluto", 1, 1, new Vector3(0, 0, 0), new Vector3(0, 0, 0), StdDraw.BLACK);
        CelestialBody haumea = new CelestialBody("Haumea", 1, 1, new Vector3(0, 0, 0), new Vector3(0, 0, 0), StdDraw.BLACK);
        CelestialBody eris = new CelestialBody("Eris", 1, 1, new Vector3(0, 0, 0), new Vector3(0, 0, 0), StdDraw.BLACK);
        CelestialBody makemake = new CelestialBody("Makemake", 1, 1, new Vector3(0, 0, 0), new Vector3(0, 0, 0), StdDraw.BLACK);

        CelestialSystem dwarfPlanets = new CelestialSystem("Dwarf-Planets");
        dwarfPlanets.add(ceres);
        dwarfPlanets.add(pluto);
        dwarfPlanets.add(haumea);
        dwarfPlanets.add(eris);
        dwarfPlanets.add(makemake);

        CelestialBody moon = new CelestialBody("Moon", 7.349e22, 1.738e6, new Vector3(149.6e9 + 384400e3, 0, 0), new Vector3(0, 29.78e3 + 1.023e3, 0), StdDraw.WHITE);

        CelestialSystem earthMoon = new CelestialSystem("Earth Moon");
        earthMoon.add(moon);

        CelestialSystem halfDwarfPlanets = new CelestialSystem("half");
        halfDwarfPlanets.add(moon);
        halfDwarfPlanets.add(haumea);
        halfDwarfPlanets.add(eris);


        System.out.println(treeVariantC.add(dwarfPlanets));
        System.out.println(treeVariantC.add(solarsystem));
        System.out.println(treeVariantC.bodies().size());
        System.out.println(treeVariantC.bodiesAsCelestialSystem());
        // CelestialSystemIndexTreeVariantC: Add different CelestialSystems to the CelestialSystemIndex-Binary-Tree
        System.out.println(solarsystem.reverse().getName() + " added to the binary tree: --> " + treeVariantC.add(solarsystem.reverse()) + " (false)");
        System.out.println(dwarfPlanets.getName() + " added to the binary tree: --> " + treeVariantC.add(dwarfPlanets) + " (false)");
        //System.out.println(earthMoon.getName() + " added to the binary tree: --> " + treeVariantC.add(earthMoon) + " (true)");

        System.out.println(treeVariantC.add(halfDwarfPlanets));

        // CelestialSystemIndexTreeVariantC: The ToString-Method to represent the names of the bodies of the binary-tree in lexicographical order
        System.out.println("Bodies in the binary tree: --> " + treeVariantC.toString());

        // Testing the Iterator of CelestialSystemIndexTreeVariantC
        System.out.println();
        for (Iterator i = treeVariantC.iterator(); i.hasNext(); ) {
            System.out.println(i.next() + " ,");
        }

        System.out.println(treeVariantC.bodies().size());
        System.out.println(treeVariantC.bodiesAsCelestialSystem());

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