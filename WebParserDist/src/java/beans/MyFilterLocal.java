/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.Person;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author denis
 */
@Local
public interface MyFilterLocal {
    List<Person> filter(String filterName);
}
