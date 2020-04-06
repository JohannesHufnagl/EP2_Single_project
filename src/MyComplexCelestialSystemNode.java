public class MyComplexCelestialSystemNode {
    private CelestialSystem system;
    private MyComplexCelestialSystemNode next;

    public MyComplexCelestialSystemNode(CelestialSystem system, MyComplexCelestialSystemNode next) {
        this.system = system;
        this.next = next;
    }

    public CelestialSystem system() {
        return system;
    }

    public MyComplexCelestialSystemNode next() {
        return next;
    }

    public void setNext(MyComplexCelestialSystemNode next) {
        this.next = next;
    }

    public void setSystem(CelestialSystem system) {
        this.system = system;
    }


}
