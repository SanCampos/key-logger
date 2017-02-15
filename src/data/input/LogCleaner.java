package data.input;

public class LogCleaner {

    public static String cleanAndEncrypt(String s) {
        String output = separateSentences(s);
        //output = cipher(output, 4);
        return output;
    }

    public static String separateSentences(String s) {
        return s.replace("!", System.lineSeparator())
                .replace(".", System.lineSeparator())
                .replace("?", System.lineSeparator());
    }

    public static String cipher(String msg, int shift) {
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
}
