/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.advice;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import org.aspectj.lang.JoinPoint;
import org.apache.commons.lang3.exception.ExceptionUtils;


/**
 *
 * @author Chad
 */
public class LoggingAdvice {
    VendingMachineAuditDao auditDao;
 
    public LoggingAdvice(VendingMachineAuditDao auditDao) {
        this.auditDao = auditDao;
    }
 
    public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry="Item: ";
        /*String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }*/

        Object currentArg;
        currentArg = args[0];
        auditEntry += currentArg;
        

        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachinePersistenceException e) {
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
    
    public void createAuditEntryForExceptions(JoinPoint jp, Throwable ex) {
        Object[] args = jp.getArgs();
        String auditEntry="Item: ";

        Object currentArg;
        currentArg = args[0];
        auditEntry += currentArg;

        auditEntry += " // Exception Thrown: " + ExceptionUtils.getRootCauseMessage(ex);
        
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachinePersistenceException e) {
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
}
