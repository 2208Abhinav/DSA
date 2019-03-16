package main

import "fmt"

// Node : contains information about node of list.
type Node struct {
	value string
	next  *Node
}

func listLength(h *Node) int {
	length := 0
	current := h

	for true {
		length++
		if (*current).next == h {
			return length
		}
		current = (*current).next
	}
	return length
}

func printList(h *Node) {
	current := h
	for true {
		if (*current).next == h {
			fmt.Println((*current).value)
			break
		}
		fmt.Printf("%s --> ", (*current).value)
		current = (*current).next
	}
}

func (h *Node) insertAtP(pos int, val string) {
	current := h
	i := 0
	for i < pos-2 {
		current = (*current).next
		i++
	}
	if current == h {
		tempHead := *h
		*h = Node{}
		(*h).value = val
		(*h).next = &tempHead
		return
	}
	newNode := &Node{}
	tempNext := (*current).next
	(*newNode).value = val
	(*newNode).next = tempNext
	(*current).next = newNode
}

func (h *Node) deleteAt1() {
	current := h
	i := 0
	for i < listLength(h)-1 {
		current = (*current).next
		i++
	}
	tempHead := *h
	*h = *(tempHead.next)
	(*current).next = h
}

func (h *Node) deleteAtP(pos int) {
	if pos == 1 {
		h.deleteAt1()
	} else {
		current := h
		i := 0
		for i < pos-2 {
			current = (*current).next
			i++
		}
		(*current).next = (*(*current).next).next
	}
}
