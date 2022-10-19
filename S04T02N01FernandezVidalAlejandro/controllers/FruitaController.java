package cat.itacademy.barcelonactiva.fernandezvidal.alejandro.s04.t02.n01.S04T02N01FernandezVidalAlejandro.controllers;

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

import cat.itacademy.barcelonactiva.fernandezvidal.alejandro.s04.t02.n01.S04T02N01FernandezVidalAlejandro.model.domain.Fruita;
import cat.itacademy.barcelonactiva.fernandezvidal.alejandro.s04.t02.n01.S04T02N01FernandezVidalAlejandro.model.repository.FruitaRepository;

@RestController
@RequestMapping("/fruita")
public class FruitaController {

	@Autowired
	private FruitaRepository fruitaRepository;

	@PostMapping("/add")
	public ResponseEntity<Fruita> addFruita(@RequestBody Fruita fruita) {

		fruitaRepository.save(fruita);
		return new ResponseEntity<Fruita>(fruita, HttpStatus.CREATED);

	}

	@PutMapping("/update")
	public ResponseEntity<Fruita> updateFruita(@RequestBody Fruita fruita) {
		ResponseEntity<Fruita> responseEntity;
		boolean fruitaBuscar = fruitaRepository.existsById(fruita.getId());
		if (fruitaBuscar) {
			fruitaRepository.save(fruita);
			responseEntity = new ResponseEntity<Fruita>(fruita, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteFruita(@PathVariable("id") int id) {
		ResponseEntity<String> responseEntity;
		boolean fruitaBuscar = fruitaRepository.existsById(id);
		if (fruitaBuscar) {
			fruitaRepository.deleteById(id);
			responseEntity = new ResponseEntity<String>("Eliminat corrcetament", HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<String>("No existeix", HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	@GetMapping("/getOne/{id}")
	public ResponseEntity<Fruita> getOneFruita(@PathVariable("id") int id) {
		ResponseEntity<Fruita> responseEntity;
		Optional<Fruita> fruitaBuscar = fruitaRepository.findById(id);
		if (fruitaBuscar.isPresent()) {
			responseEntity = new ResponseEntity<Fruita>(fruitaBuscar.get(), HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Fruita>> getAllFruita() {
		ResponseEntity<List<Fruita>> responseEntity;
		List<Fruita> lista = fruitaRepository.findAll();
		if (lista.isEmpty()) {
			responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			responseEntity = new ResponseEntity<List<Fruita>>(lista, HttpStatus.OK);
		}
		return responseEntity;
	}
}
