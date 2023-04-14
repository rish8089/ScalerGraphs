package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CloneGraph {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        int m=Integer.parseInt(br.readLine());
        HashMap<Integer, UndirectedGraphNode> mp=new HashMap<>();
        for(int i=0;i<m;i++){
            String []str=br.readLine().split(" ");
            int a=Integer.parseInt(str[0]);
            int b=Integer.parseInt(str[1]);
            UndirectedGraphNode aNode;
            UndirectedGraphNode bNode;
            if(!mp.containsKey(a)) {
                aNode=new UndirectedGraphNode(a);
                mp.put(a, aNode);
            }else
                aNode=mp.get(a);
            if(!mp.containsKey(b)) {
                bNode=new UndirectedGraphNode(b);
                mp.put(b, bNode);
            }else
                bNode=mp.get(b);
            aNode.neighbors.add(bNode);
            bNode.neighbors.add(aNode);
        }
        if(mp.size()>0){
            ArrayList<Integer> list=new ArrayList<>(mp.keySet());
            UndirectedGraphNode cgnd=cloneGraph(mp.get(list.get(0)));
            printGraph(cgnd);
        }
    }
    private static void printGraph(UndirectedGraphNode node){
        if(node==null)
            return;
        HashSet<Integer> set=new HashSet<>();
        set.add(node.label);
        printGraphUtil(node,set);
    }
    private static void printGraphUtil(UndirectedGraphNode node, HashSet<Integer> set){
        for(int i=0;i<node.neighbors.size();i++){
            if(!set.contains(node.neighbors.get(i).label)) {
                set.add(node.neighbors.get(i).label);
                System.out.format("%d %d\n", node.label, node.neighbors.get(i).label);
                printGraphUtil(node.neighbors.get(i), set);
            }
        }
    }
    public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null)
            return null;
        HashMap<Integer, UndirectedGraphNode> mp=new HashMap<>();
        UndirectedGraphNode nd= new UndirectedGraphNode(node.label);
        mp.put(node.label, nd);
        cloneGrapUtil(node,nd,mp);
        return nd;
    }
    private static void cloneGrapUtil(UndirectedGraphNode node, UndirectedGraphNode cNode,
                                                     HashMap<Integer,UndirectedGraphNode> mp){
        for(int i=0;i<node.neighbors.size();i++){
            if(!mp.containsKey(node.neighbors.get(i).label)) {
                UndirectedGraphNode nd= new UndirectedGraphNode(node.neighbors.get(i).label);
                mp.put(node.neighbors.get(i).label, nd);
                cloneGrapUtil(node.neighbors.get(i),nd,mp);
                cNode.neighbors.add(nd);
            }else{
                cNode.neighbors.add(mp.get(node.neighbors.get(i).label));
            }
        }
    }
    static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
    };
}
