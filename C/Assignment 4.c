#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include <math.h>

char commands[3][13][10] = {{"list", "end", "sort", "pop", "help", "sqrt", "rec", "neg", "pow", "+", "-", "*", "/"},
                                {"delete","search","peek","push"}, {"add", "insert"}};

typedef struct node
{
    double info;
    struct node *next;
} linkedList;

    linkedList *ptr = NULL;
    linkedList *first = NULL;
    linkedList *last = NULL;

//check if input number is correct
int checkNumbers(char tokens[][10], int count, double* parameters)
{
    int isCorrect = 1;
    for(int i = 1; i < count && isCorrect; i++)
    {
        char* tmp = "";
        parameters[i - 1] = strtod(tokens[i], &tmp);
        if(strcmp(tmp,""))
        {
            isCorrect = 0;
        }
    }
    parameters[count - 1] = 999983;
    return isCorrect;
}

//check for commands and nuumbers
int checkTokens(char tokens[][10], int count, int* groupNo, int* commandNo, double* parameters)
{
    for(int i = 0; i < 3 && *groupNo == -1; i++)
    {
        for(int j = 0; strcmp(commands[i][j],"") && j < 13 && *commandNo == -1; j++)
        {
            if(strcmp(strlwr(tokens[0]), commands[i][j]) == 0)
            {
                (*groupNo) = i;
                (*commandNo) = j;
            }
        }
    }
    if(*groupNo == 0)
    {
        if(count == 1)
        {
            if(*commandNo == 1)
                return -1;
            return 1;
        }
        else
            return 0;
    }
    else if(*groupNo == 1)
    {
        if(count == 2)
            return checkNumbers(tokens, count, parameters);
        else
            return 2;
    }
    else if(*groupNo == 2)
    {
        if(count >= 2)
            return checkNumbers(tokens, count, parameters);
        else
            return 2;
    }
    else
    {
        return 2;
    }
}

//break input into tokens
void processInput(char* input, char tokens[][10], int* count)
{
    char* tmp = strtok(input, " ");
    while(tmp != NULL)
    {
        strcpy(tokens[(*count)++], tmp);
        tmp = strtok(NULL, " ");
    }
}

//allocate dynamic memory to create a node
void createNode(double info)
{
    ptr = (linkedList*)malloc(sizeof(linkedList));
    ptr->info = info;
    ptr->next = NULL;
}

//add node to the end of the list
void addNode(double info)
{
    createNode(info);
    if(first == NULL)
    {
        first = ptr;
        last = ptr;
    }
    else
    {
        last->next = ptr;
        last = ptr;
    }
}

//free the whole list
void freeNodes()
{
    while(first != NULL)
    {
        ptr = first;
        first = first->next;
        free(ptr);
    }
}

//show the list and return number of nodes
int listNodes()
{
    ptr = first;
    int count = 0;
    if(ptr != NULL)
    {
        while(ptr != NULL)
        {
            printf("%g ",ptr->info);
            count++;
            ptr = ptr->next;
        }
        printf("\n");
    }
    else
    {
        printf("NULL\n");
    }
    return count;
}

//push node to the start of list
void pushNode(double info)
{
    createNode(info);
    if(first == NULL)
    {
        first = ptr;
        last = ptr;
    }
    else
    {
        ptr->next = first;
        first = ptr;
    }
}

//see the value of a node at a location and return the value
double peekNode(int location)
{
    double info;
    if(location == -1)
    {
        info = last->info;
    }
    else
    {
        ptr = first;
        for(int i = 0; i <= location && ptr != NULL; i++)
        {
            if(i == location)
            {
                info = ptr->info;
            }
            ptr = ptr->next;
        }
    }
    return info;
}

//search for a location of a value and return the location
int searchNode(double info, int start)
{
    int i = -1;
    int j = 0;
    int found = 0;
    ptr = first;
    while(ptr != NULL && !found)
    {
        if(ptr->info == info && j >= start)
        {
            found = 1;
            i = j;
        }
        ptr = ptr->next;
        j++;
    }
    return i;
}

//delete node from the list and return if success or not
int deleteNode(double info)
{
    int location = searchNode(info, 0);
    if(location != -1)
    {
        while(location != -1)
        {
            printf("%g found. Enter y to confirm> ",info);
            char c = getchar();
            printf("answer> ");
            if(tolower(c) == 'y')
            {
                if(location == 0)
                {
                    ptr = first;
                    if(first == last)
                    {
                        first = NULL;
                        last = NULL;
                    }
                    else
                    {
                        first = first->next;
                    }
                }
                else
                {
                    linkedList* prev = first;
                    for(int i = 1; i < location; i++)
                    {
                        prev = prev->next;
                    }
                    ptr = prev->next;
                    if(ptr == last)
                    {
                        last = prev;
                        last->next = NULL;
                    }
                    else
                    {
                        prev->next = ptr->next;
                    }
                }
                free(ptr);
            }
            rewind(stdin);
            location = searchNode(info, location + 1);
        }
    }
    else
    {
        return 0;
    }
    return 1;
}

//remove the first node and return the value
double popNode()
{
    ptr = first;
    if(first == last)
    {
        first = NULL;
        last = NULL;
    }
    else
    {
        first = first->next;
    }
    double info = ptr->info;
    free(ptr);
    return info;
}

//square root the value of the first node
double sqrtNode()
{
    double info = sqrt(popNode());
    pushNode(info);
    return info;
}

//find reciprocal of the first node
double recNode()
{
    double info = 1/popNode();
    pushNode(info);
    return info;
}

//multiply -1 to the first node
double negNode()
{
    double info = -1 * popNode();
    pushNode(info);
    return info;
}

//plus first two nodes
double plusNodes()
{
    double info1 = popNode();
    double info2 = popNode();
    double info = info2 + info1;
    pushNode(info);
    return info;
}

//minus second node by first node
double minusNodes()
{
    double info1 = popNode();
    double info2 = popNode();
    double info = info2 - info1;
    pushNode(info);
    return info;
}

//multiply first two nodes
double multiplyNodes()
{
    double info1 = popNode();
    double info2 = popNode();
    double info = info2 * info1;
    pushNode(info);
    return info;
}

//divide second node by first node
double divideNodes()
{
    double info1 = popNode();
    double info2 = popNode();
    double info = info2 / info1;
    pushNode(info);
    return info;
}

//second node raised to the power of first node
double powNodes()
{
    double info1 = popNode();
    double info2 = popNode();
    double info = pow(info2, info1);
    pushNode(info);
    return info;
}

//sort the list by scan sort
void sortNodes()
{
    linkedList *i, *j;
    double x;
    i = first;
    while(i->next != NULL)
    {
        j = i->next;
        while(j != NULL)
        {
            if(j->info < i->info)
            {
                x = i->info;
                i->info = j->info;
                j->info = x;
            }
            j = j->next;
        }
        i = i->next;
    }
}

//add a node in number order
void insertNode(double info)
{
    linkedList* tmp;
    createNode(info);
    if(first == NULL)
    {
        first = ptr;
        last = ptr;
    }
    else if(first->info > ptr->info)
    {
        ptr->next = first;
        first = ptr;
    }
    else
    {
        tmp = first;
        while(tmp->next != NULL && ((tmp->next)->info < ptr->info))
        {
            tmp = tmp->next;
        }
        ptr->next = tmp->next;
        tmp->next = ptr;
        if(tmp == last)
        {
            last = ptr;
        }
    }
}

//list all available commands
void helpCommand()
{
    printf("list of commands:\n");
    printf("1.Commands with no parameter: list end sort pop help sqrt rec neg pow + - * /\n");
    printf("2.Commands with 1 parameter: delete search peek push\n");
    printf("3.Commands with multiple parameters: add insert\n");
}

//used by commandsMenu() to use sortNode()
void sortCommand()
{
    sortNodes();
    printf("Success\n");
}

//used by commandsMenu() to use popNode()
void popCommand()
{
    printf("%g\n",popNode());
}

//used by commandsMenu() to use sqrtNode()
void sqrtCommand()
{
    printf("%g\n",sqrtNode());
}

//used by commandsMenu() to use recNode()
void recCommand()
{
    printf("%g\n",recNode());
}

//used by commandsMenu() to use negNode()
void negCommand()
{
    printf("%g\n",negNode());
}

//used by commandsMenu() to use powNodes()
void powCommand()
{
    printf("%g\n",powNodes());
}

//used by commandsMenu() to use plusNodes()
void plusCommand()
{
    printf("%g\n",plusNodes());
}

//used by commandsMenu() to use minusNodes()
void minusCommand()
{
    printf("%g\n",minusNodes());
}

//used by commandsMenu() to use multiplyNodes()
void multiplyCommand()
{
    printf("%g\n",multiplyNodes());
}

//used by commandsMenu() to use divideNodes()
void divideCommand()
{
    printf("%g\n",divideNodes());
}

//used by commandsMenu() to use deleteNode()
void deleteCommand(double info)
{
    if(deleteNode(info))
    {
        printf("Success\n");
    }
    else
    {
        printf("%g not found\n",info);
    }
}

//used by commandsMenu() to use searchNode()
void searchCommand(double info)
{
    int location = searchNode(info, 0);
    if(location == -1)
    {
        printf("%g not found\n", info);
    }
    else
    {
        printf("found %g at [%d]\n", info, location);
    }
}

//used by commandsMenu() to use peekNode()
void peekCommand(int location, int nodeCount)
{
    if(location < nodeCount && location >= -1)
    {
        printf("%g\n",peekNode(location));
    }
    else
    {
        printf("Maximum peek = %d\n",nodeCount - 1);
    }
}

//used by commandsMenu() to use pushNode()
void pushCommand(double info)
{
    pushNode(info);
    printf("Success\n");
}

//used by commandsMenu() to use addNode()
void addCommand(double parameters[])
{
    int i = 0;
    while(parameters[i] != 999983)
    {
        addNode(parameters[i]);
        i++;
    }
    printf("Success\n");
}

//used by commandsMenu() to use insertNode()
void insertCommand(double parameters[])
{
    int i = 0;
    if(first == NULL)
    {
        while(parameters[i] != 999983)
        {
            insertNode(parameters[i]);
            i++;
        }
        printf("Success\n");
    }
    else
    {
        int isSorted = 1;
        ptr = first;
        while(ptr->next != NULL)
        {
            if(ptr->info > (ptr->next)->info)
            {
                isSorted = 0;
            }
            ptr = ptr->next;
        }
        if(isSorted)
        {
            while(parameters[i] != 999983)
            {
                insertNode(parameters[i]);
                i++;
            }
            printf("Success\n");
        }
        else
        {
            printf("Can't insert. Please sort before insert\n");
        }
    }
}

//the menu to use all commands from input commands
void commandsMenu(int nodeCount, double parameters[], int groupNo, int commandNo)
{
    if(groupNo == 0)
    {
        if(nodeCount > 0)
        {
            switch(commandNo)
            {
                case 0:
                    listNodes();
                    break;
                case 2:
                    sortCommand();
                    break;
                case 3:
                    popCommand();
                    break;
                case 4:
                    helpCommand();
                    break;
                case 5:
                    sqrtCommand();
                    break;
                case 6:
                    recCommand();
                    break;
                case 7:
                    negCommand();
                    break;
                default:
                    if(nodeCount > 1)
                    {
                        switch(commandNo)
                        {
                            case 8:
                                powCommand();
                                break;
                            case 9:
                                plusCommand();
                                break;
                            case 10:
                                minusCommand();
                                break;
                            case 11:
                                multiplyCommand();
                                break;
                            case 12:
                                divideCommand();
                                break;
                        }
                    }
                    else
                    {
                        printf("Can't operation\n");
                    }
            }
        }
        else
        {
            printf("No data\n");
        }
    }
    else if(groupNo == 1)
    {
        switch(commandNo)
        {
            case 3:
                pushCommand(parameters[0]);
                break;
            default:
                if(nodeCount > 0)
                {
                    switch(commandNo)
                    {
                        case 0:
                            deleteCommand(parameters[0]);
                            break;
                        case 1:
                            searchCommand(parameters[0]);
                            break;
                        case 2:
                            peekCommand(parameters[0], nodeCount);
                            break;
                    }
                }
                else
                {
                    printf("No data\n");
                }
        }
    }
    else
    {
        switch(commandNo)
        {
            case 0:
                addCommand(parameters);
                break;
            case 1:
                insertCommand(parameters);
                break;
        }
    }
}

int main()
{
    int isEnd = 0;
    char tokens[10][10];
    double parameters[10];

    while(!isEnd)
    {
        printf("list> ");
        int nodeCount = listNodes();
        int count = 0;
        char input[30] = "";
        int groupNo = -1;
        int commandNo = -1;
        printf("command> ");
        gets(input);
        processInput(input, tokens, &count);
        int success = checkTokens(tokens, count, &groupNo, &commandNo, parameters);
        if(success == 0)
        {
            printf("answer> ");
            printf("Parameter error\n");
        }
        else if(success == 2)
        {
            printf("answer> ");
            printf("Syntax error\n");
        }
        else if(success == -1)
        {
            isEnd = 1;
        }
        else
        {
            printf("answer> ");
            commandsMenu(nodeCount, parameters, groupNo, commandNo);
        }
    }
    printf("Program written by Nathee Jaywaree 63070501035");
    freeNodes();
}
