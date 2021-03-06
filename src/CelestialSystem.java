public class CelestialSystem implements CelestialBodyCollection {

    private String name;
    private MyDLNode head, tail;

    // Initialises this system as an empty system with a name.
    public CelestialSystem(String name) {
        this.name = name;
    }

    // Adds 'body' to the list of bodies of the system if the list does not already contain a
    // body with the same name as 'body', otherwise does not change the object state. The method
    // returns 'true' if the list was changed as a result of the call and 'false' otherwise.
    public boolean add(CelestialBody body) {
        if (head == null) {
            head = tail = new MyDLNode(body, null, null);
        } else {
            if (get(body.getName()) != null) {
                return false;
            }
            tail.setNext(tail = new MyDLNode(body, tail, null));
        }
        return true;
    }

    // Returns the 'body' with the index 'i'. The body that was first added to the list has the
    // index 0, the body that was most recently added to the list has the largest index (size()-1).
    public CelestialBody get(int i) {
        for (MyDLNode n = head; n != null; n = n.next()) {
            if (i-- == 0) {
                return n.body();
            }
        }
        return null;
    }

    // Returns the body with the specified name or 'null' if no such body exits in the list.
    public CelestialBody get(String name) {
        for (MyDLNode n = head; n != null; n = n.next()) {
            if (name.equals(n.body().getName())) {
                return n.body();
            }
        }
        return null;
    }

    // returns the number of entries of the list.
    public int size() {
        int count = 0;
        for (MyDLNode n = head; n != null; n = n.next()) {
            count++;
        }
        return count;
    }


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
        if (this.size() >= i && this.get(body.getName()) == null) {
            if (i > 0) {
                for (MyDLNode n = head; n != null; n = n.next(), --i) {
                    if (i - 1 == 0) {
                        n.setNext(new MyDLNode(body, n, n.next()));
                        if (tail == n) {
                            tail = n.next();
                        } else {
                            n.next().next().setPrev(n.next());
                        }
                    }
                }
            } else if (i == 0) {
                head = new MyDLNode(body, null, head);
                if (tail == null) {
                    tail = head;
                } else {
                    head.next().setPrev(head);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    // Returns a readable representation with the name of the
    // system and all bodies in respective order of the list.
    public String toString() {
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
        CelestialSystem result = new CelestialSystem("reverse_" + name);
        for (MyDLNode n = tail; n != null; n = n.prev()) {
            result.add(n.body());
        }
        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CelestialSystem that = (CelestialSystem) o;
        if (size() != that.size()) return false;
        int counter = 0;
        for (MyDLNode n = head; n != null; n = n.next()) {
            if (n.body().equals(that.get(n.body().getName()))) {
                counter++;
            }
        }
        return counter == size();
    }

    @Override
    public int hashCode() {
        int h = 7654321;
        for (MyDLNode n = head; n != null; n = n.next()) {
            h += n.body().hashCode();
        }
        return h;
    }

    @Override
    public CelestialBodyIterator iterator() {
        return new MyDLIter(head);
    }

}

class MyDLNode {
    private CelestialBody body;
    private MyDLNode prev, next;

    public MyDLNode(CelestialBody body, MyDLNode prev, MyDLNode next) {
        this.body = body;
        this.prev = prev;
        this.next = next;
    }

    public CelestialBody body() {
        return body;
    }

    public MyDLNode prev() {
        return prev;
    }

    public MyDLNode next() {
        return next;
    }

    public void setPrev(MyDLNode prev) {
        this.prev = prev;
    }

    public void setNext(MyDLNode next) {
        this.next = next;
    }

    public void setBody(CelestialBody body) {
        this.body = body;
    }

}

class MyDLIter implements CelestialBodyIterator {
    private MyDLNode n;

    public MyDLIter(MyDLNode n) {
        this.n = n;
    }

    public boolean hasNext() {
        return n != null;
    }

    public CelestialBody next() {
        if (n == null) {
            return null;
        }
        CelestialBody result = n.body();
        n = n.next();
        return result;
    }
}
