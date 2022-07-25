package _9.leetcode_others;

public class ReverseBits {

    public static void main(String[] args) {
        ReverseBits hd = new ReverseBits();
        System.out.println(hd.reverseBits(43261596));
    }

    public int reverseBits(int n) {

        int res = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) {
                res = (res << 1) + 1;
            } else {
                res = res << 1;
            }

            n = n >> 1;
        }

        return res;
    }

}
