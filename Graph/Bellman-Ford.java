// User function Template for Java
//Problem Link - https://www.geeksforgeeks.org/problems/distance-from-the-source-bellman-ford-algorithm/1
class Solution {
    public int[] bellmanFord(int V, int[][] edges, int src) {
        // code here
        int[] dist = new int[V]; 
        for(int i=0;i<V;i++) {
            dist[i]= (int)1e8;
        }
        dist[src]= 0;
        
        // TC - O(V X E)
        for(int i=0;i<V-1;i++) {
            for(int j=0;j<edges.length;j++) {
                int u = edges[j][0];
                int v = edges[j][1];
                int wt = edges[j][2];
                if(dist[u] != (int)1e8 && dist[u]+ wt < dist[v]) {
                    dist[v]= dist[u]+wt;
                }
            }
        }
        //After N-1 iterations also distance is getting reduced then there is cycle
       for(int j=0;j<edges.length;j++) {
            int u = edges[j][0];
            int v = edges[j][1];
            int wt = edges[j][2];
            if(dist[u] != (int)1e8 && dist[u]+ wt < dist[v]) {
                dist[v]= dist[u]+wt;
                 return new int[] {-1};
            }
        }

        return dist;
    }
}
