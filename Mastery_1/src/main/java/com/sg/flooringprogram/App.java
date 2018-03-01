/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringprogram;

import com.sg.flooringprogram.controller.FlooringProgramController;
import com.sg.flooringprogram.dao.FlooringProgramPersistenceException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author jesse
 */
public class App {
	    
	    public static void main(String[] args) {
//	        UserIO myIo = new UserIOConsoleImpl();
//                FlooringProgramView view = new FlooringProgramView(myIo);
//                FlooringProgramDao dao = new FlooringProgramDaoFileImpl();
//                FlooringProgramAuditDao auditDao = new FlooringProgramAuditDaoFileImpl();
//                FlooringProgramServiceLayer service = new FlooringProgramServiceLayerImpl(auditDao, dao);
//                FlooringProgramController controller = new FlooringProgramController(view, service);
//	        controller.run();

                SettingsReader setting = new SettingsReader();
                String controllerName = "";
                
                try {
                    controllerName = setting.readSettingsFile();
                } catch (FlooringProgramPersistenceException e) {
                    return;
                }
                System.out.println(controllerName);

                ApplicationContext ctx
                        = new ClassPathXmlApplicationContext("applicationContext.xml");
                
                FlooringProgramController controller
                        = ctx.getBean(controllerName, FlooringProgramController.class);
                controller.run();
	    }   
	}
	
