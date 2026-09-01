package lab02_ex04;

import java.util.List;
import java.util.Arrays;

public class MainApp {

    public static void printList(List<?> list) {
        for (Object item : list) {
            System.out.println(item);
        }
    }

    public static double sumNumbers(List<? extends Number> list) {
        double total = 0;
        for (Number n : list) {
            total += n.doubleValue();
        }
        return total;
    }

    public static void main(String[] args) {

        List<String> words = Arrays.asList("alpha", "beta", "gamma");
        List<Integer> ints = Arrays.asList(1, 2, 3, 4);
        List<Double> doubles = Arrays.asList(1.5, 2.5, 3.0);

        printList(words);
        printList(ints);

        System.out.printf("%s%n", sumNumbers(ints));
        System.out.printf("%s%n", sumNumbers(doubles));
    }
}
