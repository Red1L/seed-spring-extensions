package org.seedstack.seed.spring.internal;

import java.lang.reflect.Field;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.seedstack.seed.core.api.SeedException;
import org.seedstack.seed.core.internal.application.ApplicationErrorCode;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.google.common.collect.Maps;
import com.google.inject.MembersInjector;

public class SpringEntityManagerMembersInjector<T> implements MembersInjector<T> {

	private Field field;
	private static final Map<String, EntityManager> ENTITY_MANAGERS = Maps.newConcurrentMap();
	private String unit;
	public SpringEntityManagerMembersInjector(Field field, String unit) {
		this.field = field;
		this.unit = unit;
		this.field.setAccessible(true);
		Map<Object,Object> resources=TransactionSynchronizationManager.getResourceMap();
		for (  Map.Entry<Object,Object> entry : resources.entrySet()) {
			if (entry.getKey() instanceof EntityManagerFactory) {
				EntityManagerFactory emf=(EntityManagerFactory)entry.getKey();
				emf.getProperties();
				ENTITY_MANAGERS.put("default", ((EntityManagerHolder)entry.getValue()).getEntityManager());
			}
		}
		throw SeedException.createNew(ApplicationErrorCode.CONFIGURATION_ERROR);
	}

	@Override
	public void injectMembers(T instance) {
		try {
			field.set(instance, ENTITY_MANAGERS.get(unit));
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

}
