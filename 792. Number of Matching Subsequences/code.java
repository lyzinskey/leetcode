//Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.

//Example :
//Input: 
//S = "abcde"
//words = ["a", "bb", "acd", "ace"]
//Output: 3
//Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".

//Note:
//
//All words in words and S will only consists of lowercase letters.
//The length of S will be in the range of [1, 50000].
//The length of words will be in the range of [1, 5000].
//The length of words[i] will be in the range of [1, 50].




class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        List<Integer>[] occurrence = new List[26];
        for (int i = 0; i < 26; i++) {
            occurrence[i] = new ArrayList<>();
        }
        for (int i = 0; i < S.length(); i++) {
            occurrence[S.charAt(i) - 'a'].add(i);
        }

        int count = 0;
        for (String word : words) {
            if (isMatched(word, occurrence)) {
                count++;
            }
        }
        return count;
    }

    private boolean isMatched(String word, List<Integer>[] occurrence) {
        int lastIndex = -1;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            List<Integer> list = occurrence[ch - 'a'];
            int index = smallestElementLargerThanTarget(list, lastIndex);
            if (index == -1) {
                return false;
            }
            lastIndex = list.get(index);
        }
        return true;
    }

    private int smallestElementLargerThanTarget(List<Integer> list, int target) {
        if (list == null || list.size() == 0) {
            return -1;
        }

        int left = 0;
        int right = list.size() - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        if (list.get(left) > target) {
            return left;
        }
        if (list.get(right) > target) {
            return right;
        }
        return -1;
    }
}




