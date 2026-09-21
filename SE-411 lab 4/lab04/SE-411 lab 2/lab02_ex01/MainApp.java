package lab02_ex01;

import lab02_ex01.util.PrintableList;

public class MainApp {

    public static void main(String[] args) {

        String[] names = { "Ada", "Alan", "Grace", "Linus" };

        PrintableList<String> list = new PrintableList<>(names);

        list.print();
    }
}
