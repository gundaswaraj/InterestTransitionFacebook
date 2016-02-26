
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Partitioner;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;



public class Driver extends Configured implements Tool{ 
		public int run(String[] args) throws Exception
			{ 
			//creating a JobConf object and assigning a job name for identification purposes 
				 JobConf conf = new JobConf(getConf(), Driver.class);
				 JobConf conf2 =new JobConf(getConf(),Driver.class);
				 conf.setJobName("Assignment"); 
				 conf2.setJobName("Assignemnt2");
				 
				 //Setting configuration object with the Data Type of output Key and Value 
				 conf.setOutputKeyClass(LongWritable.class); 
				 conf.setOutputValueClass(Text.class);
				 conf2.setOutputKeyClass(Text.class);
				 conf2.setOutputValueClass(IntWritable.class);
				 //Providing the mapper and reducer class names 
				 conf.setJarByClass(Driver.class); 
				 conf.setMapperClass(FirstMapper.class); 
				 conf.setReducerClass(FirstReducer.class); 
				 conf2.setMapperClass(SecondMapper.class);
				 conf2.setReducerClass(SecondReducer.class);
				 conf.setPartitionerClass(AgePartitioner.class);
				 conf.setNumReduceTasks(3);							//set the number of reducers to three.
				 conf2.setJarByClass(Driver.class);
				 
				 //the hdfs input and output directory to be fetched from the command line 
				 FileInputFormat.addInputPath(conf, new Path(args[0])); 
				 FileOutputFormat.setOutputPath(conf, new Path(args[1])); 
				 FileInputFormat.addInputPath(conf2, new Path(args[1]));
				 FileOutputFormat.setOutputPath(conf2,new Path(args[2]));
				 JobClient.runJob(conf); 
				 JobClient.runJob(conf2);
				 return 0; 
} 
			public static void main(String[] args) throws Exception{ 
				  int res = ToolRunner.run(new org.apache.hadoop.conf.Configuration(), new Driver(), args);
				System.exit(res); 
				} 
} 