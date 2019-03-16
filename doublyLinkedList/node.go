package main

import "fmt"

// Node : contains information about a node in linked list
type Node struct {
	value string
	next  *Node
	prev  *Node
}

func printDoublyLinkedList(h *Node) {
	current := h

	for true {
		if current == nil {
			fmt.Println(nil)
			break
		} else {
			fmt.Printf("%s --> ", (*current).value)
			current = (*current).next
		}
	}
}

func listLength(h *Node) int {
	length := 0
	current := h
	for current != nil {
		current = (*current).next
		length++
	}
	return length
}

func (h *Node) insertAt1(val string) {
	tempHead := *h
	(*h) = Node{}
	(*h).prev = nil
	(*h).value = val
	(*h).next = &tempHead
	tempHead.prev = h
}

func (h *Node) insertAtP(pos int, val string) {
	current := h
	i := 0
	if pos <= 0 || pos > listLength(h) {
		fmt.Println("Bad Request!")
		return
	}
	if pos == 1 {
		h.insertAt1(val)
		return
	}

	for i < pos-1 {
		current = (*current).next
		i++
	}
	new := &Node{}
	tempPrev := (*current).prev
	(*new).value = val
	(*new).prev = tempPrev
	(*tempPrev).next = new
	(*new).next = current
	(*current).prev = new
}

func (h *Node) deleteAt1() {
	nextNode := (*h).next
	(*nextNode).prev = nil
	(*h).next = nil
	*h = *nextNode
}

func (h *Node) deleteAtP(pos int) {
	current := h
	if pos == 1 {
		h.deleteAt1()
	} else {
		i := 0
		for i < pos-1 {
			current = (*current).next
			i++
		}
		prevNode := (*current).prev
		nextNode := (*current).next
		if nextNode != nil {
			(*prevNode).next = nextNode
			(*nextNode).prev = prevNode
		} else {
			(*prevNode).next = nil
		}
	}
}
