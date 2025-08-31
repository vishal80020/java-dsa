// User function template for JAVA
// Problem Link - https://www.geeksforgeeks.org/problems/implementing-floyd-warshall2042/1
class Solution {
    public void floydWarshall(int[][] dist) {
        // Code here
        int n = dist.length;
        int x = (int) 1e8;
        //TC - O(N^3)
        for(int via=0;via<n;via++) {
            for(int i=0;i<n;i++) {
                for(int j=0;j<n;j++) {
                    if(dist[i][via] !=x && dist[via][j]!=x) {
                         dist[i][j] = Math.min(dist[i][j],
                    dist[i][via] + dist[via][j]);
                    }
                }
            }
        }
        return;
    }
}