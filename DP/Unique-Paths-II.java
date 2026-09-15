// Problem Link - https://leetcode.com/problems/unique-paths-ii/

//Recursion
class Solution {

    private int solve(int i, int j, int[][] obstacleGrid) {
        if (i < 0 || j < 0)
            return 0;
        if (obstacleGrid[i][j] == 1) {
            return 0; // encounterd obstacle
        }

        if (i == 0 && j == 0) {
            return 1;
        }

        int left = j > 0 ? solve(i, j - 1, obstacleGrid) : 0;
        int up = i > 0 ? solve(i - 1, j, obstacleGrid) : 0;
        return left + up;
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        return solve(m - 1, n - 1, obstacleGrid);
    }
}

//Memoization
class Solution {

    private int solve(int i, int j, int[][] obstacleGrid,int[][]dp) {
        if (i < 0 || j < 0)
            return 0;
        if (obstacleGrid[i][j] == 1) {
            return 0; // encounterd obstacle
        }

        if (i == 0 && j == 0) {
            return 1;
        }
        if(dp[i][j] != -1) {
            return dp[i][j];
        }

        int left = j > 0 ? solve(i, j - 1, obstacleGrid,dp) : 0;
        int up = i > 0 ? solve(i - 1, j, obstacleGrid,dp) : 0;
        return dp[i][j]= left + up;
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                dp[i][j]=-1;
            }
        }
        return solve(m - 1, n - 1, obstacleGrid,dp);
    }
}

// Tabulation
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0; // encounter an obstacle so no valid path
                    continue;
                }
                if (i == 0 && j == 0) {
                    dp[0][0] = 1; //base case
                    continue;
                }
                int left = j > 0 ? dp[i][j - 1] : 0;
                int up = i > 0 ? dp[i - 1][j] : 0;
                dp[i][j] = left + up;

            }
        }
        return dp[m - 1][n - 1];
    }
}

//Space Optimization
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] prev = new int[n];

        for (int i = 0; i < m; i++) {
            int[] curr = new int[n];
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    curr[j] = 0; // encounter an obstacle so no valid path
                    continue;
                }
                if (i == 0 && j == 0) {
                    curr[0] = 1; //base case
                    continue;
                }
                int left = j > 0 ? curr[j - 1] : 0;
                int up = i > 0 ? prev[j] : 0;
                curr[j] = left + up;

            }
            prev = curr;
        }
        return prev[n - 1];
    }
}