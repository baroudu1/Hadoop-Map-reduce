package tp6.nbrArbreByGenre;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.BasicConfigurator;

public class NbrArbreByGenre {
    public static void main(String[] args) throws Exception {
        //System.setProperty("hadoop.home.dir","C:\\hadoop");
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "NbrArbreByGenre");
        job.setJarByClass(NbrArbreByGenre.class); // recupere le jar et les dependances
        job.setMapperClass(NbrArbreByGenreMapper.class);
        job.setCombinerClass(NbrArbreByGenreReducer.class);
        job.setReducerClass(NbrArbreByGenreReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}