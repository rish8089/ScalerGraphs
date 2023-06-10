package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DamagedRoads {
    private int MOD=(int)1e9+7;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> A=new ArrayList<>();
        String []str=br.readLine().split(" ");
        for(int i=0;i<str.length;i++)
            A.add(Integer.parseInt(str[i]));
        ArrayList<Integer> B=new ArrayList<>();
        str=br.readLine().split(" ");
        for(int i=0;i<str.length;i++)
            B.add(Integer.parseInt(str[i]));
        DamagedRoads obj=new DamagedRoads();
        System.out.println(obj.solve(A,B));
    }
    public int solve(ArrayList<Integer> A, ArrayList<Integer> B) {
        return prim(0,0,A,B);
    }
    private int prim(int sx, int sy, ArrayList<Integer> A, ArrayList<Integer> B){
        int n=A.size();
        int m=B.size();
        Queue<QNode> q=new PriorityQueue<>((nd1,nd2)->{
            int l1=nd1.v.x+nd1.v.y;
            int l2=nd2.v.x+nd2.v.y;
            if(l1<l2)
                return -1;
            else if(l1>l2)
                return 1;
            else
                return Integer.compare(nd1.w,nd2.w);
        });
        q.add(new QNode(new Coordinate(sx,sy),0));
        HashSet<Coordinate> set=new HashSet<>();
        int ans=0;
        while(!q.isEmpty()){
            QNode nd=q.poll();
            Coordinate c=nd.v;
            if(!set.contains(c)){
                set.add(c);
                ans=(ans+nd.w)%MOD;
                if(c.x+1<=n)
                    q.add(new QNode(new Coordinate(c.x+1,c.y),A.get(c.x)));
                if(c.y+1<=m)
                    q.add(new QNode(new Coordinate(c.x,c.y+1),B.get(c.y)));
            }
        }
        return ans;
    }
    class Coordinate{
        int x;
        int y;
        Coordinate(int x, int y){
            this.x=x;
            this.y=y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    class QNode{
        Coordinate v;
        int w;
        QNode(Coordinate v, int w){
            this.v=v;
            this.w=w;
        }
    }
}
