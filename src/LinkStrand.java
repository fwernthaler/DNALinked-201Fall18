/**
 * Efficient implementation IDnaStrand in terms of
 * complexity and memory usage.
 * 
 * This class implements IDnaStrand
 * 
 * @author Florian Wernthaler and Jordan McGilvery
 *
 */

public class LinkStrand implements IDnaStrand {
	
	/**
	 * A linked list node
	 * Has info (string) and points to the next
	 * node in the list
	 */
	private class Node {
		String info;
		Node next;
		public Node(String s) {
			info = s;
			next = null;
		}
	}

	private Node myFirst, myLast;
	private long mySize;
	private int myAppends;
	private int myIndex;
	private int myLocalIndex;
	private Node myCurrent;
	
	/**
	 * Constructs LinkStrand object by calling
	 * initialize method with specified parameter
	 * @param s is the string that is passed to the
	 * initialize method
	 */
	public LinkStrand(String s) {
		initialize(s);
	}
	
	/**
	 * Default constructor with an empty string
	 */
	public LinkStrand() {
		this("");
	}

	/**
	 * Establishes class invariants
	 * @param source is info of node that is
	 * created
	 * @see IDnaStrand#initialize(java.lang.String)
	 */
	@Override
	public void initialize(String source) {
		Node firstnode = new Node(source);
		myFirst = firstnode;
		myLast = myFirst;
		mySize = source.length();
		myAppends = 0;
		myIndex = 0;
		myLocalIndex = 0;
		myCurrent = myFirst;
	}
	
	/**
	 * Returns total number of characters stored in
	 * all nodes in this LinkStrand
	 * @return this LinkStrand's size
	 * @see IDnaStrand#size()
	 */
	@Override
	public long size() {
		return mySize;
	}
	
	/**
	 * Returns number of times that the append
	 * method has been called on this LinkStrand
	 * @return the number of times append has been
	 * called
	 * @see IDnaStrand#getAppendCount()
	 */
	@Override
	public int getAppendCount() {
		return myAppends;
	}
	
	/**
	 * Returns new LinkStrand to which the source
	 * parameter is passed
	 * @param source is the parameter passed to the
	 * LinkStrand constructor
	 * @return new LinkStrand initialized with
	 * source
	 * @see IDnaStrand#getInstance(java.lang.String)
	 */
	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}
	
	/**
	 * Appends new node with info dna to the end of
	 * this LinkStrand
	 * @param dna is the string appended to this
	 * LinkStrand
	 * @return this LinkStrand after dna hs been
	 * appended
	 * @see IDnaStrand#append(java.lang.String)
	 */
	@Override
	public IDnaStrand append(String dna) {
		Node newnode = new Node(dna);
		myLast.next = newnode;
		myLast = newnode;
		mySize = mySize + dna.length();
		myAppends++;
		return this;
	}
	
	/**
	 * Creates a new LinkStrand with nucleotides in
	 * the reverse order of this LinkStrand
	 * New LinkStrand same number of nodes as this
	 * LinkStrand; each new node contains reversed
	 * string of corresponding node in this
	 * LinkStrand
	 * @return reverse LinkStrand
	 * @see IDnaStrand#reverse()
	 */
	@Override
	public IDnaStrand reverse() {
		LinkStrand original = this;
		LinkStrand reversed = new LinkStrand();

		Node list = original.myFirst;

		while (list != null) {
			StringBuilder reversetext = new StringBuilder(list.info);
			String reversedtext = reversetext.reverse().toString();

			LinkStrand first = new LinkStrand(reversedtext);

			Node n = reversed.myFirst;
			while (n != null) {
				first.append(n.info);
				n = n.next;
			}
			reversed = first;
			list = list.next;
		}

		reversed.mySize = mySize;
		reversed.myAppends = myAppends;

		return reversed;
	}
	
	/**
	 * If index is valid, finds character at this
	 * index
	 * Stores location of index that was called
	 * most recently so that it is possible to
	 * continue from there on a subsequent call
	 * @param index specifies which character will
	 * be returned
	 * @return character at index
	 * @throws IndexOutOfBoundsException if index
	 * is less than 0 or greater than the number of
	 * characters in this LinkStrand
	 * @see IDnaStrand#charAt(int)
	 */
	@Override
	public char charAt(int index) throws IndexOutOfBoundsException {
		int count = myIndex;
		int dex = myLocalIndex;
		Node list = myCurrent;

		if (index > mySize - 1 || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		if (index < count) {
			count = 0;
			dex = 0;
			list = myFirst;
		}

		while (count != index) {
			count++;
			dex++;
			if (dex >= list.info.length()) {
				dex = 0;
				list = list.next;
			}
		}

		myIndex = count;
		myLocalIndex = dex;
		myCurrent = list;

		return list.info.charAt(dex);
	}
	
	/**
	 * Returns string representation of the DNA
	 * in this LinkStrand; a concatenation of the
	 * info in each node of this LinkStrand
	 * @return string of the DNA in this LinkStrand
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		Node n = myFirst;
		StringBuilder build = new StringBuilder();

		while (n != null) {
			build.append(n.info);
			n = n.next;
		}

		return build.toString();
	}
}