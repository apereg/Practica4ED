package ule.edi.recursiveList;

public interface OrderedListADT<T> extends ListADT<T> {

	/**
	 * Añade un elemento como de forma ordenada en la lista.
	 * <p>
	 * Si una lista l contiene (A B D )  y hacemos l.add("C") 
	 * la lista quedará (A B C D )
	 * 
	 * @param element el elemento a añadir
	 * 
	 * @throws NullPointerException si element es <code>null</code>
	 */
	void add(T element);

}
