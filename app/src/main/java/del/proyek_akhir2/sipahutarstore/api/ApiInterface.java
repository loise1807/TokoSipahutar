package del.proyek_akhir2.sipahutarstore.api;

import del.proyek_akhir2.sipahutarstore.model.login.Login;
import del.proyek_akhir2.sipahutarstore.model.register.Register;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login.php")
    Call<Login> loginResponse(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<Register> registerResponse(
            @Field("nama") String nama,
            @Field("username") String username,
            @Field("password") String password,
            @Field("jenis_kelamin") String jenis_kelamin,
            @Field("alamat") String alamat,
            @Field("no_tlp") String no_tlp
    );

}
