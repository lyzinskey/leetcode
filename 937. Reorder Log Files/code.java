//You have an array of logs.  Each log is a space delimited string of words.

//For each log, the first word in each log is an alphanumeric identifier.  Then, either:

//Each word after the identifier will consist only of lowercase letters, or;
//Each word after the identifier will consist only of digits.
//We will call these two varieties of logs letter-logs and digit-logs.  
//It is guaranteed that each log has at least one word after its identifier.

//Reorder the logs so that all of the letter-logs come before any digit-log.  
//The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  
//The digit-logs should be put in their original order.

//Return the final order of the logs.

//Example 1:

//Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
//Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]

//Note:
//0 <= logs.length <= 100
//3 <= logs[i].length <= 100
//logs[i] is guaranteed to have an identifier, and a word after the identifier.



class Solution {
    public static String[] reorderLogFiles(String[] logs) {
        if (logs.length < 2) {
            return logs;
        }

        Comparator<String> myComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int index1 = o1.indexOf(" ");
                int index2 = o2.indexOf(" ");
                char ch1 = o1.charAt(index1 + 1);
                char ch2 = o2.charAt(index2 + 1);

                if (Character.isDigit(ch1) && !Character.isDigit(ch2)) {
                    return 1;
                } else if (!Character.isDigit(ch1) && Character.isDigit(ch2)) {
                    return -1;
                } else if (Character.isDigit(ch1) && Character.isDigit(ch2)) {
                    return 0;
                } else {
                    int compare = o1.substring(index1 + 1).compareTo(o2.substring(index2 + 1));
                    if (compare == 0) {
                        return o1.compareTo(o2);
                    }
                    return compare;
                }
            }
        };
        Arrays.sort(logs, myComparator);
        return logs;
    }
}


