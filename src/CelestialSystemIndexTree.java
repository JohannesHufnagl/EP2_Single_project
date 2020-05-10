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
/*
 Zusatzfrage:
 Wie müsste man die Methoden von CelestialSystemIndex ändern, wenn man
 im Baum nicht nur mit Namen von Himmelskörpern, sondern auch mit
 Namen von System suchen will?

 Der binäre Suchbaum ist lexikographisch nach den Namen der bodys sortiert. Um den Baum nun zu traversieren damit man
 auch mit Namen von Systemen suchen kann, könnte der Baum mit einer inorder() Methode durchgegangen werden, dass ist
 möglich, da der Baum lexikographisch sortiert ist. Dabei werden alle Knoten in aufsteigender Reihenfolge durchgegangen.
 Findet man einen Knoten mit dem gesuchten System, kann das System ausgegeben werden und die Suche muss nicht weiter
 fortgesetzt werden.
 Falls das System nicht im Baum enthalten ist, wird null retourniert.
 */

