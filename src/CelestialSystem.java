public class CelestialSystem {

    private String name;
    private int size;
    private MyCelestialSystemNode head;

    // Initialises this system as an empty system with a name.
    public CelestialSystem(String name) {
        //TODO: implement constructor.
        this.name = name;
        this.size = 0;
    }

    // Adds 'body' to the list of bodies of the system if the list does not already contain a
    // body with the same name as 'body', otherwise does not change the object state. The method
    // returns 'true' if the list was changed as a result of the call and 'false' otherwise.
    public boolean add(CelestialBody body) {
        //TODO: implement method.
        if (this.get(body.getName()) == null) {
            if (head == null) {
                head = new MyCelestialSystemNode(body, null);
            } else {
                MyCelestialSystemNode last = head;
                while (last.next() != null) {
                    last = last.next();
                }
                last.setNext(new MyCelestialSystemNode(body, null));
            }
            size++;
            return true;
        } else return false;
    }

    // Returns the 'body' with the index 'i'. The body that was first added to the list has the
    // index 0, the body that was most recently added to the list has the largest index (size()-1).
    public CelestialBody get(int i) {
        //TODO: implement method.
        for (MyCelestialSystemNode n = head; n != null; n = n.next()) {
            if (i-- == 0) {
                return n.body();
            }
        }
        return null;
    }

    // Returns the body with the specified name or 'null' if no such body exits in the list.
    public CelestialBody get(String name) {
        //TODO: implement method.
        for (MyCelestialSystemNode n = head; n != null; n = n.next()) {
            if (name.equals(n.body().getName())) {
                return n.body();
            }
        }
        return null;
    }

    // returns the number of entries of the list.
    public int size() {
        //TODO: implement method.
        return size;
    }

    //TODO: Define additional class(es) implementing the linked list (either here or outside class).

    public String getName() {
        return name;
    }
}
