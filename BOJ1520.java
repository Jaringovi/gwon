// 36240KB 352ms
import java.io.*;
import java.util.*;

public class BOJ1520 {
    private static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static class Element implements Comparable<Element> {
        final int height;
        final int r, c;
        
        public Element(int height, int r, int c) {
            this.height = height;
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Element o) {
            return -Integer.compare(this.height, o.height);
        }
        // @Override
        // public String toString() {
        //     return "(" + this.r + ", " + this.c + ") : " + this.height;
        // }
    }
    public static void main(String[] args) throws IOException {
        // dp[r][c] = (0, 0)에서 (r, c)까지 경로의 갯수
        // 이게 가능한 이유가, 높이가 무조건 작아지는 경로들 밖에 존재하지 않기 때문.
        // dp[r][c] = 4방향 중 자기보다 높이 높은 곳의 dp 값...?
        // 근데 이제... 음... dp 값을 채울 때...
        // dp를 해야 하나?
        // 그냥 bfs 탐색을 하는데 이제 높이가 가장 높은 것부터 하면 되지 않을까?
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int R = Integer.parseInt(st.nextToken());
        final int C = Integer.parseInt(st.nextToken());

        final int[][] height = new int[R][C];
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                height[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        
        final int[][] memo = new int[R][C];
        final boolean[][] visited = new boolean[R][C];
        PriorityQueue<Element> pq = new PriorityQueue<>();
        memo[0][0] = 1;
        pq.offer(new Element(height[0][0], 0, 0));
        while (!pq.isEmpty()) {
            Element e = pq.poll();
            if (visited[e.r][e.c]) continue;
            visited[e.r][e.c] = true;
            // System.out.println(e.toString() + " = " + memo[e.r][e.c]);
            for (int[] dir : dirs) {
                int nr = e.r + dir[0];
                int nc = e.c + dir[1];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C || height[nr][nc] >= height[e.r][e.c] || visited[nr][nc]) continue;
                memo[nr][nc] += memo[e.r][e.c];
                pq.offer(new Element(height[nr][nc], nr, nc));
            }
        }
        System.out.println(memo[R - 1][C - 1]);
    }
}
