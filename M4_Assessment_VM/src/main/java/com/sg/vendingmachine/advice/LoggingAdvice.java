/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.advice;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.NoItemInventoryException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author jesse
 */
public class LoggingAdvice {
    VendingMachineAuditDao auditDao;

    public LoggingAdvice(VendingMachineAuditDao auditDao) {
        this.auditDao = auditDao;
    }
    
    public void createAuditEntry(JoinPoint jp, Throwable ex)
            throws InsufficientFundsException,
            NoItemInventoryException {
            
    
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() +": "+ ex.toString() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg + ": ";
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachinePersistenceException e) {
            System.err.println(
               "ERROR: Could not create audit entry in LoggingAdvice.");
        }
 } 
}
