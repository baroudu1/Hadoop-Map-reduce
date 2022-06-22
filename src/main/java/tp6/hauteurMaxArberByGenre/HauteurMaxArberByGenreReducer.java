package tp6.hauteurMaxArberByGenre;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class HauteurMaxArberByGenreReducer
        extends Reducer<Text, DoubleWritable,Text,DoubleWritable> {

    private DoubleWritable hauteurMax = new DoubleWritable();

    public void reduce(Text key, Iterable<DoubleWritable> values,
                       Context context
    ) throws IOException, InterruptedException {
        double Max = 0;
        for (DoubleWritable val : values) {
            //System.out.println("value: "+val.get());
            if(Max < val.get()){
                Max = val.get();
            }
        }
        System.out.println(key+" -->  "+Max);
        hauteurMax.set(Max);
        context.write(key, hauteurMax);
    }
}