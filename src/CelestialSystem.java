public class CelestialSystem {

    private String name;
    private int size;
    private MyDLNode head, tail;

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
                head = tail = new MyDLNode(body, null, null);
            } else {
                MyDLNode last = head;
                while (last.next() != null) {
                    last = last.next();
                }
                last.setNext(tail = new MyDLNode(body, last, null));
            }
            size++;
            return true;
        } else return false;
    }

    // Returns the 'body' with the index 'i'. The body that was first added to the list has the
    // index 0, the body that was most recently added to the list has the largest index (size()-1).
    public CelestialBody get(int i) {
        //TODO: implement method.
        for (MyDLNode n = head; n != null; n = n.next()) {
            if (i-- == 0) {
                return n.body();
            }
        }
        return null;
    }

    // Returns the body with the specified name or 'null' if no such body exits in the list.
    public CelestialBody get(String name) {
        //TODO: implement method.
        for (MyDLNode n = head; n != null; n = n.next()) {
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

    // Returns the name of the CelestialSystem
    public String getName() {
        return name;
    }

    // Inserts the specified 'body' at the specified position
    // in this list if 'i' is a valid index and there is no body
    // in the list with the same name as that of 'body'.
    // Shifts the element currently at that position (if any) and
    // any subsequent elements to the right (adds one to their
    // indices). The first element of the list has the index 0.
    // Returns 'true' if the list was changed as a result of
    // the call, 'false' otherwise.
    public boolean add(int i, CelestialBody body) {
        //TODO: implement method.
        if (this.size() >= i && this.get(body.getName()) == null) {
            for (MyDLNode n = head; n != null; n = n.next(), --i) {
                if (i - 1 == 0) {
                    n.setNext(new MyDLNode(body, n, n.next()));
                    n.next().next().setPrev(n.next());
                }
            }
            size++;
            return true;
        } else {
            return false;
        }
    }

    // Returns a readable representation with the name of the
    // system and all bodies in respective order of the list.
    public String toString() {
        //TODO: implement method.
        String system = this.name + ": ";
        for (MyDLNode n = head; n != null; n = n.next()) {
            system = system + n.body().getName() + (n.next() == null ? "" : ", ");
        }
        return system;
    }


    // Returns a new list that contains the same elements as this
    // list in reverse order. The list 'this' is not changed and
    // bodies are not duplicated (shallow copy).
    public CelestialSystem reverse() {
        //TODO: implement method.
        CelestialSystem result = new CelestialSystem("reverse_" + name);
        for (MyDLNode n = tail; n != null; n = n.prev()) {
            result.add(n.body());
        }
        return result;
    }

}
