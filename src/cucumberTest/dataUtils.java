package cucumberTest;

import java.util.Date;

public class dataUtils {

	public static int milliseconds;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	
	}

	public static int getTimeInt (){
		int i = (int) new Date().getTime();
		System.out.println("Integer : " + i);
		milliseconds = i;
	    //it will return unique value based on time
	    System.out.println(milliseconds);
	    
	    return i;
	    
	}
	
}
