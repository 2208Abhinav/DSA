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
			return "Trapped!"
		}
	}
	return "Not trapped!"
}

func checkTermination(current *Node) string {
	pointerStore := make(map[*Node]bool)

	for true {
		if pointerStore[current] == true {
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

func main() {
	n1 := &Node{1, nil}
	n2 := &Node{2, nil}
	n3 := &Node{3, nil}
	n4 := &Node{4, nil}
	n5 := &Node{5, nil}
	n6 := &Node{6, nil}
	n7 := &Node{7, nil}
	n8 := &Node{8, nil}
	n9 := &Node{9, nil}
	n10 := &Node{10, nil}

	n1.next = n2
	n2.next = n3
	n3.next = n4
	n4.next = n5
	n5.next = n6
	n6.next = n7
	n7.next = n8
	n8.next = n9
	n9.next = n10
	n10.next = n4

	fmt.Println(flyodDetection(n1))
}
