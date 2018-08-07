/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.advice;

import com.sg.flooringmastery.dao.FlooringMasteryAuditDao;
import com.sg.flooringmastery.dao.PersistenceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author Chad
 */
public class LoggingAdvice {
    FlooringMasteryAuditDao auditDao;
    
    public LoggingAdvice(FlooringMasteryAuditDao auditDao){
        this.auditDao = auditDao;
    }
    
     public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = "Order made";
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (PersistenceException ex) {
            System.err.println(
               "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
}
