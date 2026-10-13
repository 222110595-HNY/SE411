package lab02_ex02;

import lab02_ex02.util.NumberBox;
import java.util.List;
import java.util.Arrays;

public class MainApp {

    public static void main(String[] args) {

        NumberBox<Double> nb1 = new NumberBox<>(45.2);
        NumberBox<Integer> nb2 = new NumberBox<>(100);
        NumberBox<Double> nb3 = new NumberBox<>(65.7);

        System.out.printf("%s%n", nb1.add(nb2));

        List<NumberBox<?>> lst = Arrays.asList(nb1, nb3);

        System.out.printf("%s%n", NumberBox.sum(lst));
    }
}
