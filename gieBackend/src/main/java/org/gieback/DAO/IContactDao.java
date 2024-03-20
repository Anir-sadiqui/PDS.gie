package org.gieback.DAO;

import org.gieback.Entity.Contact;

import java.util.List;
 public interface IContactDao {



     void create(Contact contact);

     List<Contact> getAll();

     void add(Contact client);
 }

