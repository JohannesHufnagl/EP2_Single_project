public class ComplexCelestialSystem {
    /******************************************************
     class ComplexCelestialSystem : ComplexCelestialSystem is a linked list,
     which contains numerous CelestialSystem Objects.

     public methods :

     add(CelestialBoddy body):
     Adds a subsystem of bodies to this system if there are no bodies in the subsystem
     with the same name as a body or subsystem of this system.
     The method returns 'true' if the collection was changed as a result of the call and
     'false' otherwise.

     get(String name):
     Returns the single body or subsystem with 'name' or 'null' if no such body or subsystem
     exists in this system. In case of a single body, the body is returned as a subsystem of
     one body, with the same name as the body.

     size():
     Returns the number of bodies of the entire system.
     ******************************************************/
    //TODO: Define variables.
    private String name;
    private int mask = (1 << 3) - 1;
    private CelestialSystem[] list = new CelestialSystem[mask + 1];
    private int pointer;

    // Initializes this system as an empty system with a name.
    public ComplexCelestialSystem(String name) {
        //TODO: implement constructor.
        this.name = name;
    }

    // Adds a subsystem of bodies to this system if there are no bodies in the subsystem
    // with the same name as a body or subsystem of this system.
    // The method returns 'true' if the collection was changed as a result of the call and
    // 'false' otherwise.
    public boolean add(CelestialSystem subsystem) {
        //TODO: implement method.
        if (subsystem.get(this.name) != null)
            return false;
        else {
            list[pointer] = subsystem;
            pointer = (pointer + 1) & mask;
            if (pointer == mask) {
                doubleCapacity();
            }
            return true;
        }
    }

    // Returns the single body or subsystem with 'name' or 'null' if no such body or subsystem 
    // exists in this system. In case of a single body, the body is returned as a subsystem of 
    // one body, with the same name as the body.
    public CelestialSystem get(String name) {
        //TODO: implement method.
        for (int i = 0; i < pointer; i++) {
            if (list[i].getName().equals(name)) {
                return list[i];
            } else if (list[i].get(name).getName().equals(name)) {
                CelestialSystem found = new CelestialSystem(name);
                found.add(list[i].get(name));
                add(found);
                return found;
            }
        }
        return null;
    }

    // Returns the number of bodies of the entire system.
    public int size() {
        //TODO: implement method.
        int size = 0;
        for (int i = 0; i < pointer; i++) {
            size += list[i].size();
        }
        return size;
    }

    //TODO: Define additional class(es) implementing a linked list (either here or outside class).

    private void doubleCapacity() {
        mask = (mask << 1) | 1;
        CelestialSystem[] newList = new CelestialSystem[mask + 1];
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


