
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.JOptionPane;

/*
    Exportación de los datos
*/
public class Exportacion {
    public static void exportar(List<Dato> tabla){
        try{
            //Creación del archivo
            PrintWriter write = new PrintWriter("./Precipitacion.csv","UTF-8");
            
            //Escritura de las cabeceras
            write.println(
                    "Elemento,Localización,Día de la semana,Fecha,Medición"
            );
            
            //Escritura de la tabla
            for(int i=0; i<tabla.size(); i++){
                Dato fila = tabla.get(i);
                write.println(
                        fila.getElemento()+","+
                        fila.getLocalizacion()+","+
                        fila.getDiaSemana()+","+
                        fila.getFecha()+","+
                        fila.getMedicion()
                );
            }
            write.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        finally{
            JOptionPane.showMessageDialog(null, "Datos exportados correctamente");
        }
    }
}
