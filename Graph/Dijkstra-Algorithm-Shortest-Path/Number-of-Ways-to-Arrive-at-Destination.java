//Problem Link -- https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/
class Pair {
    long dis;
    int node;
    Pair(long dis, int node) {
        this.dis = dis;
        this.node = node;
    }
}
class Solution {
    public int countPaths(int n, int[][] roads) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());
        int m = roads.length;
        for(int i=0;i<m;i++) {
            adj.get(roads[i][0]).add(new Pair(roads[i][2],roads[i][1]));
            adj.get(roads[i][1]).add(new Pair(roads[i][2],roads[i][0]));
        }
        long[] dist = new long[n];
        int[] ways  = new int[n];
        int mod = (int)(1e9+7);
        for(int i=0;i<n;i++) {
            dist[i] = Long.MAX_VALUE;
        }
        dist[0] = 0;
        ways[0] = 1;
        PriorityQueue<Pair> pq = new PriorityQueue<>((x,y) ->Long.compare (x.dis, y.dis));
        pq.add(new Pair(0,0));
        while(!pq.isEmpty()) {
            Pair p = pq.peek();
            long dis = p.dis;
            int node = p.node;
            // System.out.println("Hello outside "+ node);
            pq.remove();
            for(Pair it : adj.get(node)) {
                long wt = it.dis;
                int adjNode = it.node;
                // This is the first time I am
                //coming with this short distance
                if(dis + wt <dist[adjNode]) {
                    dist[adjNode] = dis + wt;
                
                    ways[adjNode] = ways[node];
                    // System.out.println("ways "+ ways[adjNode]);
                    pq.add(new Pair(dis+ wt,adjNode));
                }
                else if(dis+wt == dist[adjNode]) {
                    ways[adjNode] = (ways[adjNode]+ways[node]) % mod;
                }
            }
        }
        return ways[n-1];

        //TC = O(ElogV)
    }
}