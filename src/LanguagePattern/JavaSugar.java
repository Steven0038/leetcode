package LanguagePattern;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JavaSugar {
    private static class Tea {
        private final List<Sugar> sugars;
//        public Tea(List<Sugar> sugars) {
//            this.sugars = sugars;
//        }

        public Tea(Sugar... sugars) { // varargs, variable-length arguments
            this.sugars = Arrays.asList(sugars);
        }

        public String whenCollectorsJoining() { // streaming of list obj. toString joining with prefix
            return sugars.stream()
                    .map(sugar -> sugar.brand)
                    .collect(Collectors.joining("-", "{", "}"));
        }

        @Override
        public String toString() {
            String sugarStr = whenCollectorsJoining();
            return "Tea: [sugars] " + sugars.size() + " cups of " + sugarStr;
        }
    }

    private static class Sugar {
        private final String brand;

        public Sugar() {
            this.brand = "NoBrand";
        }

        public Sugar(String brand) {
            this.brand = brand;
        }

        @Override
        public String toString() {
            return brand;
        }
    }

    public static void main(String[] args) {
        JavaSugar js = new JavaSugar();
        var tea = js.makeCupOfTea(); // var
        tea.forEach(System.out::println); // sugar of lambda calling function print
    }

    private List<Tea> makeCupOfTea() {
        Sugar sugar = new Sugar();
        List<Sugar> sugarList = List.of(new Sugar(), new Sugar("Lipton"));
        Sugar[] sugars = new Sugar[]{new Sugar(), new Sugar("Lipton"), new Sugar("TWINING")};

        Tea teaA = new Tea(sugar);
        Tea teaB = new Tea(sugarList.toArray(Sugar[]::new)); // list to array
        Tea teaC = new Tea(sugars);

        return List.of(teaA, teaB, teaC); // new list
    }

}




