package com.mmit.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Registeration
 *
 */
@Entity

public class Registeration implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "student_id") 
	private Student student;
	@ManyToOne
	@JoinColumn(name = "classes_id", referencedColumnName = "id") 
	private Class classes;
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	public Class getClasses() {
		return classes;
	}


	public void setClasses(Class classes) {
		this.classes = classes;
	}


	public LocalDate getReg_date() {
		return reg_date;
	}


	public void setReg_date(LocalDate reg_date) {
		this.reg_date = reg_date;
	}


	public int getPaid_amount() {
		return paid_amount;
	}


	public void setPaid_amount(int paid_amount) {
		this.paid_amount = paid_amount;
	}


	private LocalDate reg_date;
	private int paid_amount;
	

	public Registeration() {
		super();
	}
   
}
