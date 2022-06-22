package tp6.nbrArbreByGenre;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import tp6.Arbre;

import java.io.IOException;
public class NbrArbreByGenreMapper
        extends Mapper<Object, Text, Text, IntWritable>{

    private final static IntWritable one = new IntWritable(1);
    private Text genre = new Text();
    private int cleE = 0;
    public void map(Object key, Text value, Mapper.Context context
    ) throws IOException, InterruptedException {
        Arbre.fromLine(value.toString());
        if (cleE != 0){
            String genree = Arbre.getGenre();
            genre.set(genree);
            context.write(genre, one);
        }
        cleE++;
    }
}