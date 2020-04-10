public class MyNode {
    private CelestialBody body;
    private CelestialSystem system;
    private MyNode next;

    public MyNode(CelestialBody body, MyNode next) {
        this.body = body;
        this.next = next;
    }

    public MyNode(CelestialSystem system, MyNode next) {
        this.system = system;
        this.next = next;
    }

    public CelestialBody body() {
        return body;
    }

    public CelestialSystem system() {
        return system;
    }

    public MyNode next() {
        return next;
    }

    public void setNext(MyNode next) {
        this.next = next;
    }

    public void setBody(CelestialBody body) {
        this.body = body;
    }

    public String toString() {
        return body != null ? this.body.toString() : this.system.toString();
    }

}
