package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CommutableIslands {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A=Integer.parseInt(br.readLine());
        int m=Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> B=new ArrayList<>();
        for(int i=0;i<m;i++){
            String []str=br.readLine().split(" ");
            int u=Integer.parseInt(str[0]);
            int v=Integer.parseInt(str[1]);
            int w=Integer.parseInt(str[2]);
            ArrayList<Integer> list=new ArrayList<>();
            list.add(u);
            list.add(v);
            list.add(w);
            B.add(list);
        }
        CommutableIslands obj=new CommutableIslands();
        System.out.println(obj.solve(A,B));
    }
    public int solve(int A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<ArrayList<Edge>> adjList=new ArrayList<>();
        for(int i=0;i<A;i++)
            adjList.add(new ArrayList<>());
        for(int i=0;i<B.size();i++){
            int u=B.get(i).get(0);
            int v=B.get(i).get(1);
            int w=B.get(i).get(2);
            u--;
            v--;
            adjList.get(u).add(new Edge(v,w));
            adjList.get(v).add(new Edge(u,w));
        }
        return prim(A,adjList);
    }
    private int prim(int A, ArrayList<ArrayList<Edge>> adjList){
        Queue<PQNode> q=new PriorityQueue<>((nd1,nd2)->Integer.compare(nd1.w,nd2.w));
        q.add(new PQNode(0,0));
        int sum=0;
        HashSet<Integer> set=new HashSet<>();
        while(!q.isEmpty()){
            PQNode nd=q.poll();
            if(!set.contains(nd.v)) {
                sum += nd.w;
                set.add(nd.v);
                for (int i = 0; i < adjList.get(nd.v).size(); i++) {
                    Edge e = adjList.get(nd.v).get(i);
                    q.add(new PQNode(e.v, e.w));
                }
            }
        }
        return sum;
    }
    class Edge{
        int v;
        int w;
        Edge(int v, int w){
            this.v=v;
            this.w=w;
        }
    }
    class PQNode{
        int v;
        int w;
        PQNode(int v, int w){
            this.v=v;
            this.w=w;
        }
    }
}
