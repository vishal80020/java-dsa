// Problem Link - https://leetcode.com/problems/surrounded-regions/
class Solution {

    private void dfs(int row,int col,int[][]vis,int[] delrow,int[] delcol,char[][]board) {
        int n = board.length;
        int m = board[0].length;
        vis[row][col]=1;
        for(int i=0;i<4;i++) {
            int nrow = row + delrow[i];
            int ncol = col + delcol[i];
            if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && vis[nrow][ncol]==0 && board[nrow][ncol]=='O') {
                dfs(nrow,ncol,vis,delrow,delcol,board);
            }
        }
    }

    // TC - 4*(O(N*M)) (4 directions dfs call) + O(N)(boundary loop) + O(M) (boundary loop)
    // SC - O(N*M)(recursion stack space suppose all zeroes) + O(N*M) (visited matriz)

    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        int[][] vis = new int[n][m];
        int[] delrow = {-1,0,1,0};
        int[] delcol = {0,1,0,-1};
        for(int j=0;j<m;j++) {
            //check for first row
            if(vis[0][j]==0 && board[0][j]=='O'){
                dfs(0,j,vis,delrow,delcol,board);
            }
            //check for last row
            if(vis[n-1][j]==0 && board[n-1][j]=='O'){
                dfs(n-1,j,vis,delrow,delcol,board);
            }
        }

        for(int i=0;i<n;i++) {
            //check for first column
            if(vis[i][0]==0 && board[i][0]=='O') {
                dfs(i,0,vis,delrow,delcol,board);
            }
            //check for last column
            if(vis[i][m-1]==0 && board[i][m-1]=='O') {
                dfs(i,m-1,vis,delrow,delcol,board);
            }
        }

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(vis[i][j]==0 && board[i][j]=='O'){
                    board[i][j]='X';
                }
            }
        }
        return;

    }
}