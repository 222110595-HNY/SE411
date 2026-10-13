package lab02_ex02.util;

import java.util.List;

public class NumberBox<T extends Number> {

    private T element;

    private NumberBox() {
    }

    public NumberBox(T data) {
        element = data;
    }

    public double add(NumberBox<?> other) {
        return element.doubleValue() + other.getElement().doubleValue();
    }

    public static double sum(List<NumberBox<?>> boxes) {
        double total = 0;
        for (NumberBox<?> box : boxes) {
            total += box.getElement().doubleValue();
        }
        return total;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }
}
