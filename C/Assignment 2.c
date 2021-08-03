#include <stdio.h>

void swapInfo(int *a, int *b)
{
    int c = *a;
    *a = *b;
    *b = c;
}

void bubbleSort(int info[], int start, int stop)
{
    for(int i = start; i < stop; i++)
    {
        for(int j = stop; j > i; j--)
        {
            if(info[j] < info[j - 1])
            {
                swapInfo(&info[j], &info[j - 1]);
            }
        }
    }
}

void insertionSort(int info[], int start, int stop)
{
    for(int i = start + 1; i <= stop; i++)
    {
        int j;
        int x = info[i];
        for(j = i; ( j > start) && ( x < info[j - 1]); j--)
        {
            info[j] = info[j - 1];
        }
        info[j] = x;
    }
}

void selectionSort(int info[], int start, int stop)
{
    for(int i = start; i < stop; i++)
    {
        int min = i;
        for(int j = i + 1; j <= stop; j++)
        {
            if(info[j] < info[min])
            {
                min = j;
            }
        }
        swapInfo(&info[i], &info[min]);
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
    int info[10] = {10, 5, 8, 6, 3, 7, 4, 2, 1, 9};
    bubbleSort(info, 0, 9);
    printArray(info, 10);

    return 0;
}
