//---------------------------------------------------------------------------
// ArrayBoundedQueue.java 
/**
 *@author by Dale/Joyce/Weems Modified by Dylan Stechmann        Chapter 4
 */
// Implements QueueInterface with an array to hold the queue elements.
//
// Two constructors are provided: one that creates a queue of a default
// capacity and one that allows the calling program to specify the capacity.
//---------------------------------------------------------------------------
package ch04.queues;

public class ArrayBoundedQueue<T> implements QueueInterface<T>
{
  protected final int DEFCAP = 100; // default capacity
  protected T[] elements;           // array that holds queue elements
  protected int numElements = 0;    // number of elements in this queue
  protected int front = 0;          // index of front of queue
  protected int rear;               // index of rear of queue

  public ArrayBoundedQueue() 
  {
    elements = (T[]) new Object[DEFCAP];
    rear = DEFCAP - 1;
  }

  public ArrayBoundedQueue(int maxSize) 
  {
    elements = (T[]) new Object[maxSize];
    rear = maxSize - 1;
  }

 /**
  * enqueue - adds element to the rear of the queue
  * O(1) computational complexity
  * @param element to add to queue
  * @precondition queue is not full
  * @postcondition element placed at rear of queue
  * @throws QueueOverflowException if the queue is full
  */
  public void enqueue(T element)
  {  
    if (isFull())
      throw new QueueOverflowException("Enqueue attempted on a full queue.");
    else
    {
      rear = (rear + 1) % elements.length;
      elements[rear] = element;
      numElements = numElements + 1;
    }
  }

 /**
  * dequeue - removes front element from the que then returns it
  * O(1) computational complexity
  * @precondition queue is not empty
  * @postcondition front element of this queue is removed
  * @throws QueueUnderflowException if this queue is empty
  * @return the front element which was removed is returned
  */
  public T dequeue()
  {   
    if (isEmpty())
      throw new QueueUnderflowException("Dequeue attempted on empty queue.");
    else
    {
      T toReturn = elements[front];
      elements[front] = null;
      front = (front + 1) % elements.length;
      numElements = numElements - 1;
      return toReturn;
    }
  }

 /**
  * isEmpty - returns whether or not the queue is empty
  * O(1) computational complexity
  * @postcondition true is returned if the queue is empty, otherwise false is returned
  */
  public boolean isEmpty()
  {              
    return (numElements == 0);
  }
  
 /**
  * isFull - returns whether or not the queue is full
  * O(1) computational complexity
  * @postcondition true is returned if the queue is full, otherwise false is returned
  */
  public boolean isFull()
  {              
    return (numElements == elements.length);
  }
 /**
  * size - returns the number of elements in the queue
  * O(1) computational complexity
  * @postcondition the number of elements in the queue is returned
  */
  public int size()
  {
    return numElements;
  }
  
}