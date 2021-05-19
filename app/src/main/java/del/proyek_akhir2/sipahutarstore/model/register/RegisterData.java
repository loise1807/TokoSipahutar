package del.proyek_akhir2.sipahutarstore.model.register;

import com.google.gson.annotations.SerializedName;

public class RegisterData {

	@SerializedName("nama")
	private String nama;

	@SerializedName("no_tlp")
	private String noTlp;

	@SerializedName("jenis_kelamin")
	private String jenisKelamin;

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