package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PoisonousGraph {
    static int MOD=998244353;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A=Integer.parseInt(br.readLine());
        int m=Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> B=new ArrayList<>();
        for(int i=0;i<m;i++){
            String []str=br.readLine().split(" ");
            int u=Integer.parseInt(str[0]);
            int v=Integer.parseInt(str[1]);
            ArrayList<Integer> list=new ArrayList<>();
            list.add(u);
            list.add(v);
            B.add(list);
        }
        System.out.println(solve(A,B));
    }
    public static int solve(int A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<ArrayList<Integer>> adjList=new ArrayList<>();
        for(int i=0;i<A;i++)
            adjList.add(new ArrayList<>());
        for(int i=0;i<B.size();i++){
            int u=B.get(i).get(0);
            int v=B.get(i).get(1);
            u--;
            v--;
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        boolean []visited=new boolean[A];
        long ans=1;
        for(int i=0;i<A;i++){
            if(!visited[i]) {
                int res = bfs(i,A,visited,adjList);
                ans=ans*res;
                if(ans>=MOD)
                    ans=ans%MOD;
            }
        }
        return (int)ans;
    }
    private static int bfs(int s, int A, boolean []visited, ArrayList<ArrayList<Integer>> adjList){
        Queue<Integer> q=new LinkedList<>();
        visited[s]=true;
        q.add(s);
        int []level=new int[A];
        level[s]=0;
        int odd=0;
        int even=0;
        while(!q.isEmpty()){
            int u=q.poll();
            if(level[u]%2==0)
                even++;
            else
                odd++;
            for(int i=0;i<adjList.get(u).size();i++){
                int v=adjList.get(u).get(i);
                if(!visited[v]){
                    visited[v]=true;
                    q.add(v);
                    level[v]=level[u]+1;
                }else{
                    if(level[v]==level[u])
                        return 0;
                }
            }
        }
        int ans=mypow(2,odd)+mypow(2,even);
        if(ans>=MOD)
            ans%=MOD;
        return ans;
    }
    private static int mypow(long a, int b){
        long res=1;
        while(b>0){
            if(b%2!=0)
                res=(res*a)%MOD;
            b=b/2;
            a=(a*a)%MOD;
        }
        return (int)res;
    }
}
