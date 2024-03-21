package org.gieback.Service;

import org.gieback.Entity.Contact;

import java.util.List;
public interface IContactService extends IService<Contact> {


        void create(String nom ,String adresse,String telephone,String email,int code_postal);

        List<Contact> getAll();


    }





