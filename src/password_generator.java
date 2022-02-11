
public class password_generator {
    static final String numbers = "1234567890";
    static final String letterCaps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String letterSmall = "abcdefghijklmnopqrstuvwxyz";
    static final String specialChars = "~!@#$%^&*()-+";
    static final String combinedChars = numbers + letterCaps + letterSmall + specialChars;
    String password;

    String setPassword(int length){
        password = "";
        for(int i = 0; i <= length; i++){
            int index = (int)(Math.random()*75);
            password += combinedChars.charAt(index);
        }
        return password;
    }
}
