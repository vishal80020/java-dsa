// Problem Link - https://leetcode.com/problems/xor-after-range-multiplication-queries-ii

// final TC - O((n+Q)*sqrt(n))

class Solution {
    int M = (int)1e9 + 7;
    //TC - O(logn log base is 2)
    private long binaryExp(long a,long n) {
        long res = 1;
        a = a % M;
        while(n>0) {
            if(n%2==1) {
                res = (res*a) % M;
                n = n-1;
            } else {
                a =(a*a) % M;
                n=n/2; 
            }
        }
        return res;
    }

    public int xorAfterQueries(int[] nums, int[][] queries) {
        //square root decomposition and difference array technique
        int n = nums.length;
        int sqrtn = (int)Math.ceil(Math.sqrt(n));
        Map<Integer,List<int[]>> map = new HashMap<>();
        // TC - O(q*sqrt(n)) 
        for(int[] query: queries) {
            int l = query[0];
            int r = query[1];
            int k = query[2];
            int v = query[3];
            if(k>=sqrtn) {
                //root(n) times this loop will run
                for(int i=l;i<=r;i+=k) {
                    nums[i] = (int)((1L * nums[i] * v) % M);
                }
            } else {
                map.computeIfAbsent(k, x->new ArrayList<>()).add(query);
            }
        }

        // unqiue value of k < (block size i.e,.sqrt(n))
        //TC (sqrt(n)*(Q+n))
        for(Map.Entry<Integer,List<int[]>> entry: map.entrySet()) {
            long[] diff = new long[n];
            Arrays.fill(diff,1);
            int k = entry.getKey();
            for(int[] query: entry.getValue()) {
                int l = query[0];
                int r = query[1];
                int v = query[3];
                diff[l] = (diff[l]*v) % M;
                int steps = (r-l)/k;
                int nextIndex = l + (steps+1)*k;
                if(nextIndex < n) {
                    // divide by V and then calculate mod
                    //  so need to use Fermat Little theorem 
                    // (a/b)mod M = a mod M * (b^M-2)mod m 
                    // here (1/V) so = 1*(V^M-2)mod M
                    diff[nextIndex] = (diff[nextIndex]*binaryExp(v,M-2))%M;
                }
            }
            //running product with k jumps on difference array // same as prefix sum
            for(int i=0;i<n;i++) {
                if(i-k>=0) {
                    diff[i] = (diff[i]*diff[i-k])% M;
                }
            }
            // now map it to original arr
            for(int i=0;i<n;i++) {
                nums[i] = (int)((1L * nums[i] * diff[i]) % M);
            }
        }
        //finally xor and return
        int ans = 0;
        for(int num:nums) {
            ans = ans^num;
        }

        // System.out.println("my ans = "+ ans+)
        return ans;

    }
}