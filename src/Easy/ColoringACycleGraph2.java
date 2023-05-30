package Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ColoringACycleGraph2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        ColoringACycleGraph2 obj=new ColoringACycleGraph2();
        System.out.println(obj.solve(Integer.parseInt(br.readLine())));
    }
    public int solve(int A){
        return A%2==0?2:3;
    }
}
