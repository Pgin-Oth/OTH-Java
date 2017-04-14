package compilerbau.uebung03.aufgabe02;

/**
 * Created by Xiangsu Chen on 2017/4/12.
 */
public class RealZahl {
    private String input;
    private int index = 0;

    public RealZahl(String input) {
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
    private boolean isExponent(char c) {
        return c ==69 || c == 101;
    }

    private boolean isPoint(char c) {
        return c == 46;
    }

    private boolean isPlusOrMinus(char c) {
        return c == 43 || c == 45;
    }

    private boolean start() {
        if (this.isEndOfInput())
            return false;
        char c = this.getChar();
        if (this.isNumber(c))
            return this.firstState();
        return false;
    }

    private boolean firstState() {
        if (this.isEndOfInput())
            return true;
        char c = this.getChar();
        if (this.isNumber(c))
            return this.firstState();
        else if (this.isPoint(c))
            return this.secondState();
        else if (this.isExponent(c))
            return this.fourthState();
        return false;
    }

    private boolean secondState() {
        if (this.isEndOfInput())
            return false;
        char c = this.getChar();
        if (this.isNumber(c))
            return this.thirdState();
        return false;
    }

    private boolean thirdState() {
        if (this.isEndOfInput())
            return true;
        char c = this.getChar();
        if (this.isNumber(c))
            return this.thirdState();
        else if (this.isExponent(c))
            return this.fourthState();
        return false;
    }

    private boolean fourthState() {
        if (this.isEndOfInput())
            return false;
        char c = this.getChar();
        if (this.isNumber(c))
            return this.sixthState();
        if (this.isPlusOrMinus(c))
            return this.fifthState();
        return false;
    }

    private boolean fifthState() {
        if (this.isEndOfInput())
            return false;
        char c = this.getChar();
        if (this.isNumber(c))
            return this.sixthState();
        return false;
    }

    private boolean sixthState() {
        if (this.isEndOfInput())
            return true;
        char c = this.getChar();
        if (this.isNumber(c))
            return this.sixthState();
        return false;
    }

    public boolean validator() {
        return this.start();
    }

    public static void main(String args[]) {
        System.out.println(" ist" + (new RealZahl(".").validator() ? "" : " nicht") + " ein Realzahl.");
        System.out.println(" ist" + (new RealZahl(".E1").validator() ? "" : " nicht") + " ein Realzahl.");
        System.out.println(" ist" + (new RealZahl("1.").validator() ? "" : " nicht") + " ein Realzahl.");
        System.out.println(" ist" + (new RealZahl("0.E1").validator() ? "" : " nicht") + " ein Realzahl.");
        System.out.println(" ist" + (new RealZahl("0E").validator() ? "" : " nicht") + " ein Realzahl.");
        System.out.println(" ist" + (new RealZahl("0E+").validator() ? "" : " nicht") + " ein Realzahl.");
        System.out.println(" ist" + (new RealZahl("0E-").validator() ? "" : " nicht") + " ein Realzahl.");
        System.out.println(" ist" + (new RealZahl("0a0").validator() ? "" : " nicht") + " ein Realzahl.");
        System.out.println(" ist" + (new RealZahl("01").validator() ? "" : " nicht") + " ein Realzahl.");
        System.out.println(" ist" + (new RealZahl("0.0").validator() ? "" : " nicht") + " ein Realzahl.");
        System.out.println(" ist" + (new RealZahl("01.01").validator() ? "" : " nicht") + " ein Realzahl.");
        System.out.println(" ist" + (new RealZahl("0.0123E456").validator() ? "" : " nicht") + " ein Realzahl.");
        System.out.println(" ist" + (new RealZahl("0.0123E-456").validator() ? "" : " nicht") + " ein Realzahl.");
        System.out.println(" ist" + (new RealZahl("01.0123E+456").validator() ? "" : " nicht") + " ein Realzahl.");
    }
}
