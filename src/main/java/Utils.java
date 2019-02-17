import com.google.gson.Gson;

public class Utils {
    public static boolean isValidJson(String input){
        try {
            Gson gson = new Gson();
            gson.fromJson(input, Object.class);
            return true;
        } catch(com.google.gson.JsonSyntaxException ex) {
            return false;
        }
    }
}