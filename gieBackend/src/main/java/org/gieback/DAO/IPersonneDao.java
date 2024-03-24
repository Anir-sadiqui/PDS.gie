package org.gieback.DAO;

import org.gieback.Entity.Contact;
import org.gieback.Entity.Personne;

import java.util.List;
 public interface IPersonneDao {





     List<Personne> getAll();

     void add(Personne p);
     Personne getById(int id);
 }

