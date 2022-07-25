package hackerank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

public class PlusMinus {

    public static void main(String[] args) {
        List<Integer> testArr = Arrays.asList(-4, 3, -9, 0, 4, 1);
        plusMinus(testArr);
    }

    public static void plusMinus(List<Integer> arr) {
//        BigDecimal totalCount = new BigDecimal(arr.size());
//        BigDecimal posCount = new BigDecimal("0.0");
//        BigDecimal negCount = new BigDecimal("0.0");
//        BigDecimal zeroCount = new BigDecimal("0.0");
//        BigDecimal one = new BigDecimal("1.0");
//
//        for(int i : arr){
//            if(i == 0) {
//                zeroCount = zeroCount.add(one);
//            } else if (i > 0) {
//                posCount = posCount.add(one);
//            } else {
//                negCount = negCount.add(one);
//            }
//        }
//
//        System.out.println(posCount.divide(totalCount,5, RoundingMode.HALF_UP));
//        System.out.println(negCount.divide(totalCount,5, RoundingMode.HALF_UP));
//        System.out.println(zeroCount.divide(totalCount,5, RoundingMode.HALF_UP));

        double totalCount = arr.size();
        double posCount = 0.0;
        double negCount = 0.0;
        double zeroCount = 0.0;

        for(int i : arr){
            if(i == 0) {
                zeroCount++;
            } else if (i > 0) {
                posCount++;
            } else {
                negCount++;
            }
        }

        System.out.println(posCount / totalCount);
        System.out.println(negCount / totalCount);
        System.out.println(zeroCount / totalCount);

    }
}

