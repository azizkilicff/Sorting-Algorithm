import java.util.Random;

public class Project_1 {
    private static  double array[][];
    private static String type;
    private static  int number;
    private static  int size;
    private static long startingTime;
    private static long estimatedTime;


    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("Your input is incorrect! Please try again!");
            return;
        }

        type = args[0].toLowerCase();
        number = Integer.parseInt(args[1]);
        size = Integer.parseInt(args[2]);


        generateRandomArrays(number, size);


        startingTime = System.nanoTime();
        switch (type) {
            case "insertion":
                for(int i = 0; i < number; i++) {
                    insertionSort(array[i]);
                }
                break;
            case "selection":
                for(int i = 0; i < number; i++) {
                    selectionSort(array[i]);
                }
                break;
            case "quicksort":
                for(int i = 0; i < number; i++) {
                    quickSort(array[i], 0, size - 1);
                }
                break;
            case "merge":
                for(int i = 0; i < number; i++) {
                    mergeSort(array[i], 0, size - 1);
                }
                break;
            case "bubble":
                for(int i = 0; i < number; i++) {
                    bubbleSort(array[i]);
                }
                break;
            default:
                System.out.println("Invalid sort type! Please select one of the following sort types: insertion, selection, quicksort, merge, bubble!");
                break;
        }


        estimatedTime = (System.nanoTime() - startingTime)  / 1000000;

        System.out.println("Estimated Time: " + estimatedTime);
    }

    private static void insertionSort(double arra[]) {
        int i, j;
        double key;
        int n = arra.length;
        for (i = 1; i < n; i++) {
            key = arra[i];
            j = i - 1;
            while (j >= 0 && arra[j] > key) {
                arra[j + 1] = arra[j];
                j = j - 1;
            }
            arra[j + 1] = key;
        }
    }

    // Selection Sort
    private static void selectionSort(double arra[]) {
        int n = arra.length;
        for (int i = 0; i < n-1; i++) {
            int min_idx = i;
            for (int j = i+1; j < n; j++) {
                if (arra[j] < arra[min_idx]) {
                    min_idx = j;
                }
            }
            double temp = arra[min_idx];
            arra[min_idx] = arra[i];
            arra[i] = temp;
        }
    }

    // QuickSort (Recursive)
    private static void swap(double[] arra, int i, int j) {
        double temp = arra[i];
        arra[i] = arra[j];
        arra[j] = temp;
    }

    private static int partition(double[] arra, int low, int high) {
        double pivot = arra[high];
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (arra[j] < pivot) {
                i++;
                swap(arra, i, j);
            }
        }
        swap(arra, i + 1, high);
        return (i + 1);
    }

    private static void quickSort(double[] arra, int low, int high) {
        if (low < high) {
            int p = partition(arra, low, high);

            quickSort(arra, low, p - 1);
            quickSort(arra, p + 1, high);
        }
    }

    // Merge Sort (Recursive)
    private static void merge(double arra[], int left, int middle, int right) {
        int size1 = middle - left + 1;
        int size2 = right - middle;


        double L[] = new double[size1];
        double R[] = new double[size2];

        for (int i = 0; i < size1; ++i) {
            L[i] = arra[left + i];
        }
        for (int j = 0; j < size2; ++j) {
            R[j] = arra[middle + 1 + j];
        }

        int i = 0, j = 0;

        int k = left;
        while (i < size1 && j < size2) {
            if (L[i] <= R[j]) {
                arra[k] = L[i];
                i++;
            }
            else {
                arra[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < size1) {
            arra[k] = L[i];
            i++;
            k++;
        }

        while (j < size2) {
            arra[k] = R[j];
            j++;
            k++;
        }
    }

    private static void mergeSort(double arra[], int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;

            mergeSort(arra, left, middle);
            mergeSort(arra, middle + 1, right);

            merge(arra, left, middle, right);
        }
    }

    // Bubble Sort
    private static void bubbleSort(double arra[]) {
        int i, j;
        double temp;
        boolean swapped;
        int n = arra.length;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (arra[j] > arra[j + 1]) {
                    temp = arra[j];
                    arra[j] = arra[j + 1];
                    arra[j + 1] = temp;
                    swapped = true;
                }
            }

            if (swapped == false) {
                break;
            }
        }
    }

    private static void generateRandomArrays(int number, int size) {
        array = new double[number][size];
        Random rand = new Random();
        for(int i = 0; i < number; i++) {
            for(int j = 0; j < size; j++) {
                array[i][j] = rand.nextDouble();
            }
        }
    }

}
