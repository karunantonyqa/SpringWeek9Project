package com.qa.PetClinicDBApp.controller;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.PetClinicDBApp.exception.ResourceNotFoundException;
import com.qa.PetClinicDBApp.model.PetClinicOwnerModel;
import com.qa.PetClinicDBApp.repository.PetClinicOwnerRepo;


@RestController
@RequestMapping("/api")
public class PetClinicOwnerController {
	
	private final String ownerMap = "/owner";
	private final String ownerIDMap = "/owner/{id}";
	private final String id = "id";
	
	@Autowired
	PetClinicOwnerRepo myRepo;
	
	//method create person
	@PostMapping(ownerMap)
	public PetClinicOwnerModel createOwner(@Valid @RequestBody PetClinicOwnerModel pCOM) {
		return myRepo.save(pCOM);
	}
	
	//method get person
	@GetMapping(ownerIDMap)
	public PetClinicOwnerModel getOwnerbyID(@PathVariable(value = id)Long ownerID) {
		return myRepo.findById(ownerID).orElseThrow(()-> new ResourceNotFoundException("MySpringBootDataModel",id,ownerID));
	}
	
	
	//method get all people
	@GetMapping(ownerMap)
	public List<PetClinicOwnerModel> getAllOwners(){
		return myRepo.findAll();
	}
	
	//method update person
	@PutMapping(ownerIDMap)
	public PetClinicOwnerModel updateOwner(@PathVariable(value = id) Long ownerID, @Valid @RequestBody PetClinicOwnerModel ownerDetails) {
		PetClinicOwnerModel pCOM = myRepo.findById(ownerID).orElseThrow(()-> new ResourceNotFoundException("Person",id,ownerID));
		
		pCOM.setName(ownerDetails.getName());
		pCOM.setAddress(ownerDetails.getAddress());
		pCOM.setAge(ownerDetails.getAge());
		
		PetClinicOwnerModel updateData = myRepo.save(pCOM);
		return updateData;
	}
	

	//method remove person
	@DeleteMapping(ownerIDMap)
	public ResponseEntity<?> deleteOwner(@PathVariable(value = id) Long ownerID){
		PetClinicOwnerModel pCOM = myRepo.findById(ownerID).orElseThrow(()-> new ResourceNotFoundException("Owner",id,ownerID));
		
		myRepo.delete(pCOM);
		return ResponseEntity.ok().build();
	}
	
}