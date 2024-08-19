import java.util.Collection;
import java.util.Comparator;

public interface CustomList<E> {
    int size();

    boolean isEmpty();

    void add(E e);

    void add(int index, E element);

    void addAll(Collection<? extends E> c);

    E remove(int index);

    boolean remove(E e);

    void clear();

    E get(int index);

    E set(int index, E element);

    String toString();

    boolean equals(Object o);

    int hashCode();


    default void bubbleSort(Comparator<? super E> comparator) {
        int n = size();
        int i = 0;
        boolean swap = true;

        while (swap) {
            swap = false;
            for (int j = 0; j < n - i - 1; j++) {
                E cur = get(j);
                E next = get(j + 1);

                if (comparator.compare(cur, next) > 0) {
                    set(j, next);
                    set(j + 1, cur);
                    swap = true;
                }
            }
            i++;
        }
    }
}
