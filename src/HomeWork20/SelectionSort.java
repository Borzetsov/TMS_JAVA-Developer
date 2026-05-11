/**
 * Classname    SelectionSort
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         10.05.2026
 */

package HomeWork20;

public class SelectionSort implements Runnable {

    public int[] array;

    public SelectionSort(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        int currentValue;
        int minIndex;
        for (int i = 0; i < this.array.length - 1; i ++) {
            minIndex = i;
            for (int j = i + 1; j < this.array.length; j++) {
                if (this.array[j] < this.array[minIndex]) {
                    minIndex = j;
                }
            }
            currentValue = this.array[minIndex];
            this.array[minIndex] = this.array[i];
            this.array[i] = currentValue;
        }
    }

    public int[] getResult() {
        return this.array;
    }
}
