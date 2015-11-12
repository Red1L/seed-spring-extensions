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

import org.seedstack.seed.transaction.spi.TransactionalLink;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
/**
 * 
 * @author redouane.loulou@ext.mpsa.com
 *
 */
class SpringEntityManagerLink implements TransactionalLink<EntityManager> {

	private LocalEntityManagerFactoryBean localEntityManagerFactoryBean;
	private Injector injector;

	public SpringEntityManagerLink(String springEntityManagerFactoryBeanId) {
		super();
		this.localEntityManagerFactoryBean = injector.getInstance(Key.get(
				LocalEntityManagerFactoryBean.class,
				Names.named(springEntityManagerFactoryBeanId)));
		;
	}

	@Override
	public EntityManager get() {

		EntityManagerHolder holder = (EntityManagerHolder) TransactionSynchronizationManager
				.getResource(localEntityManagerFactoryBean
						.getNativeEntityManagerFactory());
		return holder.getEntityManager();
	}

}