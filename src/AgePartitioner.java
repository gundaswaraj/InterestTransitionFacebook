import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
//import org.apache.hadoop.mapred.MapReduceBase;


public class AgePartitioner extends MapReduceBase implements Partitioner<LongWritable, Text>{
//This partitioner partitions the input based on the keys . There are three reducers in this mapreduce program
	public int getPartition(LongWritable key, Text value, int numofRedTasks) {
		
		if(numofRedTasks==0)
		{
			return 0;
		}
		
		if((key).get()%3==0)
		{
			return 0; //If key mod 3 is zero
		}
		if((key).get()%3==1)
		{
			return 1; 
		}
		else
		{
			return 2;
		}
		
	}
	

}
