package ule.edi.recursiveList;

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

		this.addRec(this.front, element);

	}

	private void addRec(Node<T> node, T element) {
		if (node.next == null) {
			Comparable<? super T> nodeC = node.elem;
			if (nodeC.compareTo(element) > 0) {
				this.front.next = this.front;
				this.front = new Node<T>(element);
			} else {
			node.next = new Node<T>(element);
			}
		} else {
			Comparable<? super T> nodeC = node.elem;
			Comparable<? super T> nodeCNext = node.next.elem;
			if (nodeC.compareTo(element) > 0) {
				this.front.next = this.front;
				this.front = new Node<T>(element);
			} else if (nodeC.compareTo(element) <= 0 && nodeCNext.compareTo(element) > 0) {
				Node<T> newNode = new Node<T>(element);
				newNode.next = node.next;
				node.next = newNode;
			} else {		
				this.addRec(node.next, element);
			}
		}
	}

	@Override
	public int removeDuplicates() {
		return this.removeDuplicatesRec(this.front);
	}
	
	private int removeDuplicatesRec(Node<T> node) {
		if(node.next == null)
			return 0;
		Comparable<? super T> nodeC = node.elem;
		if(nodeC.compareTo(node.next.elem) == 0) {
			if(node.next.next != null)
				node.next = node.next.next;
			else
				node.next = null;
			return 1+removeDuplicatesRec(node.next);
		}
		return removeDuplicatesRec(node.next);
	}

}
