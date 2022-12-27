
class NumArray {
    
    class Node
    {
      int st;
      int ed;
      Node left;
      Node right;
      int val;
    }
    Node root;
    public Node construct(int []nums,int lo,int hi)
    {
      if(lo==hi)
      {
        Node base=new Node();
        base.left=base.right=null;
        base.st=base.ed=lo;
        base.val=nums[lo];
        return base;
      }
          int mid=(lo+hi)/2;
          Node left=construct(nums,lo,mid);
          Node right=construct(nums,mid+1,hi);
          Node temp=new Node();
          temp.st=left.st;
          temp.ed=right.ed;
          temp.left=left;
          temp.right=right;
          temp.val=left.val+right.val;
          return temp;
    }
    public NumArray(int[] nums) {
        root=construct(nums,0,nums.length-1);

    }
    
    public void update(int index, int val) {
        updatefun(index,val,root);
       
    }
    public void  updatefun(int index,int val,Node node)
    {
      if(node.st==node.ed){
        node.val=val;
        return;
      }
    
      int mid=(node.st+node.ed)/2;
      if(mid<index)
      {
        updatefun(index,val,node.right);
      }
      else
      {
         updatefun(index,val,node.left);
      }
       node.val=node.left.val+node.right.val;
    }
    public int sumRange(int left, int right) {
        return(sumRangefun(root,left,right));
    }
    public int sumRangefun(Node node,int left,int right)
    {
      if(right<node.st || left>node.ed)
      {
        return 0;
      }
      else if(left<=node.st && right>=node.ed)
      {
         return node.val;
      }
      else
      {
          int lt=sumRangefun(node.left,left,right);
          int rt=sumRangefun(node.right,left,right);
          return (lt+rt);
      }
    }
}



