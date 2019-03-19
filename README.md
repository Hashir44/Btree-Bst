# problem statement
Assume any tree [Binary/BST/B tree] the tree will allow undo operation whenever a change is made in any of data for last three stages. Demonstrate with suitable example.

Detailed Description:-

This project contains menu driven program for Btree and Bst for insertion,deletion and undo operation within the [Btree/BST]

Technology Stack: -
The entire coding is done in Java with NetBeans IDE 8.0.1.

Classes and Functions: -
The program makes the use of 4 classes: - 
1. Btree
2. BST


1.Btree: - 
  The class that is used to insert,delete and undo operation into the Btree. 

2.BST: - 
  The class that is used to insert,delete and undo operation into the binary search tree. 


# Key Functionality: -
The Program shows menu driven program which ask user what to do at every itteration.
operation 
   1. insertion 
   2. deletion
   3. undo operation
   4. search 
   5. display tree
   

# output

1.Binary search Tree 2.BTree (-999 to exit)
2
enter degree of Btree
3
1.insertion 	 2.deletion 	 3.undo operation 	 4.Show Tree (-999 to exit)
1
Enter the value to insert
44
1.insertion 	 2.deletion 	 3.undo operation 	 4.Show Tree (-999 to exit)
1
Enter the value to insert
75
1.insertion 	 2.deletion 	 3.undo operation 	 4.Show Tree (-999 to exit)
1
Enter the value to insert
42
1.insertion 	 2.deletion 	 3.undo operation 	 4.Show Tree (-999 to exit)
1
Enter the value to insert
32
1.insertion 	 2.deletion 	 3.undo operation 	 4.Show Tree (-999 to exit)
1
Enter the value to insert
65
1.insertion 	 2.deletion 	 3.undo operation 	 4.Show Tree (-999 to exit)
1
Enter the value to insert
36
1.insertion 	 2.deletion 	 3.undo operation 	 4.Show Tree (-999 to exit)
4
Show Btree
false 1:44 
true 3:32 36 42 
true 2:65 75 

1.insertion 	 2.deletion 	 3.undo operation 	 4.Show Tree (-999 to exit)
1
Enter the value to insert
96
1.insertion 	 2.deletion 	 3.undo operation 	 4.Show Tree (-999 to exit)
1
Enter the value to insert
1
1.insertion 	 2.deletion 	 3.undo operation 	 4.Show Tree (-999 to exit)
4
Show Btree
false 1:44 
true 4:1 32 36 42 
true 3:65 75 96 

1.insertion 	 2.deletion 	 3.undo operation 	 4.Show Tree (-999 to exit)
2
Enter the value to delete
1
1.insertion 	 2.deletion 	 3.undo operation 	 4.Show Tree (-999 to exit)
4
Show Btree
false 1:44 
true 3:32 36 42 
true 3:65 75 96 

1.insertion 	 2.deletion 	 3.undo operation 	 4.Show Tree (-999 to exit)
3
1.insertion 	 2.deletion 	 3.undo operation 	 4.Show Tree (-999 to exit)
4
Show Btree
false 1:44 
true 4:1 32 36 42 
true 3:65 75 96 

1.insertion 	 2.deletion 	 3.undo operation 	 4.Show Tree (-999 to exit)
-999
Enter the valid option
1.Binary search Tree 2.BTree (-999 to exit)
-999
Enter the valid option
BUILD SUCCESSFUL (total time: 1 minute 9 seconds)

