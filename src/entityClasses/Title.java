/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClasses;

import interfaces.IObjectMapper;
import java.io.Serializable;

/**
 *
 * @author TLohan
 */
public enum Title implements Serializable{
    MRS ("Mrs"),
    MR ("Mr."),
    MS ("Ms."),
    DR ("Dr."),
    PROF ("Prof."),
    OTHER ("Other")
    ;
    
    private final String Title;
    
    private Title(String title){
        this.Title = title;
    }
    
    public String getTitleString(){
        return Title;
    }
}
