//Given a string containing only digits, restore it by returning all possible valid IP address combinations.

//Example:
//
//Input: "25525511135"
//Output: ["255.255.11.135", "255.255.111.35"]




class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12) {
            return result;
        }

        dfs(result, s, 0, 0, new StringBuilder());
        return result;
    }

    private void dfs(List<String> result, String s, int start, int segment, StringBuilder sb) {
        if (start >= s.length() || segment > 3) {
            if (start >= s.length() && segment == 4) {
                sb.deleteCharAt(sb.length() - 1);
                result.add(sb.toString());
            }
            return;
        }

        for (int i = 1; i <= 3 && start + i <= s.length(); i++) {            
            String str = s.substring(start, start + i);
            if (str.charAt(0) == '0' && str.length() > 1) {
                continue;
            }
            int num = Integer.parseInt(str);
            if (num <= 255) {
                sb.append(num);
                sb.append('.');
                dfs(result, s, start + i, segment + 1, sb);
                sb.deleteCharAt(sb.length() - 1);
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) != '.') {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
    }    
}



