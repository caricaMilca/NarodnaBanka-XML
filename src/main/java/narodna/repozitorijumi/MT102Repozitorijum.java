package narodna.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;

import narodna.mt102.MT102;
import narodna.mt102.ZaglavljeMT102;

public interface MT102Repozitorijum extends JpaRepository<MT102, Long> {

	MT102 findByZaglavljeMT102(ZaglavljeMT102 zaglavljeMT102);

}
