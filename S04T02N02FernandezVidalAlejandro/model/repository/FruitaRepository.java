package cat.itacademy.barcelonactiva.fernandezvidal.alejandro.s04.t02.n02.S04T02N02FernandezVidalAlejandro.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cat.itacademy.barcelonactiva.fernandezvidal.alejandro.s04.t02.n02.S04T02N02FernandezVidalAlejandro.model.domain.Fruita;

@Repository
public interface FruitaRepository extends JpaRepository<Fruita, Integer>{

}
