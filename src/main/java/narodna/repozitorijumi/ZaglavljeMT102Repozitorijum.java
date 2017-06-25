package narodna.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;

import narodna.mt102.ZaglavljeMT102;

public interface ZaglavljeMT102Repozitorijum extends JpaRepository<ZaglavljeMT102, Long> {

	ZaglavljeMT102 findBySwiftKodBankePoverioca(String swiftKod);


}
