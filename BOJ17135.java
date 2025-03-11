// 18912KB 152ms

import java.io.*;
import java.util.*;

class BOJ17135 {
    private static class Position {
        private final int r, c;

        public Position(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object that) {
            if (this == that)
                return true;
            if (that instanceof Position) {
                Position pos = (Position) that;
                return (this.r == pos.r && this.c == pos.c);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int R = Integer.parseInt(st.nextToken());
        final int C = Integer.parseInt(st.nextToken());
        final int D = Integer.parseInt(st.nextToken());

        final int[][] map = new int[R][C];
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        final int[] archers = { 0, 1, 2 }; // 궁수 위치의 c값 (column)
        while (true) {
            final boolean[][] isDead = new boolean[R][C];
            int count = 0;
            for (int t = 0; t < R; t++) {
                final int ar = R - t; // 궁수가 위로 올라가는 느낌. 또한 그 위의 적들만 공격 가능.
                final Set<Position> targets = new HashSet<>();
                for (final int ac : archers) {
                    // d = 1 -> r = ar - 1 -> ar - 1 (c = ac)
                    // d = 2 -> r = ar - 1 (c = ac - 1, ac + 1) -> ar - 2 (c = ac)
                    // d = 3 -> r = ar - 1 (c = ac - 2, ac + 2) -> ar - 2 (c = ac - 1, ac + 1) ...
                    distance: for (int d = 1; d <= D; d++) {
                        int r, c;
                        for (c = ac - d + 1; c <= ac + d - 1; c++) {
                            if (c < 0) continue;
                            if (c >= C) break;
                            int dr = d - Math.abs(c - ac);
                            r = ar - dr;
                            if (r < 0) continue;
                            if (map[r][c] == 1 && !isDead[r][c]) {
                                targets.add(new Position(r, c));
                                break distance;
                            }
                        }
                    }
                }
                count += targets.size();
                for (Position pos : targets) {
                    isDead[pos.r][pos.c] = true;
                }
            }
            // System.out.println(Arrays.toString(archers) + "=" + count);
            answer = Math.max(answer, count);

            archers[2]++;
            if (archers[2] == C) {
                archers[1]++;
                archers[2] = archers[1] + 1;
            }
            if (archers[2] == C) {
                archers[0]++;
                archers[1] = archers[0] + 1;
                archers[2] = archers[1] + 1;
            }
            if (archers[2] == C)
                break;
        }

        System.out.println(answer);
    }
}