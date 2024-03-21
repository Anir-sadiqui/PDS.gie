package org.gieback.DAO;

import org.gieback.Entity.Contact;

import java.util.List;
 public interface IContactDao {



     void create(String nom ,String adresse,String telephone,String email,int code_postal);


     List<Contact> getAll();

     void add(Contact client);
 }

