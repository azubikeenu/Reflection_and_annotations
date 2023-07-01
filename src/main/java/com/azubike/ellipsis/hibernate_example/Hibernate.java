package com.azubike.ellipsis.hibernate_example;

import com.azubike.ellipsis.hibernate_example.annotaions.Column;
import com.azubike.ellipsis.hibernate_example.annotaions.PrimaryKey;
import org.h2.jdbc.JdbcSQLNonTransientException;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Hibernate<T> {
    private final Connection connection;
    private final AtomicLong id = new AtomicLong();

    String jdbcUrl = "jdbc:h2:mem:testdb";
    String username = "username";
    String password = "password";

    public static <T> Hibernate<T> getConnection() throws Exception {
        return new Hibernate<T>();
    }

    private Hibernate() throws Exception {
        this.connection = DriverManager.getConnection(jdbcUrl, username, password);
        this.createTable();
    }

    private void createTable() throws Exception {
        final Class<?> objClass = Class.forName("com.azubike.ellipsis.hibernate_example.TransactionHistory");
        String tableName = objClass.getSimpleName();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE IF NOT EXISTS  ").append(tableName);
        Field[] fields = objClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            String fieldType = this.assignFieldType(fields[i]);
            if (fields[i].isAnnotationPresent(PrimaryKey.class)) {
                stringBuilder.append("(").append(fieldName).append(" ").append(fieldType).append(" ").append("PRIMARY KEY,");
            } else if (fields[i].isAnnotationPresent(Column.class)) {
                if (i < fields.length - 1) {
                    stringBuilder.append(fieldName).append(" ").append(fieldType).append(" ,");
                } else {
                    stringBuilder.append(fieldName).append(" ").append(fieldType);
                }
            }
        }
        stringBuilder.append(" ) ");
        final String SQL = stringBuilder.toString();
        Statement statement = connection.createStatement();
        statement.execute(SQL);
    }

    public void write(T obj) throws Exception {
        final Class<?> objClass = obj.getClass();
        final Field[] declaredFields = objClass.getDeclaredFields();
        List<Field> columns = new ArrayList<>();
        Field primaryKey = null;
        StringJoiner stringJoiner = new StringJoiner(",");
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(PrimaryKey.class)) {
                primaryKey = field;
            } else if (field.isAnnotationPresent(Column.class)) {
                columns.add(field);
                stringJoiner.add(field.getName());
            }
        }
        String placeHolder = IntStream.range(0, columns.size() + 1)
                .mapToObj(e -> "?").collect(Collectors.joining(","));

        final String SQL = "INSERT INTO " + objClass.getSimpleName() + " (" + Objects.requireNonNull(primaryKey).getName() + ","
                + stringJoiner + ") VALUES (" + placeHolder + ")";

        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        if (primaryKey.getType().equals(int.class)) {
            primaryKey.setAccessible(true);
            preparedStatement.setLong(1, id.incrementAndGet());
        }
        int index = 2;
        for (Field field : columns) {
            field.setAccessible(true);
            if (field.getType().equals(int.class)) {
                preparedStatement.setInt(index++, (Integer) field.get(obj));
            } else if (field.getType().equals(String.class)) {
                preparedStatement.setString(index++, (String) field.get(obj));
            } else if (field.getType().equals(double.class)) {
                preparedStatement.setDouble(index++, (double) field.get(obj));
            }
        }
        preparedStatement.executeUpdate();
    }

    private String assignFieldType(Field field) {
        var type = "";
        if (field.getType().equals(int.class) || field.getType().equals(long.class) || field.getType().equals(double.class)) {
            type = "INT";
        } else if (field.getType().equals(String.class)) {
            type = "VARCHAR(255)";
        } else if (field.getType().equals(boolean.class)) {
            type = "SMALL_INT";
        }
        return type;
    }


    public T read(final Class<T> objClass, final long id) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM").append(" ").append(objClass.getSimpleName()).append(" ");
        final Field[] declaredFields = objClass.getDeclaredFields();
        final Field primaryField = Arrays.stream(declaredFields).
                filter(field -> field.isAnnotationPresent(PrimaryKey.class)).findFirst().orElseThrow();
        primaryField.setAccessible(true);
        stringBuilder.append("WHERE").append(" ").append(primaryField.getName()).append("=").append(id);
        final String SQL = stringBuilder.toString();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        try {
            final ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            final T obj = objClass.getConstructor().newInstance();
            final int transactionId = resultSet.getInt(primaryField.getName());
            primaryField.setAccessible(true);
            primaryField.set(obj, transactionId);
            for (Field field : declaredFields) {
                if (field.isAnnotationPresent(Column.class)) {
                    field.setAccessible(true);
                    if (field.getType().equals(int.class)) {
                        field.set(obj, resultSet.getInt(field.getName()));
                    } else if (field.getType().equals(String.class)) {
                        field.set(obj, resultSet.getString(field.getName()));
                    }
                }
            }
            return obj;
        } catch (JdbcSQLNonTransientException | SQLDataException ex) {
            return null;
        }

    }
}
