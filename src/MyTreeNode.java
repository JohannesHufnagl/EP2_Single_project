public class MyTreeNode {
    private String bodyName;
    private CelestialSystem systemName;
    private MyTreeNode left, right;

    public MyTreeNode(String b, CelestialSystem s) {
        bodyName = b;
        systemName = s;
    }

    // x.compare(y) -> -1 if x < y
    //                  0 if x == y
    //                  1 if x > y
    private int compare(String b) {
        if (b == null) {
            return bodyName == null ? 0 : -1;
        }
        if (bodyName == null) {
            return 1;
        }
        return b.compareTo(bodyName);
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
            CelestialSystem result = systemName;
            systemName = s;
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

    public boolean sameSystem(CelestialSystem s) {
        return (s == null ? systemName == s
                : s.equals(systemName)) ||
                (left != null && left.sameSystem(s)) ||
                (right != null && right.sameSystem(s));
    }

    public CelestialSystem systemName() {
        return systemName;
    }

    public int countNodes() {
        int c = 1;
        if (right != null) c += right.countNodes();
        if (left != null) c += left.countNodes();
        return c;
    }

    public int countSystems() {
        int c = 1;
        if (left != null) {
            if (systemName != left.systemName) {
                c++;
            }
            left.countSystems();
        }

        if (right != null) {
            if (systemName != right.systemName) {
                c++;
            }
            right.countSystems();
        }

        return c;
    }

}
