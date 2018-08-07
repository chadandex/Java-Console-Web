package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.model.Vending;
import com.sg.vendingmachinespringmvc.service.VendingServiceLayer;
import com.sg.vendingmachinespringmvc.ui.VendingView;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Chad
 */
@Controller
public class VendingController {
    VendingServiceLayer service;
    VendingView view;
    private final int maxItems = 9;
     
    @Inject
    public VendingController(VendingServiceLayer service, VendingView view) {
        this.service = service;
        this.view = view;
    }
    
    private void clearVending() {
        service.clearVending();
        view.clearSystemMessage();
    }

    /* -- Main Stuff -- */
    @RequestMapping(value = "/displayVending", method = RequestMethod.GET)
    public String displayVending(Model model) {
        service.loadOnce();
   
        List<Vending> vendingList = service.getAllVending();
       
        model.addAttribute("vendingList", vendingList);
        model.addAttribute("currentMoney", service.getAmount());
        model.addAttribute("changeMessage", view.changeMessage());
        model.addAttribute("currentItem", service.getCurrentItem());
        model.addAttribute("systemMessage", view.systemMessage());
        
       
        return "vending";
    }
    
    /* -- All 'Add Money' Buttons -- */
    @RequestMapping(value = "/addDollar", method = RequestMethod.POST)
    public String addDollar() {
        service.addDollar();
        view.clearChangeMessage();
       
        return "redirect:displayVending";
    }
    
    @RequestMapping(value = "/addQuarter", method = RequestMethod.POST)
    public String addQuarter() {
        service.addQuarter();
        view.clearChangeMessage();
        
        return "redirect:displayVending";
    }
    @RequestMapping(value = "/addDime", method = RequestMethod.POST)
    public String addDime() {
        service.addDime();
        view.clearChangeMessage();
       
        return "redirect:displayVending";
    }
    @RequestMapping(value = "/addNickel", method = RequestMethod.POST)
    public String addNickel() {
        service.addNickel();
        view.clearChangeMessage();

        return "redirect:displayVending";
    }
    
    /* -- 'Change Return' Button -- */
    @RequestMapping(value = "/returnChange", method = RequestMethod.POST)
    public String returnChange() {
        view.displayChange(service.getChange(service.getAmount()));
        clearVending();
        
        return "redirect:displayVending";
    }
    
    /* -- 'Make Purchase' Button -- */
    @RequestMapping(value = "/purchaseProduct", method = RequestMethod.POST)
    public String purchaseProduct(@Valid HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("item"));
        
        //product doesn't exist
        if (id <= 0 || id > maxItems){
            view.productUnavailable();
            
            return "redirect:displayVending";
        }
        
        Vending vending = service.getItemById(id);
        service.setCurrentPrice(vending.getProductPrice());
        service.setQuantity(vending.getProductQuantity());
        if (service.getQuantity() <= 0){
            view.soldOut();
            return "redirect:displayVending";
        }
        if (service.getAmount() < service.getCurrentPrice()){
            view.insertChangeMsg(service.getAmountDifference(service.getCurrentPrice(), service.getAmount()));
        } else {
            service.takeOne(vending);
            view.thankYouMsg();
            service.getEndAmount(service.getCurrentPrice());
            view.displayChange(service.getChange(service.getAmount()));
        }
        
        return "redirect:displayVending";
    }
    
    /* -- All 'Product' Buttons 1-9 -- */
    @RequestMapping(value = "/currentItem1", method = RequestMethod.GET)
    public String currentItem1() {
        service.setCurrentItem(1);
        
        return "redirect:displayVending";
    }
    @RequestMapping(value = "/currentItem2", method = RequestMethod.GET)
    public String currentItem2() {
        service.setCurrentItem(2);
        
        return "redirect:displayVending";
    }
    @RequestMapping(value = "/currentItem3", method = RequestMethod.GET)
    public String currentItem3() {
        service.setCurrentItem(3);
        
        return "redirect:displayVending";
    }
    @RequestMapping(value = "/currentItem4", method = RequestMethod.GET)
    public String currentItem4() {
        service.setCurrentItem(4);
        
        return "redirect:displayVending";
    }
    @RequestMapping(value = "/currentItem5", method = RequestMethod.GET)
    public String currentItem5() {
        service.setCurrentItem(5);
        
        return "redirect:displayVending";
    }
    @RequestMapping(value = "/currentItem6", method = RequestMethod.GET)
    public String currentItem6() {
        service.setCurrentItem(6);
        
        return "redirect:displayVending";
    }
    @RequestMapping(value = "/currentItem7", method = RequestMethod.GET)
    public String currentItem7() {
        service.setCurrentItem(7);
        
        return "redirect:displayVending";
    }
    @RequestMapping(value = "/currentItem8", method = RequestMethod.GET)
    public String currentItem8() {
        service.setCurrentItem(8);
        
        return "redirect:displayVending";
    }
    @RequestMapping(value = "/currentItem9", method = RequestMethod.GET)
    public String currentItem9() {
        service.setCurrentItem(9);
        
        return "redirect:displayVending";
    }
}
