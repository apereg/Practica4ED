package ule.edi.model;

public class Person implements Comparable<Person>{

	private String name;
	private String nif;
	
	private int age;
	
	

	public Person(String nif, String name, int edad) {
        this.nif=nif;
		this.name = name;
		this.age = edad;
		
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "{"+nif + ", " + name + ", " + age + "}";
	}
	
    @Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		// Dos personas son iguales si son iguales sus nifs
    	return false;
	}



	@Override
	public int compareTo(Person o) {
		// TODO Auto-generated method stub
		// Las personas se comparan por la edad.
		// Este método debe devolver la diferencia de edad entre this y o
		return 0;
		
	}
	
}
