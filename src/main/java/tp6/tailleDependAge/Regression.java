package tp6.tailleDependAge;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Date;

public class Regression implements Writable {

    private double age;
    private double taille;

    private double n,Sx,Sx2,Sy,Sy2,Sxy;

    public Regression() {

    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeDouble(age);
        dataOutput.writeDouble(taille);

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        age = dataInput.readDouble();
        taille = dataInput.readDouble();

    }
    public void set(double x,double y) throws IOException {
        // x --> age
        // y --> taille
        Date d = new Date();
        double age = d.getYear() - x + 1900;
        this.age = age;
        this.taille = y;

//        n=1;
//        Sx = age;
//        Sx2 = age*age;
//        Sy = y;
//        Sy2 = y*y;
//        Sxy = age*y;

    }
    public void add(double x,double y) throws IOException {
        // x --> age
        // y --> taille
        n++;
        Sx+=x;
        Sx2+=x*x;
        Sy+=y;
        Sy2+=y*y;
        Sxy+=x*y;
        //System.out.println(Sy +"------"+Sx);
    }
    public double getCoeffCore() throws IOException {
        // x --> age
        // y --> taille
        double coeffCore,Mx,Mx2,My,My2,Mxy,Vx,Vy,Vxy,denom;
        Mx = Sx/n;
        Mx2 = Sx2/n;
        My = Sy/n;
        My2 = Sy2/n;
        Mxy = Sxy/n;
        Vx = Mx2 - Mx * Mx;
        Vy = My2 - My * My;
        Vxy = Mxy - Mx * My;
        denom = Math.sqrt(Vx*Vy);
        if(denom == 0)
            return 1;
        coeffCore = Vxy/denom;
        return coeffCore;
    }

    public double getAge() {
        return age;
    }

    public double getTaille() {
        return taille;
    }
}
