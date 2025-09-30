// Problem Link - https://www.geeksforgeeks.org/problems/strongly-connected-components-kosarajus-algo/1

class Solution {
    
    private void dfs(int node,int[] vis,
                        ArrayList<ArrayList<Integer>> adj,Stack<Integer> st) {
        vis[node] = 1;
        for(Integer adjNode : adj.get(node)) {
            if(vis[adjNode]==0) {
                dfs(adjNode,vis,adj,st);
            }
        }
        st.push(node);
    }
    
    private void dfs1(int node, int[] vis, ArrayList<ArrayList<Integer>> adjT) {
        vis[node] = 1;
        for(Integer adjNode : adjT.get(node)) {
            // System.out.println("adjNode= "+ adjNode);
            if(vis[adjNode]==0) {
                dfs1(adjNode,vis,adjT);
            }
        }
    }
    
    
    // Function to find number of strongly connected components in the graph.
    public int kosaraju(ArrayList<ArrayList<Integer>> adj) {
        // code here
        int V = adj.size();
        int[] vis = new int[V];
        Stack<Integer> st = new Stack<Integer>();
        for(int i=0;i<V;i++) {
            if(vis[i]==0) {
                dfs(i,vis,adj,st);//store the nodes based on finish time
            }
        }
        
        //Reverse the edges 
        ArrayList<ArrayList<Integer>> adjT = new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<V;i++) {
            adjT.add(new ArrayList<Integer>());
            vis[i] = 0;
        }
        for(int i=0;i<V;i++) {
            for(Integer it : adj.get(i)) {
                adjT.get(it).add(i); // reverse the edges
            }
        }
        
        int scc = 0;
        while(!st.isEmpty()) {
            int node = st.peek();
            // System.out.println("node = " + node);
            st.pop();
            if(vis[node] == 0) {
                scc++;
                dfs1(node,vis,adjT);
            }
        }
        return scc;
        
        
        
    }
}