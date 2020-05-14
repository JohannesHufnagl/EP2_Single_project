import java.util.HashSet;

public class CelestialSystemIndexTreeVariant implements CelestialSystemIndex {

    private MyTreeNode root;
    private HashSet<CelestialSystem> h = new HashSet<>();

    @Override
    public boolean add(CelestialSystem system) {
        for (int i = 0; i < system.size(); i++) {
            if (root != null && root.find(system.get(i).getName()) != null)
                return false;
        }

        for (int i = 0; i < system.size(); i++) {
            if (root == null) {
                root = new MyTreeNode(system.get(0).getName(), system);
                i++;
            }
            root.put(system.get(i).getName(), system);
        }
        return true;
    }

    @Override
    public CelestialSystem get(CelestialBody body) {
        if (root == null) {
            return null;
        }
        MyTreeNode node = root.find(body.getName());
        return node == null ? null : node.systemName();
    }

    @Override
    public boolean contains(CelestialBody body) {
        return root.find(body.getName()) != null;
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
        if (root != null) {
            return root.countNodes();
        } else return 0;
    }

    // Returns the overall number of systems indexed by the tree.
    public int numberOfSystems() {
        if (root == null) {
            return 0;
        }
        h.add(root.systemName());
        return root.countSystems(h);
    }


}
