package com.qa.PetClinicDBApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.qa.PetClinicDBApp.model.PetClinicOwnerModel;

@Repository
public interface PetClinicOwnerRepo extends JpaRepository<PetClinicOwnerModel,Long> {
	
	public PetClinicOwnerModel findByName(String name);

}

