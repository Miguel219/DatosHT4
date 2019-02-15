package application;

public abstract class Stack<E> {

   public abstract void push(E item);
   // pre: 
   // post: item is added to stack
   // will be popped next if no intervening push
   
   public abstract E pop();
   // pre: stack is not empty
   // post: most recently pushed item is removed and returned
   
   public abstract E peek();
   // pre: stack is not empty
   // post: top value (next to be popped) is returned
   
   public abstract boolean empty();
   // post: returns true if and only if the stack is empty
   
   public abstract int size();
   // post: returns the number of elements in the stack

}