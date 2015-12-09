/**
 * Created by Darsh on 11/18/15.
 */
public class QuickSortIntoInsertionSort {
    private long[] data;
    private int lenght;
    int count=0;
    int iCount=0;
    
    public static void main(String[] args) {
        //Object array
    	QuickSortIntoInsertionSort array = new QuickSortIntoInsertionSort(10000);
    	//array will hold 10,000 Random variables
    	for (int j = 0; j < 10000; j++){
    		long n = (int) (java.lang.Math.random() * 100);
    		array.insert(n);
            }
            //display array, Unsorted
            array.display();
            //run sort
            array.quickSort();
            //display array after sort
            array.display();
            }
            
    public QuickSortIntoInsertionSort(int max) {
        data = new long[max];
        lenght = 0;
    }
    public void insert(long value) {
        data[lenght] = value;
        lenght++;
    }
    public void quickSort() {
        recQS(0, lenght - 1);
    }
    public void recQS(int left, int right) {
        int size = right - left + 1;
        //>>>>>>>>>>>>>>>>>>>>>>>>>>THIS IS WHERE THE ARRAY IS SWITCHED TO INSERTION SORT <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        if (size < 10) //hard coded so that I can test efficency at different array sizes to switch to insertion sort.
            insertionSort(left, right);
        else // Quick sort if large
        {
            long median = midPoint(left, right);
            int partition = partitionIt(left, right, median);
            recQS(left, partition - 1);
            recQS(partition + 1, right);
            }
        }
    public long midPoint(int left, int right) {
        int center = (left + right) / 2;
        // order left & center
        if (data[left] > data[center])
            swap(left, center);
        // order left & right
        if (data[left] > data[right])
            swap(left, right);
        //order center & right
        if (data[center] > data[right])
            swap(center, right);
        
        swap(center, right - 1);
        return data[right - 1];
    }
    //swap if nessasary
    public void swap(int d1, int d2) {
        long temp = data[d1];
        data[d1] = data[d2];
        data[d2] = temp;
    }
    public int partitionIt(int left, int right, long pivot) {
        int leftPtr = left; // right of first node
        int rightPtr = right - 1; // left of pivot
       
        while (true) {
            //find bigger
            while (data[++leftPtr] < pivot);
            //find smaller
            while (data[--rightPtr] > pivot);
            
            if (leftPtr >= rightPtr) //if pointers cross, partition finished
                break;
            else
                swap(leftPtr, rightPtr);
        }
        swap(leftPtr, right - 1); //restore pivot
        count++;
        return leftPtr; //return pivot location
    }
    public void insertionSort(int left, int right) {
        int in, out;
        //  sorted on left of out
        for (out = left + 1; out <= right; out++) {
            long temp = data[out]; // remove marked node
            in = out; // start shifts at out until one is smaller
            while (in > left && data[in - 1] >= temp) {
                data[in] = data[in - 1]; //shift node to right
                --in; //go left one position
            }
            data[in] = temp; // insert marked item
            iCount++;
            }
        }
    //Method to display Array Data created and count variables for both Insertion sort and QuickSort
    public void display() {
        System.out.print("Data:");
        for (int j = 0; j < lenght; j++)
            System.out.print(data[j] + " ");
        System.out.println("");
        System.out.println("Quick Sort count: "+count);
        System.out.println("Insertion Sort count: "+iCount);
        }
    }
