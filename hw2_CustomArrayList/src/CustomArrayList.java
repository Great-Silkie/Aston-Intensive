import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

public class CustomArrayList<E> implements CustomList<E>{

    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Внутренний массив
     */
    private E[] elementData;

    private int size;

    @SuppressWarnings("unchecked")
    public CustomArrayList() {
        size = 0;
        this.elementData = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public CustomArrayList(int initialCapacity) {
        size = 0;
        if (initialCapacity > 0) {
            this.elementData = (E[]) new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    public CustomArrayList(Collection<? extends E> c) {
        this(c.size());
        addAll(c);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void ensureCapacity() {
        int newCapacity =  elementData.length * 3 / 2 + 1;
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    public void ensureCapacity(int toGrow) {
        int newCapacity =  elementData.length + toGrow;
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return elementData[index];
    }

    @Override
    public E set(int index, E element) {
        Objects.checkIndex(index, size);
        E oldValue = elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    @Override
    public void add(E e) {
        if (size == elementData.length)
            ensureCapacity();
        elementData[size++] = e;
    }

    @Override
    public void add(int index, E element) {
        int s = size;
        E[] elementData = this.elementData;
        if (s == elementData.length) {
            ensureCapacity();
        }
        System.arraycopy(elementData, index,
                elementData, index + 1,
                s - index);
        elementData[index] = element;
        size = s + 1;
    }

    @Override
    public void addAll(Collection<? extends E> c) {
        @SuppressWarnings("unchecked")E[] a = (E[]) c.toArray();

        if (size + c.size() <= elementData.length) {
            ensureCapacity(c.size());
        }
        System.arraycopy(a, 0, elementData, this.size, a.length);
        size += c.size();
    }

    @Override
    public E remove(int index) {
        Objects.checkIndex(index, size);
        final Object[] es = elementData;

        @SuppressWarnings("unchecked") E oldValue = (E) es[index];
        fastRemove(es, index);

        return oldValue;
    }

    @Override
    public boolean remove(E e) {
        final Object[] es = elementData;
        for (int i = 0; i < size(); i++) {
            if (e != null && this.get(i) == e) {
                fastRemove(es, i);
                return true;
            }
        }
        return false;
    }

    private void fastRemove(Object[] es, int i) {
        final int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(es, i + 1, es, i, newSize - i);
        es[size = newSize] = null;
    }

    @Override
    public void clear() {
        final Object[] es = elementData;
        for (int to = size, i = size = 0; i < to; i++)
            es[i] = null;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elementData[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof CustomList<?> that)) {
            return false;
        }

        boolean equal = true;

        if (this.size() != ((CustomList<?>) o).size()) {
            return false;
        }

        for (int i = 0; i < this.size(); i++) {
            if (!this.get(i).equals(that.get(i))) {
                equal = false;
                break;
            }
        }

        return equal;
    }

    @Override
    public int hashCode() {
        int from = 0;
        int to = this.size;

        final Object[] es = elementData;
        int hashCode = 1;
        for (int i = from; i < to; i++) {
            Object e = es[i];
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        }
        return hashCode;
    }

    public void sort(Comparator<? super E> c) {
        int left = 0;
        int right = size - 1;

        quickSort(left, right, c);
    }

    private void quickSort(int left, int right, Comparator<? super E> c) {
        if (left < right) {
            int q = partition(left, right, c);
            quickSort(left, q, c);
            quickSort(q + 1, right, c);
        }
    }

    private int partition(int left, int right, Comparator<? super E> c) {
        E[] es = elementData;
        E v = es[(left + right) / 2];
        int i = left;
        int j = right;
        while (i <= j) {
            while (c.compare(es[i], v) < 0) {
                i++;
            }
            while (c.compare(es[j], v) > 0) {
                j--;
            }
            if (i >= j) {
                break;
            }
            E temp = es[i];
            es[i] = es[j];
            es[j] = temp;

            i++;
            j--;
        }
        return j;
    }
}
