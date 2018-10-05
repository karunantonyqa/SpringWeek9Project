package com.qa.PetClinicDBApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.qa.PetClinicDBApp.model.PetClinicOwnerModel;
import com.qa.PetClinicDBApp.model.PetClinicPetModel;

@Repository
public interface PetClinicPetRepo extends JpaRepository<PetClinicPetModel,Long> {
	
	public PetClinicPetModel findByName(String name);

}

