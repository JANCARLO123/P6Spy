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
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

                Map<String, Object> settings = new HashMap<String, Object>();
                settings.put(Environment.DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
                settings.put(Environment.URL, "jdbc:p6spy:mysql://127.0.0.1:3306/example1?useSSL=false&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC");

                //settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                //settings.put(Environment.URL, "jdbc:mysql://127.0.0.1:3306/example1?useSSL=false&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC");
                settings.put(Environment.USER, "user");
                settings.put(Environment.PASS, "Pa$$w0rd");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                //settings.put(Environment.SHOW_SQL, true);



                registryBuilder.applySettings(settings);
                registry = registryBuilder.build();
                // Create MetadataSources
                MetadataSources sources = new MetadataSources(registry).addAnnotatedClass(Person.class);
                // Create Metadata
                Metadata metadata = sources.getMetadataBuilder().build();
                // Create SessionFactory
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

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}

