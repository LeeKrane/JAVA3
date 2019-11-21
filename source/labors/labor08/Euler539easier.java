package labors.labor08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author LeeKrane
 */

public class Euler539easier {
	public static void main (String[] args) {
		System.out.println("getP(1) -> " + getP(1));
		System.out.println("getP(9) -> " + getP(9));
		System.out.println("getP(1000) -> " + getP(1000));
		System.out.println("getS(1000) -> " + getS(1000));
	}
	
	private static List<Integer> getList (int n) {
		if (n < 1) throw new IllegalArgumentException("The variable n has to be 1 or higher!");
		List<Integer> integerList = new ArrayList<>();
		for (int i = 1; i <= n; i++) integerList.add(i);
		return integerList;
	}
	
	private static int getP (int n) {
		List<Integer> integerList = getList(n);
		boolean leftToRight = true;
		
		while (integerList.size() > 1) {
			if (leftToRight) integerList.sort(Comparator.reverseOrder());
			else Collections.sort(integerList);
			for (int i = integerList.size() - 1; i >= 0; i -= 2) integerList.remove(i);
			leftToRight = !leftToRight;
		}
		return integerList.get(0);
	}
	
	private static long getS (int n) {
		long ret = 0;
		for (int i = 1; i <= n; i++) ret += getP(i);
		return ret;
	}
}