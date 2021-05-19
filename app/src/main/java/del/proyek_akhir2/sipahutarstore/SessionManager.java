package del.proyek_akhir2.sipahutarstore;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;

import del.proyek_akhir2.sipahutarstore.model.login.LoginData;

public class SessionManager {
    private Context _context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String ID = "id";
    public static final String NAME = "nama";
    public static final String USERNAME = "username";
    public static final String J_KELAMIN = "jenis_kelamin";
    public static final String NO_TLP = "no_tlp";

    public SessionManager(Context context){
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(LoginData user){
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(ID,user.getIdPembeli());
        editor.putString(USERNAME,user.getUsername());
        editor.putString(NAME,user.getNama());
        editor.putString(J_KELAMIN,user.getJenisKelamin());
        editor.putString(NO_TLP,user.getNoTlp());
        editor.commit();
    }

    public HashMap<String,String> getUserDetail(){
        HashMap<String,String> user = new HashMap<>();
        user.put(ID,sharedPreferences.getString(ID,null));
        user.put(NAME,sharedPreferences.getString(NAME,null));
        user.put(USERNAME,sharedPreferences.getString(USERNAME,null));
        user.put(J_KELAMIN,sharedPreferences.getString(J_KELAMIN,null));
        user.put(NO_TLP,sharedPreferences.getString(NO_TLP,null));
        return user;
    }

    public void logoutSession(){
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGGED_IN,false);
    }
}
