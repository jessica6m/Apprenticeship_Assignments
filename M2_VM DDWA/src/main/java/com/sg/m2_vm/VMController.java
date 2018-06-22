/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.m2_vm;

import com.sg.m2_vm.service.Service;
import java.math.BigDecimal;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author jesse
 */
@Controller
public class VMController {

    Service service;

    @Inject
    public VMController(Service service) {
        this.service = service;
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String returnIndex(Model model) {
        model.addAttribute("amountTendered", service.getAmountEntered());
        model.addAttribute("itemList", service.getAllItems());
        model.addAttribute("itemNumber", service.getItemSelected());
        model.addAttribute("message", service.getMessage());
        model.addAttribute("change", service.getChangeMessage());
        return "index";
    }

    @RequestMapping(value = "/addMoney/dollar", method = RequestMethod.GET)
    public String addDollar(Model model, HttpServletRequest request) {
        service.addChange(new BigDecimal("1"));
        return "redirect:/";
    }

    @RequestMapping(value = "/addMoney/quarter", method = RequestMethod.GET)
    public String addQuarter(Model model, HttpServletRequest request) {
        service.addChange(new BigDecimal(".25"));
        return "redirect:/";
    }

    @RequestMapping(value = "/addMoney/nickel", method = RequestMethod.GET)
    public String addNickel(Model model, HttpServletRequest request) {
        service.addChange(new BigDecimal(".05"));
        return "redirect:/";
    }

    @RequestMapping(value = "/addMoney/dime", method = RequestMethod.GET)
    public String addDime(Model model, HttpServletRequest request) {
        service.addChange(new BigDecimal(".10"));
        return "redirect:/";
    }

    @RequestMapping(value = "/change/return", method = RequestMethod.GET)
    public String changeReturn(Model model, HttpServletRequest request) {      
       service.changeReturn();
        return "redirect:/";
    }
    
    
    @RequestMapping(value = "/purchase", method = RequestMethod.GET)
    public String makePurchase(Model model, HttpServletRequest request) {
        service.purchaseItem();
        return "redirect:/";
    }
    
    @RequestMapping(value = "/displayItemsPage/{itemNumber}", method = RequestMethod.GET)
    public String getItemSelected(Model model, @PathVariable String itemNumber) {
        service.setItemSelected(itemNumber);
        return "redirect:/";
    }
    
    

}
