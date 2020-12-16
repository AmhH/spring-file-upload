package com.example.repository;

import com.example.model.File;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.LockMode;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.spi.SessionFactoryDelegatingImpl;

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

    //for filtering
    Restrictions.eq("f", save);
    sess.saveOrUpdate(save);
  }

  public void replicationDataStores(){
    //retrieve a cat from one database
    SessionFactory factory1 = null;
    SessionFactory factory2 = null;
    Session session1 = factory1.openSession();
    Transaction tx1 = session1.beginTransaction();
    File cat = session1.get(File.class, "fileId");
    tx1.commit();
    session1.close();

//reconcile with a second database
    Session session2 = factory2.openSession();
    Transaction tx2 = session2.beginTransaction();
    session2.replicate(cat, ReplicationMode.LATEST_VERSION);
    tx2.commit();
    session2.close();
  }
  /**
   * Flush
   *  flush, occurs by default at the following points:
   *
   * before some query executions
   * from org.hibernate.Transaction.commit()
   * from Session.flush()
   * The SQL statements are issued in the following order:
   * all entity insertions in the same order the corresponding objects were saved using Session.save()
   * all entity updates
   * all collection deletions
   * all collection element deletions, updates and insertions
   * all collection insertions
   * all entity deletions in the same order the corresponding objects were deleted using Session.delete()
   */

}
