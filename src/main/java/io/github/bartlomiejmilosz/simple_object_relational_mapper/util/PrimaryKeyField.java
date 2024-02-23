package io.github.bartlomiejmilosz.simple_object_relational_mapper.util;

import java.lang.reflect.Field;

import io.github.bartlomiejmilosz.simple_object_relational_mapper.annotations.PrimaryKey;

public class PrimaryKeyField {
    	private Field field;
	private PrimaryKey primaryKey;

	public PrimaryKeyField(Field field) {
		this.field = field;
		this.primaryKey = this.field.getAnnotation(PrimaryKey.class);
	}

	public String getName() {
		return primaryKey.name();
	}

	public Class<?> getType() {
		return field.getType();
	}

	public Field getField() {
		return this.field;
	}
}
