public class CelestialSystemIndexTreeVariantC implements CelestialSystemIndex, CelestialBodyIterable {

    private VariantCNode root;
    private CelestialBodyComparator comparator;

    // Initialises this index with a 'comparator' for sorting
    // the keys of this index.
    public CelestialSystemIndexTreeVariantC(CelestialBodyComparator comparator) {
        this.comparator = comparator;
    }

    public VariantCNode getRoot() {
        return root;
    }

    // Adds a system of bodies to the index.
    // Adding a system adds multiple (key, value) pairs to the
    // index, one for each body of the system, with the same
    // value, i.e., reference to the celestial system.
    // An attempt to add a system with a body that already exists
    // in the index leaves the index unchanged and the returned
    // value would be 'false'.
    // The method returns 'true' if the index was changed as a
    // result of the call and 'false' otherwise.
    public boolean add(CelestialSystem system) {
        if (system == null || system.size() == 0) {
            return false;
        }

        // checks if the tree is empty and searches if there are names in the tree,
        // which are equal to the names of a body in the CelestialSystem, if so, null is returned.
        for (CelestialBody body : system) {
            if (this.contains(body)) {
                return false;
            }
        }

        boolean changed = false;

        if (root == null) {
            root = new VariantCNode(system.get(0), system, null, null, comparator);
            changed = true;
        } else {
            changed = root.add(new VariantCNode(system.get(0), system, null, null, comparator));
        }

        for (int i = 1; i < system.size(); i++) {
            changed |= root.add(new VariantCNode(system.get(i), system, null, null, comparator));
        }

        return changed;

    }

    // Returns the celestial system with which a body is
    // associated. If body is not contained as a key, 'null'
    // is returned.
    public CelestialSystem get(CelestialBody body) {
        if (root == null) {
            return null;
        }

        return root.get(body);
    }

    // Returns 'true' if the specified 'body' is listed
    // in the index.
    public boolean contains(CelestialBody body) {
        return get(body) != null;
    }

    // Returns a readable representation with (key, value) pairs sorted by the key.
    public String toString() {
        if (root == null) {
            return "{}";
        }

        return "{" + root.toString() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CelestialSystemIndexTreeVariantC that = (CelestialSystemIndexTreeVariantC) o;
        if (this.root.numberOfNodes() != that.getRoot().numberOfNodes()) {
            return false;
        }
        for (CelestialBody body : this) {
            if (!that.contains(body) || that.get(body) != this.get(body)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int i = 0;
        ComplexCelestialSystem complexSystem = new ComplexCelestialSystem("hashCount");
        for (CelestialBody body : this) {
            i = i + body.hashCode();
            complexSystem.add(this.get(body));
        }
        i *= complexSystem.hashCode();
        return i;
    }


    // Returns a collection view of all entries of this index.
    public CelestialBodyCollection bodies() {
        return new MyCelestialBodyCollection(this);
    }

    // Returns all entries of this as a new collection.
    public CelestialSystem bodiesAsCelestialSystem() {
        CelestialSystem allBodies = new CelestialSystem("Bodies as Celestial System");
        for (CelestialBody b : this) {
            allBodies.add(new CelestialBody(b));
        }
        return allBodies;
    }

    // Returns the comparator used in this index.
    public CelestialBodyComparator getComparator() {
        return comparator;
    }

    @Override
    public CelestialBodyIterator iterator() {
        MyTreeIter iter = new MyTreeIter();
        if (root != null) {
            root.iter(iter, false);
        }
        return iter;
    }

    public int size() {
        return root.numberOfNodes();
    }
}

class VariantCNode {
    private VariantCNode left;
    private VariantCNode right;
    private CelestialBody key;
    private CelestialSystem cs;
    private CelestialBodyComparator comparator;

    public VariantCNode(CelestialBody key, CelestialSystem cs, VariantCNode left, VariantCNode right,
                        CelestialBodyComparator comparator) {
        this.key = key;
        this.cs = cs;
        this.left = left;
        this.right = right;
        this.comparator = comparator;

    }

    public boolean add(VariantCNode node) {
        if (node.key.equals(this.key)) {
            return false;
        }

        if (this.comparator.compare(this.key, node.key) > 0) {
            if (left == null) {
                left = node;
                return true;
            } else {
                return left.add(node);
            }
        } else {
            if (right == null) {
                right = node;
                return true;
            } else {
                return right.add(node);
            }
        }

    }

    public CelestialSystem get(CelestialBody body) {
        if (key.equals(body)) {
            return cs;
        }

        if (this.comparator.compare(this.key, body) > 0) {
            if (left == null) {
                return null;
            }
            return left.get(body);
        } else {
            if (right == null) {
                return null;
            }
            return right.get(body);
        }

    }

    public int numberOfNodes() {
        int count = 1;
        if (left != null) {
            count += left.numberOfNodes();
        }
        if (right != null) {
            count += right.numberOfNodes();
        }
        return count;
    }


    public String toString() {
        String result;
        result = left == null ? "" : left.toString();
        result += result.isEmpty() ? "" : ",\n";
        result += this.key + " belongs to " + this.cs;
        result += right == null ? "" : ",\n" + right.toString();
        return result;

    }

    public CelestialBody iter(MyTreeIter iter, boolean next) {
        VariantCNode n = next ? right : this;
        while (n != null) {
            new MyTreeIter(n, iter);
            n = n.left;
        }
        return this.key;
    }
}

class MyTreeIter implements CelestialBodyIterator {
    private VariantCNode node;
    private MyTreeIter parent;

    public MyTreeIter() {
    }

    public MyTreeIter(VariantCNode node, MyTreeIter parent) {
        this.node = parent.node;
        parent.node = node;
        this.parent = parent.parent;
        parent.parent = this;
    }

    public boolean hasNext() {
        return node != null;
    }

    public CelestialBody next() {
        if (node == null) {
            return null;
        }
        VariantCNode todo = node;
        node = parent.node;
        parent = parent.parent;
        return todo.iter(this, true);
    }
}

class MyCelestialBodyCollection implements CelestialBodyCollection {
    private CelestialSystemIndexTreeVariantC tree;

    public MyCelestialBodyCollection(CelestialSystemIndexTreeVariantC tree) {
        this.tree = tree;
    }

    @Override
    public boolean add(CelestialBody body) {
        return false;
    }

    public int contains(CelestialBody b) {
        return tree.contains(b) ? 1 : 0;
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public CelestialBodyIterator iterator() {
        return tree.iterator();
    }
}
/*
Zusatzfragen:

1. Wie verhalten sich die von bodies() und bodiesAsCelestialSystem() zurückgelieferten Objekte,
   wenn deren enthaltene Himmelskörper durch move bewegt werden? Werden dadurch die Himmelskörper
   des Suchbaums geändert? (Anmerkung: diesbezüglich gibt es im diesem Aufgabenblatt keine Vorgaben).

   Antwort: bodies() implementiert ein shallow copy, d. h. eine Instanz davon, hat dieselbe Referenz
            wie der Baum selbst. daher werden bei einem move() Aufruf auf diese Objekte auch die Objekte
            des Baumes bewegt.
            Bei der bodiesAsCelestialSystem() Methode handelt es sich um ein deep copy, wobei die Werte
            der Baum-Objekte kopiert werden, aber als neues Objekt mit einer anderen Referenz instanziert
            werden. Darum werrden bei einem move() Aufruf auf diese Objekte die Baum-Objekte nicht bewegt.
_______________________________________________________________________________________________________________________
2. Wie verhalten sich Ihre Iteratoren, wenn Objekte geändert werden?

   Antwort: Die Iteratoren verändern durch das verändern von Objekten nicht beeinflusst, wenn ein Iterator vor dem
            ändern eines Objekts am Ende der zu iterierenden Objekten ist, ist er auch nach dem ändern am Ende.
            Wenn der Iterator vor dem ändern noch nicht am Ende ist, iteriert er nach dem ändern weiter und die
            geänderten Objekte bleiben unberücksichtigt.
 */



