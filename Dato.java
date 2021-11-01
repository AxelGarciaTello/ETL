
/*
    Estructura de cada registro de datos
*/
public class Dato {
    private String elemento,
                   localizacion,
                   fecha;
    private int diaSemana;
    private float medicion;
    
    public Dato(String elemento, String localizacion,
            int diaSemana, String fecha, float medicion){
        this.elemento = elemento;
        this.localizacion = localizacion;
        this.diaSemana = diaSemana;
        this.fecha = fecha;
        this.medicion = medicion;
    }
    
    public Dato(Dato copia){
        this.elemento = copia.elemento;
        this.localizacion = copia.localizacion;
        this.diaSemana = copia.diaSemana;
        this.fecha = copia.fecha;
        this.medicion = copia.medicion;
    }
    
    public String getElemento(){
        return elemento;
    }
    
    public String getLocalizacion(){
        return localizacion;
    }
    
    public int getDiaSemana(){
        return diaSemana;
    }
    
    public String getFecha(){
        return fecha;
    }
    
    public float getMedicion(){
        return medicion;
    }
    
}
