package tp;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by adilbelhaji et Marie Rousselet on 10/4/17.
 */
public class ArbreBPlus<C extends Comparable<C>, V> {
    private int n;
    private Strategie strategie;
    private StrategieComparaison strategieComparaison;
    private Node<C, V> racine;
    private Node<C, V> noeudTrouver = null;
    private boolean debug;

    public ArbreBPlus(int n, Strategie strategie, StrategieComparaison strategieComparaison, Node<C, V> racine, boolean debug) {
        this.n = n;
        this.strategie = strategie;
        this.strategieComparaison = strategieComparaison;
        this.racine = racine;
        this.debug = debug;
    }

    public int getN() {
        return n;
    }

    public Strategie getStrategie() {
        return strategie;
    }

    public Node<C, V> getRacine() {
        return racine;
    }

    public StrategieComparaison getStrategieComparaison() {
        return strategieComparaison;
    }

    public Node<C, V> getNoeudTrouver() {
        return noeudTrouver;
    }

    //TODO: à completer
    public void insertIntoNode(Node<C, V> node, Pair<C, V> pair) {
        rechercheElement(node, pair);
        if (noeudTrouver != null) {

        }
    }

    public int tauxDeRemplisage(Node<C, V> node) {
        if (node.isFeuille()) {
            return (int) Math.ceil((n + 1) / 2.0);
        } else {
            return (int) Math.floor((n + 1) / 2.0);
        }
    }


    /**
     * @param node le noeud debordé
     */
    public void splitElement(Node<C, V> node) {
        if (node.isFeuille() && node.isRacine()) {
            int milieu = (node.getElements().size() / 2);
            Pair<C, V> elementsAGauche[] = (Pair<C, V>[]) node.getElements().stream().limit(milieu).toArray();
            Pair<C, V> elementsADroite[] = (Pair<C, V>[]) node.getElements().stream().skip(milieu).toArray();

            Node<C, V> nodeFilsDroite = new Node<>();

            Node<C, V> nodeFilsGauche = new Node<>();

            // vider la racine
            node.getElements().clear();

            // elements
            node.getElements().add(elementsADroite[0]);
            nodeFilsDroite.getElements().addAll(Arrays.asList(elementsADroite));
            nodeFilsGauche.getElements().addAll(Arrays.asList(elementsAGauche));


            // voisins
            nodeFilsDroite.getVoisins().add(nodeFilsGauche);
            nodeFilsGauche.getVoisins().add(nodeFilsDroite);


            // parent
            nodeFilsDroite.setParent(node);
            nodeFilsGauche.setParent(node);

            this.racine = node;

        } else if (node.isFeuille() && !node.isRacine()) {
            int milieu = (node.getElements().size() / 2);
            Pair<C, V> elementsAGauche[] = (Pair<C, V>[]) node.getElements().stream().limit(milieu).toArray();
            Pair<C, V> elementsADroite[] = (Pair<C, V>[]) node.getElements().stream().skip(milieu).toArray();

            Node<C, V> nodeFilsDroite = new Node<>();

            Node<C, V> nodeFilsGauche = new Node<>();


            // elements
            node.getParent().getElements().add(elementsADroite[0]);
            nodeFilsDroite.getElements().addAll(Arrays.asList(elementsADroite));
            nodeFilsGauche.getElements().addAll(Arrays.asList(elementsAGauche));


            // voisins
            nodeFilsDroite.getVoisins().add(nodeFilsGauche);
            nodeFilsDroite.getVoisins().addAll(node.getParent().getFils());

            nodeFilsGauche.getVoisins().add(nodeFilsDroite);
            nodeFilsGauche.getVoisins().addAll(node.getParent().getFils());

            // parent
            nodeFilsDroite.setParent(node.getParent());
            nodeFilsGauche.setParent(node.getParent());
            // suppression d'ancien noeud
            node.getParent().getFils().remove(node);

            if (node.getParent().getElements().size() >= n) {
                splitElement(node);
            }
        } else if (node.isRacine() && node.getFils().size() > 0) {
            int milieu = (node.getElements().size() / 2);
            Pair<C, V> elementsAGauche[] = (Pair<C, V>[]) node.getElements().stream().limit(milieu).toArray();
            Pair<C, V> elementsADroite[] = (Pair<C, V>[]) node.getElements().stream().skip(milieu).toArray();

            Node<C, V> nodeFilsDroite = new Node<>();

            Node<C, V> nodeFilsGauche = new Node<>();


            node.getElements().clear();
            Set<Node<C, V>> fils = node.getFils();

            // elements
            node.getElements().add(elementsADroite[0]);
            nodeFilsDroite.getElements().addAll(Arrays.asList(elementsADroite));
            nodeFilsGauche.getElements().addAll(Arrays.asList(elementsAGauche));


            // voisins à calculer
            nodeFilsDroite.getVoisins().add(nodeFilsGauche);
            nodeFilsGauche.getVoisins().add(nodeFilsDroite);


            // parent
            nodeFilsDroite.setParent(node);
            nodeFilsGauche.setParent(node);

            for (Node<C, V> n : fils) {
                Pair<C, V> parentMin = n.getElements().iterator().next();
                Pair<C, V> filsMin = node.getElements().iterator().next();
                if (parentMin.getCle().compareTo(filsMin.getCle()) >= 0) {
                    nodeFilsDroite.getFils().add(n);
                } else {
                    nodeFilsGauche.getFils().add(n);
                }
            }

            node.getFils().clear();
            node.getFils().add(nodeFilsDroite);
            node.getFils().add(nodeFilsGauche);

            this.racine = node;
        } else if (node.isIntermediaire()) {
            int milieu = (node.getElements().size() / 2);
            Pair<C, V> elementsAGauche[] = (Pair<C, V>[]) node.getElements().stream().limit(milieu).toArray();
            Pair<C, V> elementsADroite[] = (Pair<C, V>[]) node.getElements().stream().skip(milieu).toArray();

            Node<C, V> nodeFilsDroite = new Node<>();

            Node<C, V> nodeFilsGauche = new Node<>();


            Set<Node<C, V>> fils = node.getFils();

            // elements
            node.getParent().getElements().add(elementsADroite[0]);
            nodeFilsDroite.getElements().addAll(Arrays.asList(elementsADroite));
            nodeFilsGauche.getElements().addAll(Arrays.asList(elementsAGauche));


            // voisins à calculer
            nodeFilsDroite.getVoisins().add(nodeFilsGauche);
            nodeFilsGauche.getVoisins().add(nodeFilsDroite);


            // parent
            nodeFilsDroite.setParent(node.getParent());
            nodeFilsGauche.setParent(node.getParent());

            node.getParent().getFils().add(nodeFilsDroite);
            node.getParent().getFils().add(nodeFilsGauche);

            for (Node<C, V> n : fils) {
                Pair<C, V> filsMin = n.getElements().iterator().next();
                Pair<C, V> parentMin = node.getParent().getElements().iterator().next();
                if (parentMin.getCle().compareTo(filsMin.getCle()) >= 0) {
                    nodeFilsDroite.getFils().add(n);
                } else {
                    nodeFilsGauche.getFils().add(n);
                }
            }
            node.getParent().getFils().remove(node);
            if (node.getParent().getElements().size() >= n) {
                splitElement(node);
            }
        }


    }


    public Pair<C, V> rechercheElement(Node<C, V> node, Pair<C, V> pair) {
        noeudTrouver = null;
        rechercheNode(node, pair);
        return noeudTrouver.getElements().stream()
                .filter(p -> p.equals(pair))
                .findFirst()
                .orElse(null);
    }


    public void rechercheNode(Node<C, V> node, Pair<C, V> pair) {
        if (debug) {
            node.printNode(0);
            if (!node.isFeuille()){
                System.out.println("||");
                System.out.println("\\/");
            }

        }
        if (node.isFeuille()) {
            noeudTrouver = node;
        } else {
            Iterator<Pair<C, V>> parentElements = node.getElements().iterator();
            Iterator<Node<C, V>> fils = node.getFils().iterator();
            while (parentElements.hasNext()) {
                Pair<C, V> currentElement = parentElements.next();
                if (pair.compareTo(currentElement) < 0) {
                    rechercheNode(fils.next(), pair);
                    return;
                } else {
                    if (parentElements.hasNext() || pair.compareTo(currentElement) >= 0) {
                        fils.next();
                    }
                }

            }
            rechercheNode(fils.next(), pair);
        }
    }

    private void print(Node<C, V> node, int depth) {
        node.printNode(depth);
        for (Node<C, V> n : node.getFils()) {
            print(n, depth + 1);
        }

    }


    public void print() {
        print(this.racine, 0);
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
