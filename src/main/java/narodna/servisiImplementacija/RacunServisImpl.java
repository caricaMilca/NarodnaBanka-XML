package narodna.servisiImplementacija;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import narodna.model.Firma;
import narodna.model.Racun;
import narodna.repozitorijumi.RacunRepozitorijum;
import narodna.servisi.RacunServis;

@Service
@Transactional
public class RacunServisImpl implements RacunServis{

	@Autowired
	HttpSession sesija;
	
	@Autowired
	RacunRepozitorijum racunRepozitorijum;
	
	@Override
	public ResponseEntity<List<Racun>> sviRacuniFirme() {
		return new ResponseEntity<List<Racun>>(racunRepozitorijum.findByFirma((Firma) sesija.getAttribute("firma")), HttpStatus.OK);
	}

}
