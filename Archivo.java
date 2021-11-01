
/*
    Bibliotecas Apache POI usadas para leer archivos Excel
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
    Lectura de los archivos excel y reconstrucción de los daos
*/
public class Archivo {
    public static void abrirArchivos(List<Dato> tabla){
        String elemento = "";
        int i;
        
        String[] localizacion = new String[16];
        int diaSemana = 1;
        String fecha;
        float medicion;
        Dato dato;
        
        //Ventana para seleccionar los archivos a integrar
        JFileChooser ventanaArchivo = new JFileChooser();
        ventanaArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        ventanaArchivo.setMultiSelectionEnabled(true);
        int resultado = ventanaArchivo.showOpenDialog(new JPanel());
        
        if(resultado == JFileChooser.APPROVE_OPTION){
            //Obtención de los archivos
            File[] archivos = ventanaArchivo.getSelectedFiles();
            for(int j=0; j < archivos.length; j++){
                //Se obtienen el nombre para saber el elemento de los registros
                String nombre = archivos[j].getName();
                elemento = "";
                for(i=4; i<nombre.length()-6; i++)
                    elemento += nombre.charAt(i);
                
                FileInputStream inputStream = null;
                try{
                    //Abrimos el libro de Excel
                    inputStream = new FileInputStream(archivos[j]);
                    Workbook workbook = new XSSFWorkbook(inputStream);
                    Sheet firstSheet = workbook.getSheetAt(0);
                    Iterator iterator = firstSheet.iterator();

                    DataFormatter formatter = new DataFormatter();

                    //De la primera fila...
                    Row nextRow = (Row) iterator.next();
                    Iterator cellIterator = nextRow.cellIterator();
                    Cell cell = (Cell) cellIterator.next();
                    i = 0;
                    //...obtenemos la ubicación de cada columna
                    while(cellIterator.hasNext()){
                        cell = (Cell) cellIterator.next();
                        localizacion[i++] = formatter.formatCellValue(cell);
                    }

                    //Recorremos fila por fila de la hoja de calculo
                    while(iterator.hasNext()){
                        nextRow = (Row) iterator.next();
                        cellIterator = nextRow.cellIterator();
                        cell = (Cell) cellIterator.next();
                        fecha = formatter.formatCellValue(cell);
                        
                        //Recorremos columna por columna
                        i = 0;
                        while(cellIterator.hasNext()){
                            cell = (Cell) cellIterator.next();
                            medicion = Float.parseFloat(
                                    formatter.formatCellValue(cell)
                            );
                            //Estructuramos la información
                            dato = new Dato(
                                    elemento,
                                    localizacion[i++],
                                    diaSemana,
                                    fecha,
                                    medicion
                            );
                            //Integramos los datos a la tabla
                            tabla.add(dato); 
                       }
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
