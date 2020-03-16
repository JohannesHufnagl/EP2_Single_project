public class Simulation {

    // one astronomical unit (AU) is the average distance of earth to the sun.
    private static final double AU = 150e9;

    // all quantities are based on units of kilogram respectively second and meter.

    // The main simulation method using instances of other classes.
    public static void main(String[] args) {

        CelestialBody sun = new CelestialBody(
                "Sol",
                1.989e30,
                696340e3,
                new Vector3(0, 0, 0),
                new Vector3(0, 0, 0),
                StdDraw.YELLOW);

        CelestialBody mercury = new CelestialBody(
                "Mercury",
                3.301e23,
                2.4397e3,
                new Vector3(-46.0e9, 0, 0),
                new Vector3(0, -47.87e3, 0),
                StdDraw.BOOK_RED);

        CelestialBody venus = new CelestialBody(
                "Venus",
                4.869e24,
                6051.5e3,
                new Vector3(108.16e9, 0, 0),
                new Vector3(0, 35.02e3, 0),
                StdDraw.ORANGE);

        CelestialBody earth = new CelestialBody(
                "Earth",
                5.972e24,
                6371e3,
                new Vector3(148e9, 0, 0),
                new Vector3(0, 29.29e3, 0),
                StdDraw.BLUE);

        /*
        CelestialBody moon = new CelestialBody(
                "Moon",
                7.349e22,
                1737,
                new Vector3(AU + 365e6, 0, 0),
                new Vector3(0,29.29e3 + 1.023e3, 0),
                StdDraw.WHITE);
        */

        CelestialBody mars = new CelestialBody(
                "Mars",
                6.419e23,
                3369e3,
                new Vector3(227.99e9, 0, 0),
                new Vector3(0, 24.13e3, 0),
                StdDraw.RED);

        CelestialBody[] bodies = new CelestialBody[]{earth, sun, mercury, venus, mars};
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
                for (CelestialBody body : bodies) {
                    body.draw();
                }

                // show new positions
                StdDraw.show();
            }

        }

    }
}
/*
Beantworten Sie folgende Zusatzfragen als Kommentar in Simulation.java:
--------------------------------------------------------------------------------------------------
1. Was versteht man unter Datenkapselung?

Antwort: Datenkapselung = Zusammenfassen von Methoden und Variablen zu Einheit
         Als Datenkapselung bezeichnet man in der Programmierung das Verbergen von Daten oder Informationen
         vor dem Zugriff von außen. Der direkte Zugriff auf die interne Datenstruktur wird unterbunden und
         erfolgt stattdessen über definierte Schnittstellen (Black-Box-Modell).

--------------------------------------------------------------------------------------------------
2. Was versteht man unter data hiding?

Antwort: Data-Hiding = Verstecken vor Zugriffen von außen
         unterschiedliche Sichtbarkeit von Methoden und Variablen

--------------------------------------------------------------------------------------------------

        Datenabstraktion = Datenkapselung + Data-Hiding

 */

