package labors.labor04;

/**
 * @author LeeKrane
 */

public class PairMap <K, V> implements IMap <K, V>
{
	private java.util.LinkedList<Pair<K, V>> list = new java.util.LinkedList<>();
	
	@Override
	public V put (K key, V value)
	{
		if (key != null)
		{
			V oldVal;
			for (int i = 0; i < size(); i++)
			{
				if (list.get(i).first().equals(key))
				{
					oldVal = list.get(i).second();
					list.get(i).setSecond(value);
					return oldVal;
				}
			}
			list.add(new Pair<>(key, value));
		}
		
		else throw new NullPointerException();
		return null;
	}
	
	@Override
	public V get (K key)
	{
		for (int i = 0; i < size(); i++) { if (list.get(i).first() == key) return list.get(i).second(); }
		return null;
	}
	
	@Override
	public int size () { return list.size(); }
}