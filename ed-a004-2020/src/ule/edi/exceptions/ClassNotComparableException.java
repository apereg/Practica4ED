package ule.edi.exceptions;

public class ClassNotComparableException extends Exception{

	private static final long serialVersionUID = 4468275233875850540L;
	
	public ClassNotComparableException(String nameCollection) {
		super("La colección" +nameCollection+ " tiene objetos no comparables");
	}
	
}

