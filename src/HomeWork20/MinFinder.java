/**
 * Classname    MinFinder
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         10.05.2026
 */

package HomeWork20;

public class MinFinder implements Runnable {

    private final int[] array;
    private int minValue;

    public MinFinder(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        int minVal = this.array[0];
        for (int currentVal : this.array) {
            if (currentVal < minVal) minVal = currentVal;
        }
        this.minValue = minVal;
    }

    public int getResult() {
        return this.minValue;
    }
}
