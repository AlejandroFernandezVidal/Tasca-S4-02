package cat.itacademy.barcelonactiva.fernandezvidal.alejandro.s04.t02.n03.S04T02N03FernandezVidalAlejandro.controllers;

import java.util.List;
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

import cat.itacademy.barcelonactiva.fernandezvidal.alejandro.s04.t02.n03.S04T02N03FernandezVidalAlejandro.model.domain.Fruita;
import cat.itacademy.barcelonactiva.fernandezvidal.alejandro.s04.t02.n03.S04T02N03FernandezVidalAlejandro.model.repository.FruitaRepository;

@RestController
@RequestMapping("/fruitas")
public class FruitaController {
	@Autowired
	private FruitaRepository fruitaRepository;

	@PostMapping("/add")
	public ResponseEntity<?>/* String */ addFruita(@RequestBody Fruita fruita) {
		boolean fruitaBuscar = fruitaRepository.existsById(fruita.getId());
		ResponseEntity<?> responseEntity;
		if (!fruitaBuscar) {
			fruitaRepository.save(fruita);
			responseEntity = ResponseEntity.status(HttpStatus.CREATED).body("Fruita afegida amb id : " + fruita.getId());
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("La fruita amb id " + fruita.getId() + " ya existeix.");
		}
		return responseEntity;
	}

	@PutMapping("/update")
	public ResponseEntity<?>/*Fruita*/ update(@RequestBody Fruita fruita) {
		ResponseEntity<?> responseEntity;
		Optional<Fruita> fruitaActualizar = fruitaRepository.findById(fruita.getId());
		if (fruitaActualizar.isPresent()) {
			Fruita _fruita = fruitaActualizar.get();
			_fruita.setNom(fruita.getNom());
			_fruita.setQuantitatQuilos(fruita.getQuantitatQuilos());
			fruitaRepository.save(_fruita);
			responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(_fruita);
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existeix la fruita amb id " + fruita.getId());
		}
		return responseEntity;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?>/*String*/ deleteFruita(@PathVariable int id) {
		ResponseEntity<?> responseEntity;
		boolean fruitaBuscar = fruitaRepository.existsById(id);
		if (fruitaBuscar) {
			fruitaRepository.deleteById(id);
			responseEntity = ResponseEntity.status(HttpStatus.OK).body("Fruita eliminada amb id " + id);
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La fruita amb id " + id + " no existeix.");
		}
		return responseEntity;
	}

	@GetMapping("/getOne/{id}")
	public ResponseEntity<?>/*Optional<Fruita>*/ getOne(@PathVariable int id) {
		ResponseEntity<?> responseEntity;
		boolean fruitaBuscar = fruitaRepository.existsById(id);
		if (fruitaBuscar) {
			responseEntity = ResponseEntity.status(HttpStatus.FOUND).body(fruitaRepository.findById(id));
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existeix la fruita amb id " + id);
		}
		return responseEntity;
	}

	@GetMapping("/getAll")
	public ResponseEntity<?>/*List<Fruita>*/ getAllFruita() {
		ResponseEntity<?> responseEntity;
		List<Fruita> lista = fruitaRepository.findAll();
		if (lista.isEmpty()) {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body("La llista es buida.");
		} else {
			responseEntity =  ResponseEntity.status(HttpStatus.FOUND).body(fruitaRepository.findAll());
		}
		return responseEntity;
	}

}
