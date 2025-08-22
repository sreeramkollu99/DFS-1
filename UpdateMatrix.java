// Time Complexity : O(m * n) — each cell is enqueued/dequeued at most once.
// Space Complexity : O(m * n) — for the queue in the worst case.
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
//
// Approach (Multi-source BFS):
// - Push all zero cells into a queue as starting points; mark all 1-cells as -1 (unvisited).
// - Perform BFS level by level; the current BFS "level" is the distance to the nearest zero.
// - When visiting a neighbor that is -1, set it to the current distance and enqueue it.
// - Increment distance after processing each level.

import java.util.*;

class UpdateMatrix {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] dirs = {{1,0},{0,1},{0,-1},{-1,0}};
        Queue<int[]> q = new LinkedList<>();

        // Initialize: enqueue all zeros; mark ones as -1 (unvisited)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    q.add(new int[]{i, j});
                } else {
                    mat[i][j] = -1; // unvisited 1
                }
            }
        }

        int dist = 1; // distance layer we are assigning to neighbors
        while (!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                int[] cur = q.poll();
                for (int[] d : dirs) {
                    int r = cur[0] + d[0];
                    int c = cur[1] + d[1];
                    if (r >= 0 && c >= 0 && r < m && c < n && mat[r][c] == -1) {
                        mat[r][c] = dist;
                        q.add(new int[]{r, c});
                    }
                }
            }
            dist++;
        }

        return mat;
    }

    // --- Main method for testing ---
    public static void main(String[] args) {
        UpdateMatrix sol = new UpdateMatrix();

        int[][] mat1 = {
                {0,0,0},
                {0,1,0},
                {1,1,1}
        };
        System.out.println("Example 1:");
        print(sol.updateMatrix(copy(mat1))); // expected:
        // 0 0 0
        // 0 1 0
        // 1 2 1

        int[][] mat2 = {
                {0,0,0},
                {0,1,0},
                {1,1,1},
                {1,1,0}
        };
        System.out.println("\nExample 2:");
        print(sol.updateMatrix(copy(mat2)));

        int[][] mat3 = {
                {1,1,1},
                {1,1,1},
                {1,1,1}
        };
        System.out.println("\nAll ones (distances to nearest 0 after adding a border 0 somewhere would vary):");
        print(sol.updateMatrix(copy(mat3))); // remains all -1 if no zeros exist in input spec; LeetCode ensures at least one 0.
    }

    // Helpers
    private static int[][] copy(int[][] a) {
        int[][] b = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) b[i] = Arrays.copyOf(a[i], a[i].length);
        return b;
    }

    private static void print(int[][] g) {
        for (int[] row : g) {
            for (int v : row) System.out.print(v + " ");
            System.out.println();
        }
    }
}