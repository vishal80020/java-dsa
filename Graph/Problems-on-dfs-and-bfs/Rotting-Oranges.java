//Problem Link - https://leetcode.com/problems/rotting-oranges/

class Tuple  {
    int row;
    int col;
    int time;
    public Tuple(int row, int col, int time) {
        this.row = row;
        this.col = col;
        this.time = time;
    }
}

class Solution {
    public int orangesRotting(int[][] grid) {
        Queue<Tuple> q = new LinkedList<>();
        int n = grid.length;
        int m = grid[0].length;
        int[][] vis = new int[n][m];
        int cntFresh = 0;

        //tc -O(N x M)
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(grid[i][j]==2) {
                    q.add(new Tuple(i,j,0));
                    vis[i][j] = 1;
                } else if (grid[i][j]==1) {
                    cntFresh++;
                }
            }
        }
        int time = 0;
        int[] delrow = {-1,0,1,0};
        int[] delcol = {0,1,0,-1};

        // tc - O(N x M) * 4 (4 directions)

        // final TC - O(NxM) + O(NxM)*4
        // SC - O(NxM)(vis) + O(NxM)(queue may contain all fresh oranges)
        while(!q.isEmpty()) {
            Tuple t = q.peek();
            int row = t.row;
            int col = t.col;
            int currTime = t.time; 
            q.remove();
            for(int i=0;i<4;i++) {
                int nrow = row + delrow[i];
                int ncol = col + delcol[i];
                if(nrow <n && ncol<m && nrow>=0 && ncol>=0 && grid[nrow][ncol]==1 && vis[nrow][ncol]==0) {
                    q.add(new Tuple(nrow,ncol,currTime+1));
                    vis[nrow][ncol]=1;
                    time = currTime+1;
                    cntFresh--;
                }
            }
        }
        return cntFresh > 0 ? -1 : time; 
    }
}