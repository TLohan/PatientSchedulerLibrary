/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ORM;

import interfaces.ForeignKey;
import interfaces.IObjectMapper;
import interfaces.IServerDatabase;
import interfaces.Ignore;
import interfaces.PrimaryKey;
import interfaces.TableName;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author TLohan
 */


public class ObjectMapper implements IObjectMapper {
    @Ignore
    protected final ArrayList<String> reservedWords = new ArrayList<>(Arrays.asList("tableName", "primaryKey"));
    @Ignore
    protected static IServerDatabase DATABASE;
    
    public ObjectMapper(){
        
    }
    
    public static void setDB(IServerDatabase db){
        DATABASE = db;
    }
    
    
    public ObjectMapper(ResultSet rs){
        parse(rs);
    }
    
    
    public void parse(ResultSet rs){
        System.out.println("parsing....");
        
        Class<?> objectClass = this.getClass();
        ArrayList<Field> fields = getFields(objectClass);
        
        
        for (Field field : fields){
            field.setAccessible(true);
            String name = field.getName();

            if (!field.isAnnotationPresent(Ignore.class) && !reservedWords.contains(name)){
                
                // get the value of the fields in the ResultSet
                try {
                    Object objectValue = null;
                    
                    if (field.isAnnotationPresent(ForeignKey.class)){
                        name = field.getAnnotation(ForeignKey.class).columnName();
                    } else if (objectClass.isAnnotationPresent(TableName.class)){
                        if (field.isAnnotationPresent(PrimaryKey.class)){
                            String tableName = objectClass.getAnnotation(TableName.class).name();
                            name = tableName.substring(0, 1).toLowerCase() + tableName.substring(1, tableName.length() - 1) + "_id";
                        }
                    }
                    
                    try {
                        objectValue = rs.getObject(name);
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                    
                    if (objectValue != null){ 

                        Class<?> fieldType = field.getType();
           
                        if (!fieldType.isEnum()){
                            if (field.isAnnotationPresent(ForeignKey.class)){
                                Class<?> fieldClass = field.getAnnotation(ForeignKey.class).cls();
                                Optional<Object> object = Optional.ofNullable( (Object)  DATABASE.get(fieldClass, (int) objectValue));
                                if (object.isPresent()){
                                   field.set(this, object.get());
                                }
                            } else {
                                 field.set(this, objectValue);
                            }   
                        } else {
                            field.set(this, Enum.valueOf((Class) field.getType(), objectValue.toString()));
                        }
                    }
            } catch (IllegalAccessException iae){
                System.err.println("Error: " + iae.getMessage());
            }
        }
        }
    }
    
    public String getClassName(){
        return this.getClass().getSimpleName();
    }
    
    protected ArrayList<Field> getFields(Class<?> objectOfClass){
        ArrayList<Field> fields = new ArrayList<>();
        fields.addAll(Arrays.asList(objectOfClass.getDeclaredFields()));
        if (objectOfClass.getSuperclass() != null){
            fields.addAll(Arrays.asList(objectOfClass.getSuperclass().getDeclaredFields()));
        }
        return fields;
    }
    
    public int getPrimaryKey(Object value){
        System.out.println(value.toString());
        int primaryKey = 0;
        Class<?> objectOfClass = value.getClass();
        ArrayList<Field> fields = getFields(objectOfClass);
        System.out.println(fields.size());
        for (Field field : fields){
            field.setAccessible(true);
            if (field.isAnnotationPresent(PrimaryKey.class)){
                try {
                    primaryKey = (int)  field.get(value);
                    return primaryKey;
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(ObjectMapper.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return primaryKey;
    }
    
    
        
    public void setPrimaryKey(int number){
        System.out.println(number);
        
        ArrayList<Field> fields = getFields(this.getClass());
        System.out.println(fields.size());
        for (Field field : fields){
            field.setAccessible(true);
            if (field.isAnnotationPresent(PrimaryKey.class)){
                try {
                    field.set(this, number);
                    
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(ObjectMapper.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    private String getTableName(Class<?> objectOfClass){
        String tableName;
         // Check for non-default tablename
        if (objectOfClass.isAnnotationPresent(TableName.class)){
            tableName = objectOfClass.getAnnotation(TableName.class).name();
        } else { // use default naming convention
            tableName = objectOfClass.getSimpleName() + "s";
        }
        return tableName;
    }
    
    @Override
    public HashMap<String, Object> getMemberVariables(){
        HashMap<String, Object> memberVariables = new HashMap<>();
        ArrayList<String> primitiveTypes = new ArrayList<>(Arrays.asList("Integer", "String", "Double"));
        Class<?> objectClass = this.getClass();
        ArrayList<Field> fields = getFields(objectClass);
        String fieldClass;
        
        String tableName = getTableName(objectClass);
        memberVariables.put("tableName", tableName);
        
        Object value;
        for (Field field : fields){
            try {
                field.setAccessible(true);
                String fieldName = field.getName();
                
                // get pertinent non-null fields:
                if (!field.isAnnotationPresent(Ignore.class) && field.get(this) != null){
                    value = field.get(this);
                    fieldClass = value.getClass().getSimpleName();
    
                    if (field.isAnnotationPresent(PrimaryKey.class)){
                        if (objectClass.isAnnotationPresent(TableName.class)){
                           tableName = objectClass.getAnnotation(TableName.class).name();
                           fieldName = tableName.substring(0, 1).toLowerCase() + tableName.substring(1, tableName.length() - 1) + "_id";
                        }
                        memberVariables.put("primaryKey", fieldName);
                        memberVariables.put(fieldName, value);
                    } else if (primitiveTypes.contains(fieldClass)){
                        memberVariables.put(fieldName, value);
                    } else if (value.getClass().isEnum()){
                        memberVariables.put(fieldName, value.toString());
                    } else if (field.isAnnotationPresent(ForeignKey.class)){
                        
                        boolean foreignKeyFlag = field.getAnnotation(ForeignKey.class).persistNestedClasses();
                        int foreignKey_id;
                        
                        if (foreignKeyFlag){ // persist this object
                            ObjectMapper nonPrimitiveObject = (ObjectMapper) value;
                            HashMap<String, Object> nestedObjectMemberVariables = nonPrimitiveObject.getMemberVariables();
                            foreignKey_id = getPrimaryKey(value);
                            String foreignKeyName = (String) nestedObjectMemberVariables.get("primaryKey");
                            
                            String foreignKeyColumnName = field.getAnnotation(ForeignKey.class).columnName();
                            System.out.println("foreignKeyColumnName: "  + foreignKeyColumnName);
                            
                            System.out.println("getMV foreignKeyField: " + foreignKeyName);
                            
                            if (foreignKey_id == 0){
                                memberVariables.put(foreignKeyColumnName, nonPrimitiveObject.saveNewRecord(nestedObjectMemberVariables));
                            } else {
                                memberVariables.put(foreignKeyColumnName, foreignKey_id);
                                updateExistingRecord(nestedObjectMemberVariables);
                            }
                            
                        } else { // dont persist this object. just save the object's foreign key.
                             foreignKey_id = getPrimaryKey(value);
                             String foreignKeyColumnName = field.getAnnotation(ForeignKey.class).columnName();
                             System.out.println("foreignKeyColumnName: "  + foreignKeyColumnName);
                             System.out.println("FOREIGN KEY: " + foreignKey_id);
                             memberVariables.put(foreignKeyColumnName, foreignKey_id);
                        }
             
                         
                    }
                } else if (field.isAnnotationPresent(ForeignKey.class) && field.get(this) == null){
                    String foreignKeyColumnName = field.getAnnotation(ForeignKey.class).columnName();
                    memberVariables.put(foreignKeyColumnName, 0);
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(ObjectMapper.class.getName()).log(Level.SEVERE, null, ex);
            } 

        }
        return memberVariables;
    }
    
    
    
    public boolean save(IServerDatabase database){
        this.DATABASE = database;
        
        return save();
      
    }
    
    private boolean save(){
            
        HashMap<String, Object> memberVariables = getMemberVariables();
            
        String primaryKeyField = (String) memberVariables.get("primaryKey");
        int primaryKey = (int) memberVariables.getOrDefault(primaryKeyField, 0);
        
        System.out.println("\nin save method.   " + primaryKeyField + ": " + primaryKey);
        
        if (primaryKey > 0){
            updateExistingRecord(memberVariables);
        } else {
            int generatedPrimaryKey = saveNewRecord(memberVariables);
            this.setPrimaryKey(generatedPrimaryKey);
        }
        
        
        return true;   
    }
    

    
    private int saveNewRecord(HashMap<String, Object> memberVariables){
        String tableName = (String) memberVariables.get("tableName");
        String insert_fields = "";
        String insert_values = "";
        
        String primaryKeyField = (String) memberVariables.get("primaryKey");
        System.out.println("saveNewRecord primarKeyField:   " + primaryKeyField);
 
        for (Map.Entry<String, Object> entry : memberVariables.entrySet()) {
            String key = entry.getKey();
            System.out.println("Member variable : " + key + "" );
            Object value = entry.getValue();
            Class<?> classOfValue = value.getClass();
            
            if (!reservedWords.contains(key) && !primaryKeyField.equals(key)){
             
                    if (String.class.isAssignableFrom(classOfValue)){
                        if (insert_fields.isEmpty() && insert_values.isEmpty()) {
                            insert_fields += " " + key;
                            insert_values = insert_values + "'" + value.toString() + "'";
                        } else {
                            insert_fields += ", " + key;
                            insert_values = insert_values + ", " + "'" + value.toString() + "'";
                        }
                    } else if (Number.class.isAssignableFrom(classOfValue)){
                       if (insert_fields.isEmpty() && insert_values.isEmpty()) {
                            insert_fields += " " + key;
                            insert_values = insert_values + value.toString();
                        } else {
                                                       
                           insert_fields += ", " + key;
                           insert_values = insert_values + ", " + value.toString();
                        } 
                    } else {
                        int fk = saveNewRecord((HashMap<String, Object>) value);
                        System.out.println("in method: " + fk);
                        System.out.println("key: " + key);
                        if (insert_fields.isEmpty()){
                            insert_fields += " " + key +"_id" ;
                            insert_values += " " + fk;
                        } else {
                            insert_fields += ", " + key + "_id";
                            insert_values += ", " + fk;
                    
                        }
                    }    
            }
        
        }
        String statement = "INSERT INTO " + tableName + " (" + insert_fields + ") VALUES (" + insert_values + ");";
        int foreignKey = DATABASE.addToDB(statement);
        setPrimaryKey(foreignKey);
        return foreignKey;
    }
    
    protected void updateExistingRecord(HashMap<String, Object> memberVariables){
        String tableName = (String) memberVariables.get("tableName");
        String columnUpdates = "";
        String primaryKeyField = (String) memberVariables.get("primaryKey");
        int primaryKey = (int) memberVariables.get(primaryKeyField);
        String insertStatement = "UPDATE %s SET %s WHERE %s=%s";
    
        for (Map.Entry<String, Object> entry : memberVariables.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            Class<?> classOfValue = value.getClass();
           
            if (!reservedWords.contains(key) && !primaryKeyField.equals(key)){
             
                if (String.class.isAssignableFrom(classOfValue)){
                    columnUpdates += String.format(" %s='%s',", key, value.toString());
                } else if (Number.class.isAssignableFrom(classOfValue)){
                    columnUpdates += String.format(" %s=%s,", key, value.toString());
                } else {
                    updateExistingRecord((HashMap<String, Object>) value);
                }             
            }
            
        }   

        if (columnUpdates.endsWith(",")){
            columnUpdates = columnUpdates.substring(0, columnUpdates.length() - 1);
        }
        String statement =  String.format(insertStatement, tableName, columnUpdates, primaryKeyField, primaryKey);
        DATABASE.addToDB(statement);
    }
}
    
    

