import java.util.Random;

public class Arrays {

    public static void main(String[] args) {


        /* 
        int[] array = ArrayUtils.prepareArray();
        ArrayUtils.printArray(array);
        SortUtils.HeapSort.sort(array);
        ArrayUtils.printArray(array);
        */
        int[] testArray = ArrayUtils.prepareArray(200000);
        long startTime = System.currentTimeMillis();
        //SortUtils.directSort(testArray.clone());
        long endTime = System.currentTimeMillis();
        long processingTime = endTime - startTime;
        System.out.printf("Время работы сортировки выбором: %d мс.\n", processingTime);


        startTime = System.currentTimeMillis();
        SortUtils.quickSort(testArray.clone());
        endTime = System.currentTimeMillis();
        processingTime = endTime - startTime;
        System.out.printf("Время работы быстрой сортировки: %d мс.\n", processingTime);


        startTime = System.currentTimeMillis();
        SortUtils.HeapSort.sort(testArray.clone());
        endTime = System.currentTimeMillis();
        processingTime = endTime - startTime;
        System.out.printf("Время работы пирамидальной сортировки: %d мс.\n", processingTime);


    }

    static class ArrayUtils{

        static final Random random = new Random();

        /**
         * Подготовить массив случайных чисел
         * @return
         */
        static int[] prepareArray(){
            int[]  array = new int[random.nextInt(15) + 5];
            for ( int i = 0; i < array.length; i++){
                array[i] = random.nextInt(100);
            }
            return array;
        }

        /**
         * Подготовить массив случайных чисел
         * @return
         */
        static int[] prepareArray(int length){
            int[]  array = new int[length];
            for ( int i = 0; i < array.length; i++){
                array[i] = random.nextInt(100);
            }
            return array;
        }

        /**
         * Распечатать массив
         * @param array массив
         */
        static void printArray(int[] array){
            for (int element: array) {
                System.out.printf("%d\t", element);
            }
            System.out.println();
        }

    }

    /**
     * Утилиты для сортировки
     */
    static class SortUtils {


        static void directSort(int[] array){
            for (int i = 0; i < array.length; i++){
                int index = i;

                for (int j = i + 1; j < array.length; j++){
                    if (array[j] < array[index])
                        index = j;
                }

                if (index != i){
                    int buf = array[i];
                    array[i] = array[index];
                    array[index] = buf;
                }
            }
        }

        static  void quickSort(int[] array){
            quickSort(array, 0, array.length - 1);
        }

        static void quickSort(int[] array, int start, int end){
            int left = start;
            int right = end;
            int middle = array[(start + end) / 2];

            do {
                while (array[left] < middle) {
                    left++;
                }
                while (array[right] > middle) {
                    right--;
                }
                if (left <= right) {
                    if (left < right) {
                        int buf = array[left];
                        array[left] = array[right];
                        array[right] = buf;
                    }
                    left++;
                    right--;
                }
            }
            while (left <= right);
            if (left < end){
                quickSort(array, left, end);
            }
            if (start < right){
                quickSort(array, start, right);
            }
        }

        static class HeapSort{

            static void sort(int[] array){
                // Построение пирамиды
                for (int i = array.length/2 - 1; i >= 0; i--){
                    HeapSort.buildHeap(array, array.length, i);   
                }

                // Поочередное извлечение элементов
                for (int i = array.length - 1; i >= 0; i--){
                    // Перенос корня в конец массива
                    int temp = array[0];
                    array[0] = array[i];
                    array[i] = temp;

                    // Повторяем на уменьшевшейся пирамиде
                    HeapSort.buildHeap(array, i, 0);
                }
            }

            private static void buildHeap(int[] array, int heapSize, int rootIndex) {
                int maxValueIndex = rootIndex; //Инициализируем переменную для индекса наибольшего элемента и изначально присваиваем ей индекс корня
                int left = 2*rootIndex + 1; // Индекс левого "ребенка"
                int right = 2*rootIndex + 2; // Индекс правого "ребенка"

                // Поиск индекса наибольшего элемента в пирамиде из трех элементов
                if (left < heapSize && array[left] > array[maxValueIndex]) {
                    maxValueIndex = left;
                }

                if (right < heapSize && array[right] > array[maxValueIndex]) {
                    maxValueIndex = right;
                }

                // Выводим наибольший элемент в корень
                if (maxValueIndex != rootIndex) {
                    int temp = array[maxValueIndex];
                    array[maxValueIndex] = array[rootIndex];
                    array[rootIndex] = temp;                        
                    // Рекурсивно повторяем на измененной подкуче
                    buildHeap(array, heapSize, maxValueIndex);
                }
            }
        }


    }

    static class SearchUtils{

        static int binarySearch(int[] array, int value){
            return binarySearch(array, value, 0, array.length - 1);

        }

        static int binarySearch(int[] array, int value, int left, int right){
            if (right < left) return -1;
            int middle = (left + right) / 2;

            if (array[middle] == value){
                return middle;
            }
            else if (array[middle] < value){
                return binarySearch(array, value, middle + 1, right);
            }
            else {
                return binarySearch(array, value, left, middle - 1);
            }
        }

    }

}