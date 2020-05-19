import java.util.HashSet;

public class CelestialSystemIndexTree {

    private MyTreeNode root;

    // Adds a system of bodies to the tree. Since the keys of the tree are the names of bodies,
    // adding a system adds multiple (key, value) pairs to the tree, one for each body of the
    // system, with the same value, i.e., reference to the celestial system.
    // An attempt to add a system with a body that already exists in the tree
    // leaves the tree unchanged and the returned value would be 'false'.
    // For example, it is not allowed to have a system ("Earth", "Moon") and a system ("Jupiter",
    // "Moon") indexed by the tree, since "Moon" is not unique.
    // The method returns 'true' if the tree was changed as a result of the call and
    // 'false' otherwise.
    public boolean add(CelestialSystem system) {
        // checks if the tree is empty and searches if there are names in the tree,
        // which are equal to the names of a body in the CelestialSystem, if so, null is returned.
        for (int i = 0; i < system.size(); i++) {
            if (root != null && root.find(system.get(i).getName()) != null)
                return false;
        }
        // iterates through the system and adds (put) new nodes to the tree
        for (int i = 0; i < system.size(); i++) {
            if (root == null) {
                root = new MyTreeNode(system.get(0).getName(), system);
                i++;
            }
            root.put(system.get(i).getName(), system);
        }

        return true;
    }

    // Returns the celestial system in which a body with the specified name exists.
    // For example, if the specified name is "Europa", the system of Jupiter (Jupiter, Io,
    // Europa, Ganymed, Kallisto) will be returned.
    // If no such system is found, 'null' is returned.
    public CelestialSystem get(String name) {
        if (root == null) {
            return null;
        }
        MyTreeNode node = root.find(name);
        return node == null ? null : node.systemName();
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
        HashSet<CelestialSystem> h = new HashSet<>();
        h.add(root.systemName());
        return root.countSystems(h);
    }
}


class MyTreeNode {
    private String key;
    private CelestialSystem value;
    private MyTreeNode left, right;

    public MyTreeNode(String b, CelestialSystem s) {
        key = b;
        value = s;
    }

    // x.compare(y) -> -1 if x < y
    //                  0 if x == y
    //                  1 if x > y
    private int compare(String b) {
        if (b == null) {
            return key == null ? 0 : -1;
        }
        if (key == null) {
            return 1;
        }
        return b.compareTo(key);
    }

    public CelestialSystem put(String b, CelestialSystem s) {
        int cmp = compare(b);
        if (cmp < 0) {
            if (left != null) {
                return left.put(b, s);
            }
            left = new MyTreeNode(b, s);
        } else if (cmp > 0) {
            if (right != null) {
                return right.put(b, s);
            }
            right = new MyTreeNode(b, s);
        } else {
            CelestialSystem result = value;
            value = s;
            return result;
        }
        return null;
    }

    public MyTreeNode find(String b) {
        int cmp = compare(b);
        if (cmp == 0) {
            return this;
        }
        MyTreeNode node = cmp < 0 ? left : right;
        if (node == null) {
            return null;
        }
        return node.find(b);
    }


    public CelestialSystem systemName() {
        return value;
    }

    public int countNodes() {
        int c = 1;
        if (right != null) c += right.countNodes();
        if (left != null) c += left.countNodes();
        return c;
    }

    public int countSystems(HashSet<CelestialSystem> h) {
        if (left != null) {
            h.add(left.systemName());
            left.countSystems(h);
        }
        if (right != null) {
            h.add(right.systemName());
            right.countSystems(h);
        }
        return h.size();
    }

}

