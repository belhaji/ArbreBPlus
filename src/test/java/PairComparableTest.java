import org.junit.Assert;
import org.junit.Test;
import tp.Node;
import tp.Pair;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

/**
 * Created by adilbelhaji on 10/18/17.
 */
public class PairComparableTest {

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
        nodeSet.forEach(n -> System.out.println(n));
        Iterator<Node<Integer,String>> iterator = nodeSet.iterator();
        assertEquals(iterator.next(), node1);
        assertEquals(iterator.next(), node2);
    }
}
