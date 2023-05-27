package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FirstDepthFirstSearch2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String []str=br.readLine().split(" ");
        ArrayList<Integer> A=new ArrayList<>();
        for(int i=0;i<str.length;i++)
            A.add(Integer.parseInt(str[i]));
        String []BC=br.readLine().split(" ");
        FirstDepthFirstSearch2 obj=new FirstDepthFirstSearch2();
        System.out.println(obj.solve(A,Integer.parseInt(BC[0]),Integer.parseInt(BC[1])));
    }
    public int solve(ArrayList<Integer> A, final int B, final int C){
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        for(int i=0;i<=A.size();i++)
            adj.add(new ArrayList<>());
        for(int i=1;i<A.size();i++){
            adj.get(A.get(i)).add(i+1);
        }
        return existsPath(adj,C,B)?1:0;
    }
    private boolean existsPath(ArrayList<ArrayList<Integer>> adj, int u, int B){
        if(u==B)
            return true;
        for(int i=0;i<adj.get(u).size();i++){
            boolean res=existsPath(adj,adj.get(u).get(i),B);
            if(res)
                return true;
        }
        return false;
    }
}
