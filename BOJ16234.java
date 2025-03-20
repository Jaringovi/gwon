// 300700KB 604ms

import java.io.*;
import java.util.*;

public class BOJ16234 {
    private static final int[][] dirs = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 완전탐색:
        // 각 칸마다 bfs 돌면서 연합 찾기 & back tracking 하면서 업데이트 하기
        // O(N^2)? 결국 모든 칸들 한번씩 탐색 & 업데이트니깐!

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int L = Integer.parseInt(st.nextToken());
        final int R = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] isVisited;
        boolean isUpdated;
        int answer = -1;
        do {
            answer++;
            // System.out.println("=======" + answer + "========");
            // for (int[] row : board) {
            //     System.out.println(Arrays.toString(row));
            // }

            isUpdated = false;
            isVisited = new boolean[N][N]; // reset
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (isVisited[r][c])
                        continue;
                    // start dfs & backtracking
                    // System.out.println("start at: [" + r + ", " + c + "]");
                    List<int[]> queue = new ArrayList<>();
                    int index = 0;
                    int sum = 0;
                    queue.add(new int[] { r, c });
                    isVisited[r][c] = true;
                    while (index != queue.size()) {
                        int[] cur = queue.get(index++);
                        // if (isVisited[cur[0]][cur[1]])
                        //     continue;
                        isVisited[cur[0]][cur[1]] = true;
                        // System.out.println(sum + "+=" + board[cur[0]][cur[1]]);
                        sum += board[cur[0]][cur[1]];
                        for (int[] dir : dirs) {
                            int nr = cur[0] + dir[0];
                            int nc = cur[1] + dir[1];
                            if (nr < 0 || nr >= N || nc < 0 || nc >= N || isVisited[nr][nc])
                                continue;
                            int diff = Math.abs(board[cur[0]][cur[1]] - board[nr][nc]);
                            if (diff < L || diff > R)
                                continue;
                            queue.add(new int[] { nr, nc });
                            isVisited[nr][nc] = true;
                        }
                    }
                    if (queue.size() == 1)
                        continue;

                    final int val = sum / queue.size();
                    // System.out.println("===update starts===");
                    for (int[] pos : queue) {
                        // System.out.println(Arrays.toString(pos) + " is updated to " + val);
                        board[pos[0]][pos[1]] = val;
                    }
                    isUpdated = true;
                }
            }
        } while (isUpdated);

        System.out.println(answer);
    }
}
