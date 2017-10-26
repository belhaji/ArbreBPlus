package tp;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by adilbelhaji et Marie Rousselet on 10/4/17.
 */

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ArbreBPlus<Integer, String> arbreBPlus1 = null;
        ArbreBPlus<String, Integer> arbreBPlus2 = null;
        do {
            System.out.println("Choisir la cle 1 pour l'age et 2 pour le prenom");
            System.out.print(">> ");
            int choix = Integer.parseInt(scanner.nextLine());
            System.out.println("Choisir n ");
            System.out.print(">> ");
            int n = Integer.parseInt(scanner.nextLine());


            if (choix == 1) {

                arbreBPlus1 = new ArbreBPlus<>(n, Strategie.FULL, StrategieComparaison.EGALE, new
                        Node<Integer, String>());
                ArbreBPlus<Integer, String> finalArbreBPlus1 = arbreBPlus1;
                List<Pair<Integer, String>> list = FileUtils.getPairListForKeyAge("all.txt");
                list.forEach(finalArbreBPlus1::insert);
                do {
                    System.out.println("1. Chercher un element.");
                    System.out.println("2. Afficher le chemain vers un element.");
                    System.out.println("3. Supprimer un element");
                    System.out.println("4. Afficher l'arbre.");
                    System.out.print(">> ");
                    choix = Integer.parseInt(scanner.nextLine());
                    if (choix == 1) {
                        System.out.print("Donner la cle : ");
                        int element = Integer.parseInt(scanner.nextLine());
                        Pair<Integer, String> p = finalArbreBPlus1.rechercheElement(finalArbreBPlus1.getRacine(), new Pair<>(element, null));
                        if (p != null)
                            System.out.println("Valeur est " + p.getValeur());
                        else
                            System.out.println("element n'existe pas ");
                    } else if (choix == 2) {
                        System.out.print("Donner la cle : ");
                        int element = Integer.parseInt(scanner.nextLine());
                        finalArbreBPlus1.printPathTo(new Pair<>(element, null));
                    } else if (choix == 3) {
                        System.out.print("Donner la cle : ");
                        int element = Integer.parseInt(scanner.nextLine());
                        finalArbreBPlus1.suppressionNaif(list, new Pair<>(element, null));
                    } else if (choix == 4) {
                        finalArbreBPlus1.print();
                    } else {
                        System.err.println("Choix Incorrect");
                    }

                } while (true);
            } else if (choix == 2) {
                arbreBPlus2 = new ArbreBPlus<>(n, Strategie.FULL, StrategieComparaison.EGALE, new
                        Node<String, Integer>());
                ArbreBPlus<String, Integer> finalArbreBPlus = arbreBPlus2;
                List<Pair<String, Integer>> list = FileUtils.getPairListForKeyName("all.txt");
                list.forEach(finalArbreBPlus::insert);
                do {
                    System.out.println("1. Chercher un element.");
                    System.out.println("2. Afficher le chemain vers un element.");
                    System.out.println("3. Supprimer un element");
                    System.out.println("4. Afficher l'arbre.");
                    System.out.print(">> ");
                    choix = Integer.parseInt(scanner.nextLine());
                    if (choix == 1) {
                        System.out.print("Donner la cle : ");
                        String element = scanner.nextLine();
                        Pair<String, Integer> p = finalArbreBPlus.rechercheElement(finalArbreBPlus.getRacine(), new Pair<>(element, null));
                        if (p != null)
                            System.out.println("Valeur est " + p.getValeur());
                        else
                            System.out.println("element n'existe pas ");
                    } else if (choix == 2) {
                        System.out.print("Donner la cle : ");
                        String element = scanner.nextLine();
                        finalArbreBPlus.printPathTo(new Pair<>(element, null));
                    } else if (choix == 3) {
                        System.out.print("Donner la cle : ");
                        String element = scanner.nextLine();
                        finalArbreBPlus.suppressionNaif(list, new Pair<>(element, null));
                    } else if (choix == 4) {
                        finalArbreBPlus.print();
                    } else {
                        System.err.println("Choix Incorrect");
                    }

                } while (true);

            } else {
                System.err.println("Erreur entrer 1 ou 2");
            }
        } while (true);


    }
}
