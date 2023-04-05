package Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PathInDirectedGraph {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A=Integer.parseInt(br.readLine());
        int M=Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> B=new ArrayList<>();
        for(int i=0;i<M;i++){
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
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        for(int i=0;i<A;i++)
            adj.add(new ArrayList<>());
        for(int i=0;i<B.size();i++){
            int u=B.get(i).get(0);
            int v=B.get(i).get(1);
            u--;
            v--;
            adj.get(u).add(v);
        }
        boolean []visited=new boolean[A];
        visited[0]=true;
        return dfs(0,A-1, adj,visited)?1:0;
    }
    private static boolean dfs(int s, int d, ArrayList<ArrayList<Integer>> adj, boolean []visited){
        if(s==d)
            return true;
        for(int i=0;i<adj.get(s).size();i++){
            int nd=adj.get(s).get(i);
            if(!visited[nd]) {
                visited[nd]=true;
                boolean res = dfs(nd,d,adj,visited);
                if(res)
                    return true;
            }
        }
        return false;
    }
}