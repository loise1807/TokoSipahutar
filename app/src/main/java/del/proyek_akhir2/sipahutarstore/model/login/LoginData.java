package del.proyek_akhir2.sipahutarstore.model.login;

import com.google.gson.annotations.SerializedName;

public class LoginData {

	@SerializedName("nama")
	private String nama;

	@SerializedName("no_tlp")
	private String noTlp;

	@SerializedName("jenis_kelamin")
	private String jenisKelamin;

	@SerializedName("id_pembeli")
	private String idPembeli;

	@SerializedName("username")
	private String username;

	@SerializedName("alamat")
	private String alamat;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setNoTlp(String noTlp){
		this.noTlp = noTlp;
	}

	public String getNoTlp(){
		return noTlp;
	}

	public void setJenisKelamin(String jenisKelamin){
		this.jenisKelamin = jenisKelamin;
	}

	public String getJenisKelamin(){
		return jenisKelamin;
	}

	public void setIdPembeli(String idPembeli){
		this.idPembeli = idPembeli;
	}

	public String getIdPembeli(){
		return idPembeli;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}
}