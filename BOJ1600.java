// 95508KB 560ms
import java.io.*;
import java.util.*;

public class BOJ1600 {
    private static final int[][] horseDirs = {
            { -2, -1 }, { -2, 1 }, { -1, -2 }, { -1, 2 },
            { 1, -2 }, { 1, 2 }, { 2, -1 }, { 2, 1 }
    };
    private static final int[][] monkeyDirs = {
            { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 }
    };

    private static class E {
        final int r, c, k, steps;
        E(int r, int c, int k, int steps) {
            this.r = r;
            this.c = c;
            this.k = k;
            this.steps = steps;
        }

        @Override
        public String toString() {
            return "(" + r +", " + c + ") @ " + k + " = " + steps;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        final int K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        final int C = Integer.parseInt(st.nextToken());
        final int R = Integer.parseInt(st.nextToken());
        final int[][] board = new int[R][C];
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        if (C == 1 && R == 1) {
            System.out.println("0");
            return;
        }

        int[][][] dp = new int[R][C][K + 1]; // (r, c)까지 k번 점프해서 도착한 최소한의 시간
        // dfs & dp
        final Deque<E> queue = new LinkedList<>();
        queue.add(new E(0, 0, 0, 0)); // r, c, k, step
        while (!queue.isEmpty()) {
            E cur = queue.poll();
            // System.out.println(cur);
            if (dp[cur.r][cur.c][cur.k] != 0 && dp[cur.r][cur.c][cur.k] < cur.steps) continue;
            for (int[] dir : horseDirs) {
                int nr = cur.r + dir[0];
                int nc = cur.c + dir[1];
                int nk = cur.k + 1;
                int nsteps = cur.steps + 1;
                if (nr < 0 || nr >= R || nc < 0 || nc >= C || nk > K || board[nr][nc] == 1) continue;
                if (dp[nr][nc][nk] != 0 && dp[nr][nc][nk] <= nsteps) continue;
                dp[nr][nc][nk] = nsteps;
                queue.add(new E(nr, nc, nk, nsteps));
            }
            for (int[] dir : monkeyDirs) {
                int nr = cur.r + dir[0];
                int nc = cur.c + dir[1];
                int nk = cur.k;
                int nsteps = cur.steps + 1;
                if (nr < 0 || nr >= R || nc < 0 || nc >= C || board[nr][nc] == 1) continue;
                if (dp[nr][nc][nk] != 0 && dp[nr][nc][nk] <= nsteps) continue;
                dp[nr][nc][nk] = nsteps;
                queue.add(new E(nr, nc, nk, nsteps));
            }
        }

        int answer = -1;
        for (int steps : dp[R - 1][C - 1]) {
            if (steps == 0) continue;
            if (answer == -1) {
                answer = steps;
            } else {
                answer = Math.min(answer, steps);
            }
        }
        System.out.println(answer);
    }
}
