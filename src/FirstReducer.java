import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class FirstReducer extends MapReduceBase
    implements Reducer<LongWritable, Text,Text,IntWritable> {
    
    public void reduce(LongWritable key, Iterator<Text> values,
                       OutputCollector<Text,IntWritable> output, 
                       Reporter reporter) throws IOException {
    	Map<String, String> initalInput= new HashMap<String, String>(); //Created a map to store the output from the mapper
    	Map<String, String> sortedInput= new HashMap<String, String>(); // A map variable to store the inputdata in sorted order.
    	
    		while (values.hasNext())
    		{
	    		String[] tokens = values.next().toString().split("\\t"); 	
	    		String brandId=tokens[0];
	    		String timeStamp=tokens[1];
	    		initalInput.put(timeStamp,brandId);
    	   }
    		sortedInput = sortByKeys(initalInput);  //Used a function to sort the initalinput map.
        	List<String> brandsList = new ArrayList<String>(); //created a ArrayList to add the brandids in the sorted order.
        	String[] brandPair = new String[brandsList.size()];
        	for(String ts : sortedInput.keySet())
        	{
        		brandsList.add(sortedInput.get(ts)); 
     
        	}
        	IntWritable one = new IntWritable(1);
        	for(int i=0;i<brandsList.size()-1;i++)
        	{
        		String temp=brandsList.get(i)+"\t"+brandsList.get(i+1);
        		output.collect(new Text(temp),new IntWritable(1)); 
        	}
        	
    }
    
    //Created a method to sort the map by keys
    public static <K extends Comparable,V extends Comparable> Map<K,V> sortByKeys(Map<K,V> map){
        List<K> keys = new LinkedList<K>(map.keySet());
        Collections.sort(keys);
        Map<K,V> sortedMap = new LinkedHashMap<K,V>();
        for(K key: keys){
            sortedMap.put(key, map.get(key));
        }
      
        return sortedMap;
    }
}


