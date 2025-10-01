// Problem Link - https://leetcode.com/problems/network-delay-time/

class Pair {
    int node;
    int dis;
    public Pair(int node, int dis) {
        this.node = node;
        this.dis = dis;
    }
}

//TC - O(ElogV)

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i=0;i<=n;i++) {
            adj.add(new ArrayList<>());
        }
        for(int i=0;i<times.length;i++) {
            adj.get(times[i][0]).add(new Pair(times[i][1],times[i][2]));
        }
        int[] dist = new int[n+1];
        for(int i=0;i<=n;i++) {
            dist[i] = (int)1e9;
        }
        dist[k] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((x,y)-> x.dis-y.dis);
        pq.add(new Pair(k,0));
        while(!pq.isEmpty()) {
            Pair p = pq.peek();
            pq.remove();
            int dis = p.dis;
            int u = p.node;
            // System.out.println("u = "+ u +" wt = "+dis);
            for(Pair adjNode : adj.get(u)) {
                int v = adjNode.node;
                int wt = adjNode.dis;
                // System.out.println("v = "+ v +" wt= "+wt +" required = "+(dis+wt));
                if(wt + dis < dist[v]) {
                    dist[v] = wt + dis;
                    pq.add(new Pair(v,dist[v]));
                }
            }
        }
        int minm = -1;
        for(int i=1;i<=n;i++) {
            minm = Math.max(minm,dist[i]);
        }
        if(minm == (int)1e9) return -1;
        return minm;

    }
}