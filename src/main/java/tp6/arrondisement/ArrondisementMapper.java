package tp6.arrondisement;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import tp6.Arbre;

import java.io.IOException;

public class ArrondisementMapper
        extends Mapper<Object, Text, Text, CustomWritable>{

    private CustomWritable custom = new CustomWritable();
    private Text arrondisement = new Text("arrondissement");
    private int cleE = 0;

    public ArrondisementMapper() {
        System.out.println("##############Mapper#############");
    }

    public void map(Object key, Text value, Context context
    ) throws IOException, InterruptedException {
        Arbre.fromLine(value.toString());
        if (cleE != 0){

            try {
                double anne = Arbre.getAnnee();
                int arrond = Arbre.getArrond();
                custom.set(arrond,anne);
                System.out.println("Mapper :"+arrond + "--------- " +anne);

            } catch (Exception e) {
                e.printStackTrace();
            }
            context.write(arrondisement, custom);
        }
        cleE++;
    }
}