public class ComplexCelestialSystem {

    private String name;
    private MyNode head;

    // Initializes this system as an empty system with a name.
    public ComplexCelestialSystem(String name) {
        this.name = name;
    }

    // Adds a subsystem of bodies to this system if there are no bodies in the subsystem
    // with the same name as a body or subsystem of this system.
    // The method returns 'true' if the collection was changed as a result of the call and
    // 'false' otherwise.
    public boolean add(CelestialSystem subsystem) {
        if (this.get(subsystem.getName()) == null) {
            if (head == null) {
                head = new MyNode(subsystem, null);
            } else {
                MyNode last = head;
                while (last.next() != null) {
                    last = last.next();
                }
                last.setNext(new MyNode(subsystem, null));
            }
            return true;
        } else return false;
    }

    // Returns the single body or subsystem with 'name' or 'null' if no such body or subsystem 
    // exists in this system. In case of a single body, the body is returned as a subsystem of 
    // one body, with the same name as the body.
    public CelestialSystem get(String name) {
        for (MyNode n = head; n != null; n = n.next()) {
            if (name.equals(n.system().getName())) {
                return n.system();
            } else if (n.system().get(name) != null && name.equals(n.system().get(name).getName())) {
                CelestialSystem result = new CelestialSystem(name);
                result.add(n.system().get(name));
                return result;
            }
        }
        return null;
    }

    // Returns the number of bodies of the entire system.
    public int size() {
        int count = 0;
        for (MyNode n = head; n != null; n = n.next()) {
            count++;
        }
        return count;
    }
}