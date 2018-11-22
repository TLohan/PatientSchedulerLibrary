/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customDataStructures;

import interfaces.IPriority;
import interfaces.TableName;
import java.io.Serializable;
import java.sql.ResultSet;

/**
 * A sequence of Nodes sorted by their priority attribute
 * @author TLohan
 * @param <T>
 */

@TableName(name="WaitingLists")
public class PrioritySortedDoubleLinkedList<T extends IPriority> extends DoubleLinkedList<T>  implements Serializable {
    
    /**
     * Default constructor
     */
    public PrioritySortedDoubleLinkedList(){
        super();
    }
    
    /**
     * Constructor for use with the ORM
     * @param rs The result set from the ORM
     */
    public PrioritySortedDoubleLinkedList(ResultSet rs) {
        super(rs);
    }
    
   /**
    * Adds a Node to the sequence
    * @param object The object to be added to the sequence
    * @return The Node containing the object 
    */
    @Override
    public Node<T> addNode(IPriority object){
        Node<T> node = new Node(object, null, null);
        if (head == null){
            this.head = node;
        } else if (head.getObject().getPriority() < object.getPriority()) {
            Node oldHead = head;
            this.head = node;
            this.head.setNext(oldHead);
            oldHead.setPrevious(head);
        } else if (getNode(size()).getObject().getPriority() > object.getPriority()) {
            Node<T> lastNode = getNode(size());
            lastNode.setNext(node);
            node.setPrevious(lastNode);
        } else {
            Node<IPriority> tmp = (Node) head;
            for (int i = 0; i < size(); i++){
                if (object.getPriority() > tmp.getObject().getPriority()){
                    // inject it here
                    Node previousNode = tmp.getPrevious();
                    tmp.setPrevious(node);
                    node.setNext((Node) tmp);
                    node.setPrevious(previousNode);
                    previousNode.setNext(node);
                } else {
                    tmp = tmp.getNext();
                }
            }
        }
        
        incrementSize();
      
        return node;
    }
    
}
