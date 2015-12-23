package com.need.utils.wms;

/**
 * 产生一个唯一的ID的类。
 * <P>Author : guming </P> 
 * <P>Date : Jan 5, 2013 </P>
 */
public class GenerateKeyHandler {
	
   private static final long PREFIX_LONG_VALUE=10000000000L;
   
   /**
    * 产生一个唯一的ID
    * GenerateKeyHandler.generateKey()<BR>
    * <P>Author :pgf </P>  
    * <P>Date : Jan 5, 2013 </P>
    * @return 返回一个唯一的不重复的ID
    */
   public static Long generateKey(){
		long uniqueId=Math.abs(UniqueID.getInstance().getUniqID().hashCode());
		StringBuilder sb=new StringBuilder(24);
		sb.append(String.valueOf(uniqueId+PREFIX_LONG_VALUE));
		sb.append(getRandomInt());
		return Long.valueOf(sb.toString());
   }
   private static String getRandomInt()  
	{  
	  	  int result = 1 + new Double( Math.random() * ( 9999 - 1 ) ).intValue();  
	  	 if(result<10){
	  		 return "000"+String.valueOf(result);
	  	  }
	  	  if(result<100){
		  		 return "00"+String.valueOf(result);
		  }
	  	 if(result<1000){
	  		 return "0"+String.valueOf(result);
	  	  }
	      return String.valueOf(result);
	}
}

