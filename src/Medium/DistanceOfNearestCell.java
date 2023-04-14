package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DistanceOfNearestCell {
    static int []X={-1,1,0,0};
    static int []Y={0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String []nm=br.readLine().split(" ");
        int n=Integer.parseInt(nm[0]);
        int m=Integer.parseInt(nm[1]);
        ArrayList<ArrayList<Integer>> A=new ArrayList<>();
        for(int i=0;i<n;i++){
            String []str=br.readLine().split(" ");
            ArrayList<Integer> list=new ArrayList<>();
            for(int j=0;j<m;j++)
                list.add(Integer.parseInt(str[j]));
            A.add(list);
        }
        ArrayList<ArrayList<Integer>> ans=solve(A);
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++)
                System.out.print(ans.get(i).get(j)+" ");
            System.out.println();
        }
    }
    public static ArrayList<ArrayList<Integer>> solve(ArrayList<ArrayList<Integer>> A) {
        Queue<Node> q=new LinkedList<>();
        ArrayList<ArrayList<Integer>> ans=new ArrayList<>();
        int n=A.size();
        int m=A.get(0).size();
        for(int i=0;i<n;i++){
            ArrayList<Integer> list=new ArrayList<>();
            for(int j=0;j<m;j++)
                list.add(-1);
            ans.add(list);
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(A.get(i).get(j)==1) {
                    ans.get(i).set(j,0);
                    q.add(new Node(i, j, 0));
                }
            }
        }
        while(!q.isEmpty()){
            Node nd=q.poll();
            for(int k=0;k<4;k++){
                int x=nd.i+X[k];
                int y=nd.j+Y[k];
                if(isValid(x,y,n,m,A,ans)){
                    ans.get(x).set(y,nd.l+1);
                    q.add(new Node(x,y,nd.l+1));
                }
            }
        }
        return ans;
    }

    private static boolean isValid(int x, int y, int n, int m, ArrayList<ArrayList<Integer>> A, ArrayList<ArrayList<Integer>> mat){
        return x>=0 && x<n && y>=0 && y<m && A.get(x).get(y)==0 && mat.get(x).get(y)==-1;
    }

    static class Node {
        int i;
        int j;
        int l;
        Node(int i, int j, int l){
            this.i=i;
            this.j=j;
            this.l=l;
        }
    }
}
