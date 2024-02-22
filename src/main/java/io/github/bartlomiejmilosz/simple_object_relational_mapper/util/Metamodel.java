package io.github.bartlomiejmilosz.simple_object_relational_mapper.util;

import io.github.bartlomiejmilosz.simple_object_relational_mapper.annotations.Column;
import io.github.bartlomiejmilosz.simple_object_relational_mapper.annotations.PrimaryKey;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Metamodel<T> {
    private Class<T> tClass;

    public Metamodel(Class<T> tClass) {
        this.tClass = tClass;
    }

    public static <T> Metamodel<T> of(Class<T> tClass) {
        return new Metamodel<>(tClass);
    }

    public PrimaryKeyField getPrimaryKey() {
        Field[] fields = tClass.getDeclaredFields();
        Field primaryKeyField = Arrays.stream(fields)
                .filter(primaryKey -> primaryKey.isAnnotationPresent(PrimaryKey.class))
                .findFirst()
                .orElse(null);

        if (primaryKeyField != null) {
            return new PrimaryKeyField(primaryKeyField);
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
}

