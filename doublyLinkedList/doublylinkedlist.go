package main

import "fmt"

func main() {
	var head *Node
	var current *Node
	var nodeValue string
	var position int
	var deleteAt int
	var newPosValue string

	head = &Node{}

	fmt.Println("Enter values")
	fmt.Println("Enter \"quit\" to exit")
	for true {
		fmt.Scan(&nodeValue)
		if nodeValue == "quit" {
			break
		}
		if (*head).value == "" {
			(*head).value = nodeValue
			(*head).next = nil
			(*head).prev = nil
			current = head
		} else {
			newNode := &Node{}
			(*newNode).value = nodeValue
			(*current).next = newNode
			(*newNode).prev = current
			(*newNode).next = nil
			current = newNode
		}
	}
	fmt.Println(listLength(head))
	printDoublyLinkedList(head)
	fmt.Print("Enter position : ")
	fmt.Scan(&position)
	fmt.Print("Enter value : ")
	fmt.Scan(&newPosValue)
	head.insertAtP(position, newPosValue)
	printDoublyLinkedList(head)

	fmt.Print("Delete at : ")
	fmt.Scan(&deleteAt)
	head.deleteAtP(deleteAt)
	printDoublyLinkedList(head)
}
