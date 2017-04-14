package compilerbau.uebung03.aufgabe01;

/**
 * Created by Mei on 2017/4/12.
 */
public class FixKommaZahl {
    private String input;
    private int index = 0;

    public FixKommaZahl(String input) {
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

    private boolean isNumber(char c) {
        return this.isZero(c) || this.isNumberFromOnToNine(c);
    }

    private boolean isZero(char c) {
        return c == 48;
    }

    private boolean isNumberFromOnToNine(char c) {
        return c >= 49 && c <= 57;
    }

    private boolean isPoint(char c) {
        return c == 46;
    }

    private boolean start() {
        if (this.isEndOfInput())
            return false;
        char c = this.getChar();
        if (this.isPoint(c))
            return this.firstState();
        else if (this.isZero(c))
            return this.secondState();
        else if (this.isNumberFromOnToNine(c))
            return this.thirdState();
        return false;
    }

    private boolean firstState() {
        if (this.isEndOfInput())
            return false;
        char c = this.getChar();
        if (this.isNumber(c))
            return this.fourthState();
        return false;
    }

    private boolean secondState() {
        if (this.isEndOfInput())
            return false;
        char c = this.getChar();
        if (this.isPoint(c))
            return this.fourthState();
        return false;
    }

    private boolean thirdState() {
        if (this.isEndOfInput())
            return false;
        char c = this.getChar();
        if (this.isNumber(c))
            return this.thirdState();
        else if (this.isPoint(c))
            return this.fourthState();
        return false;
    }

    private boolean fourthState() {
        if (this.isEndOfInput())
            return true;
        char c = this.getChar();
        if (this.isNumber(c))
            return this.fourthState();
        return false;
    }

    public boolean validator() {
        return this.start();
    }

    public static void main(String args[]) {
        System.out.println(" ist" + (new FixKommaZahl(".").validator() ? "" : " nicht") + " ein Fixkommazahl.");
        System.out.println(" ist" + (new FixKommaZahl("..").validator() ? "" : " nicht") + " ein Fixkommazahl.");
        System.out.println(" ist" + (new FixKommaZahl("0").validator() ? "" : " nicht") + " ein Fixkommazahl.");
        System.out.println(" ist" + (new FixKommaZahl("0.1.").validator() ? "" : " nicht") + " ein Fixkommazahl.");
        System.out.println(" ist" + (new FixKommaZahl("0.1a1").validator() ? "" : " nicht") + " ein Fixkommazahl.");
        System.out.println(" ist" + (new FixKommaZahl("a.11").validator() ? "" : " nicht") + " ein Fixkommazahl.");
        System.out.println(" ist" + (new FixKommaZahl("0.").validator() ? "" : " nicht") + " ein Fixkommazahl.");
        System.out.println(" ist" + (new FixKommaZahl("0.0").validator() ? "" : " nicht") + " ein Fixkommazahl.");
        System.out.println(" ist" + (new FixKommaZahl(".1").validator() ? "" : " nicht") + " ein Fixkommazahl.");
        System.out.println(" ist" + (new FixKommaZahl(".11").validator() ? "" : " nicht") + " ein Fixkommazahl.");
    }
}
