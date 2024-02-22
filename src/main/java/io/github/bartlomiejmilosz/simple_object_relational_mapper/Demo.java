package io.github.bartlomiejmilosz.simple_object_relational_mapper;

import io.github.bartlomiejmilosz.simple_object_relational_mapper.model.Person;
import io.github.bartlomiejmilosz.simple_object_relational_mapper.util.ColumnField;
import io.github.bartlomiejmilosz.simple_object_relational_mapper.util.Metamodel;
import io.github.bartlomiejmilosz.simple_object_relational_mapper.util.PrimaryKeyField;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        Metamodel<Person> metamodel = Metamodel.of(Person.class);

        PrimaryKeyField primaryKeyField = metamodel.getPrimaryKey();
        List<ColumnField> columnFields = metamodel.getColumns();

        System.out.println("Primary key name = " + primaryKeyField.getName() +
                ", type = " + primaryKeyField.getType().getSimpleName());

        for (ColumnField columnField : columnFields) {
            System.out.println("Colum name = " + columnField.getName() +
                    ", type = " + columnField.getType().getSimpleName());
        }
    }
}
