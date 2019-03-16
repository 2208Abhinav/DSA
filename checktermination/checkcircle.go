package main

import "fmt"

// Node : contains detail about node in list.
type Node struct {
	value int
	next  *Node
}

func flyodDetection(head *Node) string {
	slowptr := head
	fastptr := head
	length := 0

	for true {
		fastptr = fastptr.next
		if fastptr == nil {
			break
		} else {
			fastptr = fastptr.next
			if fastptr == nil {
				break
			}
		}
		slowptr = slowptr.next
		if slowptr == fastptr {
			fastptr = fastptr.next
			length++
			for fastptr != slowptr {
				length++
				fastptr = fastptr.next
			}
			fmt.Println("Length of circular part:", length)
			return "Trapped!"
		}
	}
	return "Not trapped!"
}

func checkTermination(current *Node) string {
	pointerStore := make(map[*Node]bool)

	for true {
		if pointerStore[current] == true {
			fmt.Println("Starting loop node", current.value)
			return "Trapped in circular list!"
		}
		pointerStore[current] = true
		if current == nil {
			break
		}
		current = current.next
	}

	return "Not trapped in circular list!"
}

func addTwoNumbers(l1 *Node, l2 *Node) *Node {
	c1 := l1
	c2 := l2
	sum := 0
	nextSum := 0

	head := &Node{}
	var currentHead *Node
	for true {
		if c1 != nil || c2 != nil {
			if c1 == nil {
				sum = nextSum + c2.value
				nextSum = sum / 10
				c2 = c2.next
			} else if c2 == nil {
				sum = nextSum + c1.value
				nextSum = sum / 10
				c1 = c1.next
			} else {
				sum = nextSum + c1.value + c2.value
				nextSum = sum / 10
				c1 = c1.next
				c2 = c2.next
			}
			fmt.Println(sum % 10)

			if head.value == 0 {
				head.value = sum % 10
				head.next = &Node{}
				currentHead = head
			} else {
				currentHead.next = &Node{sum % 10, nil}
				currentHead = currentHead.next
			}
			if nextSum != 0 {
				// If nextSum is not 0 this means that there was some carry value
				// left in it which should be further.
				currentHead.next = &Node{nextSum, nil}
			}
		} else {
			break
		}
	}

	return head
}

func main() {
	l1 := &Node{9, &Node{8, nil}}
	l2 := &Node{9, &Node{3, nil}}

	// fmt.Println(flyodDetection(n1))
	// n1.insertInSorted(-1212)
	printList(addTwoNumbers(l1, l2))
}
