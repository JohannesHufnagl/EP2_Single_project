public class CelestialSystem {
    /******************************************************
     class CelestialSystem :

     public methods :

     add(CelestialBoddy body):
     Adds 'body' to the list of bodies of the system if the list does not already contain a
     body with the same name as 'body', otherwise does not change the object state. The method
     returns 'true' if the list was changed as a result of the call and 'false' otherwise.

     get(int i):
     Returns the 'body' with the index 'i'. The body that was first added to the list has the
     index 0, the body that was most recently added to the list has the largest index (size()-1).

     get(String name):
     Returns the body with the specified name or 'null' if no such body exits in the list.

     size():
     returns the number of entries of the list.
     ******************************************************/
    //TODO: Define variables.
    private String name;
    private int mask = (1 << 3) - 1;
    private CelestialBody[] list = new CelestialBody[mask + 1];
    private int pointer;

    // Initialises this system as an empty system with a name.
    public CelestialSystem(String name) {
        //TODO: implement constructor.
        this.name = name;
    }

    // Adds 'body' to the list of bodies of the system if the list does not already contain a
    // body with the same name as 'body', otherwise does not change the object state. The method
    // returns 'true' if the list was changed as a result of the call and 'false' otherwise.
    public boolean add(CelestialBody body) {
        //TODO: implement method.
        if (this.get(body.getName()) != null)
            return false;
        else {
            list[pointer] = body;
            pointer = (pointer + 1) & mask;
            if (pointer == mask) {
                doubleCapacity();
            }
            return true;
        }
    }

    // Returns the 'body' with the index 'i'. The body that was first added to the list has the
    // index 0, the body that was most recently added to the list has the largest index (size()-1).
    public CelestialBody get(int i) {
        //TODO: implement method.
        return list[i];
    }

    // Returns the body with the specified name or 'null' if no such body exits in the list.
    public CelestialBody get(String name) {
        //TODO: implement method.
        for (int i = 0; i < pointer; i++) {
            if (list[i].getName().equals(name)) {
                return list[i];
            }
        }
        return null;
    }

    // returns the number of entries of the list.
    public int size() {
        //TODO: implement method.
        return pointer;
    }

    //TODO: Define additional class(es) implementing the linked list (either here or outside class).
    private void doubleCapacity() {
        mask = (mask << 1) | 1;
        CelestialBody[] newList = new CelestialBody[mask + 1];
        int i = 0, j = 0;
        while (i < pointer) {
            newList[j++] = list[i++];
        }
        j = pointer += list.length;
        while (i < list.length) {
            newList[j++] = list[i++];
        }
        list = newList;
    }
}
