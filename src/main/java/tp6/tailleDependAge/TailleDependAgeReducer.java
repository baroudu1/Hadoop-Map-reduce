package tp6.tailleDependAge;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TailleDependAgeReducer
        extends Reducer<Text, Regression,Text,DoubleWritable> {
    public TailleDependAgeReducer() {
        System.out.println("##############Reducer#############");
    }

    private Text text = new Text();
    private DoubleWritable result = new DoubleWritable();


    public void reduce(Text key, Iterable<Regression> values,
                       Context context
    ) throws IOException, InterruptedException {
        Regression regression = new Regression();
        for (Regression val : values) {
            regression.add(val.getAge(),val.getTaille());
        }
        System.out.println("Reducer :"+key +"---------> " +regression.getCoeffCore());
        result.set(regression.getCoeffCore());
        text.set(key);
        context.write(text, result);
    }
}