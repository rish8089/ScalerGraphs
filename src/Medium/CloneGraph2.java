package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CloneGraph2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        int m=Integer.parseInt(br.readLine());
        HashMap<Integer, CloneGraph2.UndirectedGraphNode> mp=new HashMap<>();
        for(int i=0;i<m;i++){
            String []str=br.readLine().split(" ");
            int a=Integer.parseInt(str[0]);
            int b=Integer.parseInt(str[1]);
            CloneGraph2.UndirectedGraphNode aNode;
            CloneGraph2.UndirectedGraphNode bNode;
            if(!mp.containsKey(a)) {
                aNode=new CloneGraph2.UndirectedGraphNode(a);
                mp.put(a, aNode);
            }else
                aNode=mp.get(a);
            if(!mp.containsKey(b)) {
                bNode=new CloneGraph2.UndirectedGraphNode(b);
                mp.put(b, bNode);
            }else
                bNode=mp.get(b);
            aNode.neighbors.add(bNode);
            bNode.neighbors.add(aNode);
        }
        if(mp.size()>0){
            ArrayList<Integer> list=new ArrayList<>(mp.keySet());
            CloneGraph2 obj=new CloneGraph2();
            CloneGraph2.UndirectedGraphNode cgnd=obj.cloneGraph(mp.get(list.get(0)));
            printGraph(cgnd);
        }
    }
    private static void printGraph(CloneGraph2.UndirectedGraphNode node){
        if(node==null)
            return;
        HashSet<Integer> set=new HashSet<>();
        set.add(node.label);
        printGraphUtil(node,set);
    }
    private static void printGraphUtil(CloneGraph2.UndirectedGraphNode node, HashSet<Integer> set){
        for(int i=0;i<node.neighbors.size();i++){
            if(!set.contains(node.neighbors.get(i).label)) {
                set.add(node.neighbors.get(i).label);
                System.out.format("%d %d\n", node.label, node.neighbors.get(i).label);
                printGraphUtil(node.neighbors.get(i), set);
            }
        }
    }
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node){
        return dfs(node,new HashMap<>());
    }
    private UndirectedGraphNode dfs(UndirectedGraphNode node, HashMap<Integer,UndirectedGraphNode> visited){
        UndirectedGraphNode cdn=new UndirectedGraphNode(node.label);
        visited.put(node.label,cdn);
        for(int i=0;i<node.neighbors.size();i++){
            UndirectedGraphNode neighbor=node.neighbors.get(i);
            if(!visited.containsKey(neighbor.label))
                cdn.neighbors.add(dfs(neighbor,visited));
            else
                cdn.neighbors.add(visited.get(neighbor.label));
        }
        return cdn;
    }
    static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
    }
}
