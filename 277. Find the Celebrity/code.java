//Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. 
//The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

//Now you want to find out who the celebrity is or verify that there is not one. 
//The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" 
//to get information of whether A knows B. 
//You need to find out the celebrity (or verify there is not one) 
//by asking as few questions as possible (in the asymptotic sense).

//You are given a helper function bool knows(a, b) which tells you whether A knows B. 
//Implement a function int findCelebrity(n). 
//There will be exactly one celebrity if he/she is in the party. 
//Return the celebrity's label if there is a celebrity in the party. 
//If there is no celebrity, return -1.

//Example 1:
//
//Input: graph = [
//  [1,1,0],
//  [0,1,0],
//  [1,1,1]
//]
//Output: 1
//Explanation: There are three persons labeled with 0, 1 and 2. graph[i][j] = 1 means person i knows person j, 
//otherwise graph[i][j] = 0 means person i does not know person j. 
//The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.

//Example 2:
//
//Input: graph = [
//  [1,0,1],
//  [1,1,0],
//  [0,1,1]
//]
//Output: -1
//Explanation: There is no celebrity.
 
//Note:
//
//The directed graph is represented as an adjacency matrix, 
//which is an n x n matrix where a[i][j] = 1 means person i knows person j while a[i][j] = 0 means the contrary.
//Remember that you won't have direct access to the adjacency matrix.




/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    // 每次call knows(a, b)我们都能确定a，b其中一人不是celebrity
    // 如果knows(a, b)返回true，说明a不是celebrity，因为celebrity不认识任何人
    // 如果knows(a, b)返回false，说明b不是celebrity，因为所有人都认识celebrity
    //
    // Time: O(n)
    // Space: O(1)
    public int findCelebrity(int n) {
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }
        if (isCelebrity(candidate, n)) {
            return candidate;
        }
        return -1;
    }
    
    private boolean isCelebrity(int i, int n) {
        for (int j = 0; j < n; j++) {
            if (i == j) {
                continue; // Don't ask if they know themselves.
            }
            if (knows(i, j) || !knows(j, i)) {
                return false;
            }
        }
        return true;
    }
}



