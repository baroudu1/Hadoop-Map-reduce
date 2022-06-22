package tp6.hauteurMaxArberByGenre;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class HauteurMaxArberByGenre {
    public static void main(String[] args) throws Exception {
        //System.setProperty("hadoop.home.dir","C:\\hadoop");
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "NbrArbreByGenre");
        job.setJarByClass(HauteurMaxArberByGenre.class); // recupere le jar et les dependances
        job.setMapperClass(HauteurMaxArberByGenreMapper.class);
        job.setCombinerClass(HauteurMaxArberByGenreReducer.class);
        job.setReducerClass(HauteurMaxArberByGenreReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}