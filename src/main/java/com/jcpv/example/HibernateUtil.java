package com.jcpv.example;

import com.jcpv.example.entity.Person;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JanCarlo on 14/06/2017.
 */
public class HibernateUtil {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory != null) {
            try {


                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
                Map<String, String> settings = new HashMap<String, String>();
                settings.put(Environment.DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
                settings.put(Environment.URL, "jdbc:p6spy:mysql://localhost:3306/example1");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "Pa$$w0rd");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                registryBuilder.applySettings(settings);
                registry = registryBuilder.build();
                MetadataSources sources = new MetadataSources(registry).addAnnotatedClass(Person.class);
                Metadata metadata = sources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
                e.printStackTrace();
            }

        }
        return sessionFactory;
    }
        public static void shutdown(){
            if (registry != null) {
                StandardServiceRegistryBuilder.destroy(registry);
            }
        }
}

