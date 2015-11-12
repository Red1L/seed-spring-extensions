/**
 * Copyright (c) 2013-2015 by The SeedStack authors. All rights reserved.
 *
 * This file is part of SeedStack, An enterprise-oriented full development stack.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
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
		//TODO Get Spring EntityManagerFactory bean name from Spring configuration or Seed props using a plugin?
		//TODO Disable JpaPlugin
		SpringEntityManagerLink springEntityManagerLink = new SpringEntityManagerLink("myEntityManagerFactory");
		requestInjection(springEntityManagerLink);
		bind(EntityManager.class).toInstance(TransactionalProxy.create(EntityManager.class, springEntityManagerLink));
	}

}