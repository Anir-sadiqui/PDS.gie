package org.gieback.Service;

import org.gieback.Entity.Contact;

import java.util.List;
public interface IContactService extends IService<Contact> {


        void create(Contact contact);

        List<Contact> getAll();
    }





