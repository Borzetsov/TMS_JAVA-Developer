/**
 * Classname    BubbleSort
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         10.05.2026
 */

package HomeWork20;

public class BubbleSort implements Runnable {

    public int[] array;

    public BubbleSort(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        int currentVal;
        for (int i = 0; i < this.array.length - 1; i++) {
            for (int j = 0; j < this.array.length - i - 1; j++) {
                if (this.array[j] > this.array[j + 1]) {
                    currentVal = this.array[j];
                    this.array[j] = this.array[j + 1];
                    this.array[j + 1] = currentVal;
                }
            }
        }
    }

    public int[] getResult() {
        return this.array;
    }
}
