package org.gieback.Service;

import org.gieback.DAO.ContactDao;
import org.gieback.DAO.IContactDao;
import org.gieback.Entity.Contact;

import java.util.List;

public class ContactService implements IContactService {

    IContactDao dao = new ContactDao();




    @Override
    public  void ajouter(Contact client) {


       dao.add(client);
    }

    @Override
    public void create(String nom, String adresse, String telephone, String email, int code_postal) {
            Contact c =new Contact(nom,adresse,telephone,email,code_postal);
             dao.add(c);
    }

    @Override
    public List<Contact> getAll() {
        return dao.getAll();
    }

    @Override
    public String toString() {
        return "ContactService{" +
                "dao=" + dao +
                '}';
    }
}
