package org.seedstack.seed.spring.internal;

import javax.persistence.EntityManager;

import org.seedstack.seed.core.api.Install;
import org.seedstack.seed.transaction.utils.TransactionalProxy;

import com.google.inject.AbstractModule;

/**
 * 
 * @author redouane.loulou@ext.mpsa.com
 *
 */
@Install
public class SpringEntityManagerModule extends AbstractModule {

	@Override
	protected void configure() {
		//TODO Add multiple EntityManagerFactory support
		//TODO Get Spring EntityManagerFactory bean name from Spring configuration or Seed props?
		//TODO Disable JpaPlugin
		SpringEntityManagerLink springEntityManagerLink = new SpringEntityManagerLink("myEntityManagerFactory");
		requestInjection(springEntityManagerLink);
		bind(EntityManager.class).toInstance(TransactionalProxy.create(EntityManager.class, springEntityManagerLink));
	}

}
