package com.project.utility;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {

	private static StandardServiceRegistry ssrObj;
	private static SessionFactory sesFactObj;
	
	static {
		if(sesFactObj == null) {
			ssrObj=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
			MetadataSources mdsObj=new MetadataSources(ssrObj);
			Metadata metaObj = mdsObj.getMetadataBuilder().build();
			sesFactObj = metaObj.getSessionFactoryBuilder().build();
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sesFactObj;
	}
	
}
