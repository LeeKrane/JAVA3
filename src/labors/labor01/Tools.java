package labors.labor01;

/**
 * @author LeeKrane
 */

public class Tools
{
	public static void main (String[] args)
	{
		System.out.println(oddOrEven(new int[]{3,5,8,4,7,1}, true));
		System.out.println(oddOrEven(new int[]{3,5,8,4,7,1}, false));
		System.out.println(summe("ein"));
		System.out.println(summe("Informatik"));
	}
	
	private static int oddOrEven (int[] a, boolean even)
	{
		int ret = 0;
		
		for (int i = 0; i < a.length; i++)
		{
			if (even)
				ret += a[i] % 2 == 0 ? a[i] : 0;
			else
				ret += a[i] % 2 == 1 ? a[i] : 0;
		}
		
		return ret;
	}
	
	private static int summe (String s)
	{
		String str = s.toUpperCase();
		int sum = 0;
		
		for (int i = 0; i < str.length(); i++)
			sum += str.charAt(i) - 'A' + 1;
		
		return sum;
	}
}
