package dataStructure_and_algorithm;

public class BinaryConverter {
    public static void main(String[] args) {
        int decimal = 42; // 十进制整数: 32+8+2 = 2^5+2^3+2 => 101010
        String binary = decimalToBinary(decimal);
        System.out.printf("decimal %s to binary %s", decimal, binary);
        System.out.println();

        binary = "101010"; // 二进制整数表示形式
        decimal = binaryToDecimal(binary);
        System.out.printf("binary %s to decimal %s", binary, decimal);
    }

    public static String decimalToBinary(int decimal) {
        StringBuilder binary = new StringBuilder();

        if (decimal == 0) {
            binary.append("0"); // 处理特殊情况，如果输入为 0，则直接返回 "0"
        }

        /**
         * 方法：除2取余法，即每次将整数部分除以2，余数为该位权上的数，而商继续除以2，
         * 余数又为上一个位权上的数，这个步骤一直持续下去，直到商为0为止，最后读数时候，
         * 从最后一个余数读起，一直到最前面的一个余数。
         * 42 / 2 = 21 .........0
         * 21 / 2 = 10..........1
         * 10 / 2 = 5 ..........0
         * 5 / 2 = 2 ...........1
         * 2 / 2 = 1............0
         * 1 / 2 = 0............1
         */
        while (decimal > 0) {
            int bit = decimal % 2;
            binary.insert(0, bit); // 将余数插入到字符串的最前方
            decimal /= 2;
        }

        return binary.toString();
    }

    /**
     * 该程序接受一个二进制整数的字符串表示形式作为输入，并将其转换为常规整数。
     * 在循环中，我们从字符串的最高有效位（MSB）开始，逐位将二进制位转换为整数。
     * 我们将每个位乘以相应的权重（2 的幂），并将其添加到最终的十进制结果中。
     */
    public static int binaryToDecimal(String binary) {
        int decimal = 0;
        int power = 0;

        for (int i = binary.length() - 1; i >= 0; i--) {
            int bit = binary.charAt(i) - '0';
            decimal += bit * Math.pow(2, power); // 從尾數 2 的 0 次方開始遞增加上去
            power++;
        }

        return decimal;
    }
}
