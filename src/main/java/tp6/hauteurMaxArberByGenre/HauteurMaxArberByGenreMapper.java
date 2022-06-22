package tp6.hauteurMaxArberByGenre;


import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import tp6.Arbre;


public class HauteurMaxArberByGenreMapper
        extends Mapper<Object, Text, Text, DoubleWritable>{

    private DoubleWritable hauteur = new DoubleWritable();
    private Text genre = new Text();
    private int cleE = 0;
    public void map(Object key, Text value, Context context){
        Arbre.fromLine(value.toString());
        if (cleE != 0){
            String genree = Arbre.getGenre();
            genre.set(genree);
            try {
                Double hauteurr = Arbre.getHauteur();
                hauteur.set(hauteurr);
                context.write(genre, hauteur);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        cleE++;
    }
}