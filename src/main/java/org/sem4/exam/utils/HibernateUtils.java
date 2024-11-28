package org.sem4.exam.utils;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/11/28
 */
public class HibernateUtils {
    @Getter
    private static final SessionFactory sessionFactory;

    private HibernateUtils() {
    }

    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

}
