package com.model;

import javax.servlet.jsp.jstl.core.Config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.pojo.Demo;

public class BLManager {
	SessionFactory sf = new Configuration().configure().buildSessionFactory();

	public void saveImg(Demo d) {
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		s.save(d);
		t.commit();
		s.close();
	}

}
