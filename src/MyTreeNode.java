import java.util.HashSet;

public class MyTreeNode {
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
