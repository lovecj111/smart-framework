package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.ReflectionUtil;
import java.lang.reflect.Field;
import java.util.Map;

/**
 *  依赖注入助手类
 */
public final class IocHelper {

    static{
        //获取所有的Bean类与Bean实例之间的映射关系(简称BeanMap)
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if(CollectionUtil.isNotEmpty(beanMap)){
            //遍历BeanMap
            for(Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()){
                //从BeanMap中获取Bean类与Bean实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                //获取Bean类定义的所有成员变量(简称BeanField)
                Field[] beanFields = beanClass.getDeclaredFields();
                if(ArrayUtil.isNotEmpty(beanFields)){
                    //遍历BeanField
                    for(Field beanField : beanFields){
                        //判断当前beanField是否带有Inject注解
                        if(beanField.isAnnotationPresent(Inject.class)){
                            //在BeanMap中获取BeanField对应的实例
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if(beanFieldInstance != null){
                                //通过反射初始化beanField的值
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
