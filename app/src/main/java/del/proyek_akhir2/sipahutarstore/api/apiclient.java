package del.proyek_akhir2.sipahutarstore.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apiclient {
    private static final String BASE_URL = "http://192.168.1.12/TokoSipahutar/Pelanggan/";

    private static Retrofit retrofit;

    public static Retrofit getClient() {

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
