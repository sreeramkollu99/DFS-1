// Time Complexity : O(m * n) in the worst case — we may visit each pixel once.
// Space Complexity : O(m * n) in the worst case due to recursion stack (for large connected areas).
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Be careful to avoid infinite loops when new color == old color.
//
// Approach (DFS):
// - If the starting pixel already has the target color, return immediately.
// - Otherwise, remember the oldColor at (sr, sc). DFS from (sr, sc) and recolor any pixel that matches oldColor.
// - Recurse to 4-directional neighbors (up, down, left, right) as long as they are in-bounds and match oldColor.

import java.util.*;

class FloodFill {
    int[][] dirs = {{1,0},{0,1},{0,-1},{-1,0}};
    int m, n;

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        m = image.length;
        n = image[0].length;

        // If starting cell already has the target color, no work needed
        if (image[sr][sc] == color) return image;

        int oldColor = image[sr][sc];
        dfs(image, sr, sc, color, oldColor);
        return image;
    }

    // DFS recoloring
    private void dfs(int[][] image, int r, int c, int newColor, int oldColor) {
        // Bounds and color check
        if (r < 0 || c < 0 || r >= m || c >= n) return;
        if (image[r][c] != oldColor) return;

        // Recolor current cell
        image[r][c] = newColor;

        // Explore neighbors
        for (int[] d : dirs) {
            dfs(image, r + d[0], c + d[1], newColor, oldColor);
        }
    }

    // --- Main method for testing ---
    public static void main(String[] args) {
        FloodFill ff = new FloodFill();

        int[][] img1 = {
                {1,1,1},
                {1,1,0},
                {1,0,1}
        };
        int[][] out1 = ff.floodFill(copy(img1), 1, 1, 2);
        System.out.println("Example 1:");
        print(out1);  // expected:
        // 2 2 2
        // 2 2 0
        // 2 0 1

        int[][] img2 = {
                {0,0,0},
                {0,0,0}
        };
        int[][] out2 = ff.floodFill(copy(img2), 0, 0, 0);
        System.out.println("\nExample 2 (no change expected):");
        print(out2);  // same as input

        int[][] img3 = {
                {0,0,1},
                {0,1,1},
                {1,1,0}
        };
        int[][] out3 = ff.floodFill(copy(img3), 0, 2, 3);
        System.out.println("\nExample 3:");
        print(out3);
    }

    // Helpers for testing/printing
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