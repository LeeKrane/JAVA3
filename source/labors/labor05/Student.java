package labors.labor05;

import java.util.*;

/**
 * @author LeeKrane
 */

public class Student implements Comparable<Student>
{
	private String name;
	private String vorname;
	private int matrikelnummer;
	private static List<Integer> verwendeteMatrikelnummern = new LinkedList<>();
	
	public static void main (String[] args)
	{
		Set<Student> students = new TreeSet<>();
		students.add(new Student("Muster", "Thomas", 123456));
		students.add(new Student("Herbert", "Franz", 111111));
		students.add(new Student("Malt", "David R.", 323232));
		
		System.out.println(verwendeteMatrikelnummern);
		System.out.println(students);
	}
	
	Student (String name, String vorname, int matrikelnummer)
	{
		if (name == null || vorname == null || name.equals("") || vorname.equals("") || verwendeteMatrikelnummern.contains(matrikelnummer) || !(matrikelnummer >= 100000 && matrikelnummer < 1000000))
			throw new IllegalArgumentException();
		
		this.name = name;
		this.vorname = vorname;
		this.matrikelnummer = matrikelnummer;
		verwendeteMatrikelnummern.add(this.matrikelnummer);
	}
	
	@Override
	public int compareTo (Student other) { return Comparator.comparing(Student::getName).thenComparing(Student::getVorname).compare(this, other); }
	private String getName () { return name; }
	private String getVorname () { return vorname; }
	
	@Override
	public String toString () { return "Student{Name -> '" + name + "', Vorname -> '" + vorname + "', Matrikelnummer -> '" + matrikelnummer + "'}"; }
	
	@Override
	public boolean equals (Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Student student = (Student) o;
		return matrikelnummer == student.matrikelnummer &&
				Objects.equals(name, student.name) &&
				Objects.equals(vorname, student.vorname);
	}
	
	@Override
	public int hashCode () { return Objects.hash(name, vorname, matrikelnummer); }
}
