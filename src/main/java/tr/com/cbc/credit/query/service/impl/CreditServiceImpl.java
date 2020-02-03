package tr.com.cbc.credit.query.service.impl;

import tr.com.cbc.credit.query.domain.Credit;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import tr.com.cbc.credit.query.repository.CreditRepository;
import tr.com.cbc.credit.query.service.CreditService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

public class CreditServiceImpl implements CreditService {

	@Autowired
	CreditRepository repo;

	@Override
	public List<Credit> getAllKredi() {
		// TODO Auto-generated method stub
		return (List<Credit>) repo.findAll();
	}

	@Override
	public Credit getbyId(Long id) {
		return repo.findOne(id);
	}

	@Override
	public void saveOrUpdate(Credit kredi) {
		repo.save(kredi);

	}

	@Override
	public void deleteKredi(Long id) {
		// TODO Auto-generated method stub
		repo.delete(id);
	}

	@Override
	public List<Credit> findAll() {
		return repo.findAll(new Specification<Credit>() {
			@Override
			public Predicate toPredicate(Root<Credit> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return null;
			}
		});
	}

	public List<Credit> findByCriteria(String tckn, String bankaadi, String krediTuru, String tutar,
			String krediNumarasi, String datetime) {
		return repo.findAll(new Specification<Credit>() {
			@Override
			public Predicate toPredicate(Root<Credit> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (krediNumarasi != null) {
					predicates
							.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("krediNumarasi"), krediNumarasi)));
				}
				if (tckn != null) {
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("tckn"), tckn)));
				}
				if (bankaadi != null) {
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("bankaadi"), bankaadi)));
				}
				if (tutar != null) {
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("tutar"), tutar)));
				}
				if (krediTuru != null) {
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("krediTuru"), krediTuru)));
				}
				if (datetime != null) {
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("datetime"), datetime)));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}

}
