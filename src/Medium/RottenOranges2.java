package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges2 {
    int []X={-1,1,0,0};
    int []Y={0,0,1,-1};
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
        RottenOranges2 obj=new RottenOranges2();
        System.out.println(obj.solve(A));
    }
    public int solve(ArrayList<ArrayList<Integer>> A){
        Queue<Node> q=new LinkedList<>();
        int n=A.size();
        int m=A.get(0).size();
        int [][]visited=new int[n][m];
        for(int i=0;i<n;i++)
            Arrays.fill(visited[i],-1);
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(A.get(i).get(j)==2) {
                    q.add(new Node(i, j));
                    visited[i][j]=0;
                }
            }
        }
        while(!q.isEmpty()){
            Node nd=q.poll();
            for(int i=0;i<4;i++){
                if(isValid(nd.i,nd.j,X[i],Y[i],visited,A,n,m)){
                    visited[nd.i+X[i]][nd.j+Y[i]]=visited[nd.i][nd.j]+1;
                    q.add(new Node(nd.i+X[i], nd.j+Y[i]));
                }
            }
        }
        int ans=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(A.get(i).get(j)==1) {
                    if(visited[i][j]==-1)
                        return -1;
                    ans=Math.max(ans,visited[i][j]);
                }

            }
        }
        return ans;
    }
    private boolean isValid(int x, int y, int dx, int dy, int [][]visited, ArrayList<ArrayList<Integer>> A, int n, int m){
        return x+dx>=0 && x+dx<n && y+dy>=0 && y+dy<m && visited[x+dx][y+dy]==-1 && A.get(x+dx).get(y+dy)==1;
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
