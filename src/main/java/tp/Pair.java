package tp;

/**
 * Created by adilbelhaji on 10/10/17.
 */
public class Pair<C extends Comparable<C>, V> implements Comparable<Pair<C, V>> {
    private C cle;
    private V valeur;

    public Pair(C cle, V valeur) {
        this.cle = cle;
        this.valeur = valeur;
    }

    public C getCle() {
        return cle;
    }

    public void setCle(C cle) {
        this.cle = cle;
    }

    public V getValeur() {
        return valeur;
    }

    public void setValeur(V valeur) {
        this.valeur = valeur;
    }

    @Override
    public int compareTo(Pair<C, V> p) {
        return this.cle.compareTo(p.getCle());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (cle != null ? !cle.equals(pair.cle) : pair.cle != null) return false;
        return valeur != null ? valeur.equals(pair.valeur) : pair.valeur == null;
    }

    @Override
    public int hashCode() {
        int result = cle != null ? cle.hashCode() : 0;
        result = 31 * result + (valeur != null ? valeur.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "cle=" + cle +
                ", valeur=" + valeur +
                '}';
    }
}
