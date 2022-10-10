import java.util.Scanner;
import java.util.*;

public class minHeap {     // Implementing the minheap //
   private String[] Heap;     // initialize heap as a string //
   private int position;
   private int n;

   public minHeap(int n) {
      this.n = n;
      this.position = 0;
      Heap = new String[n];
   }

   private int parent(int d) {          //gets the position of the parent//
      return (d - 1) / 2;
   }

   private int left(int d) {           //gets the position of the leftChild//
      return (d * 2) + 1;              //// formula to insert element in the left node//
   }

   private int right(int d) {          //gets the position of the rightChild//
      return (d * 2) + 2;              // formula to insert element in the right node//
   }

   private boolean isLeaf(int d) {
      if (right(d) >= position || left(d) >= position) {
         return true;
      }
      return false;
   }
   
   public void insertValue(String element) {          //inserts a value into the heap //
      if (position >= n) {
         return;
      }
      Heap[position] = element;
      int current = position;
   
      while (FirstIsGreaterBetween(Heap[current] , Heap[parent(current)])<0) {
         swapValues(current, parent(current));
         current = parent(current);
      }
      position++;
   }

   public String removeValue() {  //removes a value at the top of the heap and return the removed value//
      String popped = Heap[0];
      Heap[0] = Heap[--position];
      heapify(0);
      return popped;
   
   }
   private void heapify(int d) {    // Function to rearrange the elements contained in the array. Here the left and right sub-tree of it element obeys the minheap property                          
      if (!isLeaf(d)) {
         //if value at d is greater than any of its nodes,
      //                System.out.println(Heap[d]+ " is not among the leaves, see if it is larger than child leaves");
         if ( FirstIsGreaterBetween(Heap[d] , Heap[left(d)]) > 0 ||
                FirstIsGreaterBetween(Heap[d] , Heap[right(d)]) > 0) {
            //swap value at i with the least between the leftchild and rightchild
         //                    System.out.println(Heap[d]+ " is either larger than " + Heap[rightChildNode(d)] + " or " + Heap[leftChildNode(d)]);
            if (FirstIsGreaterBetween(Heap[left(d)] , Heap[right(d)])<0) {
            //                        System.out.println("swap "+ Heap[d] + " with the smallest child " + Heap[leftChildNode(d)]);
               swapValues(d, left(d));
            //                        System.out.println("Follow up on "+Heap[leftChildNode(d)]);
               heapify(left(d));
            } else {
            //                        System.out.println("swap "+ Heap[d] + " with the smallest child " + Heap[rightChildNode(d)]);
               swapValues(d, right(d));
            //                        System.out.println("Follow up on "+Heap[rightChildNode(d)]);
               heapify(right(d));
            }
         }
      }
   }
   @Override
   public String toString() {
      String s = "";
      if(position!=0)
         s= s + Heap[0];
      for (int d = 0; d < (position / 2); d++) {
      
         if (left(d) < position) {
            if (s == "") {
               s = s + Heap[left(d)];
            } else {
               s = s + ", " + Heap[left(d)];
            }
         }
         if (right(d) < position) {
            if (s == "") {
               s = s + Heap[right(d)];
            } else {
               s = s + ", " + Heap[right(d)];
            }
         }
      }
      return s;
   }
   public void printHeap() {
      System.out.println(toString()); // print the heap by calling the toString function //
   
   }
   private void swapValues(int x, int y) {         // Swaps two values x value position to swap with y //
      String tmp;
      tmp = Heap[x];                                // x value position to swap with y //
      Heap[x] = Heap[y];                            // y value position to swap with x //
      Heap[y] = tmp;
   }

   private int FirstIsGreaterBetween(String a, String b){
      return a.compareToIgnoreCase(b);
   }

   public static void main (String ... args){
      minHeap smallest = new minHeap(5);
   
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter 5 names: ");         //prompt user to input 5 names into the array //
   
      String input = sc.nextLine();
      input.replace(" ", "");
      String[] names = input.split(",");
      for (String name: names) {
         smallest.insertValue(name);
      }
   
      System.out.println("The elements of the min-priority queue: "+smallest.toString());// prints the names in the array (queue)// 
      System.out.println("The name removed after first deletion: " +smallest.removeValue()); // print out first name that has been deleted.//
      System.out.println("The name removed after second deletion: " +smallest.removeValue()); // print out second name that has been deleted.//
      System.out.println("The elements of the min-priority queue: "+smallest.toString()); // prints the remaining names in the array(queue)//
   }
}
