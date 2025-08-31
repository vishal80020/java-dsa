//Problem Link - https://leetcode.com/problems/cheapest-flights-within-k-stops/
class Pair {
    int first;
    int second;
    Pair(int first,int second) {
        this.first = first;
        this.second = second;
    }
}
class Tuple {
    int stops;
    int node;
    int cost;
    public Tuple(int stops, int node,int cost) {
        this.stops = stops;
        this.node = node;
        this.cost = cost;
    }
}
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i=0;i<n;i++) {
            adj.add(new ArrayList<>());
        }
        int m = flights.length;
        for(int i=0;i<m;i++) {
            adj.get(flights[i][0]).add(new Pair(flights[i][1],flights[i][2]));
        }
        int[] dist = new int[n];
        for(int i=0;i<n;i++) dist[i] = (int)1e9;
        dist[src] = 0;

        // TC - O(E) here logV is not because 
        // we are not using priority queue
        // TC - O(flights.length)
        Queue<Tuple> q = new LinkedList<>();
        q.add(new Tuple(0,src,0));
        while(!q.isEmpty()) {
            Tuple t = q.peek();
            q.remove();
            int stops = t.stops;
            int node = t.node;
            int cost = t.cost;
            if(stops > k) continue;
            for(Pair p : adj.get(node)) {
                int adjNode = p.first;
                int reqcost = p.second;
                int total = cost + reqcost;
                if(stops <= k && total < dist[adjNode]) {
                    dist[adjNode] = total;
                    q.add(new Tuple(stops+1,adjNode,dist[adjNode]));
                } 
            }
        }
        if(dist[dst] == (int)1e9) return -1;
        return dist[dst];

    }
}