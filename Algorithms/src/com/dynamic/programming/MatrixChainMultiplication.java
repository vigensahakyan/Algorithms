package com.dynamic.programming;

public class MatrixChainMultiplication {

	int p[] = { 30, 35, 15, 5, 10, 20, 25 };
	int opt[][] = new int[p.length][p.length];
	int sol[][] = new int[p.length][p.length];

	public void MatrixChainOrder() {
		int n = p.length - 1;

		// initialize primitive case to o A1
		for (int i = 1; i <= n; i++) {
			opt[i][i] = 0;
		}

		for (int l = 2; l <= n; l++) {
			for (int i = 1; i <= (n - l + 1); i++) {
				int j = (i + l) - 1;
				opt[i][j] = Integer.MAX_VALUE;
				for (int k = i; k <= j - 1; k++) {
					int q = opt[i][k] + opt[k + 1][j] + p[i - 1] * p[k] * p[j];
					if (q < opt[i][j]) {
						opt[i][j] = q;
						sol[i][j] = k;
					}
				}
			}
		}
		PrintOptimalSolution(1, n);
	}

	public void PrintOptimalSolution(int i, int j) {
		if (i == j) {
			System.out.print("A" + i);
		} else {
			System.out.print("(");
			PrintOptimalSolution(i, sol[i][j]);
			PrintOptimalSolution(sol[i][j] + 1, j);
			System.out.print(")");
		}

	}

	public static void main(String[] args) {

		new MatrixChainMultiplication().MatrixChainOrder();
		System.out.println(" " + CutRod.numOper);
	}

}
