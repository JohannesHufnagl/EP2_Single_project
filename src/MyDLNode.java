public class MyDLNode {
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
