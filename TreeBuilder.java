public class TreeBuilder<T> {
	
	LinkedBinaryTree<T> tree;
	
	public TreeBuilder(T[] array) {
		LinkedQueue dataQueue = new LinkedQueue();
		
		for(int i = 0; i<array.length; i++) {
			dataQueue.enqueue(array[i]);
		}
		
		LinkedQueue parentQueue = new LinkedQueue();
		
		BinaryTreeNode root = new BinaryTreeNode(dataQueue.dequeue());
		parentQueue.enqueue(root);
		
		while(!dataQueue.isEmpty()) {
			BinaryTreeNode a = null;
			BinaryTreeNode b = null;
			
			try {
				a = new BinaryTreeNode(dataQueue.dequeue());
				b = new BinaryTreeNode(dataQueue.dequeue());
				
			}catch(Exception EmptyCollectionException ) {
			}
			
			BinaryTreeNode parent = (BinaryTreeNode) parentQueue.dequeue();
			
			if(a.getData() != null) {
				parent.setLeft(a);
				parentQueue.enqueue(a);
			}
			
			if(b.getData() != null) {
				parent.setRight(b);
				parentQueue.enqueue(b);
			}
		}
		tree = new LinkedBinaryTree(root);
	}
	
	public LinkedBinaryTree<T> getTree(){
		return tree;
	}
}
