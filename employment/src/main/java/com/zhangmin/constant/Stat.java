package com.zhangmin.constant;
import java.util.Vector;

@SuppressWarnings({ "unchecked", "serial" })
public class Stat extends Vector {
	
	public void push(Object x) {
		super.add(x); // 向队尾添加组件
		if(super.size()>10){
			super.removeElementAt(0);
		}
	}
	
	public Object pop() { // 队首元素出队(从队列删除)
		if(super.size()>0){
			Object x = super.elementAt(super.size()-1); // 返回指定索引处的组件
			super.removeElementAt(super.size()-1); // 删除指定索引处的组件
			return x;
		}
		return null;
	}
	
	
	
	public void remove() {
		super.removeAllElements(); // removeAllElements()移除全部组件，并将其大小设置为零
	}
	
	public static void main(String[] args) throws java.io.IOException {
		Stat s = new Stat();
		s.push("1");
		s.push("2");
		s.push("3");
		s.push("4");
		s.push("5");
		s.push("6");
		s.push("7");
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
	}
}