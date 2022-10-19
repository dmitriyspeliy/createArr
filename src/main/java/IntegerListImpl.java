import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class IntegerListImpl implements IntegerList {

    public static void main(String[] args) {
        IntegerListImpl integerList = new IntegerListImpl();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);
        System.out.println(Arrays.toString(integerList.arr));

    }

    private Integer[] arr;

    public IntegerListImpl() {
        arr = new Integer[0];
    }

    public IntegerListImpl(Integer... str) {
        arr = new Integer[str.length];
        System.arraycopy(str, 0, arr, 0, str.length);
    }


    @Override
    public Integer add(Integer item) {
        if (item == null) throw new NullPointerException("Вставить Null нельзя");
        int index = 0;
        if (arr.length == 0) {
            return growWhenEmpty(item);
        }
        if (arr[arr.length - 1] != null) {
            grow();
        }
        for (int j = arr.length - 1; j >= 0; j--) {
            if(arr[j] != null){
                index = j + 1;
                break;
            }
        }
        arr[index] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        if (item == null) throw new NullPointerException("Вставить Null нельзя");
        if (index > arr.length || index < 0) throw new RuntimeException("Нет такого индекса");
        if (arr.length == 0) {
            return growWhenEmpty(item);
        }
        growWithIndex(index);
        arr[index] = item;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        if (item == null) throw new NullPointerException("Установить Null нельзя");
        if (index > arr.length || index < 0) throw new RuntimeException("Нет такого индекса");
        if (arr.length == 0) {
            return growWhenEmpty(item);
        }
        arr[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        if (item == null) throw new NullPointerException("Удалить Null нельзя");
        Integer find = null;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].equals(item)) {
                find = arr[i];
                return removeWithIndex(i,find);
            }
        }
        throw new NullPointerException("Такого элемента нет");
    }

    @Override
    public Integer remove(int index) {
        if (arr.length == 0 || index < 0 || index > arr.length || arr[index] == null) throw new NullPointerException("Нет такого индекса");
        Integer elem = arr[index];
        return removeWithIndex(index, elem);
    }

    private Integer removeWithIndex(int index, Integer elem) {
        Integer[] tmpArr = new Integer[arr.length - 1];
        System.arraycopy(arr, index + 1, tmpArr, index, arr.length - index - 1);
        System.arraycopy(arr, 0, tmpArr, 0, index);
        arr = tmpArr;
        return elem;
    }


    @Override
    public boolean contains(Integer item) {
        return Arrays.asList(arr).contains(item);
    }


    public boolean BinarySearchContains(Integer item) {
        sortingArr();
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (Objects.equals(item, arr[mid])) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        if (item == null) throw new NullPointerException();
        return IntStream.range(0, getArr().length).filter(i -> getArr()[i] != null && getArr()[i].equals(item)).findFirst().orElse(-1);
    }

    @Override
    public int lastIndexOf(Integer item) {
        if (item == null) throw new NullPointerException();
        int count = 0;
        for (int i = getArr().length - 1; i >= 0; i--) {
            if (getArr()[i] != null && Objects.equals(getArr()[i], item)) {
                return count;
            }
            count++;
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index < 0 || index > arr.length) throw new NullPointerException("Нет такого индекса");
        return arr[index];
    }

    @Override
    public boolean equals(IntegerListImpl otherList) {
        if (otherList == null) throw new NullPointerException();
        return Arrays.equals(otherList.getArr(), this.getArr());
    }

    @Override
    public int size() {
        return getArr().length;
    }

    @Override
    public boolean isEmpty() {
        return getArr().length == 0;
    }


    private void sortingArr() {
        mergeSort(getArr());
    }

    @Override
    public void clear() {
        arr = new Integer[0];
    }

    @Override
    public Integer[] toArray() {
        return getArr();
    }

    public Integer[] getArr() {
        arr = Arrays.stream(arr).filter(Objects::nonNull).toArray(Integer[]::new);
        return arr;
    }

    public void grow() {
        Integer[] tmpArr = new Integer[(int) (arr.length * 1.5)];
        System.arraycopy(arr, 0, tmpArr, 0, arr.length);
        arr = tmpArr;
    }

    public void growWithIndex(int index) {
        Integer[] tmpArr;
        if (arr[arr.length - 1] != null) {
            tmpArr = new Integer[(int) (arr.length * 1.5)];
        } else {
            tmpArr = new Integer[arr.length + 1];
        }
        System.arraycopy(arr, index, tmpArr, index + 1, arr.length - index);
        System.arraycopy(arr, 0, tmpArr, 0, index);
        arr = tmpArr;
    }

    public Integer growWhenEmpty(Integer item) {
        arr = new Integer[3];
        arr[0] = item;
        return item;
    }


    public static void mergeSort(Integer[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length / 2;
        Integer[] left = new Integer[mid];
        Integer[] right = new Integer[arr.length - mid];

        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];
        }

        for (int i = 0; i < right.length; i++) {
            right[i] = arr[mid + i];
        }

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }

    public static void merge(Integer[] arr, Integer[] left, Integer[] right) {

        int mainP = 0;
        int leftP = 0;
        int rightP = 0;
        while (leftP < left.length && rightP < right.length) {
            if (left[leftP] <= right[rightP]) {
                arr[mainP++] = left[leftP++];
            } else {
                arr[mainP++] = right[rightP++];
            }
        }
        while (leftP < left.length) {
            arr[mainP++] = left[leftP++];
        }
        while (rightP < right.length) {
            arr[mainP++] = right[rightP++];
        }
    }


}
