package tp6.arrondisement;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Date;

public class CustomWritable implements Writable {
    private int arrondisement;
    private double age;

    public CustomWritable() {
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(arrondisement);
        dataOutput.writeDouble(age);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        arrondisement = dataInput.readInt();
        age = dataInput.readDouble();
    }
    public void set(int arrondisement,double anne) throws IOException {
        Date d = new Date();
        double age = d.getYear() - anne +1900;
        this.age = age;
        this.arrondisement = arrondisement;
    }
    public int getArrondisement(){
        return this.arrondisement;
    }
    public double getAge(){
        return this.age;
    }
}
