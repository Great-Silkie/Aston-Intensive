import java.util.Comparator;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        list.add(1);
        list.add(5);
        list.add(2);
        list.add(8);

        list.addAll(List.of(10, 12, 15));
        list.add(1,20);

        list.get(2);
        list.remove(3);
        list.remove((Integer) 10);

        System.out.println("Is list empty? - " + list.isEmpty());

        list.sort(Comparator.comparingInt((o) -> o));
        System.out.println(list);

        list.clear();
        System.out.println("Is list empty? - " + list.isEmpty());
    }
}
