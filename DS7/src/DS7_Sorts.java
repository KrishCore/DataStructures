public class DS7_Sorts
{
    static int[] tempData = new int[0];

    public static void selectionSort(int[] list)
    {
        for (int a = 0; a < list.length; a++) {
            int minIndex = a;
            for (int b = a + 1; b < list.length; b++) {
                if (list[b] < list[minIndex])
                    minIndex = b;
            }
            int temp = list[minIndex];
            list[minIndex] = list[a];
            list[a] = temp;
        }
    }

    public static void insertionSort(int[] list)
    {
        for (int i = 1; i < list.length; i++) {
            int temp = list[i];
            int j = i;
            while (j > 0 && list[j - 1] > temp) {
                list[j] = list[j - 1];
                j = j - 1;
                list[j] = temp;
            }
        }
    }

    public static void mergeSort(int[] data, int from, int to)
    {
        if (tempData == null || data.length != tempData.length)
            tempData = new int[data.length];

        if (from == to)
            return;

        int middle = (from+to)/2;
        mergeSort(data, from, middle);
        mergeSort(data, middle+1, to);

        int i = from;
        int j = middle+1;
        int k = from;

        while (i<=middle && j <= to)
        {
            if (data[i] < data[j])
                tempData[k++] = data[i++];
            else
                tempData[k++] = data[j++];
        }
        while (i <= middle)
            tempData[k++] = data[i++];

        while (j <= to)
            tempData[k++] = data[j++];

        for (k = from; k <= to; k++)
            data[k] = tempData[k];
    }

    public static void quickSort(int[] data, int from, int to)
    {
        if (from >= to)
            return;

        int p = (from+to)/2;
        int i=from;
        int j=to;

        while (i<=j) {
            if (data[i] <= data[p])
                i++;
            else if (data[j] >= data[p])
                j--;
            else {
                int temp = data[j];
                data[j] = data[i];
                data[i] = temp;
                i++;
                j--;
            }
        }
        if (p < j) {
            int temp = data[p];
            data[p] = data[j];
            data[j] = temp;
            p = j;
        }
        else if (p > i) {
            int temp = data[p];
            data[p] = data[i];
            data[i] = temp;
            p = i;
        }

        quickSort(data, from, p-1);
        quickSort(data, p+1, to);
    }

    public static void heapSort(int[] list)
    {
        DS7_MinHeap<Integer> heap = new DS7_MinHeap<>();
        for (int i = 0; i < list.length; i++) {
            heap.insert(list[i]);
        }
        for (int i = 0; i < list.length; i++) {
            list[i] = heap.remove();
        }
    }
}
