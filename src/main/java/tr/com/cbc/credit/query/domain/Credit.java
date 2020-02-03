package tr.com.cbc.credit.query.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kredi")
public class Credit implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "kredinumarasi")
	private String krediNumarasi;
	@Column(name = "tckn")
	private String tckn;
	@Column(name = "bankaadi")
	private String bankaadi;
	@Column(name = "tutar")
	private String tutar;
	@Column(name = "kredituru")
	private String krediTuru;
	@Column(name = "datetime")
	private String datetime;

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getTckn() {
		return tckn;
	}

	public void setTckn(String tckn) {
		this.tckn = tckn;
	}

	public String getKrediNumarasi() {
		return krediNumarasi;
	}

	public void setKrediNumarasi(String krediNumarasi) {
		this.krediNumarasi = krediNumarasi;
	}

	public String getBankaadi() {
		return bankaadi;
	}

	public void setBankaadi(String bankaadi) {
		this.bankaadi = bankaadi;
	}

	public String getTutar() {
		return tutar;
	}

	public void setTutar(String tutar) {
		this.tutar = tutar;
	}

	public String getKrediTuru() {
		return krediTuru;
	}

	public void setKrediTuru(String krediTuru) {
		this.krediTuru = krediTuru;
	}

	public Credit() {

	}

	@Override
	public String toString() {
		return "Kredi [id=" + id + ", krediNumarasi=" + krediNumarasi + ", tckn=" + tckn + ", bankaadi=" + bankaadi
				+ ", tutar=" + tutar + ", krediTuru=" + krediTuru + ", datetime=" + datetime + "]";
	}

	public Credit(String krediNumarasi, String tckn, String bankaadi, String tutar, String krediTuru, String datetime) {
		super();
		this.krediNumarasi = krediNumarasi;
		this.tckn = tckn;
		this.bankaadi = bankaadi;
		this.tutar = tutar;
		this.krediTuru = krediTuru;
		this.datetime = datetime;
	}

}
