package main

import "fmt"

// Stack struct to hold stack elements and the
// limit of the stack.
type Stack struct {
	limit int
	stack []string
}

func (stk *Stack) push(el string) {
	if len(stk.stack) == stk.limit {
		fmt.Println("Error: Stack overflow!")
	} else {
		stk.stack = append(stk.stack, el)
	}
	return
}

func areBracketsBalanced(symbols string) bool {
	symbolsToStore := map[string]bool{
		")": true,
		"(": true,
		"}": true,
		"{": true,
		"]": true,
		"[": true,
	}
	symbolPairs := map[string]string{
		")": "(",
		"}": "{",
		"]": "[",
	}

	symbolsStack := &Stack{limit: len(symbols)}
	for _, symbol := range symbols {
		if symbolPairs[string(symbol)] == top(symbolsStack) {
			symbolsStack.pop()
		} else {
			// we don't wanrt to store anything other than brackets
			if symbolsToStore[string(symbol)] == true {
				symbolsStack.push(string(symbol))
			}
		}
	}

	if size(symbolsStack) == 0 {
		return true
	}
	return false
}

func (stk *Stack) pop() {
	stackLen := len(stk.stack)
	if stackLen == 0 {
		fmt.Println("Error: Stack Underflow!")
	} else {
		stk.stack = stk.stack[:stackLen-1]
	}
}

func printStack(stk *Stack) {
	i := len(stk.stack) - 1
	for i >= 0 {
		fmt.Println(stk.stack[i])
		i--
	}
}

func size(stk *Stack) int {
	return len(stk.stack)
}

func top(stk *Stack) interface{} {
	stackLen := len(stk.stack)
	if stackLen == 0 {
		fmt.Println("Stack is empty")
		return nil
	}
	return stk.stack[stackLen-1]
}

func main() {
	symbols := "() (() [()]) {}"
	fmt.Println("Symbols balanced:", areBracketsBalanced(symbols))
}
