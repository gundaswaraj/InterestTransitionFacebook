import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class SecondMapper extends MapReduceBase
    implements Mapper<LongWritable, Text, Text, IntWritable> {
		public void map(LongWritable key, Text value, 
                OutputCollector<Text,IntWritable> output, 
                Reporter reporter) throws IOException {
			
		String line =value.toString();
		StringTokenizer itr=new StringTokenizer(line);
		while(itr.hasMoreTokens())
		{
			String brandPair1 =itr.nextToken();
			String brandPair2 =itr.nextToken();
			String brandPair = "("+brandPair1+","+brandPair2+")";
			output.collect(new Text(brandPair), new IntWritable(1));
		}
		
     }
  }