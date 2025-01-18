// 14452 KB / 108 ms

import java.io.*;
import java.util.*;

public class BOJ1477 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());
        final int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int n = 0; n < N; n++)
            pq.offer(Integer.parseInt(st.nextToken()));

        int[] distances = new int[N + 1];
        int prev = 0;
        for (int n = 0; n < N; n++) {
            int cur = pq.poll();
            distances[n] = cur - prev;
            prev = cur;
        }
        distances[N] = L - prev;

        // '간격'을 기준으로 탐색하는 것이 핵심이었다. 간격에 따른 휴게소 갯수를 계산하면 되는 것이었다...! 발상의 전환...
        int answer = L;
        int left = 1, right = L - 1;
        while (left <= right) {
            int length = left + (right - left) / 2;

            int count = 0;
            for (int distance : distances) {
                count += distance / length - 1 + (distance % length != 0 ? 1 : 0);
            }
            
            if (count <= M) {
                answer = Math.min(answer, length);
                right = length - 1;
            } else {
                left = length + 1;
            }
        }

        System.out.println(answer);
    }
}
