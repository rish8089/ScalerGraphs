package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DistanceOfNearestCell2 {
    int []X={-1,1,0,0};
    int []Y={0,0,1,-1};
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
        DistanceOfNearestCell2 obj=new DistanceOfNearestCell2();
        ArrayList<ArrayList<Integer>> ans=obj.solve(A);
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++)
                System.out.print(ans.get(i).get(j)+" ");
            System.out.println();
        }
    }
    public ArrayList<ArrayList<Integer>> solve(ArrayList<ArrayList<Integer>> A){
        Queue<Node> q=new LinkedList<>();
        int n=A.size();
        int m=A.get(0).size();
        ArrayList<ArrayList<Integer>> visited=new ArrayList<>();
        for(int i=0;i<n;i++){
            visited.add(new ArrayList<>());
            for(int j=0;j<m;j++)
                visited.get(i).add(-1);
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(A.get(i).get(j)==1) {
                    q.add(new Node(i, j));
                    visited.get(i).set(j,0);
                }
            }
        }
        while(!q.isEmpty()){
            Node nd=q.poll();
            for(int i=0;i<4;i++){
                if(isValid(nd.i,nd.j,X[i],Y[i],visited,A,n,m)){
                    visited.get(nd.i+X[i]).set(nd.j+Y[i],visited.get(nd.i).get(nd.j)+1);
                    q.add(new Node(nd.i+X[i], nd.j+Y[i]));
                }
            }
        }
        return visited;
    }
    private boolean isValid(int x, int y, int dx, int dy, ArrayList<ArrayList<Integer>> visited, ArrayList<ArrayList<Integer>> A, int n, int m){
        return x+dx>=0 && x+dx<n && y+dy>=0 && y+dy<m && visited.get(x+dx).get(y+dy)==-1 && A.get(x+dx).get(y+dy)==0;
    }
    class Node{
        int i;
        int j;
        Node(int i, int j){
            this.i=i;
            this.j=j;
        }
    }
}
