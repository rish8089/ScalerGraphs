package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LargestDistanceBetweenNodesOfATree {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String []str=br.readLine().split(" ");
        ArrayList<Integer> A=new ArrayList<>();
        for(int i=0;i<str.length;i++)
            A.add(Integer.parseInt(str[i]));
        LargestDistanceBetweenNodesOfATree obj=new LargestDistanceBetweenNodesOfATree();
        System.out.println(obj.solve(A));
    }
    public int solve(ArrayList<Integer> A) {
        ArrayList<ArrayList<Integer>> adjList=new ArrayList<>();
        for(int i=0;i<A.size();i++)
            adjList.add(new ArrayList<>());
        int root=-1;
        for(int i=0;i<A.size();i++){
            if(A.get(i)!=-1){
                adjList.get(A.get(i)).add(i);
            }else{
                root=i;
            }
        }
        Node res=dfs(root,adjList);
        return res.d;
    }
    private Node dfs(int u,ArrayList<ArrayList<Integer>> adjList){
        if(adjList.get(u).size()==0)
            return new Node();
        Node ans=new Node();
        int maxHeight=-1;
        int secondMaxHeight=-1;
        for(int i=0;i<adjList.get(u).size();i++){
            int v=adjList.get(u).get(i);
            Node res=dfs(v,adjList);
            ans.h=Math.max(ans.h,1+res.h);
            if(maxHeight<res.h){
                secondMaxHeight=maxHeight;
                maxHeight=res.h;
            }else if(secondMaxHeight<res.h){
                secondMaxHeight=res.h;
            }
            ans.d=Math.max(ans.d,res.d);
        }
        ans.d=Math.max(ans.d,secondMaxHeight>=0?2+maxHeight+secondMaxHeight:1+maxHeight);
        return ans;
    }
    class Node{
        int h;//height
        int d;//diameter
    }
}
