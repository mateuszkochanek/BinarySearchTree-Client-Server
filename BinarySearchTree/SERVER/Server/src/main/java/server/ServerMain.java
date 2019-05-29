package server;

import binarytree.BinaryTree;

public class ServerMain 
{
    public static void main( String[] args )
    {
        BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();
        binaryTree.insert(50);
        binaryTree.insert(25);
        binaryTree.insert(75);
        binaryTree.insert(15);
        binaryTree.insert(7);
        binaryTree.insert(22);
        binaryTree.insert(65);
        binaryTree.insert(85);
        binaryTree.insert(58);
        binaryTree.insert(72);
        binaryTree.insert(79);
        binaryTree.insert(92);
        binaryTree.insert(35);
        binaryTree.insert(27);
        binaryTree.insert(42);
        binaryTree.draw();
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        binaryTree.delete(75);
        binaryTree.draw();

    }
}
