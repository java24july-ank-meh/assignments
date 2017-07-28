package com.Revature.bean;

import java.io.Serializable;

public class Person implements Serializable {
	private String name;
	private int age;
	private char sex;
	
	private static final long serialVersionUID = 1L;
	
	public Person(){
		
	}
	public Person(String name, int age, char sex){
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + sex;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sex != other.sex)
			return false;
		return true;
	}

	public void setName(String string) {
		// TODO Auto-generated method stub
		name = string;
	}

	public void setSex(char c) {
		// TODO Auto-generated method stub
		sex = c;
	}

	public void setAge(int i) {
		// TODO Auto-generated method stub
		age = i;
	}
	public String getName(){
		return name;
	}
	public int getAge(){
		return age;
	}
	public char getSex(){
		return sex;
	}

}
