package ule.edi.recursiveList;

import ule.edi.exceptions.EmptyCollectionException;

public class OrderedLinkedListImpl<T extends Comparable<? super T>> extends AbstractLinkedListImpl<T>
		implements OrderedListADT<T> {

	public OrderedLinkedListImpl() {
		/* Lista vacía */
	}

	public OrderedLinkedListImpl(T... v) {
		/* Se añaden todos los elementos del array 'v' */
		for (T Vi : v) {
			add(Vi);
		}
	}

	@Override
	public void add(T element) {
		/* Se lanzan las excepciones en los casos contemplados en la documentacion */
		if (element == null)
			throw new NullPointerException();

		/*
		 * Si la lista esta vacia se modifica el front si no se llama al metodo
		 * recursivo
		 */
		if (this.isEmpty())
			this.front = new Node<T>(element);
		else
			this.addRec(this.front, element);
	}

	private void addRec(Node<T> node, T element) {
		if (node.next == null) {
			/* Si el siguiente es nulo ya no se sigue */
			if (node.elem.compareTo(element) > 0) {
				/*
				 * Que el anterior sea mayor esta contemplado siempre menos en un caso, el
				 * primero
				 */
				Node<T> aux = new Node<T>(element);
				aux.next = node;
				this.front = aux;
			} else {
				/* Se añade al final de la lista */
				node.next = new Node<T>(element);
			}
		} else {
			if (node.elem.compareTo(element) > 0) {
				/* Si el elemento es mayor se mete detras */
				Node<T> aux = new Node<T>(element);
				aux.next = node;
				this.front = aux;
			} else if (node.next.elem.compareTo(element) > 0) {
				/* Si no es mayor el elemento pero si el siguiente se mete entre medias */
				Node<T> newNode = new Node<>(element);
				newNode.next = node.next;
				node.next = newNode;
			} else {
				/* Si no se cumple nada se avanza recursivamente al siguiente nodo */
				this.addRec(node.next, element);
			}
		}
	}

	@Override
	public int removeDuplicates() throws EmptyCollectionException {
		/* Se lanzan las excepciones en los casos contemplados en la documentacion */
		if (this.isEmpty())
			throw new EmptyCollectionException("AbstractLinkedList");

		/* Llamada al metodo recursivo */
		return this.removeDuplicatesRecOrdered(this.front);
	}

	private int removeDuplicatesRecOrdered(Node<T> node) {
		/* Si el siguiente es nulo ya se llego al final */
		if (node.next == null)
			return 0;
		/* Si el nodo es el mismo que el siguiente */
		if (node.elem.compareTo(node.next.elem) == 0) {
			/* Se elimina el siguiente */
			node.next = node.next.next;
			/* Se vuelve a llamar con el mismo nodo para comprobar el nuevo siguiente */
			return 1 + removeDuplicatesRecOrdered(node);
		}

		/* Si no se cumple se avanza recursivamente al siguiente nodo */
		return removeDuplicatesRecOrdered(node.next);
	}

}
