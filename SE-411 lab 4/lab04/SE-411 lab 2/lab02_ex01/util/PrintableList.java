package lab02_ex01.util;

import java.util.List;
import java.util.Arrays;

public class PrintableList<T> {

    private List<T> list;

    public PrintableList(T[] data) {
        list = Arrays.asList(data);
    }

    public void print() {
        for (T item : list) {
            System.out.println(item);
        }
    }
}
