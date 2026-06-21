class Pair {
    int node;
    int dist;
    public Pair(int node, int dist) {
        this.node = node;
        this.dist = dist;
    }
}
class Triplet {
    int dist;
    int node;
    int consec;
    public Triplet(int dist, int node,int consec) {
        this.dist = dist;
        this.node = node;
        this.consec = consec;
    }
}

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        } 
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            adj.get(u).add(new Pair(v,wt));
        }
        //We need 2d distance array because consec characters can increased or decreased (if not same characters then consec reset to 1 but in flight with k steps in that k stops always increase)
        int[][] dist = new int[n][k + 1];
        for(int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        dist[0][1] = 0;
        PriorityQueue<Triplet> pq = new PriorityQueue<>((x,y) ->Integer.compare (x.dist, y.dist));
        pq.add(new Triplet(0,0,1));

        while(!pq.isEmpty()) {
            Triplet p = pq.peek();
            int dis = p.dist;
            int u = p.node;
            int consec = p.consec;
            pq.remove();

            if(u==n-1) return dis;
            if(consec > k) continue;

            for(Pair it : adj.get(u)) {
                int req = it.dist;
                int v = it.node;
                int newconsec;
                if(labels.charAt(v) == labels.charAt(u)) {
                    newconsec = consec+1;
                } else {
                    newconsec = 1;
                }
                if(newconsec <=k) {
                    if(dis +  req < dist[v][newconsec] && consec <= k ) {
                        dist[v][newconsec] = dis + req;
                        pq.add(new Triplet(dist[v][newconsec],v,newconsec));
                    }
                }
                
            }
        }

        return -1;
    }
}