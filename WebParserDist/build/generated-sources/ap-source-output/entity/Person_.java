package entity;

import entity.Address;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-13T21:27:28")
@StaticMetamodel(Person.class)
public class Person_ { 

    public static volatile ListAttribute<Person, Address> addressListTemp;
    public static volatile SingularAttribute<Person, String> phone;
    public static volatile ListAttribute<Person, Address> addressList;
    public static volatile SingularAttribute<Person, String> name;
    public static volatile SingularAttribute<Person, Integer> id;
    public static volatile SingularAttribute<Person, String> email;

}