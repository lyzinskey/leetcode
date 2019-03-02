//Given an array nums of n integers and an integer target, 
//are there elements a, b, c, and d in nums such that a + b + c + d = target? 
//Find all unique quadruplets in the array which gives the sum of target.

//Note:
//
//The solution set must not contain duplicate quadruplets.

//Example:
//
//Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
//
//A solution set is:
//  [
//    [-1,  0, 0, 1],
//    [-2, -1, 1, 2],
//    [-2,  0, 0, 2]
//  ]



class Solution {
    public List<List<Integer>> fourSum(int[] num, int target) {
		List<List<Integer>> rst = new ArrayList<List<Integer>>();
		Arrays.sort(num);

		for (int i = 0; i < num.length - 3; i++) {
			if (i != 0 && num[i] == num[i - 1]) {
				continue;
			}

			for (int j = i + 1; j < num.length - 2; j++) {
				if (j != i + 1 && num[j] == num[j - 1]) {
                    continue;
                }					

				int left = j + 1;
				int right = num.length - 1;
				while (left < right) {
					int sum = num[i] + num[j] + num[left] + num[right];
					if (sum == target) {
					    rst.add(Arrays.asList(num[i], num[j], num[left], num[right]));
						left++;
						right--;
						while (left < right && num[left] == num[left - 1]) {
							left++;
						}
						while (left < right && num[right] == num[right + 1]) {
							right--;
						}
					} else if (sum > target) {
						right--;
					} else {
						left++;
					}
				}
			}
		}
		return rst;
	}
}





