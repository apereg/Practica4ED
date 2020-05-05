package ule.edi.recursiveList;

import java.util.NoSuchElementException;

public class UnorderedLinkedListImpl<T> extends AbstractLinkedListImpl<T> implements UnorderedListADT<T> {

	public UnorderedLinkedListImpl() {
		/* Lista vacia */
	}

	public UnorderedLinkedListImpl(T... v) {
		/* Se añaden los elementos en el mismo orden que estaban en v */
		for (T Vi : v) {
			addLast(Vi);
		}
	}

	@Override
	public void addFirst(T element) {
		/* Se lanzan las excepciones en los casos contemplados en la documentacion */
		if (element == null)
			throw new NullPointerException();

		/*
		 * Si la lista estaba vacia se inicializa el front, si no se añade antes del
		 * front linkeandolo a este
		 */
		if (this.isEmpty())
			this.front = new Node<T>(element);
		else {
			Node<T> aux = this.front;
			this.front = new Node<T>(element);
			this.front.next = aux;
		}
	}

	@Override
	public void addLast(T element) {
		/* Se lanzan las excepciones en los casos contemplados en la documentacion */
		if (element == null)
			throw new NullPointerException();

		/*
		 * Si la lista esta vacia es lo mismo añadir por el inicio que por el final, si
		 * no se llama al metodo recursivo
		 */
		if (this.isEmpty())
			this.addFirst(element);
		else
			this.addLastRec(this.front, element);
	}

	private void addLastRec(Node<T> node, T element) {
		/*
		 * Cuando se llega al final se devuelve el elemento, si no se avanza
		 * recursivamente hasta el final
		 */
		if (node.next == null)
			node.next = new Node<T>(element);
		else
			this.addLastRec(node.next, element);
	}

	@Override
	public void addBefore(T element, T target) {
		/* Se lanzan las excepciones en los casos contemplados en la documentacion */
		if (element == null || target == null)
			throw new NullPointerException();
		if (!this.contains(target))
			throw new NoSuchElementException();

		/* Llamada al metodo recursivo */
		this.addBeforeRec(this.front, element, target);
	}

	private void addBeforeRec(Node<T> node, T element, T target) {
		/* If solo para el primer elemento */
		if (node.elem.equals(target)) {
			this.addFirst(element);
		} else if (node.next.elem.equals(target)) {
			/* Si el siguiente es el objetivo se introduce en medio */
			Node<T> aux = new Node<T>(element);
			aux.next = node.next;
			node.next = aux;
		} else {
			/* Si no se avanza recursivamente (siempre va a estar el elemento) */
			this.addBeforeRec(node.next, element, target);
		}
	}

}
