import java.security.SecureRandom;

public class RandomString {
    static final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static final String nums = "0123456789";

    static SecureRandom rnd = new SecureRandom();

    static String randomString(int len, boolean withNums){
        String all = AB + (withNums?nums:"");
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++)
            sb.append(all.charAt(rnd.nextInt(all.length())));
        return sb.toString();
    }
}
