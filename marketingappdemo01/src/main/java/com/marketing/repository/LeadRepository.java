package com.marketing.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.marketing.entities.Lead;

public interface LeadRepository extends JpaRepositoryImplementation<Lead, Long> {

}
