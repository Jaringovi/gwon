// 15500 KB / 124 ms

import java.io.*;
import java.util.*;


public class BOJ14728 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int N = Integer.parseInt(st.nextToken());
        final int T = Integer.parseInt(st.nextToken());

        // 0/1 knapsack
        int[] dp = new int[T + 1]; // dp[i] = 무게 i에서 최대 가치

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            final int weight = Integer.parseInt(st.nextToken());
            final int value = Integer.parseInt(st.nextToken());

            for (int cur = T; cur >= weight; cur--) {
                dp[cur] = Math.max(dp[cur], dp[cur - weight] + value);
            }
        }

        int answer = 0;
        for (int cur = 0; cur <= T; cur++) {
            answer = Math.max(answer, dp[cur]);
        }

        System.out.println(answer);
    }
}
