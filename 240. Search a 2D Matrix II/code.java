//Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

//Integers in each row are sorted in ascending from left to right.
//Integers in each column are sorted in ascending from top to bottom.
//Consider the following matrix:
//        [1,   4,  7, 11, 15],
//        [2,   5,  8, 12, 19],
//        [3,   6,  9, 16, 22],
//        [10, 13, 14, 17, 24],
//        [18, 21, 23, 26, 30]

//Example 1:
//
//Input: matrix, target = 5
//Output: true
//
//Example 2:
//
//Input: matrix, target = 20
//Output: false


//    O(m+n) time and O(1) extra space
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0){
            return false;
        }
        if (matrix[0] == null || matrix[0].length == 0){
            return false;
        }

        // start searching from the left bottom
        int row = matrix.length;
        int column = matrix[0].length;
        int x = row - 1;
        int y = 0;

//        Example:
//        [1, 3, 5, 7],
//        [2, 4, 7, 8],
//        [3, 5, 9, 10]
//        start searching 5 from left bottom

//        After 1st searching:
//        [3, 5, 7],
//        [4, 7, 8],
//        [5, 9, 10]
//        counter = 0

//        After 2nd searching:
//        [5, 7],
//        [7, 8],
//        counter = 1

//        After 3rd searching:
//        [5, 7],
//        counter = 1

//        After 4th searching:
//        [7],
//        counter = 2

        while (x >= 0 && y <= column - 1){
            if (matrix[x][y] < target){
                y++;
            }
            else if (matrix[x][y] > target){
                x--;
            }
            else {
                return true;
            }
        }
        return false;
    }
