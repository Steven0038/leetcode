package hackerank;

// Java program for the above approach
import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/minimum-flips-required-to-form-given-binary-string-where-every-flip-changes-all-bits-to-its-right-as-well/
 *
 * Minimum flips required to form given binary string where every flip changes all bits to its right as well
 *
 * Given a string S, the task is to find minimum flips required to convert an initial binary string consisting of only zeroes to S where every flip of a character flips all succeeding characters as well.
 */
public class MinFlips {
    // Function to return the count of
    // minimum flips required
    public static int minFlips(String target)
    {

        char curr = '1';
        int count = 0;
        for (int i = 0; i < target.length(); i++) {

            // If curr occurs in the final string
            if (target.charAt(i) == curr) {

                count++;

                // Switch curr to '0' if '1'
                // or vice-versa
                curr = (char)(48 + (curr + 1) % 2);
            }
        }

        return count;
    }

    // Driver Code
    public static void main(String args[])
    {

        String S = "011000";
        System.out.println(minFlips(S));
    }
}

