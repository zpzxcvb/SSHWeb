package com.zhangpan.ssh.test;

import java.util.Stack;

public class MyQueue<T> {
	private Stack<T> s1,s2;
	public MyQueue(){
		s1=new Stack<T>();
		s2=new Stack<T>();
	}
	public int size(){
		return s1.size()+s2.size(); 
	}
	/**
	 * 判断stack是否为空
	 * @param s
	 * @return
	 */
	public boolean isEmpty(Stack<T> s){
		return s.empty()?true:false;
	}
	/**
	 * 添加元素到stack
	 * @param t
	 */
	public void push(T t){
		s1.push(t);
	}
	/**
	 * 返回栈顶元素并出队
	 * @param s
	 * @return
	 */
	public T pop(Stack<T> s){
		return s.pop();
	}
	/**
	 * 返回栈顶元素
	 * @param s
	 * @return
	 */
	public T peek(Stack<T> s){
		return s.peek();
	}
	
	public T stackToQueue(){
		T top=null;
		if(s2.isEmpty()){
			while (!s1.isEmpty()) {  
				top=s1.pop();
                s2.push(top);  
            }
		}else{
			top=s2.pop();
		}
		return top;
	}
	
	public static void main(String[] args) {
		MyQueue<Integer> m=new MyQueue<Integer>();
		for (int i = 0; i < 10; i++) {
			m.push(i);
		}
		System.out.println(m.stackToQueue());
	}
	

}
