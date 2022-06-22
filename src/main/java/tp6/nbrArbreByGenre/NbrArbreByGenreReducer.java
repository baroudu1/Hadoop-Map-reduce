package tp6.nbrArbreByGenre;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class NbrArbreByGenreReducer
        extends Reducer<Text,IntWritable,Text,IntWritable> {

    private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
    ) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable val : values) {
            //System.out.println("value: "+val.get());
            sum += val.get();
        }
        System.out.println(key+" -->  "+sum);
        result.set(sum);
        context.write(key, result);
    }
}