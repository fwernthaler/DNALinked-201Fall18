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
	private int myIndex;
	private int myLocalIndex;
	private Node myCurrent;

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
		myIndex = 0;
		myLocalIndex = 0;
		myCurrent = myFirst;
	}

	@Override
	public long size() {
		return mySize;
	}

	@Override
	public int getAppendCount() {
		return myAppends;
	}

	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
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

	@Override
	public char charAt(int index) throws IndexOutOfBoundsException {
		int count = myIndex;
		int dex = myLocalIndex;
		Node list = myCurrent;

		if (index > mySize - 1) {
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
			if (list.next != null) {
				if (dex >= list.info.length()) {
					dex = 0;
					list = list.next;
				}
			}
		}

		myIndex = count;
		myLocalIndex = dex;
		myCurrent = list;

		return list.info.charAt(dex);
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