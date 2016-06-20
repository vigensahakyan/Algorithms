package com.dynamic.programming;

public class CommonLongestSubsequence {

	int c[][];
	int b[][];

	public void LCS(int[] x, int[] y) {
		c = new int[x.length + 1][y.length + 1];
		b = new int[x.length + 1][y.length + 1];
		for (int i = 0; i <= x.length; i++) {
			c[i][0] = 0;
		}
		for (int i = 0; i <= y.length; i++) {
			c[0][i] = 0;
		}

		for (int i = 1; i <= x.length; i++) {
			for (int j = 1; j <= y.length; j++) {
				if (x[i - 1] == y[j - 1]) {
					c[i][j] = c[i - 1][j - 1] + 1;
					b[i][j] = 1; // by diagonal
				} else if (c[i - 1][j] >= c[i][j - 1]) {
					c[i][j] = c[i - 1][j];
					b[i][j] = 2; // see up
				} else {
					c[i][j] = c[i][j - 1];
					b[i][j] = 3; // see left
				}
			}
		}
		PrintSolution(x.length, y.length, x, y);

	}

	public void PrintSolution(int m, int n, int[] x, int[] y) {
		if (m == 1 && n == 1) {
			return;
		} else {
			if (b[m][n] == 1) {
				PrintSolution(m - 1, n - 1, x, y);
				System.out.print(x[m - 1] + ", ");
			} else if (b[m][n] == 2) {
				PrintSolution(m - 1, n, x, y);
			} else if (b[m][n] == 3) {
				PrintSolution(m, n - 1, x, y);
			}

		}
	}

	public static void main(String[] args) {
		int[] x = { 1, 2, 4, 5, 7, 9, 13 };
		int[] y = { 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		new CommonLongestSubsequence().LCS(x, y);
	}

}
