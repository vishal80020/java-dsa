// Problem Link - https://leetcode.com/problems/01-matrix/

class Tuple {
    int row;
    int col;
    int dist;
    public Tuple(int row,int col,int dist) {
        this.row = row;
        this.col = col;
        this.dist = dist;
    }
}

// TC - O(M*N) + O(M*N)*4
// SC - O(M*N) + O(M*N)

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        Queue<Tuple> q = new LinkedList<>();
        int n = mat.length;
        int m = mat[0].length;
        int[][] vis = new int[n][m];
        int[][] distmat = new int[n][m];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(mat[i][j]==0) {
                    q.add(new Tuple(i,j,0));
                    vis[i][j]= 1;
                } 
            }
        }
        int[] delrow = {-1,0,1,0};
        int[] delcol = {0,1,0,-1};
        while(!q.isEmpty()) {
            Tuple t = q.peek();
            q.remove();
            int row = t.row;
            int col = t.col;
            int dist = t.dist;
            for(int i=0;i<4;i++) {
                int nrow = row + delrow[i];
                int ncol = col + delcol[i];
                if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && vis[nrow][ncol]==0){
                    distmat[nrow][ncol] = dist+1;
                    q.add(new Tuple(nrow,ncol,dist+1));
                    vis[nrow][ncol] =1;
                }
            }
        }
        return distmat;

    }
}