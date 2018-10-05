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
import com.qa.PetClinicDBApp.model.PetClinicPetModel;
import com.qa.PetClinicDBApp.repository.PetClinicPetRepo;


@RestController
@RequestMapping("/api")
public class PetClinicPetController {
	
	private final String petMap = "/owner/{id}/pet";
	private final String petIDMap = "/owner/{id}/pet/{id}";
	private final String id = "id";
	
	@Autowired
	PetClinicPetRepo myRepo;
	
	//method create pet
	@PostMapping(petMap)
	public PetClinicPetModel createPet(@Valid @RequestBody PetClinicPetModel pCPM) {
		return myRepo.save(pCPM);
	}
	
	//method get pet
	@GetMapping(petIDMap)
	public PetClinicPetModel getPetbyID(@PathVariable(value = id)Long petID) {
		return myRepo.findById(petID).orElseThrow(()-> new ResourceNotFoundException("Pet Model",id,petID));
	}
	
	
	//method get all pet
	@GetMapping(petMap)
	public List<PetClinicPetModel> getAllPeople(){
		return myRepo.findAll();
	}
	
	//method update pet
	@PutMapping(petIDMap)
	public PetClinicPetModel updatePet(@PathVariable(value = id) Long petID, @Valid @RequestBody PetClinicPetModel petDetails) {
		PetClinicPetModel pCPM = myRepo.findById(petID).orElseThrow(()-> new ResourceNotFoundException("Pet",id,petID));
		
		pCPM.setName(petDetails.getName());
		pCPM.setType(petDetails.getType());
		pCPM.setAge(petDetails.getAge());
		
		PetClinicPetModel updateData = myRepo.save(pCPM);
		return updateData;
	}
	

	//method remove pet
	@DeleteMapping(petIDMap)
	public ResponseEntity<?> deletePet(@PathVariable(value = id) Long petID){
		PetClinicPetModel pCPM = myRepo.findById(petID).orElseThrow(()-> new ResourceNotFoundException("Pet",id,petID));
		
		myRepo.delete(pCPM);
		return ResponseEntity.ok().build();
	}
	
}