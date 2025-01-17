// 154564 KB |  460 ms

import java.io.*;
import java.util.*;

public class BOJ1941 {
    final static int FIVE = 5, SEVEN = 7;
    final static int[][] dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    static int ans = 0;
    private static void bfs(char[][] board, int count, boolean[][] visited, int cur) {
        // 7개를 골랐다면 유효성 검증하기
        if (count == SEVEN) {
            // 가장 왼쪽 위 칸 찾기

            int ir = 0, ic = 0;
            findInit: for (ir = 0; ir < FIVE; ir++) {
                for (ic = 0; ic < FIVE; ic++) {
                    if (visited[ir][ic] == true) break findInit;
                }
            }
            
            // dfs 시작
            boolean[][] visitedAgain = new boolean[FIVE][FIVE];
            int totalCount = 0, sCount = 0;
            Deque<int[]> queue = new LinkedList<>();
            queue.offer(new int[] {ir, ic});
            while (!queue.isEmpty()) {
                int[] pos = queue.poll();
                if (visitedAgain[pos[0]][pos[1]]) continue;
                visitedAgain[pos[0]][pos[1]] = true;
                totalCount++;
                if (board[pos[0]][pos[1]] == 'S') sCount++;

                for (int[] dir : dirs) {
                    int nr = pos[0] + dir[0];
                    int nc = pos[1] + dir[1];
                    if (nr < 0 || nr >= FIVE || nc < 0 || nc >= FIVE) continue;
                    if (!visited[nr][nc] || visitedAgain[nr][nc]) continue;
                    queue.offer(new int[] {nr, nc});
                }
            }
            if (totalCount == SEVEN && sCount >= 4) ans++;
            return;
        }

        // 안 골랐다면
        else if (cur < 25) {
            // 현재 숫자를 고르기
            visited[cur / FIVE][cur % FIVE] = true;
            bfs(board, count + 1, visited, cur + 1);

            // 현재 숫자를 안고르기
            visited[cur / FIVE][cur % FIVE] = false;
            bfs(board, count, visited, cur + 1);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        char[][] board = new char[FIVE][FIVE];
        for (int r = 0; r < FIVE; r++) {
            String line = br.readLine();
            for (int c = 0; c < FIVE; c++) {
                board[r][c] = line.charAt(c);
            }
        }
        // 이 중 연결된 7명을 고르되, 4명 이상이 S여야 함
        // 25 C 7 = ~= 48만
        // 각각의 유효성 검증 = O(7)
        // 350만 - 해볼만하다!

        // 0 ~ 24의 숫자 중 7개를 고르는 모든 경우의 수를 만들자
        bfs(board, 0, new boolean[FIVE][FIVE], 0);

        System.out.println(ans);
    }
}
