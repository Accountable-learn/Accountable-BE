package com.accountable.utilities;

import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class EntityUtils {
    @SneakyThrows
    public static <T extends Serializable> T clone(T object) {
        T clone = (T) getInstanceOf(object.getClass());
        BeanUtils.copyProperties(object, clone);
        return (T) clone;
    }

    public static <T extends Serializable> T getInstanceOf(Class<T> aClass)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        return aClass.getDeclaredConstructor().newInstance();
    }

    /**
     * Idempotently provides and update of one entity (existingEntity) by merging in the non-null
     * values from another entity (updateEntity).
     *
     * @return a new Object matching the type of the existingEntity
     */
    public static <T extends Serializable> T mergeEntitiesDelta(
            T existingEntity, Serializable updateEntity) {
        T idempotentExistingEntity = EntityUtils.clone(existingEntity);
        // This is a Spring utility designed to copy attributes from one Bean
        // (updateStudy) to another (idempotentExistingStudy).
        // It also takes a list of Fields to ignore. getNullPropertyNames() builds a
        // list of null attributes.
        BeanUtils.copyProperties(
                updateEntity, idempotentExistingEntity, EntityUtils.getNullPropertyNames(updateEntity));
        return (T) idempotentExistingEntity;
    }

    /**
     * Returns an array of names of the of properties of an object that are null
     *
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            // check if value of this property is null then add it to the collection
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    // https://stackoverflow.com/questions/5503412/java-standard-library-to-convert-field-name-firstname-to-accessor-method-na/5503534#5503534
    public static Method findGetter(Class clazz, String field)
            throws IntrospectionException, NoSuchFieldException {
        BeanInfo info = Introspector.getBeanInfo(clazz);
        for (PropertyDescriptor pd : info.getPropertyDescriptors())
            if (field.equals(pd.getName())) return pd.getReadMethod();
        throw new NoSuchFieldException(clazz + " has no field " + field);
    }

}
