package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class KnightOnChessBoard {
    int []X={-2,2,-2,2,1,-1,1,-1};
    int []Y={-1,1,1,-1,2,-2,-2,2};
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        KnightOnChessBoard obj=new KnightOnChessBoard();

        System.out.println(obj.knight(Integer.parseInt(br.readLine()),
                Integer.parseInt(br.readLine()),Integer.parseInt(br.readLine()),Integer.parseInt(br.readLine()),Integer.parseInt(br.readLine()),Integer.parseInt(br.readLine())));
    }
    public int knight(int A, int B, int C, int D, int E, int F) {
        return bfs(C-1,D-1,E-1,F-1,A,B);
    }
    private int bfs(int C, int D, int E, int F, int A, int B){
        Queue<Coordinate> q=new LinkedList<>();
        Coordinate s=new Coordinate(C,D);
        HashMap<Coordinate,Integer> map=new HashMap<>();
        q.add(s);
        map.put(s,0);
        while(!q.isEmpty()){
            Coordinate u=q.poll();
            int d=map.get(u);
            if(u.i==E && u.j==F)
                return d;
            for(int i=0;i<8;i++){
                if(isValid(u.i,u.j,X[i],Y[i],A,B,map)){
                    Coordinate v=new Coordinate(u.i+X[i],u.j+Y[i]);
                    q.add(v);
                    map.put(v,d+1);
                }
            }
        }
        return -1;
    }
    private boolean isValid(int x, int y, int dx, int dy, int A, int B, HashMap<Coordinate,Integer> map){
        return x+dx>=0 && x+dx<A && y+dy>=0 && y+dy<B && !map.containsKey(new Coordinate(x+dx,y+dy));
    }
    class Coordinate{
        int i;
        int j;
        Coordinate(int i, int j){
            this.i=i;
            this.j=j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return i == that.i && j == that.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }
}
