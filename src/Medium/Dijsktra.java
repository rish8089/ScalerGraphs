package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dijsktra {
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
        Dijsktra obj=new Dijsktra();
        System.out.println(obj.solve(A,B,Integer.parseInt(br.readLine())));
    }
    public ArrayList<Integer> solve(int A, ArrayList<ArrayList<Integer>> B, int C) {
        ArrayList<ArrayList<Edge>> adjList=new ArrayList<>();
        for(int i=0;i<A;i++)
            adjList.add(new ArrayList<>());
        for(int i=0;i<B.size();i++){
            int u=B.get(i).get(0);
            int v=B.get(i).get(1);
            int w=B.get(i).get(2);
            adjList.get(u).add(new Edge(v,w));
            adjList.get(v).add(new Edge(u,w));
        }
        return djikstra(C,adjList,A);
    }
    private ArrayList<Integer> djikstra(int s, ArrayList<ArrayList<Edge>> adjList, int A){
        ArrayList<Integer> dist=new ArrayList<>();
        for(int i=0;i<A;i++)
            dist.add(-1);
        dist.set(s,0);
        Queue<PQNode> q=new PriorityQueue<>((nd1,nd2)->Integer.compare(nd1.d,nd2.d));
        q.add(new PQNode(s,0));
        while(!q.isEmpty()){
            PQNode nd=q.poll();
            for(int i=0;i<adjList.get(nd.v).size();i++){
                Edge e=adjList.get(nd.v).get(i);
                if(dist.get(e.v)==-1 || dist.get(e.v)>nd.d+e.w) {
                    dist.set(e.v,nd.d+e.w);
                    q.add(new PQNode(e.v, nd.d + e.w));
                }
            }
        }
        return dist;
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
        int d;
        PQNode(int v, int d){
            this.v=v;
            this.d=d;
        }
    }

}
