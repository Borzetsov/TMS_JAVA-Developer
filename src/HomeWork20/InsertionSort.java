/**
 * Classname    InsertionSort
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         10.05.2026
 */

package HomeWork20;

public class InsertionSort implements Runnable {

    public int[] array;

    public InsertionSort(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        int currentValue;
        int j;
        for (int i = 1; i < this.array.length; i++) {
            currentValue = this.array[i];
            j = i - 1;
            while ((j >= 0) && (this.array[j] > currentValue)) {
                this.array[j + 1] = this.array[j];
                j--;
            }
            this.array[j + 1] = currentValue;
        }
    }

    public int[] getResult() {
        return this.array;
    }
}
