package ule.edi.recursiveList;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
		return "(" + this.toStringRec(this.front, "") + ")";
	}

	private String toStringRec(Node<T> node, String actualString) {
		if (node == null)
			return actualString;

		return this.toStringRec(node.next, actualString + node.elem.toString() + " ");
	}

	@Override
	public boolean contains(T target) {
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
		return this.countRec(this.front, element, 0);
	}

	private int countRec(Node<T> actualFront, T target, int actual) {
		if (actualFront == null)
			return actual;
		if (actualFront.elem.equals(target))
			actual++;

		return this.countRec(actualFront.next, target, actual);
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
	public boolean isOrdered() {
		// TODO RECURSIVO
		return false;
	}

	@Override
	public T remove(T element) throws EmptyCollectionException {
		if (this.isEmpty())
			throw new EmptyCollectionException("AbstractLinkedList");
		if (element == null)
			throw new NullPointerException();
		return this.removeRec(this.front, element);

	}

	private T removeRec(Node<T> node, T element) {
		if (node.elem.equals(element))
			return element;
		else if (node.next == null)
			throw new NoSuchElementException();
		return this.removeRec(node.next, element);
	}

	@Override
	public T removeLast(T element) throws EmptyCollectionException {
		// TODO RECURSIVO
		return null;
	}

	@Override
	public boolean isEmpty() {
		return this.front != null;
	}

	@Override
	public int size() {
		return this.sizeRec(this.front, 0);
	}

	private int sizeRec(Node<T> node, int actualSize) {
		if (node == null)
			return actualSize;
		return this.sizeRec(node.next, ++actualSize);
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
		return "(" + this.toStringFromUntilRec(this.front, --from, --until, 0, "") + ")";
	}

	private String toStringFromUntilRec(Node<T> node, int from, int until, int actual, String actualString) {
		if (actual == until || node == null)
			return actualString;
		if (actual < from)
			return this.toStringFromUntilRec(node.next, from, until, ++actual, actualString);
		return this.toStringFromUntilRec(node.next, from, until, ++actual, actualString + node.elem.toString() + " ");
	}

	@Override
	public String toStringReverse() {
		return "(" + this.toStringReverseRec(this.front, "") + ")";
	}

	private String toStringReverseRec(Node<T> node, String actualToString) {
		if (node == null)
			return actualToString;
		return actualToString + node.elem.toString() + " ";
	}

	@Override
	public int removeDuplicates() throws EmptyCollectionException {
		if (this.isEmpty())
			throw new EmptyCollectionException("AbstractLinkedList");

		// TODO RECURSIVE
		// Implementar teniendo en cuenta que la lista está desordenada

		return this.removeDuplicatesRec(this.front, 0);
	}

	private int removeDuplicatesRec(Node<T> node, int elementRemoved) {
		if (node == null)
			return elementRemoved;
		// TODO hacer un metodo que busuqe la primera iteraccion ???
		return 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new IteratorImpl(this.front);
	}

}
