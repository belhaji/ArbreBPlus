package tp;

import java.util.*;

/**
 * Created by adilbelhaji et Marie Rousselet on 10/4/17.
 */
public class Node<C extends Comparable<C>, V> implements Comparable<Node<C, V>> {
    private UUID id;
    private Set<Pair<C, V>> elements = new TreeSet<>();
    private Set<Node<C, V>> fils = new TreeSet<>();
    private List<Node<C, V>> voisins = new ArrayList<>();
    private Node<C, V> parent;


    public Node() {
        this.id = UUID.randomUUID();
    }

    @Override
    public int compareTo(Node<C, V> o) {
        C max1 = this.elements.stream().map(Pair::getCle).max(Comparable::compareTo).get();
        C max2 = o.elements.stream().map(Pair::getCle).max(Comparable::compareTo).get();
        if (max1.compareTo(max2) > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    public boolean isRacine() {
        return this.parent == null;
    }

    public boolean isFeuille() {
        return (this.fils.size() == 0);
    }

    public void printNode(int depth) {
        StringBuilder tabs = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            tabs.append("\t");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("| ");
        StringBuilder dashes = new StringBuilder();
        dashes.append("+");
        Iterator<Pair<C, V>> iterator = this.getElements().iterator();
        while (iterator.hasNext()) {
            dashes.append("----+");
            sb.append(String.format("%1s | ", iterator.next().getCle().toString()));
        }
        System.out.println(tabs.toString() + dashes.toString());
        System.out.println(tabs.toString() + sb.toString());
        System.out.println(tabs.toString() + dashes.toString());
    }

    public boolean isIntermediaire() {
        return (!isRacine() && !isFeuille());
    }

    public boolean hasParent() {
        return this.parent != null;
    }

    public UUID getId() {
        return id;
    }

    public Set<Pair<C, V>> getElements() {
        return elements;
    }

    public Set<Node<C, V>> getFils() {
        return fils;
    }

    public List<Node<C, V>> getVoisins() {
        return voisins;
    }

    public Node<C, V> getParent() {
        return parent;
    }

    public void setElements(Set<Pair<C, V>> elements) {
        this.elements = elements;
    }

    public void setFils(Set<Node<C, V>> fils) {
        this.fils = fils;
    }

    public void setVoisins(List<Node<C, V>> voisins) {
        this.voisins = voisins;
    }

    public void setParent(Node<C, V> parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Node{" +
                ", elements=" + elements +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Node<?, ?> node = (Node<?, ?>) o;

        return id != null ? id.equals(node.id) : node.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
