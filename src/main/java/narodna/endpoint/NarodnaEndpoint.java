package narodna.endpoint;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import narodna.model.Banka;
import narodna.model.Racun;
import narodna.mt102.GetMT102Request;
import narodna.mt102.MT102;
import narodna.mt102.ZaglavljeMT102;
import narodna.mt103.GetMT103Request;
import narodna.mt103.MT103;
import narodna.mt900.GetMT900Response;
import narodna.mt900.MT900;
import narodna.mt910.GetMT910Request;
import narodna.mt910.MT910;
import narodna.repozitorijumi.BankaRepozitorijum;
import narodna.repozitorijumi.RacunRepozitorijum;

@Endpoint
@Component
public class NarodnaEndpoint {
	private static final String NAMESPACE_URI = "http://paket/mt103";
	private static final String NAMESPACE_URI2 = "http://paket/mt102";

	@Autowired
	private WebServiceTemplate webServiceTemplate;

	@Autowired
	BankaRepozitorijum bankaRep;

	@Autowired
	RacunRepozitorijum racunRep;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetMT103Request")
	@ResponsePayload
	public GetMT900Response getMT103(@RequestPayload GetMT103Request request) {
System.out.println(" sssssssssssssssssssssssss");
		MT103 mt103 = request.getMT103();
		MT900 mt900 = new MT900((UUID.randomUUID().toString()), mt103.getSwifKodBankeDuznika(),
				mt103.getObracunskiRacunBankeDuznika(), mt103.getIdPoruke(), mt103.getDatumValute(), mt103.getIznos(),
				mt103.getSifraValute());
		Banka bankaPovjerioca = bankaRep.findBySwiftKod(mt103.getSwiftKodBankePoverioca());
		Banka bankaDuznika = bankaRep.findBySwiftKod(mt103.getSwifKodBankeDuznika());
		String uri = "http://localhost:" + bankaPovjerioca.port + "/ws";
		System.out.println(uri + " saljeeeeeeeeeeeeee naaa");
		Racun obracunskiDuznika = racunRep.findByObracunskiAndBanka(true, bankaDuznika).get(0);
		Racun obracunskiPovjerioca = racunRep.findByObracunskiAndBanka(true, bankaPovjerioca).get(0);
		if (obracunskiDuznika.novoStanje.compareTo(mt103.getIznos()) == 1) {
			obracunskiDuznika.novoStanje.subtract(mt103.getIznos());
			obracunskiPovjerioca.novoStanje.add(mt103.getIznos());
		} else 
			return null;
		
		MT910 mt910 = new MT910((UUID.randomUUID().toString()), mt103.getSwiftKodBankePoverioca(), mt103.getObracunskiRacunBankePoverioca(), mt103.getIdPoruke(), mt103.getDatumValute(), mt103.getIznos(), mt103.getSifraValute());
		GetMT910Request mt910res = new GetMT910Request();
		mt910res.setMT900(mt910);
		webServiceTemplate.setDefaultUri(uri);
		System.out.println(uri + " mt103 aaaaaaaaaaaaaaaaaaaaaaaa");
		webServiceTemplate.marshalSendAndReceive(request);
		webServiceTemplate.marshalSendAndReceive(mt910res);
		GetMT900Response mt900res = new GetMT900Response();
		mt900res.setMT900(mt900);
		return mt900res;

	}

	@PayloadRoot(namespace = NAMESPACE_URI2, localPart = "getMT102Request")
	@ResponsePayload
	public GetMT900Response getMT102(@RequestPayload GetMT102Request request) {
		MT102 mt102 = request.getMT102();
		ZaglavljeMT102 zmt102 = mt102.getZaglavljeMT102();
		MT900 mt900 = new MT900((UUID.randomUUID().toString()), zmt102.getSwiftKodBankeDuznika(),
				zmt102.getObracunskiRacunBankeDuznika(), zmt102.getIdPoruke(), zmt102.getDatumValute(),
				zmt102.getUkupanIznos(), zmt102.getSifraValute());
		Banka bankaPovjerioca = bankaRep.findBySwiftKod(zmt102.getSwiftKodBankePoverioca());
		Banka bankaDuznika = bankaRep.findBySwiftKod(zmt102.getSwiftKodBankeDuznika());
		Racun obracunskiDuznika = racunRep.findByObracunskiAndBanka(true, bankaDuznika).get(0);
		Racun obracunskiPovjerioca = racunRep.findByObracunskiAndBanka(true, bankaPovjerioca).get(0);
		if (obracunskiDuznika.novoStanje.compareTo(zmt102.getUkupanIznos()) == 1) {
			obracunskiDuznika.novoStanje.subtract(zmt102.getUkupanIznos());
			obracunskiPovjerioca.novoStanje.add(zmt102.getUkupanIznos());
		} else 
			return null;
		
		MT910 mt910 = new MT910((UUID.randomUUID().toString()), zmt102.getSwiftKodBankePoverioca(), zmt102.getObracunskiRacunBankePoverioca(), zmt102.getIdPoruke(), zmt102.getDatumValute(), zmt102.getUkupanIznos(), zmt102.getSifraValute());
		GetMT910Request mt910res = new GetMT910Request();
		mt910res.setMT900(mt910);
		String uri = "http://localhost:" + bankaPovjerioca.port + "/ws";
		webServiceTemplate.setDefaultUri(uri);
		System.out.println(uri + " mt103 aaaaaaaaaaaaaaaaaaaaaaaa");
		webServiceTemplate.marshalSendAndReceive(request);
		webServiceTemplate.marshalSendAndReceive(mt910res);
		GetMT900Response mt900res = new GetMT900Response();
		mt900res.setMT900(mt900);
		return mt900res;
	}
}
