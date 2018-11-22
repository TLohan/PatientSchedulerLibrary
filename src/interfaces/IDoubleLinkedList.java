/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import customDataStructures.Node;

/**
 *
 * @author TLohan
 */
public interface IDoubleLinkedList<T> {
    public void incrementSize();
    public void setDoubleLinkedList_id(int number);
    public void setHead(Node node);
}
