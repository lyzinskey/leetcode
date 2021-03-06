//Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
//
//Note: For the purpose of this problem, we define empty string as valid palindrome.
//
//Example 1:
//
//Input: "A man, a plan, a canal: Panama"
//Output: true
//Example 2:
//
//Input: "race a car"
//Output: false



public boolean isPalindrome(String s) {
        if (s.isEmpty()){
            return true;
        }
        int headCount = 0;
        int tailCount = s.length() - 1;
        char head;
        char tail;

        while (headCount <= tailCount){
            head = s.charAt(headCount);
            tail = s.charAt(tailCount);

            //java.lang.Character.isLetterOrDigit(char ch)
            //This method returns true if the character is a letter or digit,
            // false otherwise.
            if (!Character.isLetterOrDigit(head)){
                headCount++;
            }
            else if (!Character.isLetterOrDigit(tail)){
                tailCount--;
            }
            else {
                if (Character.toLowerCase(head) != Character.toLowerCase(tail)){
                    return false;
                }
                //don't forget to update headCount & tailCount,
                // or endless while loop would occur
                headCount++;
                tailCount--;
            }
        }
        return true;
    }
