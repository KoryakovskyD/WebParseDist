/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.MyRepositoryLocal;
import entity.Person;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import services.DemoDOM;
import services.MyTransformer;

/**
 *
 * @author denis
 */
@Stateless
public class MyFilter implements MyFilterLocal {
    @EJB
        MyRepositoryLocal repo;

    @Override
    public List<Person> filter(String filterName) {
        List<Person> list = repo.getAllPerson();
        MyTransformer.createXML(list);
        MyTransformer.createDomXML(list);
        list = new DemoDOM().getPersonByName(filterName);
        return list;
    }

    
}
