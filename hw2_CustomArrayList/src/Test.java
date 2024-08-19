import java.util.Comparator;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        CustomList<Integer> list = new CustomArrayList<>();
        list.add(4);
        list.add(7);
        list.add(2);
        list.add(3);
        list.set(2, 8);

        CustomList<Integer> list2 = new CustomArrayList<>(List.of(4, 7, 2, 3));
        CustomLists.sort(list2);


        System.out.println(list2);
        list2.remove(3);
        System.out.println(list2);


        class listComparator implements Comparator<Integer> {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        }

        Comparator<Integer> lc = new listComparator();

        list.bubbleSort(lc);

        list.bubbleSort(Comparator.comparingInt((Integer o) -> o));




        System.out.println(list);

        System.out.println(list.equals(list2));

        CustomList<Integer> list3 = new CustomArrayList<>(List.of(1, 7, 2, 3));
        CustomList<Integer> list4 = new CustomArrayList<>(List.of(1, 7, 2, 3));

        System.out.println(list3.equals(list4));

        boolean rem = list4.remove((Integer) 0);

        System.out.println(rem);
        System.out.println(list4);
    }
}