package com.codecool.shop.dao.implementation.database;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class DaoDatabase {
    protected static final String DATABASE;
    protected static final String DB_USER;
    protected static final String DB_PASSWORD;

    protected static LinkedHashMap<String, String> columnLabelsAndTypes;

    protected List<Object> executeQuery(String query, LinkedHashMap<Integer, String> parameters){
        ResultSet resultSet = null;
        List<Object> resultList = null;
        boolean hasResult;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            setQueryValues(statement);

            hasResult = statement.execute();

            //Getting and mapping results if there is a resultSet
            if(hasResult){
                mapQueryToObjectList(statement.getResultSet());
            }
        } catch (Exception e){
            System.out.println("Exception occurred during query execution");
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
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                boolean accessible = field.isAccessible();
                field.setAccessible(true);

                if(columnLabelsAndTypes.containsKey(field.getName())){
                    switch (columnLabelsAndTypes.get(field.getName())){
                        case "string":
                            field.set(obj, resultSet.getString(field.getName()))
                    }
                } else {
                    System.out.println("Field name wasn't in columLabels: " + field.getName());
                }

                field.setAccessible(accessible);
            }
            webshopEntityList.add(obj);
        }
    }

    protected void setQueryValues(PreparedStatement statement) throws SQLException {
        int index = 1;
        for (Map.Entry<String, String> entry : columnLabelsAndTypes.entrySet()) {
            switch (entry.getValue()){
                case "string":
                    statement.setString(index, entry.getKey());
                case "int":
                    statement.setInt(index, Integer.parseInt(entry.getKey()));
                case "float":
                    statement.setFloat(index, Float.parseFloat(entry.getKey()));
                case "double":
                    statement.setDouble(index, Double.parseDouble(entry.getKey()));
            }
            index++;
        }
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    protected void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()
        ){
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
