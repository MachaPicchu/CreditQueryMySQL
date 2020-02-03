package tr.com.cbc.credit.query.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tr.com.cbc.credit.query.domain.Credit;

@Repository
@Transactional
public interface CreditRepository extends JpaRepository<Credit, Long>,JpaSpecificationExecutor<Credit> {

//	List<Credit> findByTcknAndBankaadiOrKrediTuruAndTutarOrKrediNumarasiAndDatetime(String tckn, String bankaAdi,
//			String krediTuru, String tutar, String krediNumarasi, String datetime);
//	@Query("select kredi from Credit kredi where kredi.bankaadi=?1 And  kredi.tckn=?2  And kredi.krediNumarasi=?3 And kredi.kredituru=?4 And kredi.tutar=?5 And kredi.datetime=?6")
//	public List<Credit> findAllByTcknAndBankaadiAndKrediTuruAndTutarAndKrediNumarasiAndDatetime(String tckn,
//			String bankaadi, String krediTuru, String tutar, String krediNumarasi, String datetime);

//	List<Credit> findByBankaadiContainingAndTcknContaining(String bankaadi, String tckn);

	
//
//	 @Query(value = "SELECT * FROM Credit WHERE tckn = :tckn", nativeQuery = true)
//	    List<Credit> findAuthorsByFirstName(@Param("tckn") String );
	
	   List<Credit> findAll(); 
	
	



	

}