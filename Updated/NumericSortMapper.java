import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class NumericSortMapper extends Mapper<LongWritable, Text, IntWritable, NullWritable> {
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        int num = Integer.parseInt(value.toString());
        context.write(new IntWritable(num), NullWritable.get());
    }
}
