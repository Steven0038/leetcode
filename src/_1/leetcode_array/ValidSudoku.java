package _1.leetcode_array;

import java.util.HashSet;
import java.util.Set;

/**
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be
 * validated according to the following rules:
 * <p>
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * <p>
 * A partially filled sudoku which is valid.
 * <p>
 * The Sudoku board could be partially filled, where empty cells are filled with
 * the character '.'.
 * <p></p>
 * Example 1:
 * <p>
 * Input:
 * [
 * ["5","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]
 * ]
 * <p>
 * Output: true
 * <p></p>
 * Example 2:
 * <p>
 * Input:
 * [
 * ["8","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]
 * ]
 * <p>
 * Output: false
 * <p></p>
 * Explanation: Same as Example 1, except with the 5 in the top
 * left corner being modified to 8. Since there are two 8's in the top left 3x3
 * sub-box, it is invalid.
 * <p>
 * Note:
 * A Sudoku board (partially filled) could be valid but is not necessarily
 * solvable. Only the filled cells need to be validated according to the
 * mentioned rules. The given board contain only digits 1-9 and the character
 * '.'. The given board size is always 9x9.
 *
 * @author steven
 */
public class ValidSudoku {

    public static void main(String[] args) {
        ValidSudoku vs = new ValidSudoku();
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        System.out.printf("is valid Sudoku: %s", vs.isValidSudoku(board));
    }

    public boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char number = board[i][j];
                if (number != '.') {
                    if (!seen.add(number + " in row " + i) ||
                            !seen.add(number + " in column " + j) ||
                            !seen.add(number + " in block " + i / 3 + "-" + j / 3)) {
                        return false;
                    }
                }
            }
        }

//	    Set seen = new HashSet();
//	    for (int i=0; i<9; ++i) {
//	        for (int j=0; j<9; ++j) {
//	            char number = board[i][j];
//	            if (number != '.')
//	                if (!seen.add(number + " in row " + i) ||
//	                    !seen.add(number + " in column " + j) ||
//	                    !seen.add(number + " in block " + i/3 + "-" + j/3))
//	                    return false;
//	        }
//	    }
        return true;
    }

//	private boolean isValidSudoku(char[][] sudoku) {
//		Set<String> s = new HashSet<String>();
//		List<String> l = new LinkedList<String>();
//		Map<String, Integer> m = new HashMap<String, Integer>();
//		// check rows are not replicate
//		for (char[] rows : sudoku) {
//			for (int i = 0; i < rows.length; i++) {
//				if (m.containsKey(rows[i]) && rows[i] != '.') {
//					return false;
//				} else {
//					m.put(String.valueOf(rows[i]), m.get(rows[i]) + 1);
//				}
//			}
//		}
//		
//		Set<String> set = new HashSet<String>();
//		// check columns are not replicate
//		for (int i = 0; i < i; i ++) {
//			for (int j = 0; j < 9; j++) {
//				if (!set.add(String.valueOf(sudoku[j][i])) && sudoku[j][i] != '.') {
//					return false;
//				}
//			}
//		}
//		
//		// check grids is not replicate
//		
//		return false;
//	}


}
