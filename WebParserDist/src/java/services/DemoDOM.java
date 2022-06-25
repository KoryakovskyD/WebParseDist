/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author denis
 */
public class DemoDOM {
    
    public List<Person> getPersonByName(String filterName){
        List<Person> list = new LinkedList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(MyTransformer.fileDOM);
            Element root = document.getDocumentElement();
            NodeList childList = root.getChildNodes();
            for(int i=0 ; i<childList.getLength() ; i++){
                Node node = childList.item(i);
                if(node.getNodeType()==Node.ELEMENT_NODE && node.getNodeName().equals("person")){
                    Element child = (Element)node;
                    String filt = child.getAttribute("name");
                    if(filt.toLowerCase().contains(filterName.toLowerCase())){
                        Person person = new Person();
                        person.setId(Integer.parseInt(child.getAttribute("id")));
                        person.setName(child.getAttribute("name"));
                        person.setPhone(child.getAttribute("phone"));
                        person.setEmail(child.getAttribute("email"));
                        NodeList secondChild = node.getChildNodes();
                        for(int sec=0 ; sec<secondChild.getLength() ; sec++){
                            Node nodeSec = secondChild.item(sec);
                            if(nodeSec.getNodeType() == Node.ELEMENT_NODE){
                                Element tempElem = (Element)nodeSec;
                                Address address = new Address(Integer.parseInt(tempElem.getAttribute("id")));
                                address.setAddress(tempElem.getAttribute("address"));
                                address.setCountry(tempElem.getAttribute("country"));
                                address.setRegion(tempElem.getAttribute("region"));
                                person.addAddress(address);
                            }
                        }
                        list.add(person);
                    }
                }
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DemoDOM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DemoDOM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DemoDOM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
