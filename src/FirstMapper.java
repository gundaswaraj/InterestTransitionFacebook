import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class FirstMapper extends MapReduceBase
    implements Mapper<LongWritable, Text, LongWritable, Text> {
		public void map(LongWritable key, Text value, 
                OutputCollector<LongWritable, Text> output, 
                Reporter reporter) throws IOException {
			String line = value.toString();       //Created a variable of type string to convert the value of type text to string 
			StringTokenizer itr= new StringTokenizer(line);   //created tokenizer to store all the tokens of the Text value
			while(itr.hasMoreTokens())					//iterated through all the tokens in itr
			{
				String userId=itr.nextToken();			
				long userIdLong = Long.parseLong(userId);
				String brandId=itr.nextToken();
				String timeStamp=itr.nextToken();
				String brandTimeStamp= brandId +"\t"+timeStamp;  //created a variable brandTimeStamp which contains brand and time stamp contcataned by tab space
				output.collect(new LongWritable(userIdLong),new  Text(brandTimeStamp));  //sent by the outputcollecter object "output" with the intermediate key and intermediate value. 
				
			}
			
		}
	}