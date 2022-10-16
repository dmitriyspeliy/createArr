import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class IntegerListImpl implements IntegerList {
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
        if (arr.length == 0) {
            arr = new Integer[1];
            arr[0] = item;
            return item;
        }
        Integer[] tmpArr = new Integer[arr.length + 1];
        System.arraycopy(arr, 0, tmpArr, 0, arr.length);
        arr = tmpArr;
        arr[arr.length - 1] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        if (item == null) throw new NullPointerException("Вставить Null нельзя");
        if (index > arr.length || index < 0) throw new RuntimeException("Нет такого индекса");
        if (arr.length == 0) {
            arr = new Integer[1];
            arr[0] = item;
            return item;
        }
        Integer[] tmpArr = new Integer[arr.length + 1];
        System.arraycopy(arr, index, tmpArr, index + 1, arr.length - index);
        System.arraycopy(arr, 0, tmpArr, 0, index);
        tmpArr[index] = item;
        arr = tmpArr;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        if (item == null) throw new NullPointerException("Установить Null нельзя");
        if (index > arr.length || index < 0) throw new RuntimeException("Нет такого индекса");
        if (arr.length == 0) {
            arr = new Integer[1];
            arr[0] = item;
            return item;
        }
        arr[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        if (item == null) throw new NullPointerException("Удалить Null нельзя");
        int index = 0;
        boolean find = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(item)) {
                index = i;
                find = true;
            }
        }
        if (!find) throw new NullPointerException("Такого элемента нет");
        return arr[index];
    }

    @Override
    public Integer remove(int index) {
        if (isEmpty() || index < 0 || index >= arr.length) throw new NullPointerException("Нет такого индекса");
        Integer elem = arr[index];
        return getInteger(index, elem);
    }

    private Integer getInteger(int index, Integer elem) {
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
        return IntStream.range(0, arr.length).filter(i -> arr[i].equals(item)).findFirst().orElse(-1);
    }

    @Override
    public int lastIndexOf(Integer item) {
        if (item == null) throw new NullPointerException();
        int count = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (Objects.equals(arr[i], item)) {
                return count;
            }
            count++;
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index < 0 || index >= arr.length) throw new NullPointerException("Нет такого индекса");
        return arr[index];
    }

    @Override
    public boolean equals(IntegerListImpl otherList) {
        if (otherList == null) throw new NullPointerException();
        return Arrays.equals(otherList.getArr(), this.getArr());
    }

    @Override
    public int size() {
        return arr.length;
    }

    @Override
    public boolean isEmpty() {
        return arr.length == 0;
    }

    private void sortingArr() {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
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
        return arr;
    }

}
