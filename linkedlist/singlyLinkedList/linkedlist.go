package main

import "fmt"

func main() {
	var head *Node
	var current *Node
	var nodeValue int
	// var delPosition int
	// var position int
	// var nodeAtPValue int
	// var nodeFromEnd int

	head = &Node{}
	fmt.Println("Enter string values")
	fmt.Println("Enter \"-1\" to exit")
	for true {
		fmt.Scan(&nodeValue)
		if nodeValue == -1 {
			if (*head).next == nil {
				head = nil
			}
			break
		}
		if (*head).value == 0 {
			(*head).value = nodeValue
			(*head).next = nil
			current = head
		} else {
			new := &Node{}
			(*new).value = nodeValue
			(*current).next = new
			(*new).next = nil
			current = new
		}
	}

	printLinkedList(head)
	/*
		fmt.Printf("List length: %d\n", listLength(head))
		fmt.Print("Enter position : ")
		fmt.Scan(&position)
		fmt.Print("Enter value : ")
		fmt.Scan(&nodeAtPValue)
		head.insertAtP(position, nodeAtPValue)
		printLinkedList(head)
		fmt.Print("Delete at : ")
		fmt.Scan(&delPosition)
		head.deleteAtP(delPosition)
		printLinkedList(head)
	*/

	//fmt.Print("Enter position from end: ")
	// fmt.Scan(&nodeFromEnd)

	// fmt.Println(nFromEnd(head, nodeFromEnd))
	var tagertValue int
	fmt.Print("Delete: ")
	fmt.Scan(&tagertValue)
	head.deleteTarget(tagertValue)
	printLinkedList(head)
}
