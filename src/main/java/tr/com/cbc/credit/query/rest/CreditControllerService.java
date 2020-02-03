package tr.com.cbc.credit.query.rest;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tr.com.cbc.credit.query.domain.*;
import tr.com.cbc.credit.query.repository.CreditRepository;
import tr.com.cbc.credit.query.service.CreditService;
import tr.com.cbc.credit.query.service.impl.CreditServiceImpl;

@RestController

public class CreditControllerService {

	@Autowired
	private CreditQueryService krediSorguService;

	@Autowired
	private CreditService service;

	@Autowired
	private CreditRepository c;

	@GetMapping(path = "/query")
	public ResponseEntity<?> getKredi(@RequestParam(name = "tckn", required = false) String tckn,
			@RequestParam(name = "bankaAdi", required = false) String bankaAdi,
			@RequestParam(name = "krediTuru", required = false) String krediTuru,
			@RequestParam(name = "tutar", required = false) String tutar,
			@RequestParam(name = "krediNumarasi", required = false) String krediNumarasi,
			@RequestParam(name = "datetime", required = false) String datetime, HttpServletRequest request)
			throws ValidationException, Exception {

		try {

			List<Credit> krediList = krediSorguService.getKrediListesi(tckn, bankaAdi, krediTuru, tutar, krediNumarasi,
					datetime, request);
			//service.getAllKredi();

			if ((tckn == null || tckn.isEmpty()) && (bankaAdi == null || bankaAdi.isEmpty())
					&& (krediTuru == null || krediTuru.isEmpty()) && (tutar == null || tutar.isEmpty())
					&& (krediNumarasi == null || krediNumarasi.isEmpty()) && (datetime == null || datetime.isEmpty())) {
				return ResponseEntity.ok(service.getAllKredi());
			}

			else {

				return ResponseEntity.ok(service.findByCriteria(tckn, bankaAdi, krediTuru, tutar, krediNumarasi,
						datetime));
			}

		} catch (ValidationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

}
