// Problem Link - https://leetcode.com/problems/flood-fill/
class Solution {
    private void dfs(int row,int col, int[] delrow, int[] delcol,
                    int initialColor,int color, int[][] image,int[][] ans) {
        ans[row][col] = color;
        int n = image.length;
        int m = image[0].length;
        for(int i=0;i<4;i++) {
            int nrow = row + delrow[i];
            int ncol = col + delcol[i];
            if(nrow >=0 && nrow<n && ncol>=0 && ncol<m && 
                ans[nrow][ncol] != color && image[nrow][ncol] == initialColor) {
                dfs(nrow,ncol,delrow,delcol,initialColor,color,image,ans);
            }
        }

    }

    // TC - O(NxM)*4
    // SC - O(NxM) (ans) + O(N*M) (recursion stack space)
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int n = image.length;
        int m = image[0].length;
        int[][] vis = new int[n][m];
        int[][] ans = image;
        int initialColor = image[sr][sc];
        int[] delrow = {-1,0,1,0};
        int[] delcol = {0,1,0,-1};
        dfs(sr,sc,delrow,delcol,initialColor,color,image,ans);
        return ans;
    }
}