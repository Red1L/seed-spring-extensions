/**
 * 
 */
package org.seedstack.seed.spring.internal;

import java.lang.reflect.Field;
import java.util.Map;

import javax.inject.Inject;

import org.seedstack.seed.persistence.jpa.api.JpaUnit;

import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

/**
 * @author E396976
 *
 */
public class SpringEntityManagerTypeListener implements TypeListener  {

	@Override
	public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {
        for (Class<?> c = type.getRawType(); c != Object.class; c = c.getSuperclass()) {
            for (Field field : c.getDeclaredFields()) {
            	JpaUnit jpaUnit = field.getAnnotation(JpaUnit.class);
                if (Map.class.isAssignableFrom(field.getType()) && jpaUnit != null && field.getAnnotation(Inject.class)!=null) {
                    encounter.register(new SpringEntityManagerMembersInjector<I>(field, jpaUnit.value()));
                }
            }
        }
	}

}
