package main

import "fmt"

func main() {
	var head *Node
	var current *Node
	var nodeValue string
	var position int
	var value string
	var deleteAt int

	fmt.Println("Enter values.")
	fmt.Println("Enter \"quit\" to exit.")

	for true {
		fmt.Scan(&nodeValue)
		if nodeValue == "quit" {
			if head == nil {
				break
			}
			(*current).next = head
			break
		}
		if head == nil {
			head = &Node{}
			(*head).value = nodeValue
			current = head
		} else {
			newNode := &Node{}
			(*newNode).value = nodeValue
			(*current).next = newNode
			current = newNode
		}
	}

	fmt.Println(listLength(head))
	printList(head)

	fmt.Print("Enter position: ")
	fmt.Scan(&position)
	fmt.Print("Enter value: ")
	fmt.Scan(&value)
	head.insertAtP(position, value)
	printList(head)

	fmt.Print("Delete node at: ")
	fmt.Scan(&deleteAt)
	head.deleteAtP(deleteAt)
	printList(head)
}
