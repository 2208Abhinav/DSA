package main

import "fmt"

// Node : contains data of a node in linked list.
type Node struct {
	value int
	next  *Node
}

func printLinkedList(h *Node) {
	current := h
	for true {
		if current == nil {
			fmt.Println("<nil>")
			return
		}
		fmt.Printf("%d -> ", (*current).value)
		current = (*current).next
	}
}

func listLength(h *Node) int {
	length := 0
	current := h
	for true {
		if current == nil {
			break
		}
		length++
		current = (*current).next
	}
	return length
}

func (h *Node) insertAt1(val int) {
	tempHead := *h
	*h = Node{}
	(*h).value = val
	(*h).next = &tempHead
}

func (h *Node) insertAtP(pos int, val int) {
	if pos <= 0 || pos > listLength(h)+1 {
		fmt.Println("Bad request!")
		return
	}
	if pos == 1 {
		h.insertAt1(val)
		return
	}
	target := h
	i := 0
	for i < pos-2 {
		target = (*target).next
		i++
	}
	new := &Node{}
	(*new).value = val
	(*new).next = (*target).next
	(*target).next = new
}

func (h *Node) deleteAt1() {
	secondNode := *((*h).next)
	*h = secondNode
}

func (h *Node) deleteAtP(pos int) {
	i := 0
	if pos <= 0 || pos > listLength(h) {
		return
	}
	if pos == 1 {
		h.deleteAt1()
		return
	}
	current := h
	for i < pos-2 {
		current = (*current).next
		i++
	}
	(*current).next = (*(*current).next).next
}

func nFromEnd(h *Node, n int) int {
	i := 0
	pfinal := h
	current := h

	for current != nil {
		if i >= n {
			pfinal = (*pfinal).next
		}
		current = (*current).next
		i++
	}

	return (*pfinal).value
}
