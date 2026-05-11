/**
 * Classname    MyArrayList
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         18.04.2026
 */
 
package HomeWork15;

import java.util.Arrays;

public class MyArrayList<T> {

    private static final int DEFAULT_SIZE = 10;
    private static final double RESIZE_FACTOR = 1.6;
    private T[] values;
    private int newItemPosition = 0;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        this.values = (T[])new Object[DEFAULT_SIZE];
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int size) {
        if (size > 0) this.values = (T[])new Object[size];
        else this.values = (T[])new Object[DEFAULT_SIZE];
    }

    public void add(T newItem) {
        //Если массив заполнен, расширить
        if (this.newItemPosition == this.values.length) extendValues();
        this.values[newItemPosition] = newItem;
        this.newItemPosition++;
    }

    public void remove(int index) {
        //Ошибка индекса
        if ((index < 0) || (index >= this.newItemPosition)) throw new IndexOutOfBoundsException();

        System.arraycopy(this.values, index + 1, this.values, index, (this.values.length - index - 1));
        this.newItemPosition--;
        this.values[this.newItemPosition] = null;
    }

    public T elementAt(int index) {
        //Ошибка индекса
        if ((index < 0) || (index >= this.newItemPosition)) throw new IndexOutOfBoundsException();

        return this.values[index];
    }

    public void clear() {
        //Очистить все элементы
        Arrays.fill(this.values, null);
        this.newItemPosition = 0;
    }

    @SuppressWarnings("unchecked")
    public T[] getValues() {
        T[] returnValues = (T[])new Object[this.newItemPosition];
        System.arraycopy(this.values, 0, returnValues, 0, this.values.length);
        return returnValues;
    }

    public int getLength() {
        return this.newItemPosition;
    }

    @SuppressWarnings("unchecked")
    private void extendValues() {
        System.out.print("Расширение списка: было " + this.values.length + ", стало ");
        //Объявить новый массив большего размера
        T[] newValues = (T[])new Object[(int)Math.round(this.values.length * RESIZE_FACTOR)];
        //Скопировать в новый массив старые данные
        System.arraycopy(this.values, 0, newValues, 0, this.values.length);
        //Подменить ссылку
        this.values = newValues;
        System.out.println(this.values.length);
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder("[");
        if (this.newItemPosition > 0) {
            returnString.append(this.values[0].toString());
            for (int i = 1; i < this.newItemPosition; i++) {
                returnString.append(", ").append(this.values[i].toString());
            }
        }
        returnString.append("]");
        return returnString.toString();
    }

    public boolean contains(T item) {
        for (int i = 0; i < this.newItemPosition; i++) {
            if (item.equals(this.values[i])) return true;
        }
        return false;
    }
}
