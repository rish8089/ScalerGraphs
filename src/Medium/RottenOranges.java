package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {
    static int []X={-1,1,0,0};
    static int []Y={0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String []str=br.readLine().split(" ");
        int n=Integer.parseInt(str[0]);
        int m=Integer.parseInt(str[1]);
        ArrayList<ArrayList<Integer>> A=new ArrayList<>();
        for(int i=0;i<n;i++){
            str=br.readLine().split(" ");
            ArrayList<Integer> list=new ArrayList<>();
            for(int j=0;j<m;j++)
                list.add(Integer.parseInt(str[j]));
            A.add(list);
        }
        System.out.println(solve(A));
    }
    public static int solve(ArrayList<ArrayList<Integer>> A) {
        Queue<Node> q=new LinkedList<>();
        int n=A.size();
        int m=A.get(0).size();
        boolean [][]visited=new boolean[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(A.get(i).get(j)==2) {
                    visited[i][j]=true;
                    q.add(new Node(i, j, 0));
                }
            }
        }
        int ans=0;
        while(!q.isEmpty()){
            Node nd=q.poll();
            ans=nd.l;
            for(int k=0;k<4;k++){
                int x=nd.i+X[k];
                int y=nd.j+Y[k];
                if(isValid(x,y,n,m,A,visited)){
                    visited[x][y]=true;
                    A.get(x).set(y,2);
                    q.add(new Node(x,y,nd.l+1));
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(A.get(i).get(j)==1){
                    return -1;
                }
            }
        }
        return ans;
    }
    private static boolean isValid(int x, int y, int n, int m, ArrayList<ArrayList<Integer>> A, boolean [][]visited){
        return x>=0 && x<n && y>=0 && y<m && !visited[x][y] && A.get(x).get(y)==1;
    }
    static class Node{
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
