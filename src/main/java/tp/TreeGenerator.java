package tp;

/**
 * Created by adilbelhaji on 10/19/17.
 */
public class TreeGenerator {
    public static ArbreBPlus<Integer, String> generate(boolean debug) {
        Node<Integer, String> root = createNode(45, 50);

        Node<Integer, String> node = createNode(25, 40);
        node.getFils().add(createNode(10, 20));
        node.getFils().add(createNode(25, 30));
        node.getFils().add(createNode(40, 42));
        root.getFils().add(node);

        node = createNode(47);
        node.getFils().add(createNode(45, 46));
        node.getFils().add(createNode(47, 49));
        root.getFils().add(node);

        node = createNode(60);
        node.getFils().add(createNode(50, 53));
        node.getFils().add(createNode(60, 70));
        root.getFils().add(node);
        return new ArbreBPlus<>(3, Strategie.FULL, StrategieComparaison.EGALE, root, debug);

    }

    private static Node<Integer, String> createNode(Integer... vals) {
        Node<Integer, String> node = new Node<>();
        for (Integer v : vals) {
            node.getElements().add(new Pair<>(v, null));
        }
        return node;
    }
}
