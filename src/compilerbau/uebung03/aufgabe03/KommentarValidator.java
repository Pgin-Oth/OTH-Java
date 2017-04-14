package compilerbau.uebung03.aufgabe03;

/**
 * Created by Xiangsu Chen on 2017/4/12.
 */
public class KommentarValidator {
    private String input;
    private int index = 0;

    public KommentarValidator(String input) {
        this.input = input;
    }

    private char getChar() {
        char c = this.input.charAt(this.index++);
        System.out.print(c);
        return c;
    }

    private boolean isEndOfInput() {
        return this.index == this.input.length();
    }

    private boolean isAsterisk(char c) {
        return c == 42;
    }

    private boolean isSlash(char c) {
        return c == 47;
    }

    private boolean start() {
        if (this.isEndOfInput())
            return true;
        char c = this.getChar();
        if (this.isSlash(c))
            return this.firstState();
        return this.start();
    }

    private boolean firstState() {
        if (this.isEndOfInput())
            return false;
        char c = this.getChar();
        if (this.isAsterisk(c))
            return this.secondState();
        return this.start();
    }

    private boolean secondState() {
        if (this.isEndOfInput())
            return false;
        char c = this.getChar();
        if (this.isAsterisk(c))
            return this.thirdState();
        return this.secondState();
    }

    private boolean thirdState() {
        if (this.isEndOfInput())
            return false;
        char c = this.getChar();
        if (this.isSlash(c))
            return this.start();
        return this.secondState();
    }

    public boolean validator() {
        return this.start();
    }

    public static void main(String args[]) {
        System.out.println(" ist" + (new KommentarValidator("abc/*bbc/def").validator()?"":" nicht") + " ein giltigen Kommentar.");
        System.out.println(" ist" + (new KommentarValidator("abc/*bbc*/def").validator()?"":" nicht") + " ein giltigen Kommentar.");
        System.out.println(" ist" + (new KommentarValidator("abc/bbc*/def").validator()?"":" nicht") + " ein giltigen Kommentar.");
        System.out.println(" ist" + (new KommentarValidator("abc/aa").validator()?"":" nicht") + " ein giltigen Kommentar.");
        System.out.println(" ist" + (new KommentarValidator("abc/bbc/def").validator()?"":" nicht") + " ein giltigen Kommentar.");
        System.out.println(" ist" + (new KommentarValidator("/*bbc*/").validator()?"":" nicht") + " ein giltigen Kommentar.");
        System.out.println(" ist" + (new KommentarValidator("//*b*c*/def").validator()?"":" nicht") + " ein giltigen Kommentar.");
        System.out.println(" ist" + (new KommentarValidator("//*b*c*/def//*b*c*/def").validator()?"":" nicht") + " ein giltigen Kommentar.");
    }
}
