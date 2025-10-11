
import java.io.IOException;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class SortReducer<K extends WritableComparable<K>> extends Reducer<K, NullWritable,K, NullWritable> {

    @Override
    protected void reduce(K key, Iterable<NullWritable> values, Context context)
            throws IOException, InterruptedException {
        
        // Output each key as is (Hadoop automatically sorts keys before sending them here)
        context.write(key, NullWritable.get());
    }
}

