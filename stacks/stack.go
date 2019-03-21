package main

import (
	"fmt"
	"regexp"
)

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

func top(stk *Stack) string {
	stackLen := len(stk.stack)
	if stackLen == 0 {
		fmt.Println("Stack is empty")
		return ""
	}
	return stk.stack[stackLen-1]
}

// This function will convert infix expression to postfix expression.
/*
	Here's my summary for algorithm:
	if "(" -> push
	if operand -> append in postfix string
	if ")" -> pop and append in postfix string, if "(" is found then pop "("
	if operator:
		pop and append in postfix string until lower priority operator is found
		or stack is empty
		push operator
	if end of infix expression/string -> append in postfix and pop until stack is empty.
*/
func infixToPostfix(infixExpr string) string {
	// we don't need to strictly use ^$ in regular expression because
	// we are ultimately taking one character at a time.
	operandRegex, _ := regexp.Compile("[A-Za-z0-9]")
	operatorRegex, _ := regexp.Compile("[-+/*^]")

	priorityMap := map[string]int{
		"^": 3,
		"*": 2,
		"/": 2,
		"+": 1,
		"-": 1,
		"(": 0, // 0 bcoz postfix expressions don't have brackets.
	}

	helpStack := &Stack{limit: len(infixExpr)}
	postfixExpr := ""

	for _, char := range infixExpr {
		charString := string(char)
		if charString == "(" {
			helpStack.push("(")
		} else if operandRegex.MatchString(charString) {
			postfixExpr += charString
		} else if charString == ")" {
			for top(helpStack) != "(" {
				postfixExpr += top(helpStack)
				helpStack.pop()
			}
			helpStack.pop() // to pop "(" from top
		} else if operatorRegex.MatchString(charString) {
			for priorityMap[top(helpStack)] >= priorityMap[charString] && size(helpStack) != 0 {
				postfixExpr += top(helpStack)
				helpStack.pop()
			}
			helpStack.push(charString)
		}
	}
	// if we scaned the whole expression and stack is not empty
	for size(helpStack) != 0 {
		postfixExpr += top(helpStack)
		helpStack.pop()
	}
	return postfixExpr
}

func postfixToInfix(postfixExpr string) string {
	var topEl, belowTopEl string
	helpStack := &Stack{limit: len(postfixExpr)}

	operandRegex, _ := regexp.Compile("[A-Za-z0-9]")
	operatorRegex, _ := regexp.Compile("[-+/*^]")

	for _, char := range postfixExpr {
		charString := string(char)

		if operandRegex.MatchString(charString) {
			helpStack.push(charString)
		} else if operatorRegex.MatchString(charString) {
			topEl = top(helpStack)
			helpStack.pop()
			belowTopEl = top(helpStack)
			helpStack.pop()
			// The order of belowTopEl and topEl below is important.
			// It depends on the way we generate postFix string using
			// infixToPostfix function. For example is infix: A/B
			// postFix: AB/ and now in the stack belowTopEL = A and
			// topEl = B. If we do topEl + charString + belowToEl then
			// the result would be B/A, which is wrong.
			helpStack.push(belowTopEl + charString + topEl)
		}
	}

	return top(helpStack)
}

func main() {
	// symbols := "() (() [()]) {}"
	//fmt.Println("Symbols balanced:", areBracketsBalanced(symbols))
	infixExpr := "A+B/C+D*C-D"
	postfixExpr := infixToPostfix(infixExpr)
	fmt.Printf("Infix( %s ) -> Postfix( %s )\n", infixExpr, postfixExpr)
	fmt.Println("Note: Brackets in infix will not be converted back.")
	fmt.Printf("Postfix( %s ) -> Infix( %s )\n", postfixExpr, postfixToInfix(postfixExpr))
}
