package com.marketing.services;

import java.util.List;

import com.marketing.entities.Lead;

public interface LeadService {
	
	public void saveLead(Lead lead);

	public List<Lead> getLeads();

	public void delete(long id);

	public Lead findOneLeadUpdate(long id);


}
