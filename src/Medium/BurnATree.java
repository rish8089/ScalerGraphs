package Medium;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BurnATree {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String []str=br.readLine().split(" ");
        int []levelOrder=new int[str.length];
        for(int i=0;i<str.length;i++)
            levelOrder[i]=Integer.parseInt(str[i]);
        BurnATree obj=new BurnATree();
        TreeNode root=obj.buildTree(levelOrder);
        System.out.println(obj.solve(root,Integer.parseInt(br.readLine())));
    }
    private TreeNode buildTree(int []levelOrder){
        if(levelOrder.length==0 || levelOrder[0] == -1)
            return null;
        TreeNode root=new TreeNode(levelOrder[0]);
        Queue<TreeNode> q=new LinkedList<>();
        q.add(root);
        int idx=1;
        while(!q.isEmpty()){
            TreeNode nd=q.poll();
            if(levelOrder[idx]!=-1) {
                nd.left = new TreeNode(levelOrder[idx]);
                q.add(nd.left);
            }
            if(levelOrder[idx+1]!=-1) {
                nd.right = new TreeNode(levelOrder[idx + 1]);
                q.add(nd.right);
            }
            idx+=2;
        }
        return root;
    }
    public int solve(TreeNode A, int B) {
        Node rnd=solveUtil(A,B);//root node result
        return rnd.subTreeBurnTime;
    }
    private Node solveUtil(TreeNode A, int B){
        if(A==null)
            return new Node();
        Node left=solveUtil(A.left,B);
        Node right=solveUtil(A.right,B);
        Node nd=new Node();
        nd.height=1+Math.max(left.height,right.height);
        if(A.val!=B){
            //checking if fire coming from left
            if(left.burnTime!=-1){
                nd.burnTime=left.burnTime+1;
                nd.subTreeBurnTime=Math.max(left.subTreeBurnTime,nd.burnTime+right.height);
            }else if(right.burnTime!=-1){
                nd.burnTime=right.burnTime+1;
                nd.subTreeBurnTime=Math.max(right.subTreeBurnTime,nd.burnTime+left.height);
            }
        }else{
            nd.burnTime=0;
            nd.subTreeBurnTime=0;
        }
        return nd;
    }
    class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) {
       val = x;
       left=null;
       right=null;
      }
    }
    class Node{
        int height;
        int burnTime;
        int subTreeBurnTime;
        Node(){
            height=0;
            burnTime=-1;
            subTreeBurnTime=-1;
        }
    }
}
