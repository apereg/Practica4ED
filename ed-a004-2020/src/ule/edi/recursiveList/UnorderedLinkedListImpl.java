package ule.edi.recursiveList;

import java.util.NoSuchElementException;


public class UnorderedLinkedListImpl<T> extends AbstractLinkedListImpl<T> implements UnorderedListADT<T> {

	public UnorderedLinkedListImpl() {
		//	Vacía
	}
	
	public UnorderedLinkedListImpl(T ... v) {
		//	Añadir en el mismo orden que en 'v'
		for (T Vi : v) {
			addLast(Vi);
		}
	}
	
	@Override
	public void addFirst(T element) {
		if(element == null)
			throw new NullPointerException();
     	if(this.isEmpty())
     		this.front = new Node<T>(element);
     	else {
     		Node<T> aux = this.front;
     		this.front = new Node<T>(element);
     		this.front.next = aux;
     	} 		
	}
	
	
	@Override
	public void addLast(T element) {
		if(element == null)
			throw new NullPointerException();
		if(this.isEmpty())
			this.addFirst(element);
		else
			this.addLastRec(this.front, element);	
	}	

	
	private void addLastRec(Node<T> node, T element) {
		if(node.next == null)
			node.next = new Node<T>(element);
		else
			this.addLastRec(node.next, element);
	}

	@Override
	public void addBefore(T element, T target) {
		if(element == null || target == null)
			throw new NullPointerException();
		if(!this.contains(target))
			throw new NoSuchElementException();
		this.addBeforeRec(this.front, element, target);	
	}

	private void addBeforeRec(Node<T> node, T element, T target) {
		if(node.elem.equals(target)) {
			this.addFirst(element);
		} else if(node.next.elem.equals(target)) {
				Node<T> aux = new Node<T>(element);
				aux.next = node.next;
				node.next = aux;
		} else {
			this.addBeforeRec(node.next, element, target);
		}
	}

		
}
