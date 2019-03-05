package com.codecool.shop.dao.implementation.database;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public abstract class DaoDatabase {
    protected static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop"/*System.getenv("DATABASE")*/;
    protected static final String DB_USER = "hackmaster"/*System.getenv("DB_USER")*/;
    protected static final String DB_PASSWORD = "password"/*System.getenv("DB_PASSWORD")*/;

    protected static LinkedHashMap<String, String> columnLabelsAndTypes;

    protected List<Object> executeQuery(String query, List<Object> parameters){
        ResultSet resultSet = null;
        List<Object> resultList = null;
        boolean hasResult;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            setQueryValues(statement, parameters);

            hasResult = statement.execute();

            //Getting and mapping results if there is a resultSet
            if(hasResult){
                resultList = mapQueryToObjectList(statement.getResultSet());
            }
        } catch (Exception e){
            System.out.println("Exception occurred during query execution");
            e.printStackTrace();
        } finally {
            try{
                resultSet.close();
            } catch (SQLException e){
                System.out.println("SQLException occurred during result set closing.");
            } finally {
                return resultList;
            }

        }
    }

    protected List<Object> mapQueryToObjectList(ResultSet resultSet) throws SQLException{
        List<Object> webshopEntityList = new ArrayList<>();

        while(resultSet.next()){
            Object obj = WebshopEntityFactory.getInstanceOfWebshopEntity(this.getClass());
            List<Field> fields = getEveryField(obj);

            for (Field field : fields) {
                boolean accessible = field.isAccessible();
                field.setAccessible(true);

                try{
                    if(columnLabelsAndTypes.containsKey(field.getName())){
                        switch (columnLabelsAndTypes.get(field.getName())){
                            case "string":
                                field.set(obj, resultSet.getString(field.getName()));
                                break;
                            case "int":
                                field.set(obj, resultSet.getInt(field.getName()));
                                break;
                            case "float":
                                field.set(obj, resultSet.getFloat(field.getName()));
                                break;
                            case "double":
                                field.set(obj, resultSet.getDouble(field.getName()));
                                break;
                        }
                    } else {
                        System.out.println("Field name wasn't in columLabels: " + field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }


                field.setAccessible(accessible);
            }
            webshopEntityList.add(obj);
        }

        return webshopEntityList;
    }

    private List<Field> getEveryField(Object obj) {
        List<Field> fields = new ArrayList<>();
        Class clazz = obj.getClass();
        while (clazz != Object.class) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    protected void setQueryValues(PreparedStatement statement, List<Object> parameters) throws SQLException {
        for (int i = 0; i < parameters.size(); i++) {
            if(parameters.get(i) instanceof String){
                statement.setString(i+1, (String)parameters.get(i));
            } else if (parameters.get(i) instanceof Integer){
                statement.setInt(i+1, (Integer)parameters.get(i));
            } else if (parameters.get(i) instanceof Double){
                statement.setDouble(i+1, (Double)parameters.get(i));
            } else if (parameters.get(i) instanceof Float){
                statement.setFloat(i+1, (Float)parameters.get(i));
            }
        }
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }
}
