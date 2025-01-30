// 31120kb 768ms

import java.io.*;
import java.util.*;

public class BOJ2143 {
    private static long solve(int T, int N, int[] A, int M, int[] B) {
        // 부배열이 무조건 이어진 부분 배열만 말하는 거였구나... LIS 풀고 와서 헷갈림 ㅠㅠ
        // 그럼 누적합 빠따죠
        int sum, idx;

        int[] prefixSumA = new int[N + 1];
        sum = 0;
        for (int i = 0; i < N; i++) {
            sum += A[i];
            prefixSumA[i + 1] = sum;
        }

        int[] subSumA = new int[N * (N + 1) / 2];
        idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j <= N; j++) {
                subSumA[idx++] = prefixSumA[j] - prefixSumA[i];
            }
        }

        int[] prefixSumB = new int[M + 1];
        sum = 0;
        for (int i = 0; i < M; i++) {
            sum += B[i];
            prefixSumB[i + 1] = sum;
        }

        int[] subSumB = new int[M * (M + 1) / 2];
        idx = 0;
        for (int i = 0; i < M; i++) {
            for (int j = i + 1; j <= M; j++) {
                subSumB[idx++] = prefixSumB[j] - prefixSumB[i];
            }
        }

        // 완전 탐색) N^2 * M^2 = 1000^4 = 10^12 = T.O.
        // 이분 탐색) 각 prefix sum 배열의 i~j번째를... 음... 
        // 1000 * 1000 의 부배열들의 합도 미리 구해두고, 이를 정렬한 후 이분 탐색을 할까?

        Arrays.sort(subSumA);
        Arrays.sort(subSumB);
        final int LEFT = 0, RIGHT = subSumB.length - 1;
        long answer = 0;
        for (int t1 : subSumA) {
            final int t2 = T - t1;
            int left, right;
            int leftmost, rightmost;

            left = LEFT;
            right = RIGHT;
            leftmost = RIGHT + 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (subSumB[mid] < t2) {
                    left = mid + 1;
                } else if (subSumB[mid] > t2) {
                    right = mid - 1;
                } else {
                    leftmost = Integer.min(mid, leftmost);
                    right = mid - 1;
                }
            }

            if (leftmost == RIGHT + 1) continue; // 아예 찾을 수 없음. 1개도 없다는 것.

            left = LEFT;
            right = RIGHT;
            rightmost = LEFT - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (subSumB[mid] < t2) {
                    left = mid + 1;
                } else if (subSumB[mid] > t2) {
                    right = mid - 1;
                } else {
                    rightmost = Integer.max(mid, rightmost);
                    left = mid + 1;
                }
            }

            answer += rightmost - leftmost + 1;
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        final int T = Integer.parseInt(br.readLine());

        final int N = Integer.parseInt(br.readLine());
        final int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

        final int M = Integer.parseInt(br.readLine());
        final int[] B = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) B[i] = Integer.parseInt(st.nextToken());

        System.out.println(solve(T, N, A, M, B));
    }
}
