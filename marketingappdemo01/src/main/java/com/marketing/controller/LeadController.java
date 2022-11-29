package com.marketing.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketing.entities.Lead;
import com.marketing.services.LeadService;
import com.marketing.utilities.EmailService;

@Controller
public class LeadController {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private LeadService leadService;  // Controller interact with Service layer
	
	@RequestMapping(value = "/viewCreateLeadPage" , method =RequestMethod.GET ) //@RequestMapping - this line use as @webServlet
	public String viewCreateLeadPage() {
		return "create_lead";  
	}

//  save data in db - 1
	@RequestMapping("/saveLead") 
	public String saveOneLead(@ModelAttribute("lead") Lead lead, ModelMap model) {
		leadService.saveLead(lead);
		emailService.sendEmail(lead.getEmail(), "WELCOME", "Your Record Is Saved To Our Database");
		model.addAttribute("msg", "Lead is saved !!");
		return "create_lead";
	}
	
	@RequestMapping("/listall")
	public String getAllLeads(ModelMap model) {
		List<Lead> leads = leadService.getLeads();
	    model.addAttribute("leads", leads);
		return "leads_result";
	}
	
	@RequestMapping("/delete")
	public String deleteLead(@RequestParam("id") long id, ModelMap model) {
		leadService.delete(id);
		List<Lead> leads =  leadService.getLeads();
		model.addAttribute("leads", leads);
		return "leads_result";
	}
	
	@RequestMapping("/update")
	public String updateOneLead(@RequestParam("id") long id , ModelMap model) {
		Lead lead = leadService.findOneLeadUpdate(id);
		model.addAttribute("lead", lead);
		return "update_lead";
	}
	
	@RequestMapping(value = "/updateLead", method = RequestMethod.POST)
	public String updateLead(@ModelAttribute("lead") Lead lead, ModelMap model){
		leadService.saveLead(lead);
		model.addAttribute("msg", "Lead is update");
		List<Lead> leads = leadService.getLeads();
	    model.addAttribute("leads", leads);
		return "leads_result";	
	}

	
//	save data - 2	
//	@RequestMapping("/saveLead") 
//	public String saveOneLead(@RequestParam("firstName")String firstName , @RequestParam("lastName")String lastName , @RequestParam("email")String email , @RequestParam("mobile")String mobile) {
//		
//		Lead l = new Lead();
//		l.setFirstName(firstName);
//		l.setLastName(lastName);
//		l.setEmail(email);
//		l.setMobile(mobile);
//		
//		leadService.saveLead(l);
//		
//		return "create_lead";
//		
//	}
	
	
//	save data -3	
//	@RequestMapping("/saveLead") 
//	public String saveOneLead(LeadData data , ModelMap model) { //ModelMap works as setAttribute & getAttribute
//		Lead l = new Lead();
//		l.setFirstName(data.getFirstName());
//		l.setLastName(data.getLastName());
//		l.setEmail(data.getEmail());
//		l.setMobile(data.getMobile());
//		
//		model.addAttribute("msg", "Lead is Saved!!");
//		leadService.saveLead(l);
//		
//		return "create_lead";
//		
//	}
	

}


	