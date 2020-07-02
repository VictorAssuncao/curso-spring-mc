package com.vras.cursomc.resources.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer fieldName;
	private String message;
	
	public FieldMessage() {
		super();
	}

	public FieldMessage(Integer fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}

	public Integer getFieldName() {
		return fieldName;
	}

	public void setFieldName(Integer fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
