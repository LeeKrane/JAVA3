package labors.labor04;

/**
 * @author LeeKrane
 */

public class Pair<X, Y>
{
	private X first;
	private Y second;
	
	Pair (X first, Y second)
	{
		this.setFirst(first);
		this.setSecond(second);
	}
	
	X first () { return first; }
	Y second () { return second; }
	private void setFirst (X first) { this.first = first; }
	void setSecond (Y second) { this.second = second; }
	public Pair<Y, X> flip () { return new Pair<>(second, first); }
}
