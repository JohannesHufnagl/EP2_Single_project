public class MyTreeNode {
    private String key, value;
    private MyTreeNode left, right;

    public MyTreeNode(String k, String v) {
        key = k;
        value = v;
    }

    private int compare(String k) {
        if (k == null) {
            return key == null ? 0 : -1;
        }
        if (key == null) {
            return 1;
        }
        return k.compareTo(key);
    }

    public String put(String k, String v) {
        int cmp = compare(k);
        if (cmp < 0) {
            if (left != null) {
                return left.put(k, v);
            }
            left = new MyTreeNode(k, v);
        } else if (cmp > 0) {
            if (right != null) {
                return right.put(k, v);
            }
            right = new MyTreeNode(k, v);
        } else {
            String result = value;
            value = v;
            return result;
        }
        return null;
    }

    public MyTreeNode find(String k) {
        int cmp = compare(k);
        if (cmp == 0) {
            return this;
        }
        MyTreeNode node = cmp < 0 ? left : right;
        if (node == null) {
            return null;
        }
        return node.find(k);
    }

    public boolean hasValue(String v) {
        return (v == null ? value == v
                : v.equals(value)) ||
                (left != null && left.hasValue(v)) ||
                (right != null && right.hasValue(v));
    }

    public String value() {
        return value;
    }

}
