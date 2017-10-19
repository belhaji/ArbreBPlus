package tp;

/**
 * Created by adilbelhaji et Marie Rousselet on 10/4/17.
 */

public class Main {

    public static void main(String[] args) {
        ArbreBPlus<Integer, String> arbreBPlus = TreeGenerator.generate(true);
        arbreBPlus.rechercheNode(arbreBPlus.getRacine(), new Pair<>(41, null));
        Node<Integer,String> node = arbreBPlus.getNoeudTrouver();
        node.printNode(0);
    }
}
