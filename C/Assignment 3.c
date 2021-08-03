#include <stdio.h>

void swapData(int *a, int *b)
{
    int c = *a;
    *a = *b;
    *b = c;
}

void quickSort(int data[], int first, int last)
{int i = first, j = last;
    if(first < last) {
        do {while(i < j && data[i] <= data[j])
                j--;
            if(data[i] > data[j]){
                swapData(&data[i],&data[j]);
                i++;
            }
            while(i < j && data[i] <= data[j])
                i++;
            if(data[i] > data[j]){
                swapData(&data[i],&data[j]);
                j--;
            }
        }while(i < j);
        if(first < j - 1)
            quickSort(data, first, j-1);
        if(i + 1 < last)
            quickSort(data, i+1, last);
    }
}

void mergeData(int data[], int temp[], int first, int mid, int last)
{
    int i1 = first;
    int i2 = mid + 1;
    for(int i = 0; i <= last - first; i++){
        if(i1 <= mid && i2 <= last){
            if(data[i1] < data[i2])
                temp[i] = data[i1++];
            else
                temp[i] = data[i2++];
        }
        else if(i1 > mid)
            temp[i] = data[i2++];
        else if(i2 > last)
            temp[i] = data[i1++];
    }
    for(int i = 0; i <= last - first; i++)
        data[first + i] = temp[i];
}

void mergeSort(int data[], int temp[], int first, int last)
{
    if(first < last)
    {
        int mid = (first + last) / 2;
        mergeSort(data, temp, first, mid);
        mergeSort(data, temp, mid + 1, last);
        mergeData(data, temp, first, mid, last);
    }
}

void printArray(int info[], int count)
{
    for(int i = 0; i < count; i++)
    {
        printf("%d, ",info[i]);
    }
    printf("\n");
}

int main()
{
    int temp[10];
    int data[10] = {5, 8, 3, 7, 4, 1, 2, 6, 10, 9};
    quickSort(data, 0, 9);
    printArray(data, 10);
    return 0;
}
