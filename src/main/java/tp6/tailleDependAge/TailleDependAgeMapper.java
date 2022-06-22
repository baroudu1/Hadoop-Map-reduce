package tp6.tailleDependAge;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import tp6.Arbre;

import java.io.IOException;

public class TailleDependAgeMapper
        extends Mapper<Object, Text, Text, Regression>{

    private Regression custom = new Regression();
    private Text genre = new Text("1");
    private int cleE = 0;

    public TailleDependAgeMapper() {
        System.out.println("##############Mapper#############");
    }

    public void map(Object key, Text value, Context context
    ) throws IOException, InterruptedException {
        Arbre.fromLine(value.toString());
        if (cleE != 0){
            try {
                double anne = Arbre.getAnnee();
                double taille = Arbre.getHauteur();
                String genree = Arbre.getGenre();
                genre.set(genree);
                custom.set(anne,taille);
                System.out.println("Mapper :"+genree+"-----------"+taille + "--------- " +custom.getAge());

            } catch (Exception e) {
                e.printStackTrace();
            }
            context.write(genre, custom);
        }
        cleE++;
    }
}