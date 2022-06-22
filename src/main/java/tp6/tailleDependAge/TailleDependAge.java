package tp6.tailleDependAge;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class TailleDependAge {
    public static void main(String[] args) throws Exception {
        //System.setProperty("hadoop.home.dir","C:\\hadoop");
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Coeffcient de corrélation entre l'age et la taille d'un arbre");
        job.setJarByClass(TailleDependAge.class);
        job.setMapperClass(TailleDependAgeMapper.class);
        job.setReducerClass(TailleDependAgeReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Regression.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }
}