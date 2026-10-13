package lab02_ex03;

import lab02_ex03.util.PipeLine;
import lab02_ex03.util.Transformer;

public class MainApp {

    static class Trim implements Transformer<String, String> {
        public String transform(String input) {
            return input.trim();
        }
    }

    static class Length implements Transformer<String, Integer> {
        public Integer transform(String input) {
            return input.length();
        }
    }

    static class Doubler implements Transformer<Integer, Integer> {
        public Integer transform(Integer input) {
            return input * 2;
        }
    }

    public static void main(String[] args) {

        PipeLine<String, Integer> pipeline = new PipeLine<String, String>()
                .addTransformer(new Trim())
                .addTransformer(new Length())
                .addTransformer(new Doubler());

        System.out.printf("%s%n", pipeline.execute("   hello   "));
        System.out.printf("%s%n", pipeline.execute("generics"));
    }
}
