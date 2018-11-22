/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import ORM.ObjectMapper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author TLohan
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface ForeignKey {
    Class<?> cls();
    String columnName();
    public boolean persistNestedClasses() default true;
}
