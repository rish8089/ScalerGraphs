package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistanceInAMaze2 {
    int []X={-1,1,0,0};
    int []Y={0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
            String []str=br.readLine().split(" ");
            for(int j=0;j<m;j++)
                adj.get(i).add(Integer.parseInt(str[j]));
        }
        String []s=br.readLine().split(" ");
        String []d=br.readLine().split(" ");
        ArrayList<Integer> B=new ArrayList<>();
        B.add(Integer.parseInt(s[0]));
        B.add(Integer.parseInt(s[1]));
        ArrayList<Integer> C=new ArrayList<>();
        C.add(Integer.parseInt(d[0]));
        C.add(Integer.parseInt(d[1]));
        ShortestDistanceInAMaze2 obj=new ShortestDistanceInAMaze2();
        System.out.println(obj.solve(adj,B,C));
    }
    public int solve(ArrayList<ArrayList<Integer>> A, ArrayList<Integer> B, ArrayList<Integer> C){
        Queue<Node> q=new LinkedList<>();
        int n=A.size();
        int m=A.get(0).size();
        int [][][]visited=new int[n][m][4];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++)
                Arrays.fill(visited[i][j],-1);
        }
        if(A.get(B.get(0)).get(B.get(1)) == 1 || A.get(C.get(0)).get(C.get(1))==1)
            return -1;
        for(int i=0;i<4;i++) {
            q.add(new Node(B.get(0), B.get(1), i));
            visited[B.get(0)][B.get(1)][i]=0;
        }
        while(!q.isEmpty()){
            Node nd=q.poll();
            int ux=nd.i;
            int uy=nd.j;
            while(isEmptyCell(ux,uy,nd.d,n,m,A) && visited[ux+X[nd.d]][uy+Y[nd.d]][nd.d]==-1){
                visited[ux+X[nd.d]][uy+Y[nd.d]][nd.d]=visited[ux][uy][nd.d]+1;
                ux+=X[nd.d];
                uy+=Y[nd.d];
            }
            //did we stop due to obstacle
            if (!isEmptyCell(ux, uy, nd.d, n, m, A)) {
                if(ux==C.get(0) && uy==C.get(1))
                    return visited[ux][uy][nd.d];
                for (int i = 0; i < 4; i++) {
                    if (visited[ux][uy][i]==-1) {
                        visited[ux][uy][i] = visited[ux][uy][nd.d];
                        q.add(new Node(ux, uy, i));
                    }
                }
            }
        }
        return -1;
    }
    private boolean isEmptyCell(int x, int y, int d, int n, int m,ArrayList<ArrayList<Integer>> A){
        return x+X[d]>=0 && x+X[d]<n && y+Y[d]>=0 && y+Y[d]<m && A.get(x+X[d]).get(y+Y[d])==0;
    }
    class Node{
        int i;
        int j;
        int d;
        Node(int i, int j,int d){
            this.i=i;
            this.j=j;
            this.d=d;
        }
    }
}
