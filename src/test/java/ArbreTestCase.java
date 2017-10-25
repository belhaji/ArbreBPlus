import org.junit.Assert;
import org.junit.Test;
import tp.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

/**
 * Created by adilbelhaji on 10/18/17.
 */
public class ArbreTestCase {

    @Test
    public void testPairSetSort() {
        Pair<Integer, String> pair1 = new Pair<>(1, "pair1");
        Pair<Integer, String> pair2 = new Pair<>(2, "pair2");
        Pair<Integer, String> pair3 = new Pair<>(3, "pair3");
        Set<Pair<Integer, String>> pairSet = new TreeSet<>();
        pairSet.add(pair2);
        pairSet.add(pair1);
        pairSet.add(pair3);
        Iterator<Pair<Integer, String>> pairIterator = pairSet.iterator();
        assertEquals(pairIterator.next(), pair1);
        assertEquals(pairIterator.next(), pair2);
        assertEquals(pairIterator.next(), pair3);
    }

    @Test
    public void testNodeSortSet() {
        Node<Integer, String> node1 = new Node<>();
        Pair<Integer, String> pair1 = new Pair<>(1, "pair1");
        Pair<Integer, String> pair2 = new Pair<>(2, "pair2");
        Pair<Integer, String> pair3 = new Pair<>(3, "pair3");
        node1.getElements().addAll(Arrays.asList(pair2, pair1, pair3));

        Node<Integer, String> node2 = new Node<>();
        Pair<Integer, String> pair4 = new Pair<>(4, "pair4");
        Pair<Integer, String> pair5 = new Pair<>(5, "pair5");
        Pair<Integer, String> pair6 = new Pair<>(6, "pair6");
        node2.getElements().addAll(Arrays.asList(pair5, pair4, pair6));

        Node<Integer, String> node3 = new Node<>();
        Pair<Integer, String> pair7 = new Pair<>(7, "pair7");
        Pair<Integer, String> pair8 = new Pair<>(8, "pair8");
        Pair<Integer, String> pair9 = new Pair<>(9, "pair9");
        node3.getElements().addAll(Arrays.asList(pair7, pair8, pair9));

        Set<Node<Integer, String>> nodeSet = new TreeSet<>();
        nodeSet.add(node3);
        nodeSet.add(node1);
        nodeSet.add(node2);
        Iterator<Node<Integer, String>> iterator = nodeSet.iterator();
        assertEquals(iterator.next(), node1);
        assertEquals(iterator.next(), node2);
    }

    @Test
    public void testRechercheNode() {
        ArbreBPlus<Integer, String> arbreBPlus = TreeGenerator.generate(true);
        arbreBPlus.rechercheNode(arbreBPlus.getRacine(), new Pair<>(41, null), false);
        Node<Integer, String> node = arbreBPlus.getNoeudTrouver();
        assertNotNull(node);
    }

    @Test
    public void testRechercheElement() {
        Integer cle = 30;
        ArbreBPlus<Integer, String> arbreBPlus = TreeGenerator.generate(true);
        Pair<Integer, String> pair = arbreBPlus.rechercheElement(arbreBPlus.getRacine(), new Pair<>(cle, null));
        assertNotNull(pair);
        System.out.println("Cle " + cle + " => " + pair.getValeur());
    }

    @Test
    public void testArbrePrint() {
        ArbreBPlus<Integer, String> arbreBPlus = TreeGenerator.generate(true);
        arbreBPlus.print();
    }

    @Test
    public void testArbrePrintPathTo() {
        ArbreBPlus<Integer, String> arbreBPlus = TreeGenerator.generate(true);
        arbreBPlus.printPathTo(new Pair<>(30, ""));
    }

    @Test
    public void testInsertion() {
        ArbreBPlus<Integer, String> arbreBPlus = new ArbreBPlus<>(3, Strategie.FULL, StrategieComparaison.EGALE, new Node<Integer, String>());
        //Integer[] keys = new Integer[]{20, 40, 60, 30, 25, 10, 70, 50, 53, 45};
        Integer[] keys = new Integer[]{50, 25, 40, 20, 10, 30, 49, 53, 45, 60, 70, 42, 46,47};
        for (Integer key : keys) {
            System.out.println("Ajout de " + key);
            arbreBPlus.insert(new Pair<Integer, String>(key, "val" + key));
            arbreBPlus.print();
        }

    }


}
