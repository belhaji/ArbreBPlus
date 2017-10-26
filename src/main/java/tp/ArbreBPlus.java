package tp;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by adilbelhaji et Marie Rousselet on 10/4/17.
 */
public class ArbreBPlus<C extends Comparable<C>, V> {
    private int n;
    private Strategie strategie;
    private StrategieComparaison strategieComparaison;
    private Node<C, V> racine;
    private Node<C, V> noeudTrouver = null;

    public ArbreBPlus(int n, Strategie strategie, StrategieComparaison strategieComparaison, Node<C, V> racine) {
        this.n = n;
        this.strategie = strategie;
        this.strategieComparaison = strategieComparaison;
        this.racine = racine;
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
    public void insert(Pair<C, V> pair) {
        rechercheElement(this.racine, pair);
        if (noeudTrouver != null) {
            noeudTrouver.getElements().add(pair);
            if (noeudTrouver.getElements().size() > n) // si il y a un debordement
                splitElement(noeudTrouver);
        }
        noeudTrouver = null;
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
        int milieu = (node.getElements().size() / 2);
        List<Pair<C, V>> elementsAGauche = node.getElements().stream().limit(milieu).collect(Collectors.toList());
        List<Pair<C, V>> elementsADroite = node.getElements().stream().skip(milieu).collect(Collectors.toList());

        Node<C, V> nodeFilsDroite = new Node<>();
        Node<C, V> nodeFilsGauche = new Node<>();

        nodeFilsDroite.getElements().addAll(elementsADroite);
        nodeFilsGauche.getElements().addAll(elementsAGauche);


        if (node.isRacine()) {

            // reusing the same node
            node.getElements().clear();
            Pair<C, V> el = new Pair<C, V>(elementsADroite.get(0).getCle(), elementsADroite.get(0).getValeur());
            el.setValeur(null);
            node.getElements().add(el);

            // parent
            nodeFilsDroite.setParent(node);
            nodeFilsGauche.setParent(node);

            if (!node.isFeuille()) {
                Iterator<Pair<C, V>> parentIterator = nodeFilsGauche.getElements().iterator();
                Iterator<Node<C, V>> filsIterator = node.getFils().iterator();
                Node<C, V> n = null;
                Pair<C, V> p = null;
                boolean passToNextChild = true;
                boolean passToNextElement = true;
                while (parentIterator.hasNext() && filsIterator.hasNext()) {
                    if (passToNextChild) n = filsIterator.next();
                    if (passToNextElement) p = parentIterator.next();
                    Pair<C, V> filsMin = n.getElements().iterator().next();
                    if (filsMin.compareTo(p) <= 0) {
                        nodeFilsGauche.getFils().add(n);
                        n.setParent(nodeFilsGauche);
                        passToNextChild = true;
                        passToNextElement = false;
                    } else {
                        passToNextChild = false;
                        passToNextElement = true;
                    }
                }
                while (filsIterator.hasNext()) {
                    Node<C, V> nn = filsIterator.next();
                    nodeFilsDroite.getFils().add(nn);
                    nn.setParent(nodeFilsDroite);
                }


                if (nodeFilsDroite.getFils().size() < (nodeFilsDroite.getElements().size() + 1)) {
                    // suppressio du 1er element
                    nodeFilsDroite.getElements().remove(elementsADroite.get(0));
                }

            }

            node.getFils().clear();
            node.getFils().add(nodeFilsDroite);
            node.getFils().add(nodeFilsGauche);
        } else {

            Pair<C, V> el = new Pair<>(elementsADroite.get(0).getCle(), elementsADroite.get(0).getValeur());
            el.setValeur(null);
            node.getParent().getElements().add(el);
            // parent
            nodeFilsDroite.setParent(node.getParent());
            nodeFilsGauche.setParent(node.getParent());

            node.getParent().getFils().add(nodeFilsDroite);
            node.getParent().getFils().add(nodeFilsGauche);

            if (!node.isFeuille()) {


                Iterator<Pair<C, V>> parentIterator = nodeFilsGauche.getElements().iterator();
                Iterator<Node<C, V>> filsIterator = node.getFils().iterator();
                Node<C, V> n = null;
                Pair<C, V> p = null;
                boolean passToNextChild = true;
                boolean passToNextElement = true;
                while (parentIterator.hasNext() && filsIterator.hasNext()) {
                    if (passToNextChild) n = filsIterator.next();
                    if (passToNextElement) p = parentIterator.next();
                    Pair<C, V> filsMin = n.getElements().iterator().next();
                    if (filsMin.compareTo(p) <= 0) {
                        nodeFilsGauche.getFils().add(n);
                        n.setParent(nodeFilsGauche);
                        passToNextChild = true;
                        passToNextElement = false;
                    } else {
                        passToNextChild = false;
                        passToNextElement = true;
                    }
                }
                while (filsIterator.hasNext()) {
                    Node<C, V> nn = filsIterator.next();
                    nodeFilsDroite.getFils().add(nn);
                    nn.setParent(nodeFilsDroite);
                }

                if (nodeFilsDroite.getFils().size() < (nodeFilsDroite.getElements().size() + 1)) {
                    // suppressio du 1er element
                    nodeFilsDroite.getElements().remove(elementsADroite.get(0));
                }

            }

            node.getParent().getFils().removeIf(n -> n.getId().equals(node.getId()));
            if (node.getParent().getElements().size() > n) {
                splitElement(node.getParent());
            }
        }

    }


    public Pair<C, V> rechercheElement(Node<C, V> node, Pair<C, V> pair) {
        noeudTrouver = null;
        rechercheNode(node, pair, false);
        if (noeudTrouver != null)
            return noeudTrouver.getElements().stream()
                    .filter(p -> p.equals(pair))
                    .findFirst()
                    .orElse(null);
        return null;
    }


    public void rechercheNode(Node<C, V> node, Pair<C, V> pair, boolean printPath) {
        if (printPath) {
            node.printNode(0);
            if (!node.isFeuille()) {
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
                    rechercheNode(fils.next(), pair, printPath);
                    return;
                } else {
                    fils.next();
                }

            }
            if (fils.hasNext())
                rechercheNode(fils.next(), pair, printPath);
        }
    }


    // TODO: à ameliorer pour recalculer les fils lord de suppression de un fils
    public void suppression(Node<C, V> node, Pair<C, V> pair) {
        noeudTrouver = null;
        rechercheNode(node, pair, false);
        if (noeudTrouver != null) {
            noeudTrouver.getElements().removeIf(p -> p.getCle().equals(pair.getCle()));
            if (noeudTrouver.getElements().size() == 0) {
                if (noeudTrouver.isRacine()) {
                    this.racine = null;
                } else {
                    // supression de noeu vide
                    noeudTrouver.getParent().getFils().removeIf(f -> f.getId().equals(noeudTrouver.getId()));
                }
            }
        }
    }

    /**
     * l'idée ici de recreer l'arbre en ignorant l'element à supprimmer
     *
     * @param pair
     */
    public void suppressionNaif(List<Pair<C, V>> list, Pair<C, V> pair) {
        Node<C, V> newRacine = new Node<>();
        list.removeIf(p -> p.equals(pair));
        this.racine = newRacine;
        list.forEach(this::insert);
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

    public void printPathTo(Pair<C, V> pair) {
        this.rechercheNode(this.racine, pair, true);
    }

}
