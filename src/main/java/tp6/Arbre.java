package tp6;

public abstract class Arbre
{
    /* Les champs sont
     *  0: Geo point
     *  1: ARRONDISSEMENT
     *  2: GENRE
     *  3: ESPECE
     *  4: FAMILLE
     *  5: ANNEE PLANTATION
     *  6: HAUTEUR
     *  7: CIRCONFERENCE
     *  8: ADRESSE
     *  9: NOM COMMUN
     * 10: VARIETE
     * 11: OBJECTID
     * 12: NOM_EV
     */
    static String[] champs;

    public static void fromLine(String ligne)
    {
        champs = ligne.split(";");
    }

    public static String getGenre()
    {
        return champs[2];
    }
    public static int getArrond()
    {
        int x = 0;
        try{
            x = Integer.parseInt(champs[1]);
        }catch(Exception e){
        }
        return x;
    }

    public static double getAnnee()
    {
        double x = 1850;
        try{
            x = Double.parseDouble(champs[5]);
        }catch(Exception e){
        }
        return x;
    }

    public static double getHauteur() throws Exception
    {
        double x = 0;
        try{
            x = Double.parseDouble(champs[6]);
        }catch(Exception e){
        }
        return x;
    }

}