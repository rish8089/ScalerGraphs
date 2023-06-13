package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class MinimumWeightedCycle {
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
        MinimumWeightedCycle obj=new MinimumWeightedCycle();
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
        ArrayList<ArrayList<Integer>> mat=new ArrayList<>();
        for(int i=0;i<A;i++){
            ArrayList<Integer> list=new ArrayList<>();
            for(int j=0;j<A;j++)
                list.add(-1);
            mat.add(list);
        }
        for(int i=0;i<A;i++){
            djikstra(i,adjList,A,mat);
        }
        int ans=Integer.MAX_VALUE;
        for(int i=0;i<B.size();i++){
            int u=B.get(i).get(0);
            int v=B.get(i).get(1);
            int w=B.get(i).get(2);
            u--;
            v--;
            if(u==v)
                ans=Math.min(ans,w);
            else if(mat.get(u).get(v)!=-1)
                ans=Math.min(ans,mat.get(u).get(v)+w);
        }
        return ans==Integer.MAX_VALUE?-1:ans;
    }
    private void djikstra(int s, ArrayList<ArrayList<Edge>> adjList, int A, ArrayList<ArrayList<Integer>> mat){
        PriorityQueue<PQNode> q=new PriorityQueue<>((nd1,nd2)->Integer.compare(nd1.d,nd2.d));
        ArrayList<Integer> dist=new ArrayList<>();
        for(int i=0;i<A;i++)
            dist.add(-1);
        dist.set(s,0);
        q.add(new PQNode(-1,s,0));
        while(!q.isEmpty()){
            PQNode nd=q.poll();
            for(int i=0;i<adjList.get(nd.v).size();i++){
                Edge e=adjList.get(nd.v).get(i);
                if(e.v!=nd.u) {
                    if (dist.get(e.v) == -1 || dist.get(e.v) > nd.d + e.w) {
                        dist.set(e.v, nd.d + e.w);
                        q.add(new PQNode(nd.v, e.v, nd.d + e.w));
                    }
                    if (nd.v != s && s != e.v) {
                        if (mat.get(s).get(e.v) == -1 || mat.get(s).get(e.v) > nd.d + e.w) {
                            mat.get(s).set(e.v, nd.d + e.w);
                        }
                    }
                }
            }
        }
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
        int u;
        int v;
        int d;
        PQNode(int u, int v, int d){
            this.u=u;
            this.v=v;
            this.d=d;
        }
    }
}
