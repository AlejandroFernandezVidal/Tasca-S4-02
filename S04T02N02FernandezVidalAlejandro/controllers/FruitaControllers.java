package cat.itacademy.barcelonactiva.fernandezvidal.alejandro.s04.t02.n02.S04T02N02FernandezVidalAlejandro.controllers;

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

import cat.itacademy.barcelonactiva.fernandezvidal.alejandro.s04.t02.n02.S04T02N02FernandezVidalAlejandro.model.domain.Fruita;
import cat.itacademy.barcelonactiva.fernandezvidal.alejandro.s04.t02.n02.S04T02N02FernandezVidalAlejandro.model.services.FruitaServiceImpl;


@RestController
@RequestMapping("/fruitas")
public class FruitaControllers {

	@Autowired
	private FruitaServiceImpl fruitaServiceImpl;

	@PostMapping("/add")
	public ResponseEntity<?> addFruita(@RequestBody Fruita fruita) {
		return ResponseEntity.status(HttpStatus.CREATED).body(fruitaServiceImpl.addFruita(fruita));
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateFruita(@RequestBody Fruita fruita) {
		ResponseEntity<?> responseEntity;
		Optional<Fruita> fruitaActualizar = fruitaServiceImpl.getOneFruita(fruita.getId());
		if (fruitaActualizar.isPresent()) {
			Fruita _fruita = fruitaActualizar.get();
			_fruita.setNom(fruita.getNom());
			_fruita.setQuantitatQuilos(fruita.getQuantitatQuilos());
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(fruitaServiceImpl.updateFruita(_fruita));
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("La fruita amb id " + fruita.getId() + " no existeix");
		}
		return responseEntity;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteFruita(@PathVariable int id) {
		ResponseEntity<?> responseEntity;
		if (!fruitaService.getOneFruita(id).isPresent()) {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("La fruita amb id " + id + " no existeix.");
		} else {
			fruitaServiceImpl.deleteFruita(id);
			responseEntity = ResponseEntity.ok("Fruita amb id " + id + " eliminada correctament.");
		}
		return responseEntity;
	}

	@GetMapping("/getOne/{id}")
	public ResponseEntity<?> getOneFruita(@PathVariable int id) {
		ResponseEntity<?> responseEntity;
		Optional<Fruita> fruitaBuscar = fruitaServiceImpl.getOneFruita(id);

		if (!fruitaBuscar.isPresent()) {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("La fruita amb id " + id + " no existeix.");
		} else {
			responseEntity = ResponseEntity.ok(fruitaBuscar);
		}
		return responseEntity;
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllFruita() {
		ResponseEntity<?> responseEntity;
		List<Fruita> fruitas = StreamSupport
				.stream(fruitaServiceImpl.getAllFruita().spliterator(),false).collect(Collectors.toList());
		if (fruitas.isEmpty()) {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body("La llista es buida.");
		} else {
			responseEntity = ResponseEntity.ok(fruitas);
		}
		return responseEntity;
	}
}
