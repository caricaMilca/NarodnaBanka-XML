package narodna.servisi;

import java.util.List;

import org.springframework.http.ResponseEntity;

import narodna.model.Racun;

public interface RacunServis {

	ResponseEntity<List<Racun>> sviRacuniFirme();

}
