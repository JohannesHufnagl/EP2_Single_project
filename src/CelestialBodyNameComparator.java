public class CelestialBodyNameComparator implements CelestialBodyComparator {

    @Override
    // compares two bodies by their names.
    public int compare(CelestialBody b1, CelestialBody b2) {
        //TODO: implement method.
        if (b1.equals(b2)) {
            return 1;
        }
        return 0;
    }
}
