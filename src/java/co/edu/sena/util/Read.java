/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.util;

import co.edu.sena.dto.Cabecera;
import co.edu.sena.dto.InstructorDTO;
import co.edu.sena.dao.InstructoresDAO;
import com.mysql.jdbc.StringUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.servlet.http.Part;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import co.edu.sena.util.ConexionSer;

/**
 *
 * @author equipo
 */
public class Read {

    public ArrayList<InstructorDTO> readingXls(Part file) throws FileNotFoundException, IOException {

        // convitiendo el archivo a fileInputStream
        FileInputStream fi1 = new FileInputStream(new File(generateUrl(file.toString())));

        // instancia de los objetos para poder leer archivos excel
        HSSFWorkbook wb1 = new HSSFWorkbook(fi1);
        HSSFSheet sheet1 = wb1.getSheetAt(0);

        FormulaEvaluator formulaEvaluator = wb1.getCreationHelper().createFormulaEvaluator();

        //instancia de la lista para ser llenada de objetos aprendiz y actividades
        ArrayList<InstructorDTO> lista = new ArrayList<>();
        InstructorDTO instructorDTO;

        // instanciar la clase conexion y actividadDao para consultar si ya esta en la bd
        ConexionSer conexion = new ConexionSer();
        InstructoresDAO instructoresDAO = new InstructoresDAO(conexion.getConnection());
        int idFuncionar = 0;
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(0);
        String num = "";

        for (int i = 1; i < sheet1.getLastRowNum() + 1; i++) {
            Row rows = sheet1.getRow(i);

            instructorDTO = new InstructorDTO();

            for (Cell cell : rows) {
                switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {

                    // lectura de campos de tipo caracter
                    case Cell.CELL_TYPE_STRING:

                        switch (cell.getColumnIndex()) {
                            case 0:
                                // separando tipo de documento y documento
                                System.out.println(cell.getStringCellValue());
                                instructorDTO.setIdTipoDocumento(cell.getStringCellValue());
                                break;
                            case 2:
                                System.out.println(cell.getStringCellValue());
                                instructorDTO.setNomFuncionario(cell.getStringCellValue().toUpperCase());
                                break;
                            case 3:
                                System.out.println(cell.getStringCellValue());
                                instructorDTO.setApeFuncionario(cell.getStringCellValue().toUpperCase());
                                break;
                            case 4:
                                System.out.println(cell.getStringCellValue());
                                instructorDTO.setCorreo(cell.getStringCellValue());
                                break;
                            case 5:
                                System.out.println(cell.getStringCellValue());
                                instructorDTO.setCargo(cell.getStringCellValue());
                                break;
                        }

                        break;

                    case Cell.CELL_TYPE_NUMERIC:
                        switch (cell.getColumnIndex()) {
                            case 1:
                                System.out.println(cell.getNumericCellValue());
                                num = df.format(cell.getNumericCellValue());
                                instructorDTO.setNumDocumento(num);
                                System.out.println(instructorDTO.getNumDocumento());
                                break;
                              case 6:
                                System.out.println(cell.getNumericCellValue());
                                num = df.format(cell.getNumericCellValue());
                                instructorDTO.setTelefono(num);
                                System.out.println(instructorDTO.getTelefono());
                                break;
                        }
                        break;
                }

            }

            // validaciones, para que no se repitan datos en la bd
            idFuncionar = instructoresDAO.checkDocument(instructorDTO.getNumDocumento(), instructorDTO.getCorreo());

            //si existe no se agrega
            if (idFuncionar == 0) {

                lista.add(instructorDTO);

            } // cierre de comprobación de actividad 

        }

        // limpiando memoria por lectura del archivo
        formulaEvaluator.clearAllCachedResultValues();
        fi1.close();
        instructoresDAO.CloseAll();

        // retornando los valores del archivo en una lista
        return lista;

    }

    public boolean validationXls(Part file) throws FileNotFoundException, IOException {

        // convitiendo el archivo a fileInputStream
        FileInputStream fi1 = new FileInputStream(new File(generateUrl(file.toString())));

        // instancia de los objetos para poder leer archivos excel
        HSSFWorkbook wb1 = new HSSFWorkbook(fi1);
        HSSFSheet sheet1 = wb1.getSheetAt(0);

        FormulaEvaluator formulaEvaluator = wb1.getCreationHelper().createFormulaEvaluator();

        Cabecera cabeceraExcel = new Cabecera();
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
                            if (!cell.getStringCellValue().equals(cabeceraExcel.getTipoDocumento())) {
                                estado = false;
                            }

                            break;

                        case 1:

                            contador++;
                            System.out.println(cell.getStringCellValue());
                            if (!cell.getStringCellValue().equals(cabeceraExcel.getNumeroDocumento())) {
                                estado = false;
                            }
                            break;

                        case 2:

                            contador++;
                            System.out.println(cell.getStringCellValue());
                            if (!cell.getStringCellValue().equals(cabeceraExcel.getNombres())) {
                                estado = false;
                            }
                            break;
                        case 3:

                            contador++;
                            System.out.println(cell.getStringCellValue());
                            if (!cell.getStringCellValue().equals(cabeceraExcel.getApellidos())) {
                                estado = false;
                            }
                            break;
                        case 4:

                            contador++;
                            System.out.println(cell.getStringCellValue());
                            if (!cell.getStringCellValue().equals(cabeceraExcel.getCorreo())) {
                                estado = false;
                            }
                            break;
                        case 5:

                            contador++;
                            System.out.println(cell.getStringCellValue());
                            if (!cell.getStringCellValue().equals(cabeceraExcel.getCargo())) {
                                estado = false;
                            }
                            break;
                        case 6:

                            contador++;
                            System.out.println(cell.getStringCellValue());
                            if (!cell.getStringCellValue().equals(cabeceraExcel.getNumero())) {
                                estado = false;
                            }
                            break;

                    }

                    break;
            }
        }

        System.out.println(estado);

        if (contador != 7) {
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

    public boolean validateFuncionario(InstructorDTO instructorDTO) {

        boolean estado = true;

        if (StringUtils.isNullOrEmpty(instructorDTO.getIdTipoDocumento())) {
            estado = false;
            System.out.println(estado);
        }

        System.out.println(StringUtils.isNullOrEmpty(instructorDTO.getNumDocumento()));
        
        if (StringUtils.isNullOrEmpty(instructorDTO.getNumDocumento())) {
            estado = false;
            System.out.println(estado);
        }

        if (StringUtils.isNullOrEmpty(instructorDTO.getNomFuncionario())) {
            estado = false;
            System.out.println(estado);
        }

        if (StringUtils.isNullOrEmpty(instructorDTO.getApeFuncionario())) {
            estado = false;
            System.out.println(estado);
        }

        if (StringUtils.isNullOrEmpty(instructorDTO.getCorreo())) {
            estado = false;
            System.out.println(estado);
        }

        if (StringUtils.isNullOrEmpty(instructorDTO.getCargo())) {
            estado = false;
            System.out.println(estado);
        }

        if (StringUtils.isNullOrEmpty(instructorDTO.getTelefono())) {
            estado = false;
            System.out.println(estado);
        }

        return estado;

    }

}
