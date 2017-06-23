package narodna.repozitorijumi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import narodna.model.Firma;
import narodna.model.Racun;

public interface RacunRepozitorijum extends JpaRepository<Racun, Long> {

	List<Racun> findByFirma(Firma attribute);

}
