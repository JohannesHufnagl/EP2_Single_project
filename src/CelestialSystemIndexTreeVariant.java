import java.util.HashSet;

public class CelestialSystemIndexTreeVariant extends CelestialSystemIndexTree implements CelestialSystemIndex {

    private HashSet<CelestialSystem> h = new HashSet<>();

    // Adds a system of bodies to the tree.
    // Adding a system adds multiple (key, value) pairs to the
    // tree, one for each body of the system, with the same
    // value, i.e., reference to the celestial system.
    // An attempt to add a system with a body that already exists
    // in the tree leaves the tree unchanged and the returned
    // value would be 'false'.
    // The method returns 'true' if the tree was changed as a
    // result of the call and 'false' otherwise.
    @Override
    public boolean add(CelestialSystem system) {
        return super.add(system);
    }

    @Override
    public CelestialSystem get(CelestialBody body) {
        return super.get(body.getName());
    }

    @Override
    public boolean contains(CelestialBody body) {
        return super.get(body.getName()) != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CelestialSystemIndexTreeVariant that = (CelestialSystemIndexTreeVariant) o;
        if (this.numberOfBodies() != that.numberOfBodies() || this.numberOfSystems() != that.numberOfSystems()) {
            return false;
        }
        for (int i = 0; i < this.h.size(); i++) {
            if (that.add(this.h.iterator().next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int x = 73651;
        int hash = x;
        for (int i = 1; i < this.h.size(); i++) {
            hash += x * i + h.size();
        }
        return hash;
    }

    // Returns the overall number of bodies indexed by the tree.
    public int numberOfBodies() {
        return super.numberOfBodies();
    }

    // Returns the overall number of systems indexed by the tree.
    public int numberOfSystems() {
        return super.numberOfSystems();
    }


}
