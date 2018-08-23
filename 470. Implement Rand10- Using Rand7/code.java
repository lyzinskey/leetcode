//Given a function rand7 which generates a uniform random integer in the range 1 to 7, 
//write a function rand10 which generates a uniform random integer in the range 1 to 10.

//Do NOT use system's Math.random().

//Example 1:
//
//Input: 1
//Output: [7]

//Example 2:
//
//Input: 2
//Output: [8,4]

//Example 3:
//
//Input: 3
//Output: [8,1,10] 

//Note:
//
//rand7 is predefined.
//Each testcase has one argument: n, the number of times that rand10 is called.

//Follow up:
//
//What is the expected value for the number of calls to rand7() function?
//Could you minimize the number of calls to rand7()?




/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */
class Solution extends SolBase {
    public int rand10() {
        while (true) {
            //题目提供的API: rand7()生成的是1～7，因此在计算7进制时要-1            
            int random = 7 * (rand7() - 1) + rand7() - 1;
            if (random < 40) {
                // 返回值random % 10是0～9，因此要+1以满足1～10
                return random % 10 + 1;
            }
        }
    }
}



