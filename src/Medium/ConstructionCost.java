package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class ConstructionCost {
    private int MOD=(int)1e9+7;
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
        ConstructionCost obj=new ConstructionCost();
        System.out.println(obj.solve(A,B));
    }
    public int solve(int A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<Triplet> edges=new ArrayList<>();
        for(int i=0;i<B.size();i++)
            edges.add(new Triplet(B.get(i).get(0)-1, B.get(i).get(1)-1, B.get(i).get(2)));
        return kruskal(A,edges);
    }

    //let's solve it via kruskal's algorithm with union find
    private int kruskal(int A, ArrayList<Triplet> edges){
        Collections.sort(edges, (t1,t2)->Integer.compare(t1.w,t2.w));
        int []parent=new int[A];
        for(int i=0;i<A;i++)
            parent[i]=i;
        int ans=0;
        for(int i=0;i<edges.size();i++){
            Triplet t=edges.get(i);
            int res=union(t.u,t.v,parent);
            if(res==0)
                ans=(ans+t.w)%MOD;
        }
        return ans;
    }

    //returns -1 if union can't be done
    //else returns 0
    private int union(int u, int v, int []parent){
        int ru=findRoot(u,parent);
        int rv=findRoot(v,parent);
        if(ru==rv)
            return -1;
        parent[ru]=parent[rv];
        return 0;
    }
    private int findRoot(int u, int []parent){
        while(u!=parent[u]){
            u=parent[u];
        }
        return u;
    }
    class Triplet{
        int u;
        int v;
        int w;
        Triplet(int u, int v, int w){
            this.u=u;
            this.v=v;
            this.w=w;
        }
    }

}
