package main

func merge2Lists(h1 *Node, h2 *Node) *Node {
	head := &Node{}
	current := head
	current1 := h1
	current2 := h2

	if h1 == nil && h2 == nil {
		return nil
	}

	for current1 != nil && current2 != nil {
		if (*current1).value <= (*current2).value {
			(*current).value = (*current1).value
			current1 = (*current1).next
		} else {
			(*current).value = (*current2).value
			current2 = (*current2).next
		}
		(*current).next = &Node{}
		current = (*current).next
	}

	if current1 == nil {
		*current = *current2
	} else {
		*current = *current1
	}

	return head
}

func mergeKLists(heads []*Node) {
	newHead := &Node{}
	current := newHead

	for true {
		smallestIndex := 0

		for index := range heads[1:] {
			if (*heads[smallestIndex]).value > (*heads[index]).value {
				smallestIndex = index
			}
		}
	}
}
