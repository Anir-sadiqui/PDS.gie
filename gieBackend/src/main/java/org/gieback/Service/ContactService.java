package org.gieback.Service;

import org.gieback.DAO.ContactDao;
import org.gieback.DAO.IContactDao;
import org.gieback.Entity.Contact;

import java.util.List;

public class ContactService implements IContactService {

    IContactDao dao = new ContactDao();





    @Override
    public void create(Contact contact) {

    }

    @Override
    public void ajouter(Contact client) {


       dao.add(client);
    }

    @Override
    public List<Contact> getAll() {
        return null;
    }

    @Override
    public String toString() {
        return "ContactService{" +
                "dao=" + dao +
                '}';
    }
}
