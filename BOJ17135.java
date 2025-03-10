import java.io.*;
import java.util.*;

class BOJ17135 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int R = Integer.parseInt(st.nextToken());
        final int C = Integer.parseInt(st.nextToken());
        final int D = Integer.parseInt(st.nextToken());

        int[][] map = new int[R + 1][C];
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        final int[] a = {0, 1, 2}; // 궁수 위치의 c값 (column)
        while (true) {
            for (int t = 0; t < R; t++) {
                final int ar = R - t; // 궁수가 위로 올라가는 느낌. 또한 그 위의 적들만 공격 가능.
                final Set<Integer> targets = new HashSet<>();
                archer: for (final int ac : a) {
                    for (int d = 1; d < D; d++) {
                        for (int step = 1; step <= d; step++) {
                            for (int r = ar - 1; r > ar - step; r--) {
                                // TODO
                            }
                            // r = ar - d
                            if (map[ar - d][ac] == 1) {
                                answer++;
                                map[ar - d][ac] = -1;
                            }
                        }
                    }
                }
            }

            for (int i = a.length - 1; i != 0; i--) {
                a[i]++;
                if (a[i] == C - i) {
                    a[i - 1]++;
                    a[i] = a[i - 1] + 1;
                }
            }
            if (a[0] == C - a.length + 1) break;
        }
    }
}