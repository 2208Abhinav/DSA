package main

import "fmt"

// CircularQueue will hold all the details of the
// circular queue ADT.
type CircularQueue struct {
	limit int
	queue []int
	head  int
	tail  int
	size  int
}

// Constructor will initialize the queue.
func Constructor(k int) CircularQueue {
	newQueue := CircularQueue{
		limit: k,
		queue: make([]int, k),
		head:  0,
		tail:  -1,
		size:  0,
	}

	return newQueue
}

func (q *CircularQueue) enQueue(val int) bool {
	if q.size == q.limit {
		return false
	}
	if q.head == -1 {
		q.head = 0
	}
	q.tail++
	if q.tail == q.limit {
		q.tail = 0
		q.queue[q.tail] = val
	} else {
		q.queue[q.tail] = val
	}
	q.size++
	return true
}

func (q *CircularQueue) deQueue() bool {
	if q.size == 0 {
		return false
	}
	q.queue[q.head] = 0
	q.head++
	if q.head == q.limit {
		q.head = 0
	}
	q.size--
	return true
}

func (q *CircularQueue) front() int {
	if q.isEmpty() {
		return -1
	}
	return q.queue[q.head]
}

func (q *CircularQueue) back() int {
	if q.isEmpty() {
		return -1
	}
	return q.queue[q.tail]
}

func (q *CircularQueue) isEmpty() bool {
	if q.size == 0 {
		return true
	}
	return false
}

func (q *CircularQueue) isFull() bool {
	if q.size == q.limit {
		return true
	}
	return false
}

// Stack used to reverse queue.
type Stack struct {
	limit int
	stk   []int
}

// StackConstructor will initialize the stack.
func StackConstructor(k int) *Stack {
	stk := &Stack{
		limit: k,
		stk:   []int{},
	}

	return stk
}

func (s *Stack) size() int {
	return len(s.stk)
}

func (s *Stack) push(val int) {
	s.stk = append(s.stk, val)
}

func (s *Stack) pop() {
	s.stk = s.stk[:s.size()-1]
}

func (s *Stack) top() int {
	if s.size() == 0 {
		return -1
	}
	return s.stk[s.size()-1]
}

func main() {
	/*
		queue := Constructor(3)
		fmt.Println(queue.enQueue(1))
		fmt.Println(queue.enQueue(2))
		fmt.Println(queue.enQueue(3))
		fmt.Println(queue.enQueue(4))
		fmt.Println(queue.back())
		fmt.Println(queue.isFull())
	*/
	queue := Constructor(7)
	queue.enQueue(1)
	queue.enQueue(2)
	queue.enQueue(3)
	queue.enQueue(4)
	queue.enQueue(5)
	queue.enQueue(6)
	queue.enQueue(7)
	helpStack := StackConstructor(queue.size)

	fmt.Println(queue.front())
	fmt.Println(queue.back())

	i := 0
	queueSize := queue.size
	for i < queueSize {
		helpStack.push(queue.front())
		queue.deQueue()
		i++
	}

	for helpStack.top() != -1 {
		queue.enQueue(helpStack.top())
		helpStack.pop()
	}

	fmt.Println(queue.front())
	fmt.Println(queue.back())
}
