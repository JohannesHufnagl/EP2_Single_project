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

    private int m = 50;
    private CelestialBody[] keys = new CelestialBody[m];
    private CelestialSystem[] values = new CelestialSystem[m];
    private final static double a = (Math.sqrt(5) - 1) / 2;
    private int n;

    public CelestialSystemIndexMap() {
        n = 0;
    }

    private int hashfunction(CelestialBody k, int i) {
        int y = (int) (k.hashCode() * a);
        double z = k.hashCode() * a;
        int x = (int) Math.sqrt(Math.pow((m * (z - y)), 2.0));
        return (int) ((x + 0.5 * i + 0.5 * i * i) % m);
    }

    private int hashfunction(CelestialBody k) {
        int y = (int) (k.hashCode() * a);
        double z = k.hashCode() * a;
        return (int) Math.sqrt(Math.pow((m * (z - y)), 2.0));

    }

    private int find(CelestialBody k) {
        if (k == null) {
            return keys.length - 1;
        }

        int j = hashfunction(k);
        do {
            int xi = hashfunction(k, j);
            if (k.equals(keys[xi])) {
                return xi;
            } else {
                if (j < keys.length - 1) {
                    j++;
                } else {
                    j = 0;
                }
            }
        } while (j != hashfunction(k));
        return keys.length - 1;
    }

    @Override
    public boolean add(CelestialSystem system) {
        if (system == null) {
            return false;
        }
        for (int i = 0; i < system.size(); i++) {
            if (find(system.get(i)) != keys.length - 1) {
                return false;
            }
        }
        n += system.size();
        if ((double) n / m > 0.8) {
            m = m * 2 + n;
            CelestialBody[] oks = keys;
            CelestialSystem[] ovs = values;
            keys = new CelestialBody[2 * m];
            values = new CelestialSystem[2 * m];
            for (int j = 0; j < oks.length; j++) {
                if (oks[j] != null) {
                    keys[find(oks[j])] = oks[j];
                    values[find(oks[j])] = ovs[j];
                }
            }
        }
        for (int i = 0; i < system.size(); i++) {
            int j = hashfunction(system.get(i));
            boolean added = false;
            while (!added) {
                int xi = hashfunction(system.get(i), j);
                if (keys[xi] == null) {
                    keys[xi] = system.get(i);
                    values[xi] = system;
                    added = true;
                } else {
                    if (j < keys.length - 1) {
                        j++;
                    } else {
                        j = 0;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public CelestialSystem get(CelestialBody body) {
        return values[find(body)];
    }

    @Override
    public boolean contains(CelestialBody body) {
        if (find(body) != keys.length - 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CelestialSystemIndexMap that = (CelestialSystemIndexMap) o;
        if (that.n != this.n) return false;
        for (int i = 0; i < m; i++) {
            if (that.contains(keys[i]) && keys[i] != null && values[i].equals(that.values[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(keys);
        result += 31 * Arrays.hashCode(values);
        return result;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < keys.length - 1; i++) {
            if (keys[i] != null) {
                s += (s.isEmpty() ? "(" : ", (") + keys[i].getName() + ", " + values[i].getName() + ")";
            }
        }
        return "{" + s + "}";
    }
}

/*
    Zusatzfragen:

    1. Wie ändert sich das Verhalten von CelestialSystemIndexMap, wenn Sie in Ihrer Lösung die Implementierung
       der Methoden equals und hashCode aus der Klasse CelestialBody löschen?

    Antwort: Es wird nicht mehr richtig erkannt ob ein body bereits in der Hash-Tabelle vorhanden ist. Daraus folgt,
             dass nun gleiche (im Sinne von gleichen bodys was ja eigentlich in der equals Methode in CelestialBody
             überprüft wurde) (body, system) Paare mehrfach hinzugefügt werden können.

    2. Was passiert, wenn Sie nur hashCode löschen?

    Antwort: Im ersten Augenblick passiert nichts, jedoch wird nun die hashCode Methode von der Object Klasse verwendet,
             um einen hashCode zu generieren. In der Object Methode werden jedoch alle CelestialBody Attribute verwendet
             um einen hashCode zu generieren.

    3. Welche Bedingungen gelten allgemein für die Methoden equals und hashCode?

    4. Gilt in Ihrer Lösung, dass x.toString().equals(y.toString()) den Wert true liefert,
       wenn x.equals(y) den Wert true liefert? Welche Probleme können entstehen, wenn diese Bedingung nicht erfüllt ist?
       (Anmerkung: Es war in diesem Aufgabenblatt nicht verlangt, dass Ihre Lösung die Bedingung erfüllen muss.)

    5. Was könnte man ändern, damit neben CelestialSystemIndexMap auch CelestialSystemIndexTree das Interface
       CelestialSystemIndex implementieren kann?
 */