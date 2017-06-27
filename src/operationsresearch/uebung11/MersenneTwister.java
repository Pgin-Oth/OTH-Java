package operationsresearch.uebung11;

/**
 * Created by 陈香甦 on 2017/6/23.
 */
public class MersenneTwister {
    private int seed;
    private int[] mt = new int[624];
    private int index = 0;

    public MersenneTwister() {
    }

    public void initializeGenerator(int seed) {
        this.mt[0] = seed;
        for (int i = 1; i < 624; i++) {
            this.mt[i] = (int)(1812433253 * (this.mt[i - 1]^((this.mt[i-1])>>30)));
        }
    }

    public int extractNumber(){
        if (this.index == 0)
            this.generateNumbers();
        long y = this.mt[index];
        y = y^(y>>11);
        y = y^(y<<7 & 2636928640l);
        y = y^(y<<15 & 4022730752l);
        y = y^(y>>18);
        this.index = (this.index + 1) % 624;
        return (int)y;
    }

    private void generateNumbers() {
        for (int i = 0; i < 623; i++) {
            int y = (this.mt[i] & 0x80000000) + (this.mt[(i+1) % 624] & 0x7fffffff);
            this.mt[i] = this.mt[(i + 397) % 624]^(y>>1);
            if ((y % 2) != 0)
                this.mt[i] = (int)(this.mt[i]^(2567483615l));
        }
    }
    public static void main(String[] arg){
        MersenneTwister z1 = new MersenneTwister();
        z1.initializeGenerator(10);
        System.out.println(z1.extractNumber());
        MersenneTwister z2 = new MersenneTwister();
        z2.initializeGenerator(10);
        System.out.println(z2.extractNumber());
    }
}
