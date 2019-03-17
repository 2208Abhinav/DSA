package main

import "fmt"

// Node : contains detail about node in list.
type Node struct {
	value int
	next  *Node
}

// The following function will reverse the linked list in
// O(n) time complexity and O(1) space complexity.
// The way I think about this algorithm is that our main
// focus should be on reversing the direction of links
// i.e., direction of pointers.
func reverseInOnO1(head *Node) *Node {
	var prev *Node // declared this way because we want prev to be nil
	current := head
	next := current.next
	for current != nil {
		current.next = prev // we reverse the direction of pointer here.
		// The following lines of code will just traverse through the
		// while updating the pointers. You should draw the steps on
		// paper, it will get clear.
		prev = current
		current = next
		// If not true then we prevent nil.nil which will cause errors.
		if current != nil {
			next = current.next
		}
	}
	return prev
}

func (h *Node) insertInSorted(nodeValue int) {
	i := 0
	current := h
	for current != nil {
		fmt.Println(current.value)
		i++
		if current.next == nil {
			// We are in the end game now!
			// We reached end of the list.
			tempNextHead := current.next
			newNode := &Node{nodeValue, tempNextHead}
			current.next = newNode
			return
		}
		if current.next.value > nodeValue {
			// If we need to add at the beginning then the
			// situation is quite different.
			if i == 1 {
				tempHead := *h
				*h = Node{nodeValue, &tempHead}
			} else {
				tempNextHead := current.next
				newNode := &Node{nodeValue, tempNextHead}
				current.next = newNode
			}
			return
		}
		current = current.next
	}
}

func printList(current *Node) {
	for current != nil {
		fmt.Printf("%d --> ", current.value)
		current = current.next
	}

	fmt.Println(nil)
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
	l1 := &Node{1, &Node{4, &Node{6, &Node{8, nil}}}}

	// fmt.Println(flyodDetection(n1))
	//l1.insertInSorted(0)
	// printList(addTwoNumbers(l1, l2))
	printList(l1)

	// Update the head "pointer" after reversing the list.
	l1 = reverseInOnO1(l1)

	printList(l1)
}
