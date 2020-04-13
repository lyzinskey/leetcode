//Given a m * n matrix seats  that represent seats distributions in a classroom. 
//If a seat is broken, it is denoted by '#' character otherwise it is denoted by a '.' character.

//Students can see the answers of those sitting next to the left, right, upper left and upper right, 
//but he cannot see the answers of the student sitting directly in front or behind him. 
//Return the maximum number of students that can take the exam together without any cheating being possible..

//Students must be placed in seats in good condition.

//Example 1:
//
//Input: seats = [["#",".","#","#",".","#"],
//                [".","#","#","#","#","."],
//                ["#",".","#","#",".","#"]]
//Output: 4
//Explanation: Teacher can place 4 students in available seats so they don't cheat on the exam. 

//Example 2:
//
//Input: seats = [[".","#"],
//                ["#","#"],
//                ["#","."],
//                ["#","#"],
//                [".","#"]]
//Output: 3
//Explanation: Place all students in available seats. 

//Example 3:
//
//Input: seats = [["#",".",".",".","#"],
//                [".","#",".","#","."],
//                [".",".","#",".","."],
//                [".","#",".","#","."],
//                ["#",".",".",".","#"]]
//Output: 10
//Explanation: Place students in available seats in column 1, 3 and 5.

//Constraints:
//
//seats contains only characters '.' and'#'.
//m == seats.length
//n == seats[i].length
//1 <= m <= 8
//1 <= n <= 8





class Solution {
    // Time: O(m * 2^2n)
    // Space: O(m * 2^2n)
    public int maxStudents(char[][] seats) {
        int row = seats.length;
        int col = seats[0].length;
        int[][] memo = new int[row][1 << col];
        for (int i = 0; i < row; i++) {
            Arrays.fill(memo[i], -1);
        }

        return getMax(seats, 0, 0, memo);
    }

    private int getMax(char[][] seats, int curRow, int prevRowMask, int[][] memo) {
        if (curRow == seats.length) {
            return 0;
        }
        if (memo[curRow][prevRowMask] != -1) {
            return memo[curRow][prevRowMask];
        }

        List<Integer> masks = new ArrayList<>(); // reset the masks list for backtrack
        backtrack(seats[curRow], 0, prevRowMask, 0, masks); // backtrack results store in masks list

        int res = 0;
        for (int mask : masks) {
            res = Math.max(res, Integer.bitCount(mask) + getMax(seats, curRow + 1, mask, memo));
        }
        memo[curRow][prevRowMask] = res;
        return res;
    }

    // this returns all combination of legal seat assignment for a given row based on prevous row's mask
    private void backtrack(char[] seats, int cur, int prevRowMask, int curRowMask, List<Integer> masks) {
        if (cur == seats.length) {
            masks.add(curRowMask);
            return;
        }

        // cur seat is not taken
        backtrack(seats, cur + 1, prevRowMask, curRowMask, masks);

        // cur seat is taken, check if left, upper left and upper right positions are empty
        if (seats[cur] != '#'
                && (cur == 0 || (((curRowMask & (1 << (cur - 1))) == 0) && (prevRowMask & (1 << (cur - 1))) == 0))
                && (cur == seats.length - 1 || ((prevRowMask & (1 << (cur + 1))) == 0))) {
            curRowMask |= (1 << (cur));
            backtrack(seats, cur + 1, prevRowMask, curRowMask, masks);
            curRowMask ^= (1 << (cur));
        }
    }
}



