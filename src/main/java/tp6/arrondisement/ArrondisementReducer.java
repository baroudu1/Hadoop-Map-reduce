package tp6.arrondisement;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ArrondisementReducer
        extends Reducer<Text, CustomWritable,Text,DoubleWritable> {
    public ArrondisementReducer() {
        System.out.println("##############Reducer#############");
    }

    private Text text = new Text();
    private DoubleWritable result = new DoubleWritable();

    public void reduce(Text key, Iterable<CustomWritable> values,
                       Context context
    ) throws IOException, InterruptedException {
        double Max = 0;
        int arrond = 0;
        for (CustomWritable val : values) {
            //System.out.println("value: "+val.get());
            if(Max < val.getAge()){
                Max = val.getAge();
                arrond = val.getArrondisement();

            }
        }
        System.out.println("Reducer :"+key +"--------- " +arrond+ "--------- " +Max);
        result.set(Max);
        text.set(String.valueOf(arrond));
        context.write(text, result);
    }
}