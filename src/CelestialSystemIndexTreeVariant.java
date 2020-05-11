import java.util.Objects;

public class CelestialSystemIndexTreeVariant implements CelestialSystemIndex {

    private MyTreeNode root;

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
        return Objects.equals(root, that.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }
}
