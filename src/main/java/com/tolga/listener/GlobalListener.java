package com.tolga.listener;

import java.io.FileNotFoundException;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.tolga.initializer.DbInitializer;
import com.tolga.initializer.SmtpClient;

/**
 * Application Lifecycle Listener implementation class GlobalListener
 *
 */
public class GlobalListener implements ServletContextAttributeListener, ServletContextListener {

	/**
	 * Default constructor.
	 */
	public GlobalListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
	 */
	public void attributeAdded(ServletContextAttributeEvent event) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
	 */
	public void attributeRemoved(ServletContextAttributeEvent event) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
	 */
	public void attributeReplaced(ServletContextAttributeEvent event) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {

		DbInitializer dbInitializer = new DbInitializer(sce.getServletContext());
		dbInitializer.init();
		try {
			dbInitializer.saveSampleCompanies();
			dbInitializer.saveSampleJobAdverts();
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SmtpClient smtpClient = new SmtpClient(sce.getServletContext());
		smtpClient.init();
	}

}
