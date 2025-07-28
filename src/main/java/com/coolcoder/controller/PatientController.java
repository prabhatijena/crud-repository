package com.coolcoder.controller;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.coolcoder.entity.Patient;
import com.coolcoder.repository.PatientRepository;

@RestController
@RequestMapping("/send")
public class PatientController {
	
	@Autowired
	private PatientRepository repository;
	
	@PostMapping("/save")
	public ResponseEntity<Object> create(@RequestBody Patient patient){
		repository.save(patient);
		return ResponseEntity.status(HttpStatus.CREATED).body("Patient inserted successfully..");
	}
	@GetMapping("/get")
	public ResponseEntity<Object> getAllPatient(){
		return ResponseEntity.status(HttpStatus.FOUND).body(repository.findAll());
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Patient patient){
		Optional<Patient> pat = repository.findById(id);
		if(pat.isPresent()) {
			Patient existingPat = pat.get();
			existingPat.setName(patient.getName());
			existingPat.setAge(patient.getAge());
			repository.save(existingPat);
		    return ResponseEntity.status(HttpStatus.OK).body("Patient updated successfully for Id-"+id);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient Not Found for Id-"+id);

		}
	}
	@DeleteMapping("/delete/{id}")
		public ResponseEntity<Object> deleteById(@PathVariable Integer id){
		    repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Patient deleted for id:" +id);
		}
	}
