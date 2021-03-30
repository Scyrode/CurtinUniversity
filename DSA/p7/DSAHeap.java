public class DSAHeap {
    
    // CLASS FIELDS
    private DSAHeapEntry[] m_heap;
    private int m_count;

    // METHODS
    public DSAHeap(int maxSize) {
	m_heap = new DSAHeapEntry[maxSize];
    }

    public void add(int priority, Object value) {

	DSAHeapEntry newEntry = new DSAHeapEntry(priority, value);

	m_heap[m_count] = newEntry;

	trickleUp(m_count);

	m_count++;

    }

    public Object remove() {

	Object removeValue = m_heap[0].value;

	m_heap[0] = m_heap[m_count - 1];

	trickleDown(0);

	m_count--;

	return removeValue;

    }

    public void heapSort(DSAHeapEntry[] list) {

	// replace all items in m_heap with the imported list
	int i = 0;
	m_count = 0;
	while (list[i] != null) {

	    m_heap[i] = list[i];
	    i++;
	    m_count++;

	}

	i = 0;

	// heapify: newly created m_heap will become a heap
	for (int ii = (m_count / 2) - 1; ii >= 0; ii--) {
	    
	    // put the ith element in the correct place
	    trickleDown(ii);

	}

	// swap the root and last entries, then trickle down the new root
	for (int ii = m_count - 1; ii > 1; ii--) {

	    DSAHeapEntry temp = m_heap[0];
	    m_heap[0] = m_heap[ii];
	    m_heap[ii] = temp;

	    trickleDown(0);

	}

    }

    //trickleUp and trickleDown MUST be recursive
    private void trickleUp(int index) {

	int parentIdx = (index - 1) / 2;
	DSAHeapEntry temp;

	if (index > 0) {

	    if (m_heap[index].priority > m_heap[parentIdx].priority) {

		temp = m_heap[parentIdx];
		m_heap[parentIdx] = m_heap[index];
		m_heap[index] = temp;

		trickleUp(parentIdx);

	    }

	}

    }

    private void trickleDown(int index) {

	int lChildIdx = index * 2 + 1;
	int rChildIdx = lChildIdx + 1;

	if (lChildIdx < m_count) {

	    int largeIdx = lChildIdx;

	    if (rChildIdx < m_count) {

		if (m_heap[lChildIdx].priority < m_heap[rChildIdx].priority) {
		    largeIdx = rChildIdx;
		}

	    }

	    if (m_heap[largeIdx].priority > m_heap[index].priority) {

		DSAHeapEntry temp = m_heap[largeIdx];
		m_heap[largeIdx] = m_heap[index];
		m_heap[index] = temp;

		trickleDown(largeIdx);

	    }

	}

    }
   
}

