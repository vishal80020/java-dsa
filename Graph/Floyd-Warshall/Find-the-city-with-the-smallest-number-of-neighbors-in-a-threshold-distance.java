// Problem Link - https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/
class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] dist = new int[n][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(i!=j) dist[i][j] = (int) 1e9;
            }
        }
        for(int i=0;i<edges.length;i++) {
            dist[edges[i][0]][edges[i][1]] = edges[i][2];
            dist[edges[i][1]][edges[i][0]] = edges[i][2];
        }

        //Floyd Warshall
        //TC - O(N^3)
        for(int via =0; via<n;via++) {
            for(int i=0;i<n;i++) {
                for(int j=0;j<n;j++) {
                    if(dist[i][via]!=(int)1e9 && dist[via][j]!=(int)1e9){
                        dist[i][j] = Math.min(dist[i][j],
                        dist[i][via]+ dist[via][j]);
                    }
                }
            }
        }

        int maxCity = n;
        int ans = 0;
        for(int i=0;i<n;i++){
            int cnt = 0;
            for(int j=0;j<n;j++) {
                if(dist[i][j] <= distanceThreshold){
                    cnt++;
                }
            }
            if(cnt<= maxCity) {
                ans = i;
                maxCity = cnt;
            }
        }
        return ans;


    }
}