//On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.
//A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
//The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

//Given a puzzle board, return the least number of moves required so that the state of the board is solved. 
//If it is impossible for the state of the board to be solved, return -1.

//Examples:
//
//Input: board = [[1,2,3],[4,0,5]]
//Output: 1
//Explanation: Swap the 0 and the 5 in one move.

//Input: board = [[1,2,3],[5,4,0]]
//Output: -1
//Explanation: No number of moves will make the board solved.

//Input: board = [[4,1,2],[5,0,3]]
//Output: 5
//Explanation: 5 is the smallest number of moves that solves the board.

//An example path:
//After move 0: [[4,1,2],[5,0,3]]
//After move 1: [[4,1,2],[0,5,3]]
//After move 2: [[0,1,2],[4,5,3]]
//After move 3: [[1,0,2],[4,5,3]]
//After move 4: [[1,2,0],[4,5,3]]
//After move 5: [[1,2,3],[4,5,0]]
//Input: board = [[3,2,4],[1,5,0]]
//Output: 14

//Note:
//board will be a 2 x 3 array as described above.
//board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].




class Solution {
    public int slidingPuzzle(int[][] board) {
        int kRows = 2;
        int kCols = 3;
        String goal = "";
        String start = "";

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                start += board[i][j];
                goal += (i * kCols + j + 1) % (kRows * kCols);
            }
        }
        if (start.equals(goal)) {
            return 0;
        }

        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        Set<String> visited = new HashSet<>();
        int step = 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String s = queue.poll();
                int p = s.indexOf('0');
                int x = p % kCols;
                int y = p / kCols;
                for (int j = 0; j < 4; j++) {
                    int tx = x + dirs[j][0];
                    int ty = y + dirs[j][1];
                    if (tx < 0 || ty < 0 || tx >= kCols || ty >= kRows) {
                        continue;
                    }
                    int pp = ty * kCols + tx;
                    String t = swap(s, p, pp);
                    if (visited.contains(t)) {
                        continue;
                    }
                    if (t.equals(goal)) {
                        return step;
                    }
                    visited.add(t);
                    queue.offer(t);
                }
            }
        }
        return -1;
    }

    private String swap(String s, int i, int j) {
        char[] array = s.toCharArray();
        char ch = array[i];
        array[i] = array[j];
        array[j] = ch;
        return new String(array);
    }
}



