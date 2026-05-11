/**
 * Classname    MaxFinder
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         10.05.2026
 */

package HomeWork20;

public class MaxFinder implements Runnable {

    private final int[] array;
    private int maxValue;

    public MaxFinder(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        int maxVal = this.array[0];
        for (int currentVal : this.array) {
            if (currentVal > maxVal) maxVal = currentVal;
        }
        this.maxValue = maxVal;
    }

    public int getResult() {
        return this.maxValue;
    }
}
