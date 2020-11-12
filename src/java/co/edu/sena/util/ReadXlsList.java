/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.util;

import co.edu.sena.dao.InstructoresDAO;
import co.edu.sena.dto.Cabecera;
import co.edu.sena.dto.InstructorDTO;
import co.edu.sena.dto.ListaDTO;
import static co.edu.sena.util.Read.generateUrl;
import com.mysql.jdbc.StringUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.Part;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author equipo
 */
public class ReadXlsList {

    private final String NOMBRE = "Nombre_lista";
    private final String DESCRIPCION = "Descripción_lista";
    private final String ITEMSLISTA = "Items_lista";

    private String nombre_lista;
    private String descripcion_lista;

    public ArrayList<ListaDTO> readXls(Part file) throws FileNotFoundException, IOException {

        // convitiendo el archivo a fileInputStream
        FileInputStream fi1 = new FileInputStream(new File(generateUrl(file.toString())));

        // instancia de los objetos para poder leer archivos excel
        HSSFWorkbook wb1 = new HSSFWorkbook(fi1);
        HSSFSheet sheet1 = wb1.getSheetAt(0);

        FormulaEvaluator formulaEvaluator = wb1.getCreationHelper().createFormulaEvaluator();

        ArrayList<ListaDTO> lista = new ArrayList<>();
        ListaDTO listaDTO = null;

        for (int i = 1; i < sheet1.getLastRowNum() + 1; i++) {
            Row rows = sheet1.getRow(i);

            for (Cell cell : rows) {

                switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {

                    // lectura de campos de tipo caracter
                    case Cell.CELL_TYPE_STRING:

                        switch (cell.getColumnIndex()) {
                            case 2:
                                listaDTO = new ListaDTO();
                                listaDTO.setDescripcion(cell.getStringCellValue());
                                listaDTO.setTipoItem("0");
                                lista.add(listaDTO);
                                break;
                        }

                        break;

                }

            }

        }

        // limpiando memoria por lectura del archivo
        formulaEvaluator.clearAllCachedResultValues();
        fi1.close();

        // retornando los valores del archivo en una lista
        return lista;

    }

    public boolean validationSizeandContent(Part file) throws Exception {

        // convitiendo el archivo a fileInputStream
        FileInputStream fi1 = new FileInputStream(new File(generateUrl(file.toString())));

        // instancia de los objetos para poder leer archivos excel
        HSSFWorkbook wb1 = new HSSFWorkbook(fi1);
        HSSFSheet sheet1 = wb1.getSheetAt(0);

        boolean estado = true;

        FormulaEvaluator formulaEvaluator = wb1.getCreationHelper().createFormulaEvaluator();

        for (int i = 1; i < sheet1.getLastRowNum() + 1; i++) {

            Row rows = sheet1.getRow(i);

            for (Cell cell : rows) {

                switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {

                    case Cell.CELL_TYPE_STRING:

                        switch (cell.getColumnIndex()) {
                            case 2:

                                if (cell.getStringCellValue().length() > 800) {
                                     estado = false;
                                }
                                if (StringUtils.isNullOrEmpty(cell.getStringCellValue())) {
                                    estado = false;
                                }
                                if (cell.getStringCellValue().length() <= 2) {
                                    estado = false;
                                }
                                if (cell.getStringCellValue().equals("")) {
                                     estado = false;
                                }
                                break;
                        }

                        break;

                }

            }

        }
        
        // limpiando memoria por lectura del archivo
        formulaEvaluator.clearAllCachedResultValues();
        fi1.close();

        // retornando los valores del archivo en una lista
        return estado;

    }

    public boolean validationXlsNameDescription(Part file) throws Exception {

        // convitiendo el archivo a fileInputStream
        FileInputStream fi1 = new FileInputStream(new File(generateUrl(file.toString())));

        // instancia de los objetos para poder leer archivos excel
        HSSFWorkbook wb1 = new HSSFWorkbook(fi1);
        HSSFSheet sheet1 = wb1.getSheetAt(0);

        FormulaEvaluator formulaEvaluator = wb1.getCreationHelper().createFormulaEvaluator();

        boolean estado = true;

        Row rows2 = sheet1.getRow(1);

        // validando las cabeceras
        for (Cell cell : rows2) {
            switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {

                // lectura de campos de tipo caracter
                case Cell.CELL_TYPE_STRING:

                    switch (cell.getColumnIndex()) {

                        case 0:

                            if (StringUtils.isNullOrEmpty(cell.getStringCellValue())
                                    && cell.getStringCellValue().length() > 800
                                    && cell.getStringCellValue().length() <= 2) {
                                 estado = false;
                            } else {
                                this.nombre_lista = cell.getStringCellValue();
                            }
                            if (cell.getStringCellValue().equals("nombre_lista")) {
                                estado = false;
                            }

                            break;

                        case 1:

                            if (StringUtils.isNullOrEmpty(cell.getStringCellValue())
                                    && cell.getStringCellValue().length() > 800
                                    && cell.getStringCellValue().length() <= 2) {
                                 estado = false;
                            } else {
                                this.descripcion_lista = cell.getStringCellValue();
                            }
                            if (cell.getStringCellValue().equals("descripción_lista")) {
                                estado = false;
                            }

                            break;

                    }

                    break;
            }
        }

        System.out.println(estado);

        // limpiando memoria por lectura del archivo
        formulaEvaluator.clearAllCachedResultValues();
        fi1.close();

        // retornando los valores del archivo en una lista
        return estado;

    } // cierre del metodo readingXls

    public boolean validationXlsCebecera(Part file) throws FileNotFoundException, IOException, Exception {

        // convitiendo el archivo a fileInputStream
        FileInputStream fi1 = new FileInputStream(new File(generateUrl(file.toString())));

        // instancia de los objetos para poder leer archivos excel
        HSSFWorkbook wb1 = new HSSFWorkbook(fi1);
        HSSFSheet sheet1 = wb1.getSheetAt(0);

        FormulaEvaluator formulaEvaluator = wb1.getCreationHelper().createFormulaEvaluator();

        boolean estado = true;
        int contador = 0;

        // obteniendo la fila encabezado
        Row rows = sheet1.getRow(0);

        // validando las cabeceras
        for (Cell cell : rows) {
            switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {

                // lectura de campos de tipo caracter
                case Cell.CELL_TYPE_STRING:

                    switch (cell.getColumnIndex()) {

                        case 0:

                            contador++;
                            System.out.println(cell.getStringCellValue());
                            if (!cell.getStringCellValue().equals(NOMBRE)) {
                                estado = false;
                            }

                            break;

                        case 1:

                            contador++;
                            System.out.println(cell.getStringCellValue());
                            if (!cell.getStringCellValue().equals(DESCRIPCION)) {
                                estado = false;
                            }
                            break;

                        case 2:

                            contador++;
                            System.out.println(cell.getStringCellValue());
                            if (!cell.getStringCellValue().equals(ITEMSLISTA)) {
                                estado = false;
                            }
                            break;

                    }

                    break;
            }
        }

        System.out.println(estado);

        if (contador != 3) {
           estado = false;
        }

        // limpiando memoria por lectura del archivo
        formulaEvaluator.clearAllCachedResultValues();
        fi1.close();

        // retornando los valores del archivo en una lista
        return estado;

    } // cierre del metodo readingXls

    public static String generateUrl(String url) {
        String[] parts = url.split(",");

        String url1 = parts[1];
        String[] parts2 = url1.split("=");

        return parts2[1];
    }

    public String getNombre_lista() {
        return nombre_lista;
    }

    public String getDescripcion_lista() {
        return descripcion_lista;
    }

}
