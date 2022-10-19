package cat.itacademy.barcelonactiva.fernandezvidal.alejandro.s04.t02.n02.S04T02N02FernandezVidalAlejandro.model.services;

import java.util.List;
import java.util.Optional;

import cat.itacademy.barcelonactiva.fernandezvidal.alejandro.s04.t02.n02.S04T02N02FernandezVidalAlejandro.model.domain.Fruita;

public interface FruitaService {
	
	public Fruita addFruita(Fruita fruita);
	
	public Fruita updateFruita(Fruita fruita);
	
	public void deleteFruita(int id);
	
	public Optional<Fruita> getOneFruita(int id);
	
	public Iterable<Fruita> getAllFruita();

}
