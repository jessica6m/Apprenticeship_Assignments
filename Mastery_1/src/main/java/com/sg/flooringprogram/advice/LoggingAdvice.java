/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringprogram.advice;

import com.sg.flooringprogram.dao.FlooringProgramAuditDao;
import com.sg.flooringprogram.dao.FlooringProgramPersistenceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author jesse
 */
public class LoggingAdvice {
 
    FlooringProgramAuditDao auditDao;
 
    public LoggingAdvice(FlooringProgramAuditDao auditDao) {
        this.auditDao = auditDao;
    }
 
    public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringProgramPersistenceException e) {
            System.err.println(
               "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
} 
