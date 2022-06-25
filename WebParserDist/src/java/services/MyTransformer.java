/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Address;
import entity.Person;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author denis
 */
public class MyTransformer {
    public static final File file = new File("Persons.xml");
    public static final File fileDOM = new File("PersonsDom.xml");
    static{
        try {
            if(!file.exists()) file.createNewFile();
            if(!fileDOM.exists()) fileDOM.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(MyTransformer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void createXML(List<Person> personList){
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append("<persons>\n");
        for(Person p : personList){
            sb.append("<person ").append("id=\"").append(p.getId()).append("\" ");
            sb.append("name=\"").append(p.getName()).append("\" ");
            sb.append("phone=\"").append(p.getPhone()).append("\" ");
            sb.append("email=\"").append(p.getEmail()).append("\" />\n");
        }
        sb.append("</persons>");
        try(FileWriter writer = new FileWriter(file) ){
            writer.write(sb.toString());
        }catch(IOException e){}
    }
    
    public static void createDomXML(List<Person> personList){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element root = document.createElement("persons");
            document.appendChild(root);
            for(Person p : personList){
                Element child = document.createElement("person");
                child.setAttribute("id", String.valueOf(p.getId()));
                child.setAttribute("name", p.getName());
                child.setAttribute("phone", p.getPhone());
                child.setAttribute("email", p.getEmail());
                List<Address> addressList = p.getAddressList();
                if(addressList!=null && addressList.size()>0){
                    for(Address adr : addressList){
                        Element secondChild = document.createElement("address");
                        secondChild.setAttribute("id", String.valueOf(adr.getId()));
                        secondChild.setAttribute("address", adr.getAddress());
                        secondChild.setAttribute("region", adr.getRegion());
                        secondChild.setAttribute("country", adr.getCountry());
                        child.appendChild(secondChild);
                    }
                }
                root.appendChild(child);
            }
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource doms = new DOMSource(document);
            StreamResult result = new StreamResult(fileDOM);
            transformer.transform(doms, result);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(MyTransformer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(MyTransformer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
