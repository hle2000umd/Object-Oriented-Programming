
import java.lang.IndexOutOfBoundsException;

public class RegularLinkedList<T extends Comparable<T>> 
implements Comparable<RegularLinkedList<T>> {
	protected Node head;
	
	// inner class
	protected class Node {
		protected T data;
		protected Node next;
		protected Node prev;
		
		Node(T d) {
			data = d;
			next = null;
		}
	}

	
	public void add(T newValue) {
		if(newValue == null) {
			throw new IllegalArgumentException("no bueno");
		}
		Node newNode = new Node(newValue);
		// since newNode is the last element in the linked list
		newNode.next = null;
		Node curr = head;
		
		if(head == null) {
			head = newNode;
			head.prev = null;
		} else {
			while(curr.next != null) {
				curr = curr.next;
			}
			curr.next = newNode;
			newNode.prev = curr;
		}
	}

	public int length() {
		int total = 0;
		Node curr = head;
		
		while(curr != null) {
			total ++;
			curr = curr.next;
		}
		return total;
	}

	public String toString() {
		Node curr = head;
		String result = new String();
		
		while(curr != null) {
			result += curr.data;
			if(curr.next != null)
				result += " ";
			curr = curr.next;
		}
		return result;
	}

	public void clear() {
		head = null;
	}

	public int numTimesValueIsPresent(T value) {
		if(value == null) {
			throw new IllegalArgumentException("no bueno");
		}	
		Node curr = head;
		int result = 0;
		
		while(curr != null) {
			if(curr.data.compareTo(value) == 0) 
				result++;
			curr = curr.next;
		}
		return result;
	}
	
	public int indexOfValue(T value) {
		if(value == null) {
			throw new IllegalArgumentException("no bueno");
		}	
		Node curr = head;
		int idx = 0;
		
		while(curr != null) {
			if(curr.data.compareTo(value) == 0) {
				return idx;
			}
			idx++;
			curr = curr.next;
		}
		return -1;
	}

	public T valueAtIndex(int position) throws IndexOutOfBoundsException {
		if(position < 0 || position > length()) {
			throw new IndexOutOfBoundsException("no bueno");
		}
		int idx = 0;
		Node curr = head;
		T result = null;
		
		while(curr != null) {
			if(idx == position) {
				result = curr.data;
			}		
			idx++;
			curr = curr.next;
		}
		return result;
		
	}

	public void removeValuesInRange(int fromPos, int toPos) 
			throws IndexOutOfBoundsException {
		if(fromPos < 0 || toPos < 0 || fromPos > length() 
				|| toPos > length()) {
			throw new IndexOutOfBoundsException("no bueno");
		}
		if(fromPos > toPos) throw new IllegalArgumentException("no bueno");
		Node curr = head, from = null, to = null;
		int idx = 0;
		
		while(curr != null) {
			if(idx == fromPos) {
				from = curr;
			} 
			if(idx == toPos) {
				to = curr;
			}
			idx++;
			curr = curr.next;
		}
		if(from != null && to != null) {
			// if fromPos is the beginning of list, toPos to the end of list
			if(from == head && to.next == null) {
				clear();
			} else {
				from.prev.next = to.next;
			}
		}
	}
	
	public int compareTo(RegularLinkedList<T> otherList) {
		int flag = 0;
		Node curr1 = this.head;
		Node curr2 = otherList.head;

		while (curr1 != null && curr2 != null) {
			if (curr1.data.compareTo(curr2.data) == 0) {
				flag = 0;
			} else if (curr1.data.compareTo(curr2.data) < 0) {
				return -1;
			} else {
				return 1;
			}
			curr1 = curr1.next;
			curr2 = curr2.next;
		}
		if(flag == 0 && (this.length() > otherList.length())) {
			flag = 1;
		} else if (flag == 0 && (this.length() < otherList.length())) {
			flag = -1;
		} 
		return flag;
	}
}
