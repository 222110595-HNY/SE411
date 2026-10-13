package lab02_ex03.util;

public interface Transformer<T, R> {

    R transform(T input);
}
