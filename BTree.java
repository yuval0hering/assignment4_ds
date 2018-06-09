import jdk.nashorn.internal.ir.BinaryNode;

import java.io.File;
import java.util.Scanner;


public class BTree {
    private BTreeNode root;

    //Btree constructor.
    public BTree(String String_T){
        int t;
        try {
          t = Integer.parseInt(String_T);
        }
        catch (Exception e){
            throw new IllegalArgumentException("t should be integer");
        }
        if(t<2){
            throw new IllegalArgumentException("t should be at least 2");
        }
        this.root=new BTreeNode(t);

    }
    //gets file path with friendships and inserts them to the tree one by one.
    public void createFullTree(String path) {
        File file = new File(path);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                this.insert(sc.nextLine());
            }
            sc.close();
        }
        catch (Exception e){
            throw new IllegalArgumentException();
        }
    }
    //this function returns the node with the friendship if it exists using the function below
    public BTreeNode search(String friend1, String friend2){
        String friendship1= friend1+" & "+friend2;
        String friendship2= friend2+" & "+friend1;
        char indexFriendship1=friendship1.charAt(0);
        char indexFriendship2=friendship2.charAt(0);
        BTreeNode searchFriendship1=search(friendship1,this.root, indexFriendship1);
        BTreeNode searchFriendship2= search(friendship2,this.root, indexFriendship2);
        if (searchFriendship1!=null | searchFriendship2!=null){
           if (searchFriendship1!=null){
               return searchFriendship1;
           }
           else
               return searchFriendship2;
        }
        return null;
    }
    //this function is the recursive function that finds the node
    public BTreeNode search(String friendship, BTreeNode node, char key){
        int i=0;
        if(friendship==null || node==null){
            throw new NullPointerException();
        }
        while (i<node.getSize() && key>node.getKey(i).charAt(0)){
            i=i+1;
        }
        while (i<node.getSize() && key==node.getKey(i).charAt(0) & friendship.compareTo(node.getKey(i))>=0){
            if (friendship.equals(node.getKey(i))){
                return node;
            }
                i=i+1;
        }

        if (node.isLeaf())
            return null;
        else return search(friendship,node.getKid(i),key);
    }
    //this function returns true if two names are friends and false is they are not
    public boolean isFriends(String friend1, String friend2){
        if (search(friend1,friend2)!=null)
            return true;
        else return false;
    }
    //this function is splitting full nodes if it reaches the maximum number
    public void splitChild(BTreeNode node, int index){
       BTreeNode left=node.getKid(index);
       int t=left.getT();
       String toParent= left.getKey(t-1);// choose the key to put in the parent
       BTreeNode right= new BTreeNode(t); //allocate new right node.
       right.setLeaf(left.isLeaf());
       for (int i=0; i<t-1;i=i+1){ //put the relevant keys in the right node.
           right.addKey(left.getKey(t+i),i);
       }
       if (!left.isLeaf()){ //take the relevant kids from the lest node and move them to the right node.
           for (int i=0; i<t;i=i+1){
               right.addKid(i,left.getKid(t+i));
           }
       }
        setLeftKeys(left);
       if (!left.isLeaf()){
           setLeftKids(left);
       }
       node.addKid(index+1,right);
       node.addKey(toParent,index);
       setNodesSizes(node,left,right);
    }

    //sets the node, left, right sizes.
    private static void setNodesSizes(BTreeNode node, BTreeNode left, BTreeNode right){
        left.setSize(left.getKeys().size());
        right.setSize(right.getKeys().size());
        node.setSize(node.getKeys().size());
    }

    //sets the left new kids after the split.
    private static void setLeftKids(BTreeNode left){
        LinkedList<BTreeNode> copyKids = new LinkedList<>();
        for (int i=0; i<left.getT();i=i+1){
            copyKids.add(i,left.getKid(i));
        }
        left.setKids(copyKids);
    }

    //sets the left node the keys after split
    private static void setLeftKeys(BTreeNode left){
        LinkedList<String> copyKeys = new LinkedList<>();
        for (int i=0; i<left.getT()-1;i=i+1){
            copyKeys.add(i,left.getKey(i));
        }
        left.setKeys(copyKeys);
    }

    // inserts keys to the tree.
    public void insert(String friendship){
        BTreeNode newNode=this.root;
        if(newNode.getSize()==(2*this.root.getT())-1){//checks if the root is full
            BTreeNode temp= new BTreeNode(this.root.getT());// allocate new node
            this.root=temp;
            temp.setLeaf(false);
            temp.setSize(0);
            temp.addKid(0,newNode);
            splitChild(temp,0);
            newNode=temp;
        }
        insertNonFull(newNode,friendship);// sends non full node and friendship
    }
    //inserts key to no full node
    public void insertNonFull(BTreeNode node, String friendship){
        int i=0;
        if(node.isLeaf()){
            while(i<node.getSize() && friendship.compareTo(node.getKey(i))>0){
                i=i+1;
            }
            node.addKey(friendship,i);
            node.setSize(node.getKeys().size());
        }
        else{
            while(i<node.getSize() && friendship.compareTo(node.getKey(i))>0){
                i=i+1;
            }
            if (node.getKid(i).getSize()==(2*node.getT())-1){
                splitChild(node,i);
                if (friendship.compareTo(node.getKey(i))>0)
                    i=i+1;
            }
            insertNonFull(node.getKid(i),friendship);
        }
    }

    //Prints the tree BFS
    public String toString (){
        return this.root.toString("");
    }

}


