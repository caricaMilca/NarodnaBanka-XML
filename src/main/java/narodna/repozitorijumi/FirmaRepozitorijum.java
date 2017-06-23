package narodna.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;

import narodna.model.Firma;

public interface FirmaRepozitorijum extends JpaRepository<Firma, Long> {

	Firma findByPortAndLozinka(String port, String lozinka);

	Firma findByPib(String pibDobavljaca);

}
