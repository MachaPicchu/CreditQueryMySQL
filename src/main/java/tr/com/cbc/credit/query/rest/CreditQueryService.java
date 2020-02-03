package tr.com.cbc.credit.query.rest;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tr.com.cbc.credit.query.domain.Credit;

@Service
public class CreditQueryService {

	public java.util.List<Credit> getKrediListesi(String tckn, String bankaAdi, String krediTuru, String tutar,
			String krediNumarasi, String datetime, HttpServletRequest request) throws ValidationException, Exception {

		Map<String, String[]> allMap = request.getParameterMap();

		for (String key : allMap.keySet()) {
			if (!key.equals("tckn") && !key.equals("bankaAdi") && !key.equals("krediTuru") && !key.equals("tutar")
					&& !key.equals("krediNumarasi") && !key.equals("datetime")) {
				throw new ValidationException("hata mesaji: " + "parametre hatasi");
			}

		}

		if (tckn != null && !tckn.isEmpty() && (tcknKontrol(tckn) == false)) {

			if (isAllNumeric(tckn) == false) {
				throw new ValidationException("hata mesaji : " + " TCKN nümerik olmalıdır.");
			} else {
				throw new ValidationException("hata mesaji: " + " Geçersiz TCKN.");
			}

		}

		if (krediTuru != null && !krediTuru.isEmpty()) {

			if (isAllNumeric(krediTuru) == true) {
				throw new ValidationException("hata mesaji : " + " Kredi Türü string  olmalıdır.");
			}

			else if (krediTuru.equals("Konut Kredisi") == false && krediTuru.equals("Tasit Kredisi") == false
					&& krediTuru.equals("ihtiyac Kredisi") == false) {
				throw new ValidationException("hata mesaji : " + " Geçersiz kredi türü");
			}
		}

		if (krediNumarasi != null && !krediNumarasi.isEmpty()) {

			if (isAllNumeric(krediNumarasi) == false) {
				throw new ValidationException("hata mesaji : " + " Kredi numarası nümerik olmalıdır.");
			}

		}

		if (tutar != null && !tutar.isEmpty()) {

			if (!isAllNumeric(tutar)) {
				throw new ValidationException("hata mesaji : " + " Tutar nümerik olmalıdır.");
			}

		}
		
		if (datetime != null && !datetime.isEmpty()) {

			if (!dateValidate(datetime)) {
				throw new ValidationException("hata mesaji : " + " Geçersiz format.");
			}

		}
		

		if (bankaAdi != null && !bankaAdi.isEmpty()) {

			if (isAllNumeric(bankaAdi) == true) {
				throw new ValidationException("hata mesaji : " + " bankaAdi String olmalıdır.");
			}

			else if (bankaAdi.equals("Akbank") == false && bankaAdi.equals("Halkbank") == false
					&& bankaAdi.equals("Ziraat") == false && bankaAdi.equals("Garanti") == false
					&& bankaAdi.equals("Finansbank") == false) {
				throw new ValidationException("hata var : " + " Geçersiz banka adı");
			}

		}
		return null;

//		java.util.List<Credit> forReturn = new ArrayList<Credit>();
//
//		try {
//			String connectionString = "jdbc:mysql://localhost:3306/crediregister?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection baglanti = (Connection) DriverManager.getConnection(connectionString, "root", "1234");
//			try {
//
//				String SQL = "SELECT * FROM kredi";
//				Statement durum = (Statement) baglanti.createStatement();
//				ResultSet rs = (ResultSet) durum.executeQuery(SQL);
//				while (rs.next()) {
//					boolean krediNoOk = true;
//					boolean tcknOk = true;
//					boolean bankaAdiOk = true;
//					boolean krediTuruOk = true;
//					boolean tutarOK = true;
//					boolean sorguTarihiOK = true;
//
//					if (krediNumarasi != null && !krediNumarasi.isEmpty()) {
//
//						krediNoOk = rs.getString("kredinumarasi").equals(krediNumarasi);
//
//					}
//
//					if (tckn != null && !tckn.isEmpty()) {
//
//						tcknOk = rs.getString("tckn").equals(tckn);
//					}
//
//					if (bankaAdi != null && !bankaAdi.isEmpty()) {
//
//						bankaAdiOk = rs.getString("bankaadi").equals(bankaAdi);
//
//					}
//
//					if (krediTuru != null && !krediTuru.isEmpty()) {
//
//						krediTuruOk = rs.getString("kredituru").equals(krediTuru);
//
//					}
//
//					if (tutar != null && !tutar.isEmpty()) {
//
//						tutarOK = rs.getString("tutar").equals(tutar);
//
//					}
//					if (datetime != null && !datetime.isEmpty()) {
//
//						sorguTarihiOK = rs.getString("datetime").equals(datetime);
//
//					}
//
//					if (krediNoOk && tcknOk && bankaAdiOk && krediTuruOk && tutarOK && sorguTarihiOK) {
//
//						forReturn.add(new Credit(rs.getString("kredinumarasi"), rs.getString("tckn"),
//								rs.getString("bankaadi"), rs.getString("tutar"), rs.getString("kredituru"),
//								rs.getString("datetime")));
//					}
//
//					else {
//						throw new ValidationException("Veri bulunamadı !!!");
//					}
//
//				}
//
//			}
//
//			catch (ValidationException e) {
//				e.printStackTrace();
//				throw e;
//			}
//		} catch (ValidationException e) {
//			throw e;
//		}
//
//		catch (Exception e) {
//			e.printStackTrace();
//			throw new ValidationException("Genel bir hata  oluştu.");
//		}
//
//		return forReturn;

	}

	private boolean tcknKontrol(String tckn) {

		String id = tckn;

		if (id.length() != 11)
			return false;

		char[] chars = id.toCharArray();
		int[] a = new int[11];

		for (int i = 0; i < 11; i++) {
			a[i] = chars[i] - '0';
		}

		if (a[0] == 0)
			return false;
		if (a[10] % 2 == 1)
			return false;

		if (((a[0] + a[2] + a[4] + a[6] + a[8]) * 7 - (a[1] + a[3] + a[5] + a[7])) % 10 != a[9])
			return false;

		if ((a[0] + a[1] + a[2] + a[3] + a[4] + a[5] + a[6] + a[7] + a[8] + a[9]) % 10 != a[10])
			return false;

		return true;
	}

	public boolean isAllNumeric(String code) {
		for (int i = 0; i < code.length(); i++) {
			if (!Character.isDigit(code.charAt(i)))
				return false;
		}
		return true;
	}

	public boolean isValidDate(String date) {
		int date1 = Integer.valueOf(date);

		int DiziAdı[] = new int[1000];
		int i = 0;
		while (date1 > 0) {

			DiziAdı[i] = date1 % 10;

			date1 = date1 / 10;

			System.out.println(i + " " + DiziAdı[i]);
			i++;

		}

		if (i != 8)
			return false;
		if (i == 8 && DiziAdı[1] > 1)
			return false;

		if (i == 8 && DiziAdı[3] > 3) {
			if (DiziAdı[2] > 1) {
				return false;
			}

		}

		if (i == 8 && DiziAdı[1] == 1) {
			if (DiziAdı[0] > 2) {
				return false;
			}

		}

		return true;

	}
	
	 private  boolean dateValidate(String inputDate) {
         try {
             String[] datePattern = {"yyyy-MM-dd HH:mm:ss"};
             for (String pattern : datePattern) {
                 SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                 Date date = sdf.parse(inputDate);
                 String formattedDate = sdf.format(date);
                 if (inputDate.equals(formattedDate)) {
                     return true;
                 }
             }
         } catch (ParseException ex) {
             return false;
         }
         return false;

     }
	

}
