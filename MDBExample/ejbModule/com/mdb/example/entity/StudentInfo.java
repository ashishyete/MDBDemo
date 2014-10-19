/**
 * 
 */
package com.mdb.example.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ashish
 *
 */
public class StudentInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int studentId;
	private String studentName;
	private String studentLastName;
	private String studentClass;
	private List<String> studentSub;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}

	public List<String> getStudentSub() {
		return studentSub;
	}

	public void setStudentSub(List<String> studentSub) {
		this.studentSub = studentSub;
	}

}
