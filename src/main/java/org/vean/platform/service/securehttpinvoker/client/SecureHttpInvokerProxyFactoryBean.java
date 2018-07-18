package org.vean.platform.service.securehttpinvoker.client;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.FactoryBean;

public class SecureHttpInvokerProxyFactoryBean extends SecureHttpInvokerClientInterceptor implements FactoryBean<Object> {
    private Object serviceProxy;

    public SecureHttpInvokerProxyFactoryBean() {
    }

    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        if (this.getServiceInterface() == null) {
            throw new IllegalArgumentException("Property 'serviceInterface' is required");
        } else {
            this.serviceProxy = (new ProxyFactory(this.getServiceInterface(), this)).getProxy(this.getBeanClassLoader());
        }
    }

    public Object getObject() {
        return this.serviceProxy;
    }

    public Class<?> getObjectType() {
        return this.getServiceInterface();
    }

    public boolean isSingleton() {
        return true;
    }
}

