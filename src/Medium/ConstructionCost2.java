package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class ConstructionCost2 {
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
        ConstructionCost3 obj=new ConstructionCost3();
        System.out.println(obj.solve(A,B));
    }
    public int solve(int A, ArrayList<ArrayList<Integer>> B) {
        return kruskal(A,B);
    }

    //let's solve it via kruskal's algorithm with union find
    private int kruskal(int A, ArrayList<ArrayList<Integer>> B){
        Collections.sort(B, (l1, l2)->Integer.compare(l1.get(2),l2.get(2)));
        int []parent=new int[A];
        for(int i=0;i<A;i++)
            parent[i]=i;
        int ans=0;
        for(int i=0;i<B.size();i++){
            int res=union(B.get(i).get(0)-1,B.get(i).get(1)-1,parent);
            if(res==0)
                ans=(ans+B.get(i).get(2))%MOD;
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

    //path compression technique
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
}
