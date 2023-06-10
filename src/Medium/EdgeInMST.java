package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class EdgeInMST {
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
        EdgeInMST obj=new EdgeInMST();
        System.out.println(obj.solve(A,B));
    }
    public ArrayList<Integer> solve(int A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<Integer> res=new ArrayList<>();
        for(int i=0;i<B.size();i++)
            res.add(0);
        kruskal(A,B,res);
        return res;
    }
    //let's solve it via kruskal's algorithm with union find
    private void kruskal(int A, ArrayList<ArrayList<Integer>> B, ArrayList<Integer> res){
        ArrayList<Node> edges=new ArrayList<>();
        for(int i=0;i<B.size();i++)
            edges.add(new Node(B.get(i),i));
        Collections.sort(edges, (e1, e2)->Integer.compare(e1.list.get(2),e2.list.get(2)));
        int []parent=new int[A];
        for(int i=0;i<A;i++)
            parent[i]=i;
        for(int i=0;i<edges.size();i++){
            int w=edges.get(i).list.get(2);
            int j=i;
            while(j<edges.size()){
                if(!isCycleFormed(edges.get(j).list.get(0)-1, edges.get(j).list.get(1)-1,parent))
                    res.set(edges.get(j).i,1);
                if(j+1<edges.size() && edges.get(j+1).list.get(2)==w)
                    j++;
                else
                    break;
            }
            while(i<edges.size()){
                union(edges.get(i).list.get(0)-1, edges.get(i).list.get(1)-1,parent);
                if(i+1<edges.size() && edges.get(i+1).list.get(2)==w)
                    i++;
                else
                    break;
            }
        }
    }

    private boolean isCycleFormed(int u, int v, int []parent){
        int ru=findRoot(u,parent);
        int rv=findRoot(v,parent);
        return ru==rv;
    }

    //returns -1 if union can't be done
    //else returns 0
    private void union(int u, int v, int []parent){
        int ru=findRoot(u,parent);
        int rv=findRoot(v,parent);
        if(ru==rv)
            return;
        parent[ru]=parent[rv];
    }
    private int findRoot(int u, int []parent){
        int temp=u;
        while(u!=parent[u]){
            u=parent[u];
        }
        while(temp!=u){
            int temp2=parent[temp];
            parent[temp]=u;
            temp=temp2;
        }
        return u;
    }
    class Node{
        ArrayList<Integer> list;
        int i;
        Node(ArrayList<Integer> list, int i){
            this.list=list;
            this.i=i;
        }
    }
}
