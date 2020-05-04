package ule.edi.recursiveList;

import ule.edi.exceptions.EmptyCollectionException;

public class OrderedLinkedListImpl<T extends Comparable<? super T>> extends AbstractLinkedListImpl<T>
		implements OrderedListADT<T> {

	public OrderedLinkedListImpl() {
		// Vacía
	}

	public OrderedLinkedListImpl(T... v) {
		// Añade todos los elementos del array 'v'
		for (T Vi : v) {
			add(Vi);
		}
	}

	@Override
	public void add(T element) {
		if (element == null)
			throw new NullPointerException();

		if (this.isEmpty()) 
			this.front = new Node<T>(element);
		else 
			this.addRec(this.front, element);
	}

	private void addRec(Node<T> node, T element) {
		if (node.next == null) {
			if(node.elem.compareTo(element) > 0) {
				Node<T> aux = new Node<T>(element);
				aux.next = node;
				this.front = aux;
			} else
				node.next = new Node<T>(element);
		} else {
			if(node.elem.compareTo(element) > 0) {
				Node<T> aux = new Node<T>(element);
				aux.next = node;
				this.front = aux;
			}
			else if (node.next.elem.compareTo(element) > 0) {
				Node<T> newNode = new Node<>(element);
				newNode.next = node.next;
				node.next = newNode;
			} else {
				this.addRec(node.next, element);
			}
		}
	}

	@Override
	public int removeDuplicates() throws EmptyCollectionException {
		if (this.isEmpty())
			throw new EmptyCollectionException("AbstractLinkedList");
		return this.removeDuplicatesRecOrdered(this.front);
	}

	private int removeDuplicatesRecOrdered(Node<T> node) {
		if (node.next == null)
			return 0;
		if (node.elem.compareTo(node.next.elem) == 0) {
			node.next = node.next.next;
			return 1 + removeDuplicatesRecOrdered(node);
		}

		return removeDuplicatesRecOrdered(node.next);
	}

}
