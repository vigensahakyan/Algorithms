package com.dynamic.programming;

public class CutRod {

	int[] p = { 0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30 };
	public static int numOper = 0;

	// recursive it has exponential time as it decide each of overlapping
	// subproblems several time
	public int cutRod(int n) {
		numOper++;
		if (n == 0) {
			return 0;
		}

		int q = Integer.MIN_VALUE;
		for (int i = 1; i <= (n); i++) {
			q = Math.max(q, p[i] + cutRod((n) - i));
		}
		return q;
	}

	public int MemorizedCutRod(int n) {
		int[] r = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			r[i] = Integer.MIN_VALUE;
		}

		return MemorizeCutRodAux(n, r);

	}

	private int MemorizeCutRodAux(int n, int[] r) {
		numOper++;
		if (r[n] >= 0) {
			return r[n];
		}
		if (n == 0) {
			return 0;
		}

		int q = Integer.MIN_VALUE;
		for (int i = 1; i <= (n); i++) {
			q = Math.max(q, p[i] + MemorizeCutRodAux((n) - i, r));
		}
		r[n] = q;

		return q;
	}

	public int BottomUpCutRod(int n) {
		int[] r = new int[n + 1];
		int[] s = new int[n + 1];
		r[0] = 0;
		for (int j = 1; j <= (n); j++) {
			int q = Integer.MIN_VALUE;
			for (int i = 1; i <= (j); i++) {
				numOper++;
				if (q < (p[i] + r[j - i])) {
					q = p[i] + r[j - i];
					s[j] = i;
				}
				// q = Math.max(q, p[i] +r[j-i]);
			}
			r[j] = q;
		}
		int k = n;
		while (n > 0) {
			System.out.println(s[n]);
			n = n - s[n];
		}
		return r[k];

	}

	public static void main(String[] args) {

		System.out.println(new CutRod().BottomUpCutRod(9) + " " + CutRod.numOper);
	}
}
