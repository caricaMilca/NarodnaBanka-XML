package narodna.servisi;

import org.springframework.http.ResponseEntity;

import narodna.model.Firma;


public interface FirmaServis {

	ResponseEntity<Firma> login(String port, String lozinka);


}
