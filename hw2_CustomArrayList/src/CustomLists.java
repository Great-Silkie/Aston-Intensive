public class CustomLists {
    public static <E extends Comparable<? super E>> void sort(CustomList<E> list) {
        int n = list.size();
        int i = 0;
        boolean swap = true;

        while (swap) {
            swap = false;
            for (int j = 0; j < n - i - 1; j++) {
                E cur = list.get(j);
                E next = list.get(j + 1);

                if (cur.compareTo(next) > 0) {
                    list.set(j, next);
                    list.set(j + 1, cur);
                    swap = true;
                }
            }
            i++;
        }
    }
}
