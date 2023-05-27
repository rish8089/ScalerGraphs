package Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PathInDirectedGraph2 {
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
        PathInDirectedGraph2 obj=new PathInDirectedGraph2();
        System.out.println(obj.solve(A,B));
    }
    public int solve(int A, ArrayList<ArrayList<Integer>> B){
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
        return bfs(0,A-1,adj)?1:0;
    }
    private boolean bfs(int s, int d, ArrayList<ArrayList<Integer>> adj){
        Queue<Integer> q=new LinkedList<>();
        q.add(s);
        HashSet<Integer> set=new HashSet<>();
        set.add(s);
        while(!q.isEmpty()){
            int u=q.poll();
            if(u==d)
                return true;
            for(int i=0;i<adj.get(u).size();i++){
                int v=adj.get(u).get(i);
                if(!set.contains(v)) {
                    set.add(v);
                    q.add(v);
                }
            }
        }
        return false;
    }
}
