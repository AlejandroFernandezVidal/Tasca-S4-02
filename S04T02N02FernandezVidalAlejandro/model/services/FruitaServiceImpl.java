package cat.itacademy.barcelonactiva.fernandezvidal.alejandro.s04.t02.n02.S04T02N02FernandezVidalAlejandro.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cat.itacademy.barcelonactiva.fernandezvidal.alejandro.s04.t02.n02.S04T02N02FernandezVidalAlejandro.model.domain.Fruita;
import cat.itacademy.barcelonactiva.fernandezvidal.alejandro.s04.t02.n02.S04T02N02FernandezVidalAlejandro.model.repository.FruitaRepository;

@Service
public class FruitaServiceImpl implements FruitaService{
	
	@Autowired
	private FruitaRepository fruitaRepository;

	@Override
	@Transactional
	public Fruita addFruita(Fruita fruita) {
		
		return fruitaRepository.save(fruita);
	}

	@Override
	@Transactional
	public Fruita updateFruita(Fruita fruita) {
		
		return fruitaRepository.save(fruita);
	}

	@Override
	@Transactional
	public void deleteFruita(int id) {
		
		fruitaRepository.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Fruita> getOneFruita(int id) {
		
		return fruitaRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Fruita> getAllFruita() {
		
		return fruitaRepository.findAll();
	}

}
