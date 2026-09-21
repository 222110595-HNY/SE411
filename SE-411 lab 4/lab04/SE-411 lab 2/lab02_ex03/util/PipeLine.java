package lab02_ex03.util;

import java.util.List;
import java.util.ArrayList;

public class PipeLine<T, R> {

    private List<Transformer<?, ?>> transformers;

    public PipeLine() {
        transformers = new ArrayList<>();
    }

    private PipeLine(List<Transformer<?, ?>> previous, Transformer<?, ?> next) {
        transformers = new ArrayList<>(previous);
        transformers.add(next);
    }

    public <V> PipeLine<T, V> addTransformer(Transformer<R, V> transformer) {
        return new PipeLine<>(transformers, transformer);
    }

    @SuppressWarnings("unchecked")
    public R execute(T input) {
        Object value = input;
        for (Transformer<?, ?> transformer : transformers) {
            value = ((Transformer<Object, Object>) transformer).transform(value);
        }
        return (R) value;
    }
}
