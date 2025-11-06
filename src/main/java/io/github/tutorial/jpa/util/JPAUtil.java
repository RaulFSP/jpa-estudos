/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.tutorial.jpa.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author usuario
 */
public final class JPAUtil {

    private static volatile JPAUtil instance;
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev-profile");
    
    private JPAUtil() {
    }
    
    public static JPAUtil getInstance() {
        JPAUtil result = instance;
        if (result == null){
            synchronized(emf){
                result = instance;
                if(result == null){
                    instance = new JPAUtil();
                }
            }
        }
        return instance;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }
    
}
