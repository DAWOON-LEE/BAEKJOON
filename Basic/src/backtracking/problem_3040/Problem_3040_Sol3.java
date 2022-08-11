package backtracking.problem_3040;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_3040_Sol3 {
	private static final int IDENTIFIER = 100;

	private static int[] nums;
	private static int[] dwarfs;

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		nums = new int[9];
		dwarfs = new int[7];

		for (int i = 0; i < 9; i++) {
			nums[i] = Integer.parseInt(input.readLine());
		}

		setDwarfs(0, 0, 0);
	}

	private static void setDwarfs(int start, int cnt, int sum) {
		if (cnt == 7) {
			if (sum != IDENTIFIER) {
				return;
			}

			print();
			return;
		}

		for (int i = start; i < 9; i++) {
			dwarfs[cnt] = nums[i];
			setDwarfs(i + 1, cnt + 1, sum + nums[i]);
		}
	}

	private static void print() {
		StringBuilder answer = new StringBuilder();

		for (int cur : dwarfs) {
			answer.append(cur).append('\n');
		}

		System.out.println(answer);
	}
}
