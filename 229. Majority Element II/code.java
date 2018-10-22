//Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

//Note: The algorithm should run in linear time and in O(1) space.

//Example 1:
//
//Input: [3,2,3]
//Output: [3]

//Example 2:
//
//Input: [1,1,1,3,3,2,2,2]
//Output: [1,2]




class Solution {
    public List<Integer> majorityElement(int[] array) {
        List<Integer> result = new ArrayList<>();

        if (array.length == 0) {
            return result;
        }

        int counter1 = 0;
        int counter2 = 0;
        int num1 = 0;
        int num2 = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == num1) {
                counter1++;
            } else if (array[i] == num2) {
                counter2++;
            } else if (counter1 == 0) {
                num1 = array[i];
                counter1++;
            } else if (counter2 == 0) {
                num2 = array[i];
                counter2++;
            } else {
                counter1--;
                counter2--;
            }
        }

        counter1 = 0;
        counter2 = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == num1) {
                counter1++;
            } else if (array[i] == num2) {
                counter2++;
            }
        }

        if (counter1 > array.length/3) {
            result.add(num1);
        }
        if (counter2 > array.length/3) {
            result.add(num2);
        }

        return result;
    }   
}



