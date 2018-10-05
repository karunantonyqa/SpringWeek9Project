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
import com.qa.PetClinicDBApp.model.PetClinicInjuryModel;
import com.qa.PetClinicDBApp.repository.PetClinicInjuryRepo;


@RestController
@RequestMapping("api")
public class PetClinicInjuryController {
	
	
	private final String injuryMap = "/owner/{id}/pet/{id}/injury";
	private final String injuryIDMap = "/owner/{id}/pet/{id}/injury/{id}";
	private final String id = "id";
	
	
	@Autowired
	PetClinicInjuryRepo myRepo;
	
	//method create person
	@PostMapping(injuryMap)
	public PetClinicInjuryModel createInjury(@Valid @RequestBody PetClinicInjuryModel pCOM) {
		return myRepo.save(pCOM);
	}
	
	//method get person
	@GetMapping(injuryIDMap)
	public PetClinicInjuryModel getInjurybyID(@PathVariable(value = id)Long injuryID) {
		return myRepo.findById(injuryID).orElseThrow(()-> new ResourceNotFoundException("PetClinicInjuryModel",id,injuryID));
	}
	
	
	//method get all people
	@GetMapping(injuryMap)
	public List<PetClinicInjuryModel> getAllInjuries(){
		return myRepo.findAll();
	}
	
	//method update person
	@PutMapping(injuryIDMap)
	public PetClinicInjuryModel updateInjury(@PathVariable(value = id) Long injuryID, @Valid @RequestBody PetClinicInjuryModel injuryDetails) {
		PetClinicInjuryModel pCOM = myRepo.findById(injuryID).orElseThrow(()-> new ResourceNotFoundException("Injury",id,injuryID));
		
		pCOM.setName(injuryDetails.getName());
		pCOM.setDescription(injuryDetails.getDescription());
		
		PetClinicInjuryModel updateData = myRepo.save(pCOM);
		return updateData;
	}
	

	//method remove person
	@DeleteMapping(injuryIDMap)
	public ResponseEntity<?> deleteInjury(@PathVariable(value = id) Long injuryID){
		PetClinicInjuryModel pCOM = myRepo.findById(injuryID).orElseThrow(()-> new ResourceNotFoundException("Injury",id,injuryID));
		
		myRepo.delete(pCOM);
		return ResponseEntity.ok().build();
	}
	
}