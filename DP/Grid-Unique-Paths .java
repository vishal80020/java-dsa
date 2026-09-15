//Problem Link - https://leetcode.com/problems/unique-paths/

// Recursive solution
class Solution {

    private int solve(int i, int j, int m, int n) {
        if(i <0 || j<0) {
            return 0;
        }
        if(i==0 && j== 0) {
            return 1;
        }

        int left = solve(i,j-1,m,n);
        int up = solve(i-1,j,m,n);

        return left + up;

    }

    public int uniquePaths(int m, int n) {
        return solve(m-1,n-1,m,n);
    }
}

// Memoization
class Solution {

    private int solve(int i, int j, int m, int n,int[][]dp) {
        if(i <0 || j<0) {
            return 0;
        }
        if(i==0 && j== 0) {
            return 1;
        }

        if(dp[i][j] != -1) {
            return dp[i][j];
        }

        int left = solve(i,j-1,m,n,dp);
        int up = solve(i-1,j,m,n,dp);

        return dp[i][j]= left + up;

    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                dp[i][j] = -1;
            }
        }
        return solve(m-1,n-1,m,n,dp);
    }
}

// Tabulation
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                dp[i][j] = -1;
            }
        }
        //fill the base case
        dp[0][0]  = 1; 

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(i==0 && j==0) {
                    continue; // already handled in base case
                }
                int left = j>0 ? dp[i][j-1] : 0;
                int up = i>0 ? dp[i-1][j] : 0;
                dp[i][j] = left + up;
            }
        }
        //we will reach at the top of the recursion tree
        return dp[m-1][n-1];
    }
}

//Space Optimization
class Solution {
    public int uniquePaths(int m, int n) {
        int[] prev = new int[n]; // store the all column values for a row
        
        //fill the base case

        for(int i=0;i<m;i++) {
            int[] curr = new int[n]; // currently computing for this ith row so I need i-1 row that is stored in prev
            for(int j=0;j<n;j++) {
                if(i==0 && j==0) {
                    curr[j] = 1;
                } else {
                    int left = j>0 ? curr[j-1] : 0; // dp[i][j-1] // current row
                    int up = i>0 ? prev[j] : 0; // dp[i-1][j] // i-1 means prev row
                    curr[j] = left + up;    
                }
            }
            prev = curr;
        }
        //we will reach at the top of the recursion tree
        return prev[n-1];
    }
}

