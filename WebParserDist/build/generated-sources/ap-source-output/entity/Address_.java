package entity;

import entity.Person;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-13T21:27:28")
@StaticMetamodel(Address.class)
public class Address_ { 

    public static volatile SingularAttribute<Address, String> country;
    public static volatile SingularAttribute<Address, String> address;
    public static volatile SingularAttribute<Address, Person> personId;
    public static volatile SingularAttribute<Address, Integer> id;
    public static volatile SingularAttribute<Address, String> region;

}