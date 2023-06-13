package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SheldonAndPairOfCities {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        SheldonAndPairOfCities obj=new SheldonAndPairOfCities();
        int A=Integer.parseInt(br.readLine());
        ArrayList<Integer> D=obj.inputList(br);
        ArrayList<Integer> E=obj.inputList(br);
        ArrayList<Integer> F=obj.inputList(br);
        ArrayList<Integer> G=obj.inputList(br);
        ArrayList<Integer> H=obj.inputList(br);
        System.out.println(obj.solve(A,D.size(),G.size(),D,E,F,G,H));
    }
    private ArrayList<Integer> inputList(BufferedReader br) throws IOException {
        ArrayList<Integer> list=new ArrayList<>();
        String []str=br.readLine().split(" ");
        for(int i=0;i<str.length;i++)
            list.add(Integer.parseInt(str[i]));
        return list;
    }
    public ArrayList<Integer> solve(int A, int B, int C, ArrayList<Integer> D, ArrayList<Integer> E, ArrayList<Integer> F, ArrayList<Integer> G, ArrayList<Integer> H) {
        ArrayList<ArrayList<Integer>> mat=new ArrayList<>();
        for(int i=0;i<A;i++){
            ArrayList<Integer> list=new ArrayList<>();
            for(int j=0;j<A;j++){
                if(i==j)
                    list.add(0);
                else
                    list.add(-1);
            }
            mat.add(list);
        }
        for(int i=0;i<B;i++){
            int u=D.get(i);
            int v=E.get(i);
            int w=F.get(i);
            u--;
            v--;
            if(mat.get(u).get(v)==-1 || mat.get(u).get(v)>w){
                mat.get(u).set(v,w);
                mat.get(v).set(u,w);
            }
        }
        for(int k=0;k<A;k++){
            for(int i=0;i<A;i++){
                for(int j=0;j<A;j++){
                    if(mat.get(i).get(k)!=-1 && mat.get(k).get(j)!=-1){
                        int newDist=mat.get(i).get(k)+mat.get(k).get(j);
                        if(mat.get(i).get(j)==-1 || mat.get(i).get(j)>newDist)
                            mat.get(i).set(j,newDist);
                    }
                }
            }
        }
        ArrayList<Integer> res=new ArrayList<>();
        for(int i=0;i<C;i++){
            int u=G.get(i);
            int v=H.get(i);
            u--;
            v--;
            res.add(mat.get(u).get(v));
        }
        return res;
    }
}
