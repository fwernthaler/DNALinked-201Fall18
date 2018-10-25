public class LinkStrand implements IDnaStrand {
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

	public LinkStrand(String s) {
		initialize(s);
	}

	public LinkStrand() {
		this("");
	}
	
	@Override
	public void initialize(String source) {
		Node firstnode = new Node(source);
		myFirst = firstnode;
		myLast = myFirst;
		mySize = source.length();
		myAppends = 0;
	}
	
	@Override
	public long size() {
		return mySize;
	}
	
	@Override
	public IDnaStrand getInstance(String source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDnaStrand append(String dna) {
		Node newnode = new Node(dna);
		myLast.next = newnode;
		myLast = newnode;
		mySize = mySize + dna.length();
		myAppends++;
		return this;
	}

	@Override
	public IDnaStrand reverse() {
		StringBuilder copy = new StringBuilder(myFirst.info);
		copy.reverse();
		LinkStrand reversed = new LinkStrand(copy.toString());
		
		Node n = myFirst;
		n = n.next;
		Node nextnode = reversed.myFirst;
		
		while (n != null) {
			StringBuilder reversetext = new StringBuilder(n.info);
			n.info = reversetext.reverse().toString();
			
			reversed.myFirst = n;
			reversed.myFirst.next = nextnode;
			nextnode = n;
			n = n.next;
		}
		
		return reversed;
	}

	@Override
	public int getAppendCount() {
		return myAppends;
	}

	@Override
	public char charAt(int index) {
		// TODO Auto-generated method stub
		return 0;
	}
	
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