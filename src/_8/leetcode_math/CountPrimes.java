package _8.leetcode_math;

public class CountPrimes {
    public static void main(String[] args) {
        CountPrimes cp = new CountPrimes();
        int n = 10;
        System.out.println(cp.countPrimes(n));
    }

    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n]; // 題目要求不包括 n => 0 ~ n-1
        // 0 跟 1 不是質數
        for (int i = 2; i < n; i++) { // 先全部預設成 true
            isPrime[i] = true;
        }

        /** 直接當公式記了: i^2 + i*n 為非質數 **/
        for (int i = 2; i * i < n; i++) { //n=10, i = 2,(4),3(9),4(沒必要),5(沒必要-質數),6(沒必要-2*3=4+2),
            //7(沒必要-質數), 9(沒必要-3*3)
            if (!isPrime[i]) continue; // 不加這行會過, 但是超慢

            for (int j = i * i; j < n; j += i) { // 整數 i 自乘的數必為非質數，且自乘數 i^2 + i*n也仍為非質數
                // i 的平方倍不是質數, i^i + i 也不會是質數
                // ex: 3*3=9, 3*3+3 = 12 = 3^4
                isPrime[j] = false;
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) count++;
        }

        return count;
    }
}
