package io.github.bartlomiejmilosz.simple_object_relational_mapper.util;

import java.lang.reflect.Field;

import io.github.bartlomiejmilosz.simple_object_relational_mapper.annotations.Column;

public class ColumnField {
    private Field field;
	private Column column;

	public ColumnField(Field field) {
		this.field = field;
		this.column = field.getAnnotation(Column.class);
	}

	public String getName() {
		return column.name();
	}

	public Class<?> getType() {
		return field.getType();
	}

	public Field getField() {
		return this.field;
	}
}
