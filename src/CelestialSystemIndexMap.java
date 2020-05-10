// TODO: define class according to 'Aufgabenblatt5'.
/*
Vervollständigen Sie eine Klasse CelestialSystemIndexMap, die das Interface CelestialSystemIndex
mittels Hash-Tabelle implementiert. Verwenden Sie dabei keine vorgefertigten Klassen aus dem
Java-Collection-Framework, sondern orientieren Sie sich an den Beispielen aus dem Skriptum.
Wählen Sie eine geeignete Form der Kollisionsbehandlung. Testen Sie die Implementierung
mit eigenen Testfällen. Die Klasse CelestialSystemIndexMap soll einen parameterlosen Konstruktor haben.
 */

import java.util.Arrays;

public class CelestialSystemIndexMap implements CelestialSystemIndex {

    private String[] ks = new String[65];
    private CelestialSystem[] vs = new CelestialSystem[65];
    private int count;

    public CelestialSystemIndexMap() {

    }

    private int find(CelestialSystem system) {
        if (system == null) {
            return ks.length - 1;
        }
        int i = system.hashCode() & (ks.length - 2);
        while (ks[i] != null && !ks[i].equals(system.getName())) {
            i = (i + 1) & (ks.length - 2);
        }
        return i;
    }

    private int find(String k) {
        if (k == null) {
            return ks.length - 1;
        }
        int i = k.hashCode() & (ks.length - 2);
        while (ks[i] != null && !ks[i].equals(k)) {
            i = (i + 1) & (ks.length - 2);
        }
        return i;
    }

    private int find(CelestialBody body) {
        if (body == null) {
            return ks.length - 1;
        }
        int i = body.hashCode() & (ks.length - 2);
        while (vs[i] != null && vs[i].get(body.getName()) != null) {
            i = (i + 1) & (ks.length - 2);
        }
        return i;
    }

    @Override
    public boolean add(CelestialSystem system) {
        if (system == null) {
            return false;
        }

        int i = find(system);
        if (vs[i] != null) {
            return false;
        }
        for (int x = 0; x < system.size(); x++) {
            if (find(system.get(x)) == ks.length - 1) {
                return false;
            }
        }
        CelestialSystem old = vs[i];
        vs[i] = system;
        if (ks[i] == null) {
            ks[i] = system.getName();
            if (++count >= ks.length * 3 / 4) {
                String[] oks = ks;
                CelestialSystem[] ovs = vs;
                ks = new String[(oks.length << 1) - 1];
                vs = new CelestialSystem[(oks.length << 1) - 1];
                for (int j = 0; j < oks.length; j++) {
                    if (oks[j] != null) {
                        ks[i = find(oks[j])] = oks[j];
                        vs[i] = ovs[j];
                    }
                }
            }
        }
        return true;
    }

    @Override
    public CelestialSystem get(CelestialBody body) {
        return vs[find(body.getName())];
    }

    @Override
    public boolean contains(CelestialBody body) {
        return ks[find(body.getName())] != null;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < ks.length - 1; i++) {
            if (ks[i] != null) {
                s += (s.isEmpty() ? "(" : ", (") + ks[i] + ", " + vs[i] + ")";
            }
        }
        return "{" + s + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CelestialSystemIndexMap that = (CelestialSystemIndexMap) o;
        return Arrays.equals(ks, that.ks);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(ks);
    }
}