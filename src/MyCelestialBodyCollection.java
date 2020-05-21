public class MyCelestialBodyCollection implements CelestialBodyCollection {
    private CelestialSystemIndexTreeVariantC tree;

    public MyCelestialBodyCollection(CelestialSystemIndexTreeVariantC tree) {
        this.tree = tree;
    }

    public boolean add(CelestialBody body) {
        return false;
    }

    public int contains(CelestialBody b) {
        return tree.contains(b) ? 1 : 0;
    }

    public int size() {
        return tree.size();
    }

    @Override
    public CelestialBodyIterator iterator() {
        return tree.iterator();
    }
}