/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gruposb.servicos.activeDiretory;

import java.awt.HeadlessException;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.security.sasl.AuthenticationException;
import javax.swing.JOptionPane;

/**
 *
 * @author douglas
 */
public class ActiveDiretoryService {

    public static boolean AutenticarAD(String userName, String Password) {

        Hashtable authEnv = new Hashtable(11);
        DirContext obDirContext = null;
        boolean bResult = false;

        authEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        authEnv.put(Context.PROVIDER_URL, "ldap://172.16.1.51:389");
        authEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
        authEnv.put(Context.SECURITY_PRINCIPAL, userName);
        authEnv.put(Context.SECURITY_CREDENTIALS, Password);
        
        try {
            // Cria um Initial Context
            obDirContext = new InitialDirContext(authEnv);
        } catch (NamingException ne) {
            System.out.println("AutenticarAD -> AutenticarAD");
            ne.printStackTrace();
        } finally {
            if (obDirContext != null) {
                bResult = true;
            }
        }
        return bResult;

    }

}
