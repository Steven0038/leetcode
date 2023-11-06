package ikmTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class IkmTest {
    public static void main(String[] args) {
        IkmTest ikmTest = new IkmTest();
        ikmTest.lambdaComparator();
        ikmTest.nullCatchCompareTest();
    }

    /**
     * output:
     * true
     * class java.lang.NullPointerException
     */
    private void nullCatchCompareTest() {
        Integer x = 3, y = null;
        try {
            System.out.println(
                    Integer.compareUnsigned(x, 3) == 0 ||
                    Integer.compareUnsigned(y, 0) == 0);
        } catch (Exception ex) {
            System.out.println(ex.getClass().toString());
        }
        try {
            System.out.println(y.compareTo(null) == 0 || true);
        } catch (Exception ex) {
            System.out.println(ex.getClass().toString());
        }
    }

    class Namer {
        final private String firstName, lastName;

        public Namer(String fn, String ln) {
            firstName = fn;
            lastName = ln;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }
    }

    /**
     * output:
     * Harry Homeowner
     * Harry Smith
     * Jane Doe
     * Joe Smith
     */
    private void lambdaComparator() {
        Consumer<Namer> printit = e -> System.out.println(e.getFirstName() + " " + e.getLastName());
        List<Namer> names = new ArrayList<>(Arrays.asList(
                new Namer("Harry", "Smith"),
                new Namer("Joe", "Smith"),
                new Namer("Jane", "Doe"),
                new Namer("Mary", "Jane"),
                new Namer("Harry", "Homeowner")));
        Comparator<Namer> groupBy = Comparator.comparing(e -> e.getFirstName()); // ASC by alphabet
        groupBy = groupBy.thenComparing(e -> e.getLastName());
        names.removeIf(e -> e.getFirstName().equals("Mary"));
        names.sort(groupBy);
        names.forEach(printit);
    }
}
