package tp;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by adilbelhaji et Marie Rousselet on 10/4/17.
 */

public class Main {

    public static void main(String[] args) {
        ArbreBPlus<Integer, String> arbreBPlus = new ArbreBPlus<>(3, Strategie.FULL, StrategieComparaison.EGALE, new
                Node<Integer, String>());
        List<Pair<Integer, String>> list = FileUtils.getPairListForKeyAge("all.txt");
        for (Pair<Integer, String> p:list){
            System.out.println("Insertion de " + p.getCle());
            arbreBPlus.insert(p);
            arbreBPlus.print();
        }

        // 57 


    }
}
