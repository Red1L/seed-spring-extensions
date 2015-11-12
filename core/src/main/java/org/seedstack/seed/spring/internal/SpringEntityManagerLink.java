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

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.seedstack.seed.core.api.SeedException;
import org.seedstack.seed.core.internal.application.ApplicationErrorCode;
import org.seedstack.seed.transaction.spi.TransactionalLink;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;
/**
 * 
 * @author redouane.loulou@ext.mpsa.com
 *
 */
class SpringEntityManagerLink implements TransactionalLink<EntityManager> {

	@Override
	public EntityManager get() {
	  Map<Object,Object> resources=TransactionSynchronizationManager.getResourceMap();
	  for (  Map.Entry<Object,Object> entry : resources.entrySet()) {
	    if (entry.getKey() instanceof EntityManagerFactory) {
	      EntityManagerFactory emf=(EntityManagerFactory)entry.getKey();
	      return ((EntityManagerHolder)entry.getValue()).getEntityManager();
	    }
	  }
	  throw SeedException.createNew(ApplicationErrorCode.CONFIGURATION_ERROR);
	}

}