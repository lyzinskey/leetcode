//Given a non-empty list of words, return the k most frequent elements.

//Your answer should be sorted by frequency from highest to lowest. 
//If two words have the same frequency, then the word with the lower alphabetical order comes first.

//Example 1:
//Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
//Output: ["i", "love"]
//Explanation: "i" and "love" are the two most frequent words.
//    Note that "i" comes before "love" due to a lower alphabetical order.

//Example 2:
//Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
//Output: ["the", "is", "sunny", "day"]
//Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
//    with the number of occurrence being 4, 3, 2 and 1 respectively.

//Note:
//You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
//Input words contain only lowercase letters.

//Follow up:
//Try to solve it in O(n log k) time and O(n) extra space.



class Solution {
    public List<String> topKFrequent(String[] combo, int k) {
        List<String> result = new ArrayList<>();
        if (k == 0) {
            return result;
        }
        
        Map<String, Integer> hashmap = new HashMap<>();

        for (String string : combo) {
            if (hashmap.containsKey(string)) {
                hashmap.put(string, hashmap.get(string) + 1);
            }
            else {
                hashmap.put(string, 1);
            }
        }

        PriorityQueue<Map.Entry<String, Integer>> minheap
                = new PriorityQueue<>(k,
                new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare (Map.Entry<String, Integer> entry1,
                                        Map.Entry<String, Integer> entry2) {
                        if (entry1.getValue() == entry2.getValue()) {
                            return -entry1.getKey().compareTo(entry2.getKey());
                        }

                        return entry1.getValue() < entry2.getValue() ? -1 : 1;
                    }
                });

        for (Map.Entry<String, Integer> entry : hashmap.entrySet()) {
            minheap.offer(entry);
            if (minheap.size() > k) {
                minheap.poll();
            }
        }

        getTopK(result, minheap);

        return result;
    }

    private void getTopK(List<String> result, PriorityQueue<Map.Entry<String, Integer>> minheap) {
        while (!minheap.isEmpty()) {
            result.add(minheap.poll().getKey());
        }
        Collections.reverse(result);
    } 
}


