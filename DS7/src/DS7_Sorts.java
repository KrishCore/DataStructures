public class DS7_Sorts
{
    public static void selectionSort(int[] list)
    {
        for (int a = 0; a < list.length; a++) {
            int minIndex = a;
            for (int b = a + 1; b < list.length; b++) {
                if (list[b] < list[minIndex]) {
                    minIndex = b;
                    int temp = list[minIndex];
                    list[minIndex] = list[a];
                    list[a] = temp;
                }
            }
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

    static int[] tempData = new int[1];

    public static void mergeSort(int[] data, int from, int to)
    {
        if (tempData == null || tempData.length != data.length)
            tempData = new int[data.length];

        if (to == from)
            return;

        int middle = (from+to)/2;
        mergeSort(data, from, middle);
        mergeSort(data, middle+1, to);

        int i = from;
        int j = middle+1;
        int k = from;

        while (i<=middle && j <= to) {
            if (data[i] < data[j])
                tempData[k++] = data[i++];
            else
                tempData[k++] = data[j++];

            while (i <= middle)
                tempData[k++] = data[i++];

            while (j <= to)
                tempData[k++] = data[j++];

            for (k = from; k <= to; k++)
            {
                data[k] = tempData[k];
            }
        }
    }
}
