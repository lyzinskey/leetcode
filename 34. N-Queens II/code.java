//Follow up for N-Queens problem.

//Now, instead outputting board configurations, return the total number of distinct solutions.

//Example
//For n=4, there are 2 distinct solutions.



public class Solution {
    /**
     * @param n: The number of queens.
     * @return: The total number of distinct solutions.
     */
    private int results = 0;
    
    public int totalNQueens(int n) {
        if (n <= 0) {
            return results;
        }
        
        search(new ArrayList<Integer>(), n);
        return results;        
    }
    
    /*
     * results store all of the chessboards
     * cols store the column indices for each row
     */
    private void search(List<Integer> cols, int n) {
        if (cols.size() == n) {
            results++;
            return;
        }
        
        for (int colIndex = 0; colIndex < n; colIndex++) {
            if (!isValid(cols, colIndex)) {
                continue;
            }
            cols.add(colIndex);
            search(cols, n);
            cols.remove(cols.size() - 1);
        }
    }
    
    private boolean isValid(List<Integer> cols, int column) {
        int row = cols.size();
        
        for (int rowIndex = 0; rowIndex < cols.size(); rowIndex++) {
            //排除两个Queen在同一列的情况
            if (cols.get(rowIndex) == column) {
                return false;
            }
            
            //排除两个Queen处于右上左下的情况
            if (rowIndex + cols.get(rowIndex) == row + column) {
                return false;
            }
            
            //排除两个Queen处于左上右下的情况
            if (rowIndex - cols.get(rowIndex) == row - column) {
                return false;
            }
        }
        
        return true;
    }    
}

