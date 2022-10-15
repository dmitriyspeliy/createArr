import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class StringListImpl implements StringList {
    private String[] arr;

    public StringListImpl() {
        arr = new String[0];
    }

    public StringListImpl(String... str) {
        arr = new String[str.length];
        System.arraycopy(str, 0, arr, 0, str.length);
    }


    @Override
    public String add(String item) {
        if (item == null) throw new NullPointerException("Вставить Null нельзя");
        if (arr.length == 0) {
            arr = new String[1];
            arr[0] = item;
            return item;
        }
        String[] tmpArr = new String[arr.length + 1];
        System.arraycopy(arr, 0, tmpArr, 0, arr.length);
        arr = tmpArr;
        arr[arr.length - 1] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        if (item == null) throw new NullPointerException("Вставить Null нельзя");
        if (index > arr.length || index < 0) throw new RuntimeException("Нет такого индекса");
        if (arr.length == 0) {
            arr = new String[1];
            arr[0] = item;
            return item;
        }
        String[] tmpArr = new String[arr.length + 1];
        System.arraycopy(arr, index, tmpArr, index + 1, arr.length - index);
        System.arraycopy(arr, 0, tmpArr, 0, index);
        tmpArr[index] = item;
        arr = tmpArr;
        return item;
    }

    @Override
    public String set(int index, String item) {
        if (item == null) throw new NullPointerException("Установить Null нельзя");
        if (index > arr.length || index < 0) throw new RuntimeException("Нет такого индекса");
        if (arr.length == 0) {
            arr = new String[1];
            arr[0] = item;
            return item;
        }
        arr[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
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
        return getString(index, item);
    }

    @Override
    public String remove(int index) {
        if (isEmpty() || index < 0 || index >= arr.length) throw new NullPointerException("Нет такого индекса");
        String elem = arr[index];
        return getString(index, elem);
    }

    private String getString(int index, String elem) {
        String[] tmpArr = new String[arr.length - 1];
        System.arraycopy(arr, index + 1, tmpArr, index, arr.length - index - 1);
        System.arraycopy(arr, 0, tmpArr, 0, index);
        arr = tmpArr;
        return elem;
    }

    @Override
    public boolean contains(String item) {
        return Arrays.asList(arr).contains(item);
    }

    @Override
    public int indexOf(String item) {
        if (item == null) throw new NullPointerException();
        return IntStream.range(0, arr.length).filter(i -> arr[i].equals(item)).findFirst().orElse(-1);
    }

    @Override
    public int lastIndexOf(String item) {
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
    public String get(int index) {
        if (index < 0 || index >= arr.length) throw new NullPointerException("Нет такого индекса");
        return arr[index];
    }

    @Override
    public boolean equals(StringListImpl otherList) {
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

    @Override
    public void clear() {
        arr = new String[0];
    }

    @Override
    public String[] toArray() {
        return getArr();
    }

    public String[] getArr() {
        return arr;
    }

}
