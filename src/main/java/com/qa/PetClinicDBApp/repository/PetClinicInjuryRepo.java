package com.qa.PetClinicDBApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.qa.PetClinicDBApp.model.PetClinicInjuryModel;
import com.qa.PetClinicDBApp.model.PetClinicOwnerModel;

@Repository
public interface PetClinicInjuryRepo extends JpaRepository<PetClinicInjuryModel,Long> {
	
	public PetClinicInjuryModel findByName(String name);

}

