package cat.itacademy.barcelonactiva.fernandezvidal.alejandro.s04.t02.n02.S04T02N02FernandezVidalAlejandro.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fruitas")
public class Fruita implements Serializable{

	private static final long serialVersionUID = -7908837782071043835L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "quantitatQuilos")
	private int quantitatQuilos;
	
	
	
	public Fruita() {
		
	}

	public Fruita(String nom, int quantitatQuilos) {
		super();
		this.nom = nom;
		this.quantitatQuilos = quantitatQuilos;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getQuantitatQuilos() {
		return quantitatQuilos;
	}

	public void setQuantitatQuilos(int quantitatQuilos) {
		this.quantitatQuilos = quantitatQuilos;
	}

	@Override
	public String toString() {
		return "Fruita [id=" + id + ", nom=" + nom + ", quantitatQuilos=" + quantitatQuilos + "]";
	}
	
}
