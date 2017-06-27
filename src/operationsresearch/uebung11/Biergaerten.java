package operationsresearch.uebung11;

import java.util.Random;

/**
 * Created by 陈香甦 on 2017/6/23.
 */
public class Biergaerten {
    private int[][] biergaertenMatrix = new int[127][2];

    public Biergaerten(int[][] biergaertenMatrix) {
    }

    public void readTSPFile(){
        for (int i = 0; i < 127; i++) {
            this.biergaertenMatrix[i][0] = 1;
            this.biergaertenMatrix[i][1] = 2;
        }
    }

    public int[][] getBiergaertenMatrix() {
        return biergaertenMatrix;
    }

    public void setBiergaertenMatrix(int[][] biergaertenMatrix) {
        this.biergaertenMatrix = biergaertenMatrix;
    }

    public double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2)+Math.pow(y1 - y2, 2));
    }

    public static void main(String[] args) {
        Biergaerten biergaerten = new Biergaerten(new int[2][3]);
        biergaerten.readTSPFile();
        Random random = new Random(System.currentTimeMillis());
        int[] xy0 = new int[2];
        xy0 = biergaerten.getBiergaertenMatrix()[random.nextInt(126)];
        int n = 0;
        double x0 = 0;
        double xbest = x0;
        int n_mcs = 1000;
        int n_max = 1000;
        while (true){
            for (int i = 0; i< 127; i++){
                System.out.println();
            }
        }

    }
}
