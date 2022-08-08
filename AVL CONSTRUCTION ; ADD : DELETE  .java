import java.util.*;
public class Main
{
    // Node Class
    static class Node{
        int data;
        Node left;
        Node right;
        int bal;
        int ht;
        Node(){}
        Node(int data)
        {
            this.data=data;
            left=null;
            right=null;
            
        }
    }
  
  // CONSTRUCTION
    public static Node Construct(int []arr,int lo,int hi){
       return(Construct_helper(arr,lo,hi));
    }
    public static Node Construct_helper(int []arr,int lo,int hi){
        if(lo>hi)
        {
            return null;
        }
        int mid=(lo+hi)/2;
        Node node=new Node();
        node.data=arr[mid];
        node.left=Construct_helper(arr,lo,mid-1);
        node.right=Construct_helper(arr,mid+1,hi);
        node.bal=get_Balance(node);
        node.ht=get_height(node);
        return node;
    }
  
  
  // Display
    public static void display(Node node){
        if(node==null)
        {
            return ;
        }
       
        String str=" <- "+node.data+"["+node.bal+","+node.ht+"]"+"-> ";
        String lstr=(node.left!=null)?node.left.data+"":".";
        String rstr=(node.right!=null)?node.right.data+"":".";
        System.out.println(lstr+str+rstr);
        display(node.left);
        display(node.right);
    }
  
  
    private static int get_Balance(Node node){
        int lht=(node.left!=null)?node.left.ht:0;
        int rht=(node.right!=null)?node.right.ht:0;
        return (lht-rht);
    }
    private static int get_height(Node node){
        int lht=(node.left!=null)?node.left.ht:0;
        int rht=(node.right!=null)?node.right.ht:0;
        return (Math.max(lht,rht)+1);
    }
  
  // ADD
    public static Node add(Node node,int val){
     
     if(node==null){
         return(new Node(val));
     }
     if(val<node.data)
     {
         node.left=add(node.left,val);
     }
     else if(val>node.data)
     {
         node.right=add(node.right,val);
     }
    
     node.ht=get_height(node);
     node.bal=get_Balance(node);
     
     if(node.bal>1) // LL and Lr
     {
         if(node.left.bal>=0) // LL
         {
            node= RightRotation(node);
         }
         else // LR
         {
            node.left=LeftRotation(node.left);
            node=RightRotation(node);
         }
     }
     else if(node.bal<-1) // rr and rl
     {
         if(node.right.bal<0) // rr
         {
           node=LeftRotation(node);
         }
         else // rl
         {
             node.right=RightRotation(node.right);
             node=LeftRotation(node);
         }
     }
     return node;
     
    }
    public static Node LeftRotation(Node node){
        Node z=node;
        Node y=z.left;
        Node t2=y.left;
        y.left=z;
        z.right=t2;
        
     
      z.ht=get_height(z);
      z.bal=get_Balance(z);
      y.ht=get_height(y);
      y.bal=get_Balance(y);
        
        return y;
    }
    public static Node RightRotation(Node node){
        Node z=node;
        Node y=z.left;
        Node t3=y.right;
        y.right=z;
        z.left=t3;
        
     
      z.ht=get_height(z);
      z.bal=get_Balance(z);
      y.ht=get_height(y);
      y.bal=get_Balance(y);
     
        return y;
    }
 
 // DELETE
   public static Node remove(Node node,int value){
       if(node.data==value)
       {
           if(node.left!=null && node.right!=null)
           {
              int lmax=max(node.left);
              node.data=lmax;
              node.left=remove(node.left,lmax);
             
           }
           else if(node.left!=null)
           {
               return(node.left);
           }
           else if(node.right!=null)
           {
               return(node.right);
           }
           else// if(node.left==null && node.right==null)
           {
              return null; 
           }
       }
       else if(value<node.data)
       {
           node.left=remove(node.left,value);
       }
       else if(value>node.data)
       {
          node.right=remove(node.right,value); 
       }
       
         node.ht=get_height(node);
         node.bal=get_Balance(node);
         
         if(node.bal>1) // LL and Lr
         {
             if(node.left.bal>=0) // LL
             {
                node= RightRotation(node);
             }
             else // LR
             {
                node.left=LeftRotation(node.left);
                node=RightRotation(node);
             }
         }
         else if(node.bal<-1) // rr and rl
         {
             if(node.right.bal<0) // rr
             {
               node=LeftRotation(node);
             }
             else // rl
             {
                 node.right=RightRotation(node.right);
                 node=LeftRotation(node);
             }
         }
         
         
         
         return node;
     
   }
   public static int max(Node node){
       if(node.right==null)
       {
           return(node.data);
       }
       else
       return(max(node.right));
   }
 
    public static void main(String []args)
    {
        int []arr={10,12,20,25,30,37,40,50,60,62,70,75,80,87,90}; // sorted array
        Node root=Construct(arr,0,arr.length-1);
        root=add(root,5);
        root=add(root,1);
        
        remove(root,1);
        display(root);
    }
}
