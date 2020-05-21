public class CelestialBodyNameComparator implements CelestialBodyComparator {

    @Override
    // compares two bodies by their names.
    public int compare(CelestialBody b1, CelestialBody b2) {
        //TODO: implement method.
        return b1.getName().compareTo(b2.getName());
    }
}
