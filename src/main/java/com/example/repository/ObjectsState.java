package com.example.repository;

import com.example.model.File;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class ObjectsState {
  
  public void states(){
    Session sess = null;
    String save = (String) sess.save(new File());
    sess.persist(new File());
    File load = sess.load(File.class, "6576");//not hit immediately, throw exception if doesn't exist
    File get = sess.get(File.class, "6576");// hit immediately, null if doesn't exist
    File lock = sess.get(File.class, "6576", LockMode.PESSIMISTIC_WRITE);
    sess.flush();//force SQL insert
    sess.refresh(lock);//re-load

    List list = sess.createQuery("")
        .setCacheable(true)
        .setParameter("0", "")
        .list();
    //pagination
    Query q = sess.createQuery("");
    q.setFirstResult(12);
    q.setMaxResults(10);

    Restrictions.eq("f", save);
  }

}
