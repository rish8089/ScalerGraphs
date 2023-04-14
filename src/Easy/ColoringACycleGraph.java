package Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ColoringACycleGraph {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A=Integer.parseInt(br.readLine());
        System.out.println(solve(A));
    }
    public static int solve(int A) {
        return A%2==0?2:3;
    }
}
