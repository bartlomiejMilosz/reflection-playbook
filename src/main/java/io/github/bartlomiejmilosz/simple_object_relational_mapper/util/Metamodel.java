package io.github.bartlomiejmilosz.simple_object_relational_mapper.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.github.bartlomiejmilosz.simple_object_relational_mapper.annotations.Column;
import io.github.bartlomiejmilosz.simple_object_relational_mapper.annotations.PrimaryKey;



public class Metamodel {


	private Class<?> tClass;

	public static Metamodel of(Class<?> tClass) {
		return new Metamodel(tClass);
	}

	public Metamodel(Class<?> tClass) {
		this.tClass = tClass;
	}

	public PrimaryKeyField getPrimaryKey() {
		
		Field[] fields = tClass.getDeclaredFields();
		for (Field field : fields) {
			
			PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
			if (primaryKey != null) {
				return new PrimaryKeyField(field);
			}
		}
		
		throw new IllegalArgumentException("No primary key found in class " + tClass.getSimpleName());
	}

	public List<ColumnField> getColumns() {
		
		List<ColumnField> columnFields = new ArrayList<>();
		Field[] fields = tClass.getDeclaredFields();
		for (Field field : fields) {
			
			Column column = field.getAnnotation(Column.class);
			if (column != null) {
				ColumnField columnField = new ColumnField(field);
				columnFields.add(columnField);
			}
		}
		return columnFields;
	}

	public String buildInsertRequest() {
		// insert into Person (id, name, age) values (?, ?, ?)
		
		String columnElement = buildColumnNames();
		String questionMarksElement = buildQuestionMarksElement();
				
		return "insert into " + this.tClass.getSimpleName() + 
				" (" + columnElement + ") values (" + questionMarksElement + ")";
	}
	
	public String buildSelectRequest() {
		// select id, name, age from Person where id = ?
		String columnElement = buildColumnNames();
		return "select " + columnElement + " from " + this.tClass.getSimpleName() +
			   " where " + getPrimaryKey().getName() + " = ?";
	}

	private String buildQuestionMarksElement() {
		int numberOfColumns = getColumns().size() + 1;
		return IntStream.range(0, numberOfColumns)
				.mapToObj(index -> "?")
				.collect(Collectors.joining(", "));
	}

	private String buildColumnNames() {
		String primaryKeyColumnName = getPrimaryKey().getName();
		List<String> columnNames = 
				getColumns().stream().map(ColumnField::getName).collect(Collectors.toList());
		columnNames.add(0, primaryKeyColumnName);
		return String.join(", ", columnNames);
	}
}
