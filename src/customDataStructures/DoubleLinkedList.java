/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customDataStructures;

import ORM.ObjectMapper;
import interfaces.ForeignKey;
import interfaces.IDoubleLinkedList;
import interfaces.Ignore;
import interfaces.PrimaryKey;
import interfaces.TableName;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * This is a DoubleLinkedList DataStructure made up of generic Nodes of type T.
 * The first Node in the list is called the Head
 * Each node knows the address of it's following node
 * The last node is the node who's next node is null
 * @author TLohan
 * @param <T>
 */

// ORM Decorator
@TableName(name="WaitingLists")
public class DoubleLinkedList<T> extends ObjectMapper implements Serializable, IDoubleLinkedList {
    
    @PrimaryKey
    private int doubleLinkedList_id;
    
    @ForeignKey(cls=Node.class, columnName = "head_id")
    protected Node<T> head;
    private int size;
    @Ignore
    public ArrayList<T> arrList;
    
    /**
     * Default constructor
     */
    public DoubleLinkedList(){
        this.head = null;
        this.size = 0;
    }
    
    /**
     * Constructor for the ORM
     * @param rs - The result set received from the ORM
     */
    public DoubleLinkedList(ResultSet rs){
        super(rs);
    }
    
    /**
     * Returns the DLL's Id
     * @return int - id
     */
    public int getDoubleLinkedList_id() {
        return doubleLinkedList_id;
    }
    
    /**
     * Sets the DLL's id
     * @param doubleLinkedList_id - int - the ID
     */
    public void setDoubleLinkedList_id(int doubleLinkedList_id) {
        this.doubleLinkedList_id = doubleLinkedList_id;
    }
    
    /**
     * Sets the number of Nodes in the DLL
     * @param size - The number of nodes
     */
    public void setSize(int size) {
        this.size = size;
    }
    
    /**
     * Gets the first node in the list
     * @return Node of type T. Null if list is empty.
     */
    public Node<T> getHead() {
        return head;
    }
    
    /**
     * Sets the first element in the list
     * @param head - Node of type T. 
     */
    @Override
    public void setHead(Node head) {
        this.head = head;
    }
    
    /**
     * Increase the size of the list by 1.
     */
    public void incrementSize(){
        this.size++;
    }
    
    /**
     * Returns the number of Nodes in the list.
     * @return int - the number of nodes in the list.
     */
    public int size() {
        return size;
    }
    
    /**
     * Returns the Node at a specific index in the list.
     * @param pos The index of the Node to be returned
     * @return Node of type T at the index 'pos'
     */
    public Node<T> getNode(int pos){
        Node<T> node = getHead();
        if (pos == 1){
            return node;

        } else {
            for (int i = 1; i < pos; i++){
               node = node.getNext();
            }
        }
        return node;

    }
    
    /**
     * Adds a Node to the end of the List.
     * @param object The object of Type T to be added to the list.
     * @return Node of type T
     */
    public Node<T> addNode(T object){
        Node<T> node = new Node(object, null, null);
        if (head == null){
            this.head = node;
        } else {
            Node<T> currentTailNode = getNode(size);
            node.setPrevious(currentTailNode);
            currentTailNode.setNext(node);
        }
        ++size;
        return node;
    }
    
    /**
     * Removes the Node at a specific index from the List
     * @param pos The index of the Node to be removed.
     */
    public void remove(int pos){
        if (pos == 1){
            this.head = head.getNext();
            head.setPrevious(null);
        } else if (pos == size && size > 1){
            Node<T> node = getNode(pos - 1);
            node.setNext(null);
        }
    }
    
    
    @Override
    public String toString(){
        String output = "";
        for (int i = 1; i <= size(); i++) {
            output += getNode(i) + "\n";
        }
        return output;
    }
    
    /**
     * Converts the List to an ArrayList
     * @return An ArrayList of type T
     */
    public ArrayList<T> getArrList(){
       arrList = new ArrayList<>();
       Node<T> node = head;
        for (int i = 0; i < size(); i++) {
            arrList.add(node.getObject());
            node = node.getNext();
        }
        return arrList;
    }
    
    /**
     * Removes the last Node of the list and returns it's element
     * @return The object of type T in the last Node in the list
     */
    public T pop(){
        T object = this.head.getObject();
        
        if (this.head.hasNext()){
            this.head = head.getNext();
            head.setPrevious(null);
        } else {
            this.head = null;
        }
        
        --size;
        
        return object;
    }

    
    public static void main(String[] args) {
        DoubleLinkedList<Integer> dll = new DoubleLinkedList<>();
        
        int n1 = 256;
        int n2= 112;
        int n3 = 450;
        int n4 = 4528;

        
        dll.addNode(n1);
        dll.addNode(n2);
        dll.addNode(n3);
        dll.addNode(n4);
        
       System.out.println(dll);
    }

  
}
