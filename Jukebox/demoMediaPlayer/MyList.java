package demoMediaPlayer;
/*
 * @Author: Chi Thieu
 */
public class MyList { 
	String[] array;
	public MyList() {
		this.array = new String[0]; }  
	/**  * Adds the element as the first element of the list.  
	 * * You don't need to implement this fully, just have something that is a start  
	 * * using the appropriate structure.  */ 
	public void addFirst(String element) {   
		for (int i = (this.array.length-1); i>=-1; i--) {
			array[i+1]=array[i];
		}
		array[0]=element;}
	/**  * Removes the first element of the list.  
	 * * You don't need to implement this fully, must have something that is a start  
	 * * using the appropriate structure.  */ 
	public void removeFirst() { 
		int n=this.array.length-1;
		String[] newarray = new String[n];
		System.arraycopy(this.array,1,newarray,0,n);
		this.array=newarray;
}
	}
