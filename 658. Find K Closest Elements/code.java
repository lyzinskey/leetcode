//Given a sorted array, two integers k and x, find the k closest elements to x in the array.
//The result should also be sorted in ascending order.
//If there is a tie, the smaller elements are always preferred.

//Example 1:
//Input: [1,2,3,4,5], k=4, x=3
//Output: [1,2,3,4]

//Example 2:
//Input: [1,2,3,4,5], k=4, x=-1
//Output: [1,2,3,4]

//Note:
//The value k is positive and will always be smaller than the length of the sorted array.
//Length of the given array is positive and will not exceed 10^4
//Absolute value of elements in the array and x will not exceed 10^4


    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = findLowerClosest(arr, x);
        int right = left + 1;

        int[] results = new int[k];
        for (int i = 0; i < k; i++) {
            if (isLeftCloser(arr, x, left, right)) {
                results[i] = arr[left];
                left--;
            } else {
                results[i] = arr[right];
                right++;
            }
        }
        Arrays.sort(results);
        List<Integer> output = new ArrayList<Integer>();
        for (int i = 0; i < results.length; i++) {
            output.add(results[i]);
        }
        return output;
    }

    private boolean isLeftCloser(int[] A, int target, int left, int right) {
        if (left < 0) {
            return false;
        }

        if (right >= A.length) {
            return true;
        }

        if (target - A[left] != A[right] - target) {
            return target - A[left] < A[right] - target;
        }

        return true;
    }

    private int findLowerClosest(int[] A, int target) {
        // find the biggest element smaller than target
        int start = 0, end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (A[end] < target) {
            return end;
        }
        if (A[start] < target) {
            return start;
        }

        return -1;
    }
    
    
