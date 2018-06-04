//Find the kth largest element in an unsorted array. 
//Note that it is the kth largest element in the sorted order, not the kth distinct element.

//Example 1:
//
//Input: [3,2,1,5,6,4] and k = 2
//Output: 5

//Example 2:
//
//Input: [3,2,3,1,2,4,5,5,6] and k = 4
//Output: 4

//Note: 
//You may assume k is always valid, 1 ≤ k ≤ array's length.


    public int findKthLargest(int[] nums, int k) {
        int low = 0, high = nums.length -1;
        while(low <= high){  
            int pivot = nums[high];
            int index = low-1;
            //把所有比pivot大的数字全部集中在左侧
            for(int i = low; i < high; i++){
                if(nums[i] > nums[high]){
                    swap(nums, i, ++index);
                }
            }
            
            //让index左侧全是大于pivot的数，右侧全是小于pivot的数
            swap(nums, ++index, high);
            if(index == k - 1){
                return nums[index];
            }
            if(index < k -1){
                low = index + 1;
            }else{
                high = index - 1;
            }
        }
        return -1;
    }
    
    private void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }   
    
    
    
