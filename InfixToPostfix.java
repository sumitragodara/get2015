import java.util.Scanner;

import com.Stack;
/*
 * sumitra
 * convert INFIX TO postfix
 */
public class InfixToPostfix {
/*
 * method return priority of operator
 */
	
	private int priority(char operator) {
		switch(operator) {
		case '(':
			return 0;
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 3;
		}
		return -1;
	}
	/*
	 * Method return boolean value if it is operand
	 */
	private boolean isOperand(char c) {
		if(c >='0' && c <= '9' ) {
			return true;
		} else if(c >= 'a' && c <= 'z') {
			return true;
		} else if(c >= 'A' && c <= 'Z') {
			return true;
		}
		return false;		
	}
	/*
	 * method return true if operator
	 */
	private boolean isOperator(char c) {
		if(c == '/' || c == '*' || c == '+' || c == '-' || c== '^') {
			return true;
		}
		return false;
	}
	/*
	 * method convert infix into postfix
	 */
	public void infixToPostfix (String infix) {
		Stack stack = new Stack();
		/**
		 * check that operator/operand and next to that operator/operand are same or not...if same throw exception
		 */
		try{
		for(int i = 0; i < (infix.length()-1) ; i++)
		{
			if(isOperator(infix.charAt(i))){
				if(isOperator(infix.charAt(i+1))){
					throw new Exception("operators are coming back to back");
				}
					}
			
			if(isOperand(infix.charAt(i))){
				if(isOperand(infix.charAt(i+1))){
					throw new Exception("operands are coming back to back");
				}
			}
		}
		/**
		 * check that open and closing brackets
		 */
		char value;
		int openBreaket = 0,closeBreaket = 0;
		for(int i = 0;i<infix.length();i++){
			value=infix.charAt(i);
			if(value == '(')
				openBreaket++;
			else if(value ==')')
				closeBreaket++;	
		}
		if(openBreaket !=closeBreaket)
			throw new Exception("open and closing brackets are not equal");
	
		stack.pushItem('(');
		for(int count = 0; count < infix.length()+1; count++) {
			char currentChar ;
			if(count == infix.length()) {
				currentChar = ')';
			} else {
				currentChar = infix.charAt(count);
			}
			if(currentChar =='('){
				stack.pushItem(currentChar);
			}
			if(isOperand(currentChar)) {
				System.out.print("" + currentChar);
			} else if(isOperator(currentChar)) {
				if(priority(currentChar) <= priority((char)Stack.top.data)) {
					System.out.print("" + (char)Stack.top.data);
				 stack.popItem();	
				 count=count-1;
					continue;
				} else {
					stack.pushItem(currentChar);
				}
			} else if(currentChar == ')') {
				while((char)Stack.top.data != '(') {
					System.out.print("" + (char)Stack.top.data);
					stack.popItem();
				
				}
			} 
			
		}
		while(Stack.top != null){
			if((char)Stack.top.data == '(')
				stack.popItem();
			else{
			System.out.print("" + (char)Stack.top.data);
			 stack.popItem();
		}}
	}catch (Exception e) {
		
		//System.out.println("invalid string");
		System.out.println(e);
	}
	}
	/*
	 * main method
	 */
	public static void main(String[] args) {
		System.out.println("enter infix String");
		Scanner scanner = new Scanner(System.in);
		String infix = scanner.next();
		InfixToPostfix infixToPostfix = new InfixToPostfix();
		infixToPostfix.infixToPostfix(infix);
		scanner.close();
		
	}
}
