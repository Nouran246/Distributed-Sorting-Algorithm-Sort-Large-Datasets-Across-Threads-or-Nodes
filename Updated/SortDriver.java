import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SortDriver {
    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.err.println("Usage: SortDriver <input path> <output path> <mode: numeric|alpha>");
            System.exit(-1);
        }

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Distributed Sort");

        job.setJarByClass(SortDriver.class);

        String mode = args[2].toLowerCase();

        if (mode.equals("numeric")) {
            job.setMapperClass(NumericSortMapper.class);
            job.setMapOutputKeyClass(IntWritable.class);
            job.setMapOutputValueClass(NullWritable.class);
            job.setOutputKeyClass(IntWritable.class);
            job.setOutputValueClass(NullWritable.class);
        } else if (mode.equals("alpha")) {
            job.setMapperClass(AlphanumericSortMapper.class);
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(NullWritable.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(NullWritable.class);
        } else {
            System.err.println("Invalid mode: " + mode + ". Use 'numeric' or 'alpha'.");
            System.exit(-1);
        }

        job.setReducerClass(SortReducer.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}

