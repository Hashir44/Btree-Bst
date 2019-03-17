/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adsproject;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author vigilante
 */

class BTree {

    /**
     * @param args the command line arguments
     */
    
private int T;

public class bNode {
int n;
int key[] = new int[2*T-1];
bNode child[] = new bNode[2*T];
boolean leaf = true;

public int Find(int k){
        for (int i = 0; i < this.n; i++) {
                if (this.key[i] == k) {
                        return i;
                }
        }
        return -1;
};
}

private class PairNode {
bNode x;
int pos;
}

public BTree(int _T) {
        T = _T;
        root = new bNode();
        root.n = 0;
        root.leaf = true;
}

private bNode root;

private bNode Search (bNode x,int key) {
        int i = 0;
        if (x == null) return x;
        for (i = 0; i < x.n; i++) {
                if (key < x.key[i]) {
                        break;
                }
                if (key == x.key[i]) {
                        return x;
                }
        }
        if (x.leaf) {
                return null;
        } else {
                return Search(x.child[i],key);
        }
}

private void Split (bNode x, int pos, bNode y) {
        bNode z = new bNode();
        z.leaf = y.leaf;
        z.n = T - 1;
        for (int j = 0; j < T - 1; j++) {
                z.key[j] = y.key[j+T];
        }
        if (!y.leaf) {
                for (int j = 0; j < T; j++) {
                        z.child[j] = y.child[j+T];
                }
        }
        y.n = T-1;
        for (int j = x.n; j >= pos+1; j--) {
                x.child[j+1] = x.child[j];
        }
        x.child[pos+1] = z;

        for (int j = x.n-1; j >= pos; j--) {
                x.key[j+1] = x.key[j];
        }
        x.key[pos] = y.key[T-1];
        x.n = x.n + 1;
}

public void Insert (final int key) {
        bNode r = root;
        if (r.n == 2*T - 1 ) {
                bNode s = new bNode();
                root = s;
                s.leaf = false;
                s.n = 0;
                s.child[0] = r;
                Split(s,0,r);
                _Insert(s,key);
        } else {
                _Insert(r,key);
        }
}

final private void _Insert (bNode x, int k) {

        if (x.leaf) {
                int i = 0;
                for (i = x.n-1; i >= 0 && k < x.key[i]; i--) {
                        x.key[i+1] = x.key[i];
                }
                x.key[i+1] = k;
                x.n = x.n + 1;
        } else {
                int i = 0;
                for (i = x.n-1; i >= 0 && k < x.key[i]; i--) {};
                i++;
                bNode tmp = x.child[i];
                if (tmp.n == 2*T -1) {
                        Split(x,i,tmp);
                        if ( k > x.key[i]) {
                                i++;
                        }
                }
                _Insert(x.child[i], k);
        }

}

public void Show () {
        Show(root);
}

private void Show (bNode x) {
        assert (x == null);
        System.out.print(x.leaf + " " + x.n + ":" );
        for ( int i = 0; i < x.n; i++) {
                System.out.print(x.key[i]+ " ");
        }
        System.out.println();
        if (!x.leaf) {
                for (int i = 0; i <  x.n + 1; i++) {
                        Show(x.child[i]);
                }
        }
}



private void Remove (bNode x, int key) {
        int pos = x.Find(key);
        if (pos != -1) {
                if (x.leaf) {
                        int i = 0;
                        for (i = 0; i < x.n && x.key[i] != key; i++) {};
                        for (; i < x.n; i++) {
                                if (i != 2*T - 2) {
                                        x.key[i] = x.key[i+1];
                                }
                        }
                        x.n--;
                        return;
                }
                if (!x.leaf) {
                        //if (x.child[pos].n >= T){
                        bNode pred = x.child[pos];
                        int predKey = 0;
                        //System.out.println(pos);
                        if (pred.n >= T) {
                                //System.out.println("2a");
                                for (;;) {
                                        if (pred.leaf) {
                                                System.out.println(pred.n);
                                                predKey = pred.key[pred.n - 1];
                                                break;
                                        } else {
                                                pred = pred.child[pred.n];
                                        }
                                }
                                Remove (pred, predKey);
                                x.key[pos] = predKey;
                                return;
                        }
                        //System.out.println("2b");
                         bNode nextNode = x.child[pos+1];
                        if (nextNode.n >= T) {
                                int nextKey = nextNode.key[0];
                                if (!nextNode.leaf) {
                                        nextNode = nextNode.child[0];
                                        for (;;) {
                                                if (nextNode.leaf) {
                                                        nextKey = nextNode.key[nextNode.n-1];
                                                        break;
                                                } else {
                                                        nextNode = nextNode.child[nextNode.n];
                                                }
                                        }
                                }
                                Remove(nextNode, nextKey);
                                x.key[pos] = nextKey;
                                return;
                        }
                        //System.out.println("2v");
                        int temp = pred.n + 1;
                        pred.key[pred.n++] = x.key[pos];
                        for (int i = 0, j = pred.n; i < nextNode.n; i++) {
                                pred.key[j++] = nextNode.key[i];
                                pred.n++;
                        }
                        for (int i = 0; i < nextNode.n+1; i++) {
                                pred.child[temp++] = nextNode.child[i];
                        }

                        x.child[pos] = pred;
                        for (int i = pos; i < x.n; i++) {
                                if (i != 2*T - 2) {
                                        x.key[i] = x.key[i+1];
                                }
                        }
                        for (int i = pos+1; i < x.n+1; i++) {
                                if (i != 2*T - 1) {
                                        x.child[i] = x.child[i+1];
                                }
                        }
                        x.n--;
                        if (x.n == 0) {
                                if (x == root) {
                                        root = x.child[0];
                                }
                                x = x.child[0];
                        }
                        Remove(pred,key);
                        return;
                        //}
                }
        } else {
                for (pos = 0; pos < x.n; pos++) {
                        if (x.key[pos] > key) {
                                break;
                        }
                }
//			System.out.println(pos);
//			Show(x);
                bNode tmp = x.child[pos];
                if (tmp.n >= T) {
                        Remove (tmp,key);
                        return;
                }
//			System.out.println(pos + " " + T + " " + tmp.n);
                if (true) {
                        bNode nb = null;
                        int devider = -1;
                        if (pos != x.n && x.child[pos+1].n >= T) {
                                devider = x.key[pos];
                                nb = x.child[pos+1];
                                x.key[pos] = nb.key[0];
                                tmp.key[tmp.n++] = devider;
                                tmp.child[tmp.n] = nb.child[0];
                                for (int i = 1; i < nb.n; i++) {
                                        nb.key[i-1] = nb.key[i];
                                }
                                for (int i = 1; i <= nb.n; i++) {
                                        nb.child[i-1] = nb.child[i];
                                }
                                nb.n--;
                                Remove(tmp,key);
                                  return;
                        } else if (pos != 0 && x.child[pos-1].n >= T) {
                                devider = x.key[pos-1];
                                nb = x.child[pos-1];
                                x.key[pos-1] = nb.key[nb.n-1];
                                bNode child = nb.child[nb.n];
                                nb.n--;
                                for(int i = tmp.n; i > 0; i--) {
                                        tmp.key[i] = tmp.key[i-1];
                                }
                                tmp.key[0] = devider;
                                for(int i = tmp.n + 1; i > 0; i--) {
                                        tmp.child[i] = tmp.child[i-1];
                                }
                                tmp.child[0] = child;
                                tmp.n++;
//					Show(root);
                                Remove(tmp,key);
                                return;
                        } else {
//					devider = x.key[pos];
//					Show(x);
                                bNode lt = null;
                                bNode rt = null;
                                boolean last = false;
                                //System.out.println(x.key[pos]);
                                if (pos != x.n) {
                                        devider = x.key[pos];
                                        lt = x.child[pos]; 
                                        rt = x.child[pos+1];
                                } else {
                                        devider = x.key[pos-1];
                                        rt = x.child[pos];
                                        lt = x.child[pos-1];
                                        last = true;
                                        pos--;
                                }
                                
                                for (int i = pos; i < x.n-1; i++) {
                                        x.key[i] = x.key[i+1];
                                }
                                
//					for(int i = x.n + 1 ; i > pos ; i--) {
//						x.child[i-1] = x.child[i];
//					}
                                for(int i = pos+1; i < x.n; i++) {
                                        x.child[i] = x.child[i+1];
                                }
                                x.n--;
                                lt.key[lt.n++] = devider;
                                int numChild = 0;
                                //lt.child[lt.n] = rt.child[numChild++];
                                //Show(root);
                                for (int i = 0, j = lt.n; i < rt.n+1; i++,j++) {
                                        if (i < rt.n) {
                                                lt.key[j] = rt.key[i];
                                        }
                                        lt.child[j] = rt.child[i];
                                }
                                lt.n += rt.n;
                                if (x.n == 0) {
                                        if (x == root) {
                                                root = x.child[0];
                                        }
                                        x = x.child[0];
                                }
                                //Show(lt);
                                //Show(lt);
                                Remove(lt,key);

                                return;
                        }
                }
        }
}
public void Remove (int key) {
        bNode x = Search(root, key);
        if (x == null) {
            return;
        }
        Remove(root,key);
}



public void Task (int a, int b){
        Stack<Integer> st = new Stack<>();
        FindKeys(a,b,root,st);
        while (st.isEmpty() == false) {
//			System.out.println("--------------------");
//			System.out.println(st.peek());
//			Show(root);
//			System.out.println("--------------------");
                this.Remove(root,st.pop());
        }
}

private void FindKeys (int a, int b, bNode x, Stack<Integer> st){
        int i = 0;
        for (i = 0; i < x.n && x.key[i] < b; i++) {
                if ( x.key[i] > a  ) {
                        st.push(x.key[i]);
                }
        }
        if (!x.leaf) {
                for (int j = 0; j < i+1; j++) {
                        FindKeys(a,b,x.child[j],st);
                }
                //FindKeys(a,b,x.child[i],st);
        }
}

public boolean Contain(int k) {
        if (this.Search(root, k) != null) {
                return true;
        } else {
                return false;
        }
    }
void showr(){
        Show(root);
}
    
    
}


public class Adsproject
{ 
    /* Class containing left and right child of current node and key value*/
    class Node 
    { 
        int key; 
        Node left, right; 
  
        public Node(int item) 
        { 
            key = item; 
            left = right = null; 
        } 
    } 
  
    // Root of BST 
    Node root; 
  
    // Constructor 
    Adsproject() 
    { 
        root = null; 
    } 
  
    // This method mainly calls deleteRec() 
    void deleteKey(int key) 
    { 
        root = deleteRec(root, key); 
    } 
  
    /* A recursive function to insert a new key in BST */
    Node deleteRec(Node root, int key) 
    { 
        /* Base Case: If the tree is empty */
        if (root == null)  return root; 
  
        /* Otherwise, recur down the tree */
        if (key < root.key) 
            root.left = deleteRec(root.left, key); 
        else if (key > root.key) 
            root.right = deleteRec(root.right, key); 
  
        // if key is same as root's key, then This is the node 
        // to be deleted 
        else
        { 
            // node with only one child or no child 
            if (root.left == null) 
                return root.right; 
            else if (root.right == null) 
                return root.left; 
  
            // node with two children: Get the inorder successor (smallest 
            // in the right subtree) 
            root.key = minValue(root.right); 
  
            // Delete the inorder successor 
            root.right = deleteRec(root.right, root.key); 
        } 
  
        return root; 
    } 
  
    int minValue(Node root) 
    { 
        int minv = root.key; 
        while (root.left != null) 
        { 
            minv = root.left.key; 
            root = root.left; 
        } 
        return minv; 
    } 
  
    // This method mainly calls insertRec() 
    void insert(int key) 
    { 
        root = insertRec(root, key); 
    } 
  
    /* A recursive function to insert a new key in BST */
    Node insertRec(Node root, int key) 
    { 
  
        /* If the tree is empty, return a new node */
        if (root == null) 
        { 
            root = new Node(key); 
            return root; 
        } 
  
        /* Otherwise, recur down the tree */
        if (key < root.key) 
            root.left = insertRec(root.left, key); 
        else if (key > root.key) 
            root.right = insertRec(root.right, key); 
  
        /* return the (unchanged) node pointer */
        return root; 
    } 
  
    // This method mainly calls InorderRec() 
    void inorder() 
    { 
        inorderRec(root); 
    } 
  
    // A utility function to do inorder traversal of BST 
    void inorderRec(Node root) 
    { 
        if (root != null) 
        { 
            inorderRec(root.left); 
            System.out.print(root.key + " "); 
            inorderRec(root.right); 
        } 
    } 
  
    
    // A utility function to search a given key in BST 
public Node search(Node root, int key) 
{ 
	// Base Cases: root is null or key is present at root 
	if (root==null){
            System.out.println("key not found");
            return root;
        }
            
        if(root.key==key){ 
            System.out.println("key found");
            return root; 
        }
	// val is greater than root's key 
	if (root.key > key) 
		return search(root.left, key); 

	// val is less than root's key 
	return search(root.right, key); 
} 

void searchnode(int key){
    search(root,key);
}


    // Driver Program to test above functions 
    public static void main(String[] args) 
    { 
        Adsproject tree = new Adsproject(); 
        Scanner sc = new Scanner(System.in);
        int process,select;
        ArrayList al1 = new ArrayList();
        ArrayList al2 = new ArrayList();
        
        do{
        /* Let us create following BST 
              50 
           /     \ 
          30      70 
         /  \    /  \ 
        20   40  60   80 */
        System.out.println("1.Binary search Tree 2.BTree (-999 to exit)");
        select = sc.nextInt();
        switch(select){
            case 1:do{
                    System.out.println("1.insertion \t 2.deletion \t 3.undo operation \t 4.search operation \t 5.inorder traversal (-999 to exit)");
                    process = sc.nextInt();
                    int value;
                    switch(process){
                        case 1: System.out.println("Enter the value to insert"); 
                                value = sc.nextInt();
                                al1.add(1);
                                al2.add(value);
                                tree.insert(value);
                                break;
                        case 2: System.out.println("Enter the value to delete"); 
                                value = sc.nextInt();
                                al1.add(2);
                                al2.add(value);
                                tree.deleteKey(value);
                                break;
                        case 3: if((int)al1.get(al1.size()-1)==1 ){
                                    tree.deleteKey((int)al2.get(al2.size()-1));
                                    al1.remove( al1.size() - 1 );
                                    al2.remove( al2.size() - 1 );
                                }
                                else{
                                    tree.insert((int)al2.get(al2.size()-1));
                                    al1.remove( al1.size() - 1 );
                                    al2.remove( al2.size() - 1 );
                                }
                                break;
                        case 4: System.out.println("Enter the value to search"); 
                                value = sc.nextInt();
                                tree.searchnode(value);
                                break;
                        case 5:System.out.println("Inorder traversal of the given tree"); 
                               tree.inorder(); 
                               System.out.println("");
                               break;
                        default:System.out.println("Enter the valid option");
                                break;
                    }
                }while(process!=-999);
                break;

            case 2:System.out.println("enter no of key in single node of Btree");
                    int value;
                    value = sc.nextInt();
                    BTree  bt = new BTree((value+1)/2);
                    do{
                        System.out.println("1.insertion \t 2.deletion \t 3.undo operation \t 4"
                                + ".Show Tree (-999 to exit)");
                        process = sc.nextInt();
                        switch(process){
                            case 1: System.out.println("Enter the value to insert"); 
                                    value = sc.nextInt();
                                    al1.add(1);
                                    al2.add(value);
                                    bt.Insert(value);
                                    break;
                            case 2: System.out.println("Enter the value to delete"); 
                                    value = sc.nextInt();
                                    al1.add(2);
                                    al2.add(value);
                                    bt.Remove(value);
                                    break;
                            case 3: if((int)al1.get(al1.size()-1)==1 ){
                                        bt.Remove((int)al2.get(al2.size()-1));
                                        al1.remove( al1.size() - 1 );
                                        al2.remove( al2.size() - 1 );
                                    }
                                    else{
                                        bt.Insert((int)al2.get(al2.size()-1));
                                        al1.remove( al1.size() - 1 );
                                        al2.remove( al2.size() - 1 );
                                    }
                                    break;
                            case 4:System.out.println("Show Btree"); 
                                   bt.showr();
                                   System.out.println("");
                                   break;
                            default:System.out.println("Enter the valid option");
                                    break;
                    }
                }while(process!=-999);
                break;
           
            default:System.out.println("Enter the valid option");
                   break;
           
        }
        }while(select!=-999);
    } 
} 
