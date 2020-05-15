/*
Vervollständigen Sie eine Klasse CelestialSystemIndexMap, die das Interface CelestialSystemIndex
mittels Hash-Tabelle implementiert. Verwenden Sie dabei keine vorgefertigten Klassen aus dem
Java-Collection-Framework, sondern orientieren Sie sich an den Beispielen aus dem Skriptum.
Wählen Sie eine geeignete Form der Kollisionsbehandlung. Testen Sie die Implementierung
mit eigenen Testfällen. Die Klasse CelestialSystemIndexMap soll einen parameterlosen Konstruktor haben.
 */

import java.util.Arrays;

public class CelestialSystemIndexMap implements CelestialSystemIndex {

    private int m = 50; // size of the hash map when first initialized
    private CelestialBody[] keys = new CelestialBody[m]; // keys of the hash map
    private CelestialSystem[] values = new CelestialSystem[m]; // values of the hash map
    private final static double a = (Math.sqrt(5) - 1) / 2; // irrational number for calculation (golden ratio)
    private int n; // variable that stores the hash map entries

    public CelestialSystemIndexMap() {
        n = 0;
    }

    // Method which calculates and returns the index where the new entry should be stored.
    // This method is responsible for collision handling, by increasing the parameter i.
    // This type of collision handling is called quadratic probing
    private int hashfunction(CelestialBody k, int i) {
        int y = (int) (k.hashCode() * a);
        double z = k.hashCode() * a;
        int x = (int) Math.sqrt(Math.pow((m * (z - y)), 2.0));
        return (int) ((x + 0.5 * i + 0.5 * i * i) % m);
    }

    // Method which calculates and returns the index where the new entry should be stored.
    // here no additional parameter is handed over, simply to get the first index where 'k' would be added.
    private int hashfunction(CelestialBody k) {
        int y = (int) (k.hashCode() * a);
        double z = k.hashCode() * a;
        return (int) Math.sqrt(Math.pow((m * (z - y)), 2.0));

    }

    // returns the index where CelestialBody k is stored
    // if CelestialBody k is not in the hash map, the length of the hash map - 1 is returned
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

    // Adds a system of bodies to the hash map.
    // Adding a system adds multiple (key, value) pairs to the
    // hash map, one for each body of the system, with the same
    // value, i.e., reference to the celestial system.
    // An attempt to add a system with a body that already exists
    // in the hash map leaves the hash map unchanged and the returned
    // value would be 'false'.
    // The method returns 'true' if the index was changed as a
    // result of the call and 'false' otherwise.
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

    // Returns the celestial system with which a body is
    // associated. If body is not contained as a key, 'null'
    // is returned.
    @Override
    public CelestialSystem get(CelestialBody body) {
        return values[find(body)];
    }

    // Returns 'true' if the specified 'body' is listed
    // in the hash map.
    @Override
    public boolean contains(CelestialBody body) {
        if (find(body) != keys.length - 1) {
            return true;
        }
        return false;
    }

    // Returns 'true' if 'o' is of the same class as 'this' and
    // 'this' and 'o' contain an equal set of
    // (key, value) pairs, i.e. each (key, value) pair of 'this'
    // is contained (i.e. has an equal counterpart) in 'o' and
    // vice versa. Two (key, value) pairs are equal if they have
    // equal keys and equal values.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CelestialSystemIndexMap that = (CelestialSystemIndexMap) o;
        if (that.n != this.n) return false;
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                if (!that.contains(keys[i]) || !values[i].equals(that.values[that.find(keys[i])])) {
                    return false;
                }
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
________________________________________________________________________________________________________________________
    2. Was passiert, wenn Sie nur hashCode löschen?

    Antwort: Im ersten Augenblick passiert nichts, jedoch wird nun die hashCode Methode von der Object Klasse verwendet,
             um einen hashCode zu generieren. In der Object Methode werden jedoch alle CelestialBody Attribute verwendet
             um einen hashCode zu generieren. Das führt dazu, dass die equals Methode nicht mehr mit der hashCode Methode
             zusammenpasst.
________________________________________________________________________________________________________________________
    3. Welche Bedingungen gelten allgemein für die Methoden equals und hashCode?

        equals:
            - x != null liefert x.equals(null) stets false
            - x != null liefert x.equals(x) stets (reflexiv)
            - x != null und y != null liefert x.equals(y) stets das gleiche Ergebnis wie y.equals(x) (symmetrisch)
            - x.equals(y) und y.equals(z) beide true leifern, dann tut dies auch x.equals(z) (transitiv)
            - Die Ergebnise müssen wiederholbar sein, dass bedeutet das bei unveränderten x und y equals das selbe
            Ergebnis liefert.

        hashCode:
            - Hat x.equals(y) als Ergebnis ture, dann x.hashCode() == y.hashCode().
            - Solange x nicht geändert wird, liefern wiederholte Aufrufe von x.hashCode stets gleiche Ergebnisse.
            - x.hashCode() == y.hashCode() folgt aus x.equals(y)
________________________________________________________________________________________________________________________
    4. Gilt in Ihrer Lösung, dass x.toString().equals(y.toString()) den Wert true liefert,
       wenn x.equals(y) den Wert true liefert? Welche Probleme können entstehen, wenn diese Bedingung nicht erfüllt ist?
       (Anmerkung: Es war in diesem Aufgabenblatt nicht verlangt, dass Ihre Lösung die Bedingung erfüllen muss.)

       Antwort: Nein, da die erzeugten Strings nicht gleich sind. Das liegt daran, dass die Reihenfolge der Bodys in
                einem CelestialSystem keine Rolle spielt. Auch bei der Hash-Tabelle wird die Reihenfolge nicht beachtet,
                sondern lediglich der Inhalt.
________________________________________________________________________________________________________________________
    5. Was könnte man ändern, damit neben CelestialSystemIndexMap auch CelestialSystemIndexTree das Interface
       CelestialSystemIndex implementieren kann?

       Antwort: Ich habe es probiert, indem ich die hashMap in der ich die Systeme im Baum speichere als Klassenattribut
                definiere um jederzeit, auf alle Systeme im Baum zugreifen zu können.
                Danach müssen die Methoden vom Interface noch implementiert werden. (siehe Klasse)
                Die verschiedenen Methoden rufe ich mittels Generalisierung auf. Dabei erbt die Klasse CelestialSystem-
                IndexTreeVariant von der Klasse CelestialSystemIndexTree und kann ihre Methoden nutzen.
                Ein paar Kleinigkeiten müssen noch verändert werden (z.B. die Übergabeparameter angepasst)
                Für die equals-Methode iteriere ich mit dem Iterator der hashSet Klasse über die Systeme und versuche
                diese zu adden, würde dies funktionieren, würde das bedeuten, dass die zu vergleichenden Objekte nicht
                equal sind.

 */