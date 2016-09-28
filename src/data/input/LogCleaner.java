package data.input;

public class LogCleaner {

    public String separateSentences(StringBuilder sb) {
        for (int i = 0; i < sb.length(); i++) {
            if ((sb.charAt(i) == '.' || sb.charAt(i) == '!' || sb.charAt(i) == '?') && sb.charAt(sb.length() - 1) != sb.charAt(i)) {
                sb.insert(i + 1, System.lineSeparator());
            }
        }
        return sb.toString();
    }


    public String cipher(String msg, int shift) {
        String s = "";

        for (int i = 0; i < msg.length(); i++) {
            char c = (char) (msg.charAt(i) + shift);
            char d = (char) (msg.charAt(i) - (26 - shift));
            
            if (Character.isUpperCase(msg.charAt(i))) {
                c = Character.toUpperCase(c);
                d = Character.toUpperCase(d);
            }
            
            if (c > 'z') {
                s += d;
            } else {
                s += c;
            }
        }
        return s;
    }
   
    public String cleanAndEncrypt(StringBuilder sb) {
        String output = separateSentences(sb);
        //output = cipher(output, 4);
        return output;
    }
}
