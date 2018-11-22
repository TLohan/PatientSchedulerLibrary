/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import ORM.ObjectMapper;

/**
 *
 * @author TLohan
 */
public interface IServerDatabase extends IDatabase {
    
    public Object get(Class<?> objectClass, int primaryKey);
    public int addToDB(String statement);
    public void populateWaitLists();
    
}
