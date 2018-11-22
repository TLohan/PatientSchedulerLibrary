/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customDataStructures;

import ORM.ObjectMapper;
import entityClasses.Patient;
import interfaces.ForeignKey;
import interfaces.IServerDatabase;
import interfaces.PrimaryKey;
import java.io.Serializable;
import java.sql.ResultSet;
 
/**
 * A container for objects of type T
 * @author TLohan
 * @param <T>
 */


public class Node<T> extends ObjectMapper implements Serializable {
    

    @PrimaryKey
    private int node_id;
    
    @ForeignKey(cls=Patient.class, columnName = "patient_id")
    private T object;
    
    @ForeignKey(cls= Node.class, columnName = "next_id", persistNestedClasses = false)
    private  Node next;
    
    @ForeignKey(cls=Node.class, columnName = "previous_id", persistNestedClasses = false)
    private Node previous;
    
    /**
     * Default constructor
     */
    public Node(){
       
    }
    
    /**
     * Constructor
     * @param object The object of type T to be contained in the Node
     * @param previous The location of the preceding Node
     * @param next The location of the following Node
     */
    public Node(T object, Node previous, Node next){
        setObject(object);
        setNext(next);
        setPrevious(previous);
    }
    
    /**
     * Getter for the Node's Id
     * @return int - The id of the Node
     */
    public int getNode_id() {
        return node_id;
    }
    
    /**
     * Setter for the Node's Id
     * @param node_id The id to be set
     */
    public void setNode_id(int node_id) {
        this.node_id = node_id;
    }
    
    /**
     * Gets the object contained in the Node
     * @return Object of type T
     */
    public T getObject() {
        return object;
    }

    /**
     * Sets the object contained in the Node
     * @param object The object of type T to be contained in the Node
     */
    public void setObject(T object) {
        this.object = object;
    }
    
    /**
     * Gets the following Node in the List
     * @return The Node following this one
     */
    public Node getNext() {
        return next;
    }
    
    /**
     * Sets the location of the Node following this one
     * @param next The next Node in the list
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * Gets the preceding Node in the list
     * @return The preceding Node to this one
     */
    public Node getPrevious() {
        return previous;
    }
    
    /**
     * Sets  the preceding Node in the list
     * @param previous The preceding Node in the List
     */
    public void setPrevious(Node previous) {
        this.previous = previous;
    }
    
    /**
     * Checks if this Node is the last Node in the sequence
     * @return Boolean - true if there is another Node in the sequence
     */
    public boolean hasNext(){
        return getNext() != null;
    }
     /**
      * Checks if this is the Head of the sequence
      * @return Boolean- True if this is the head of the sequence
      */
    public boolean  hasPrevious(){
        return getPrevious() != null;
    }
    
    
    @Override
    public String toString(){
        return getObject().toString();
    }
    
    /**
     * Saves the Node to the Database
     * If the Node has a preceding or following Nodes, those Node's Next and Previous values are also updated
     * @param database The database object being used.
     * @return Boolean - True if the Node was saved successfully.
     */
    @Override
    public boolean save(IServerDatabase database){
        super.save(database);

        Node previousNode = this.getPrevious();
        Node nextNode = this.getNext();
  
        if (previousNode != null) updateExistingRecord(previousNode.getMemberVariables());
        
        if (nextNode != null) updateExistingRecord(nextNode.getMemberVariables());
        
        return true;
    }

}
