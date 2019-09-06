//In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  
//(A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)

//We may rotate the i-th domino, so that A[i] and B[i] swap values.
//Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.
//If it cannot be done, return -1.

//Example 1:
//https://assets.leetcode.com/uploads/2019/03/08/domino.png
//
//Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
//Output: 2
//Explanation: 
//The first figure represents the dominoes as given by A and B: before we do any rotations.
//If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.

//Example 2:
//
//Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
//Output: -1
//Explanation: 
//In this case, it is not possible to rotate the dominoes to make one row of values equal.

//Note:
//1 <= A[i], B[i] <= 6
//2 <= A.length == B.length <= 20000




class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        int n = A.length;        
        int rotations = check(A[0], B, A, n);
        // If one could make all elements in A or B equal to A[0]
        if (rotations != -1 || A[0] == B[0]) return rotations;
            // If one could make all elements in A or B equal to B[0]
        else return check(B[0], B, A, n);
    }
    
    public int check(int x, int[] A, int[] B, int n) {
        // how many rotations should be done
        // to have all elements in A equal to x
        // and to have all elements in B equal to x
        int rotations_a = 0, rotations_b = 0;
        for (int i = 0; i < n; i++) {
            // rotations coudn't be done
            if (A[i] != x && B[i] != x) return -1;
                // A[i] != x and B[i] == x
            else if (A[i] != x) rotations_a++;
                // A[i] == x and B[i] != x    
            else if (B[i] != x) rotations_b++;
        }
        // min number of rotations to have all
        // elements equal to x in A or B
        return Math.min(rotations_a, rotations_b);
    }
}



