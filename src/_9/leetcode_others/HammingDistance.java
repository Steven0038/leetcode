package _9.leetcode_others;

public class HammingDistance {

    public static void main(String[] args) {
        HammingDistance hd = new HammingDistance();
        System.out.println(hd.hammingDistance(1,4));
    }

    public int hammingDistance(int x, int y) {
        int num = x ^ y;
        int count = 0;
        while(num != 0){
            if((num & 1) == 1){
                count++;
            }
            // num >>>= 1;
            num =  num >> 1; // 位元右移
        }

        return count;
    }

}
