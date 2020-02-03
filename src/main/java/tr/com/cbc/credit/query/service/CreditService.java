package tr.com.cbc.credit.query.service;

import java.util.List;

import tr.com.cbc.credit.query.domain.Credit;

public interface CreditService {
	
	List<Credit> getAllKredi();
	Credit getbyId(Long id);
	void saveOrUpdate(Credit kredi);
	void deleteKredi(Long id);
	 List<Credit> findAll();
	 List<Credit> findByCriteria(String tckn ,String  bankaAdi, String krediTuru, String tutar,String  krediNumarasi,
				String datetime);

	
}
