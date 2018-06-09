public class BTreeNode {

    private LinkedList<String> keys;
    private int t;
    private int size;
    private boolean isLeaf;
    private LinkedList<BTreeNode> kids;
    public BTreeNode(int t){
        this.t=t;
        this.size=0;
        this.isLeaf=true;
        this.keys=new LinkedList<String>();
        this.kids=new LinkedList<BTreeNode>();
        for (int i=0; i<kids.size(); i=i+1){
            kids.set(i,null);
        }
    }
    //getters:
    public int getSize(){
        return this.size;
    }
    public String getKey(int index) { return keys.get(index);}
    public BTreeNode getKid(int index){ return kids.get(index); }
    public int getT(){
        return this.t;
    }
    public LinkedList getKids(){
        return this.kids;
    }
    public LinkedList<String> getKeys(){
        return this.keys;
    }

    //setters:
    public void setSize(int size){
        this.size=size;
    }
    public void setKeys(LinkedList<String> list){
        this.keys=list;
    }
    public void setLeaf(boolean isLeaf){
        this.isLeaf=isLeaf;
    }
    public void setKids(LinkedList<BTreeNode> list){
        this.kids=list;
    }

    // add keys to node
    public void addKey(String data, int index){
        this.keys.add(index,data);
    }
    //checks if node is leaf
    public boolean isLeaf(){
        return this.isLeaf;
    }
    //add kid to node.
    public void addKid(int index, BTreeNode node){
        kids.add(index,node);
    }


    //create string with the tree keys by BFS
    public String toString(String bTree){
        BTreeNode currNode;
        Queue<BTreeNode> qNodes=new QueueAsLinkedList<>(); Queue<String> qString=new QueueAsLinkedList<>();
        qNodes.enqueue(this);
        qNodes.enqueue(new BTreeNode(t));
        qString.enqueue("#");
        while(!qNodes.isEmpty()){
            currNode=qNodes.dequeue();
            bTree=bTree+addKeysToString(currNode)+qString.dequeue();
            enqueueKids(currNode,qNodes,qString);
            if (!qNodes.isEmpty()){
                if (qNodes.peek().getKeys().isEmpty()){
                    qNodes.dequeue();
                    BTreeNode node=new BTreeNode(t);
                    qNodes.enqueue(node); qString.enqueue("#");
                }
                else qString.enqueue("^");
            }
        }
        return bTree.substring(0,bTree.length()-2);
    }

    //add the node keys to the string
    public String addKeysToString(BTreeNode node){
        String bTree="";
        for (int i=0;i<node.size;i++){
            bTree=bTree+node.getKey(i);
            if (i<node.size-1)
                bTree=bTree+",";
        }
        return bTree;
    }
    //enqueue the node's kids
    public void enqueueKids(BTreeNode node, Queue<BTreeNode> qNodes, Queue<String> qString){
        for (int j=0; j<node.getKids().size();j++){
            qNodes.enqueue(node.getKid(j));
            if (j<node.getKids().size()-1)
                qString.enqueue("|");
        }
    }

}
