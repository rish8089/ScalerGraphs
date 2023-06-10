package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CaptureRegionsOnBoard {
    int []X={-1,1,0,0};
    int []Y={0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String []nm=br.readLine().split(" ");
        int n=Integer.parseInt(nm[0]);
        int m=Integer.parseInt(nm[1]);
        ArrayList<ArrayList<Character>> list=new ArrayList<>();
        for(int i=0;i<n;i++) {
            String str=br.readLine();
            ArrayList<Character> subList=new ArrayList<>();
            for(int j=0;j<m;j++)
                subList.add(str.charAt(j));
            list.add(subList);
        }
        CaptureRegionsOnBoard obj=new CaptureRegionsOnBoard();
        obj.solve(list);
        System.out.println(list);
    }
    public void solve(ArrayList<ArrayList<Character>> a) {
        Queue<Coordinate> q=new LinkedList<>();
        int n=a.size();
        int m=a.get(0).size();
        boolean [][]visited=new boolean[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if((i==0 || j==0 || i==n-1 || j==n-1) && a.get(i).get(j)=='O') {
                    q.add(new Coordinate(i, j));
                    visited[i][j]=true;
                }
            }
        }
        while(!q.isEmpty()){
            Coordinate c=q.poll();
            for(int i=0;i<4;i++){
                if(isValid(c.i,c.j,X[i],Y[i],n,m,visited,a)){
                    visited[c.i+X[i]][c.j+Y[i]]=true;
                    q.add(new Coordinate(c.i+X[i],c.j+Y[i]));
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!visited[i][j])
                    a.get(i).set(j,'X');
            }
        }
        try{

        }finally{

        }
    }
    private boolean isValid(int x, int y, int dx, int dy, int n, int m, boolean [][]visited, ArrayList<ArrayList<Character>> a){
        return x+dx>=0 && x+dx<n && y+dy>=0 && y+dy<m && !visited[x+dx][y+dy] && a.get(x+dx).get(y+dy)=='O';
    }
    class Coordinate{
        int i;
        int j;
        Coordinate(int i, int j){
            this.i=i;
            this.j=j;
        }
    }
}
