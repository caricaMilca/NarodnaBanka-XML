package narodna.kontroleri;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import narodna.model.Racun;
import narodna.servisi.RacunServis;

@RestController
@RequestMapping("/racun")
public class RacunController {

	@Autowired
	RacunServis racunServis;
	
	@GetMapping(path ="/sviRacuniFirme")
	public ResponseEntity<List<Racun>> sviRacuniFirme() {
		return racunServis.sviRacuniFirme();
	}
}
