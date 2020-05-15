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

    // Returns the celestial system with which a body is
    // associated. If body is not contained as a key, 'null'
    // is returned.
    @Override
    public CelestialSystem get(CelestialBody body) {
        return super.get(body.getName());
    }

    // Returns 'true' if the specified 'body' is listed
    // in the tree.
    @Override
    public boolean contains(CelestialBody body) {
        return super.get(body.getName()) != null;
    }

    // Returns 'true' if 'o' is of the same class as 'this' and
    // 'this' and 'o' contain an equal set of
    // (key, value) pairs, i.e. each (key, value) pair of 'this'
    // is contained (i.e. has an equal counterpart) in 'o' and
    // vice versa. Two (key, value) pairs are equal if they have
    // equal keys and equal values.
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
