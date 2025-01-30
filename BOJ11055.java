// 14892KB 128ms

import java.io.*;
import java.util.*;

public class BOJ11055 {
    static private int solve(int N, int[] A) {
        // 1) 완전탐색: 2^1000 = 1000^100 = 10^300 = T.O.

        // 2) 아무튼 DP (1)
        // 일단 O(N^2) 순회 하면서 각 원소보다 처음으로 큰 오른쪽 원소의 index를 찾아두기
        // 이렇게 하면 i번째 원소부터 시작하는 가장 큰 부분 수열 찾기 가능
        // int[] nextIdx = new int[N]; // 0 means no nextIdx

        // for (int i = 0; i < N; i++) {
        //     int prev = A[i];
        //     for (int j = i + 1; j < N; j++) {
        //         int cur = A[j];
        //         if (prev < cur) {
        //             nextIdx[i] = j;
        //             break;
        //         }
        //     }
        // }

        // int answer = 0;
        // boolean[] visited = new boolean[N];
        // for (int i = 0; i < N; i++) {
        //     if (visited[i]) continue;
        //     int sum = 0;
        //     int idx = i;
        //     while (true) {
        //         visited[idx] = true;
        //         sum += A[idx];
        //         if (nextIdx[idx] != 0) {
        //             idx = nextIdx[idx];
        //         } else {
        //             break;
        //         }
        //     }
        //     answer = Integer.max(answer, sum);
        // }

        // 3) 아무튼 DP (2)
        int[] dp = new int[N]; // dp[i] = i를 포함한, i번째까지 LIS 중 최대 합
        for (int i = 0; i < N; i++) {
            dp[i] = A[i];
            for (int j = i - 1; j >= 0; j--) {
                if (A[j] >= A[i]) continue;
                dp[i] = Integer.max(dp[i], dp[j] + A[i]);
            }
        }

        return Arrays.stream(dp).reduce(Integer::max).getAsInt();
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(br.readLine());
        final int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve(N, A));
    }
}