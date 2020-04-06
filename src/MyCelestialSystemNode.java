public class MyCelestialSystemNode {
    private CelestialBody body;
    private MyCelestialSystemNode next;

    public MyCelestialSystemNode(CelestialBody body, MyCelestialSystemNode next) {
        this.body = body;
        this.next = next;
    }

    public CelestialBody body() {
        return body;
    }

    public MyCelestialSystemNode next() {
        return next;
    }

    public void setNext(MyCelestialSystemNode next) {
        this.next = next;
    }

    public void setBody(CelestialBody body) {
        this.body = body;
    }

}
