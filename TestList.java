import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang3.RandomStringUtils;




public class TestList {

	public static void main(String[] args) throws IOException 
	{
		 Calendar cal1=Calendar.getInstance();
	        SimpleDateFormat sdf1 =new SimpleDateFormat("HH:mm:ss");
			String startTime =sdf1.format(cal1.getTime());
		List<HashSet> ls=new CopyOnWriteArrayList<HashSet>();
	     
	     BufferedReader br=new BufferedReader(new FileReader("/home/cloudera/Downloads/TCOUTPUT.csv"));
	     String Data;
	     System.out.println("Creating List...");
	     while((Data=br.readLine())!=null)
	     {
	         String[] DataLine=Data.split(",");
	         HashSet<String> hs =new HashSet();
	         
	         for(int i=0;i<DataLine.length;i++)
	         {
	        	 hs.add(DataLine[i]);
	        	 
	         }
	         ls.add(hs);
	     }
	     System.out.println("List Created....");
	     br.close();
	     //System.out.println(ls.iterator().next());
	     Iterator<HashSet> itr=ls.iterator();
	     HashSet<String> hs2=null;
	     int counter2 =0;
	     while(itr.hasNext())
    	 {
	    	 HashSet<String> ele=itr.next();
     
	    	 for (HashSet<String> hs1 : ls) 
		     {
	    		 if(!Collections.disjoint(hs1, ele))
	    		 {
	    			System.out.println(counter2++);
	    			hs2=new HashSet<String>(hs1);
	    		    hs2.addAll(ele);
	    	        ls.remove(ele);
	    	        ls.remove(hs1);
	    		 }
 	 
	    	 }
	    	 ls.add(hs2);

		 }
	     
	     int counter=0;
		 BufferedWriter bw=new BufferedWriter(new FileWriter("/home/cloudera/Desktop/datasets/IndexFiles/TCOUTPUT"));
		 for(Set s: ls)
		 {
			 bw.write("LINK_"+randGenerator()+"|"+s.toString().replace("[","").replace("]", "").trim());
			 bw.newLine();
			 System.out.println("LINK_"+randGenerator()+"|"+s.toString().replace("[","").replace("]", "").trim());
		 }
		 System.out.println("Transitive Closure comepleted.....");
		 //bw.close();
		 
		 
		 Calendar cal2=Calendar.getInstance();
	        SimpleDateFormat sdf2 =new SimpleDateFormat("HH:mm:ss");
	 		String endTime =sdf2.format(cal2.getTime());
	 		
	    	System.out.println("Start time: "+startTime);
	    	System.out.println("End Time: "+endTime);
	     
	}
	public static String randGenerator()
	{
		int length=10;
		boolean useLetters=true;
		boolean useNumbers=true;
		String generatedString= RandomStringUtils.random(length, useLetters, useNumbers);
		return generatedString.toUpperCase();
	}

}
