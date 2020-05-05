package ule.edi.recursiveList;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.ClassNotComparableException;
import ule.edi.exceptions.EmptyCollectionException;

public class AbstractLinkedListImpl<T> implements ListADT<T> {

	// Estructura de datos, lista simplemente enlazada
	//
	// Este es el primer nodo de la lista
	protected Node<T> front = null;

	// Clase para cada nodo en la lista
	protected class Node<T> {

		T elem;

		Node<T> next;

		Node(T element) {
			this.elem = element;
			this.next = null;
		}

		@Override
		public String toString() {
			return "(" + elem + ")";
		}

	}

	private class IteratorImpl implements Iterator<T> {
		Node<T> node;

		public IteratorImpl(Node<T> node) {
			this.node = node;
		}

		@Override
		public boolean hasNext() {
			return this.node != null;
		}

		@Override
		public T next() {
			if (this.node == null)
				throw new NoSuchElementException();

			T elemReturn = this.node.elem;
			this.node = this.node.next;
			return elemReturn;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public String toString() {
		/* Llamada al metodo recursivo */
		return "(" + this.toStringRec(this.front);
	}

	private String toStringRec(Node<T> node) {
		/* Si ya se acabo se introduce el parentesis */
		if (node == null)
			return ")";
		/*
		 * Si no se vuelve a llamar avanzando al siguiente insertando el elemento actual
		 */
		return node.elem.toString() + " " + this.toStringRec(node.next);
	}

	@Override
	public boolean contains(T target) {
		if(target == null)
			throw new NullPointerException();
		/* Llamada al metodo recursivo */
		return this.containsRec(this.front, target);
	}

	private boolean containsRec(Node<T> node, T target) {
		if (node == null)
			return false;
		if (node.elem.equals(target))
			return true;

		return this.containsRec(node.next, target);
	}

	@Override
	public int count(T element) {
		return this.countRec(this.front, element);
	}

	private int countRec(Node<T> actualFront, T target) {
		if (actualFront == null)
			return 0;
		if (actualFront.elem.equals(target))
			return 1 + this.countRec(actualFront.next, target);

		return this.countRec(actualFront.next, target);
	}

	@Override
	public T getLast() throws EmptyCollectionException {
		if (this.isEmpty())
			throw new EmptyCollectionException("AbstractLinkedListImpl");

		return this.getLastRec(this.front);
	}

	private T getLastRec(Node<T> node) {
		if (node.next == null)
			return node.elem;

		return this.getLastRec(node.next);
	}

	@Override
	public boolean isOrdered() throws ClassNotComparableException {
		/* Cualquier lista vacia esta ordenada */
		if (this.isEmpty())
			return true;

		/* Llamada al metodo recursivo */
		return this.isOrderedRec(this.front);
	}

	private boolean isOrderedRec(Node<T> node) throws ClassNotComparableException {
		/*
		 * Si el siguiente nodo es nulo y no ha saltado el false antes la lista esta
		 * ordenada
		 */
		if (node.next == null)
			return true;
		/*
		 * Se compara si estan ordenados comtemplando la excepcion de que no sean
		 * comparables
		 */
		try {
			@SuppressWarnings("unchecked") // Si esta comprobado por el catch pero aun así salta el aviso
			Comparable<T> nodeC = (Comparable<T>) node.elem;
			if (nodeC.compareTo(node.next.elem) > 0)
				return false;
		} catch (ClassCastException e) {
			/*
			 * He creado una nueva excepcion en el paquete en el caso que los objetos no
			 * sean comparables
			 */
			throw new ClassNotComparableException("AbstractLinkedListImpl");
		}
		/* Si estan ordenados y aun no es el final se comprueba el siguiente */
		return this.isOrderedRec(node.next);
	}

	@Override
	public T remove(T element) throws EmptyCollectionException {
		/* Se contemplan los casos para lanzar excepciones de la documentacion */
		if (this.isEmpty())
			throw new EmptyCollectionException("AbstractLinkedList");
		if (element == null)
			throw new NullPointerException();
		if (!this.contains(element))
			throw new NoSuchElementException();

		/* Llamada al metodo recursivo */
		return this.removeRec(this.front, element);
	}

	private T removeRec(Node<T> node, T element) {
		/* If solo para el primer elemento */
		if (node.elem.equals(element)) {
			if (this.front.next != null)
				this.front = this.front.next;
			else
				this.front = null;
			return element;
		}
		/*
		 * Este .next es seguro ya que siempre si el actual no es el siguiente va a ser
		 * porque la lista contiene el elemento
		 */
		if (node.next.elem.equals(element)) {
			/* Si no es el ultimo se linkea al siguiente */
			if (node.next.next != null)
				node.next = node.next.next;
			else
				node.next = null;
			return element;
		}
		/* Si aun no se encontro ni es el final se avanza al siguiente nodo */
		return this.removeRec(node.next, element);
	}

	@Override
	public T removeLast(T element) throws EmptyCollectionException{
		if(element == null)
			throw new NullPointerException();
		if(this.isEmpty())
			throw new EmptyCollectionException("AbstractLinkedList");
		if(!this.contains(element))
			throw new NoSuchElementException();
		/* Llamada al metodo recursivo */
		System.out.println("Se pide eliminar la ultima " +element.toString());
		int lastPos = this.findLastRec(this.front, element, 0, 0);
		System.out.println("Se va a eliminar la de la posicion " +(lastPos+2));
		return this.removeLastRec(this.front, element, 0, lastPos);
	}

	private int findLastRec(Node<T> node, T element, int actual, int pos) {
		
		if(node.next == null) {
			return pos;
		}
		if(node.next.elem.equals(element))
			pos = actual;
		
		return this.findLastRec(node.next, element, ++actual, pos);
	}

	private T removeLastRec(Node<T> node, T element, int actual, int lastPos) {
		if(actual < lastPos)
			return this.removeLastRec(node.next, element, ++actual, lastPos);
		T elemReturn = node.next.elem;
		if(node.next.next != null)
			node.next = node.next.next;
		else
			node.next = null;
		return elemReturn;
	}

	@Override
	public boolean isEmpty() {
		return this.front == null;
	}

	@Override
	public int size() {
		return this.sizeRec(this.front);
	}

	private int sizeRec(Node<T> node) {
		if (node == null)
			return 0;
		return 1 + this.sizeRec(node.next);
	}

	@Override
	public T getFirst() throws EmptyCollectionException {
		if (this.isEmpty())
			throw new EmptyCollectionException("AbstractLinkedList");
		return this.front.elem;
	}

	@Override
	public String toStringFromUntil(int from, int until) {
		if (from <= 0 || until < from)
			throw new IllegalArgumentException();
		return "(" + this.toStringFromUntilRec(this.front, --from, --until, 0);
	}

	private String toStringFromUntilRec(Node<T> node, int from, int until, int actual) {
		if (actual == until+1)
			return ")";
		if (node == null)
			return "";
		if (actual < from)
			return this.toStringFromUntilRec(node.next, from, until, ++actual);
		return node.elem.toString() + " " + this.toStringFromUntilRec(node.next, from, until, ++actual);
	}

	@Override
	public String toStringReverse() {
		return this.toStringReverseRec(this.front) + ")";
	}

	private String toStringReverseRec(Node<T> node) {
		if (node == null)
			return "(";
		
		return this.toStringReverseRec(node.next) + node.elem.toString() + " ";
	}

	@Override
	public int removeDuplicates() throws EmptyCollectionException {
		if (this.isEmpty())
			throw new EmptyCollectionException("AbstractLinkedList");

		return this.removeDuplicatesRec(this.front);
	}

	private int removeDuplicatesRec(Node<T> node) throws EmptyCollectionException {
		if (node == null)
			return 0;
		System.out.println("Se entra con " +node.elem.toString());
		if(this.count(node.elem) > 1) {
			System.out.println("ESTA DUPLICAO");
			this.removeLast(node.elem);
			return 1 + this.removeDuplicatesRec(node.next);
		}
		return this.removeDuplicatesRec(node.next);
	}

	@Override
	public Iterator<T> iterator() {
		return new IteratorImpl(this.front);
	}

}
