/**  Project 1A: Circular ArrayDeque.
 *
 *   ref = "https://sp18.datastructur.es/materials/proj/proj1a/proj1a"
 *
 *   @author: Boyu CHEN 05/04/2021
 *
 * */

public class ArrayDeque<T> {
    private float usage;
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] arrayList;

    public ArrayDeque() {
        arrayList = (T[]) new Object[8];
        size = 0;
        usage = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    private int minusOne(int index) {
        if (index != 0) {
            index--;
        } else {
            index = arrayList.length - 1;
        }
        return index;
    }

    public void addFirst(T item) {
        resize();
        arrayList[nextFirst] = item;
        size++;
        usage = (float) size / (float) arrayList.length;
        nextFirst = minusOne(nextFirst);
    }

    private int plusOne(int index) {
        if (index != arrayList.length - 1) {
            index++;
        } else {
            index = 0;
        }
        return index;
    }

    public void addLast(T item) {
        resize();
        arrayList[nextLast] = item;
        size++;
        usage = (float) size / (float) arrayList.length;
        nextLast = plusOne(nextLast);
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size == 0) {
            System.out.println("Empty AList!");
        } else {
            int p = plusOne(nextFirst);
            while (p != nextLast) {
                System.out.print(arrayList[p].toString() + ' ');
                p = plusOne(p);
            }
            System.out.println();
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T firstItem = arrayList[plusOne(nextFirst)];
        arrayList[plusOne(nextFirst)] = null;
        nextFirst = plusOne(nextFirst);
        size--;
        usage = (float) size / (float) arrayList.length;
        resize();
        return firstItem;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T lastItem = arrayList[minusOne(nextLast)];
        arrayList[minusOne(nextLast)] = null;
        nextLast = minusOne(nextLast);
        size--;
        usage = (float) size / (float) arrayList.length;
        resize();
        return lastItem;
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return arrayList[plusOne(nextFirst + index) % arrayList.length]; //use Array.length as modelo
    }


    private boolean checkUsage() {
        return ((usage < 0.25) && (arrayList.length >= 16));
    }

    private void resize() {
        /** Enlarge the Array when the AList is full. */
        if (size == arrayList.length) {
            T[] copyArray = (T[]) new Object[size * 2];
//            System.arraycopy(Array, 0, copyArray, 1, size);
            System.arraycopy(arrayList, plusOne(nextFirst), copyArray, 1, arrayList.length - plusOne(nextFirst));
            System.arraycopy(arrayList, 0, copyArray, arrayList.length - plusOne(nextFirst) + 1, nextLast);

            nextFirst = 0;
            nextLast = size + 1;
            arrayList = copyArray;
        }
        /** Shrink the Array when checkUsage is true. */
        else if (checkUsage()) {
            T[] copyArray = (T[]) new Object[arrayList.length / 2];
            if (nextLast > nextFirst) {
                System.arraycopy(arrayList, plusOne(nextFirst), copyArray, 1, nextLast - nextFirst + 1);
            } else {
                System.arraycopy(arrayList, plusOne(nextFirst), copyArray, 1, arrayList.length - nextFirst - 1);
                System.arraycopy(arrayList, 0, copyArray, arrayList.length - nextFirst, nextLast);
            }
            nextFirst = 0;
            nextLast = size + 1;
            arrayList = copyArray;
        }
    }

    /** Test resizing method. */

//    public void printArray() {
//        for (T p : arrayList) {
//            System.out.print(p + " ");
//        }
//        System.out.println();
//    }

//    public static void main(String[] args) {
//        ArrayDeque<Integer> testAList = new ArrayDeque<>();
//        testAList.addLast(0);
//        testAList.addLast(1);
//        testAList.addLast(2);
//        testAList.addLast(3);
//        testAList.addLast(4);
//        testAList.addLast(5);
//        testAList.addLast(6);
//        testAList.addLast(7);
//        testAList.addLast(8);
//        testAList.addLast(9);
////        TestAList.addLast(10);
////        TestAList.addLast(11);
////        TestAList.addLast(12);
////        TestAList.addLast(13);
////        TestAList.addLast(14);
////        TestAList.addLast(15);
////        TestAList.addLast(16);
////        TestAList.addLast(17);
//
//        testAList.printArray();
//        System.out.println(testAList.get(0));
////        System.out.println(TestAList.get(1));
////        System.out.println(TestAList.get(2));
////        System.out.println(TestAList.get(3));
////        System.out.println(TestAList.get(4));
////        System.out.println(TestAList.get(5));
////        System.out.println(TestAList.get(6));
////        System.out.println(TestAList.get(7));
//
//        int rm = testAList.removeFirst();
//        System.out.println(rm);
//        testAList.printArray();
//
//        testAList.addFirst(rm);
//        testAList.printArray();
//    }
}
