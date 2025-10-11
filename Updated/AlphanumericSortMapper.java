import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;

public class AlphanumericSortMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String line = value.toString().trim();
        if (!line.isEmpty()) {
            context.write(new Text(line), NullWritable.get());
        }
    }
}
