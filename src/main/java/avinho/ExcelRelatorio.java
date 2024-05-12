package avinho;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;

public class ExcelRelatorio {
    private static final DataFormatter formatter = new DataFormatter();
    private static final Pattern pattern = Pattern.compile("[^a-zA-Z]");

    public static void readExcel(String fileName) {
        try(FileInputStream file = new FileInputStream(fileName)) {
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Map<String, List<Row>> map = processSheet(sheet);

            map.forEach(ExcelRelatorio::processRows);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Arquivo nao encontrado");
            System.exit(0);
        }
    }

    private static void processRows(String corretor, List<Row> rows) {
        Map<String, Seguradora> seguradoras = new HashMap<>();
        DecimalFormat df = new DecimalFormat("##,##0.00#");
        double total = 0;
        int totalLancamentos = 0;

        for (Row row : rows) {
            Cell valor = row.getCell(11);
            Cell cia = row.getCell(12);
            total += valor.getNumericCellValue();
            String nome = formatter.formatCellValue(cia);
            Seguradora seguradora;

            if (seguradoras.containsKey(nome)){
                seguradora = seguradoras.get(nome);
            } else {
                seguradora = new Seguradora();
                seguradora.setNome(nome);
                seguradoras.put(nome, seguradora);
            }

            seguradora.addSaldo(valor.getNumericCellValue());
            seguradora.addQuantidade();
            totalLancamentos++;
        }
        System.out.println("---------------------- " + corretor + " ----------------------");
        for (Seguradora seguradora : seguradoras.values()) {
            System.out.println("Seguradora: " + seguradora.getNome() + " - " + "Saldo: " + df.format(seguradora.getSaldo()) + " - " + "Qtd: " + seguradora.getQuantidade());
        }
        System.out.println("------------------------------------------------");
        System.out.println("Total de lançamentos: " + totalLancamentos + " - " + "Comissão: R$ " + df.format(total));
        System.out.println("------------------------------------------------");
    }

    private static Map<String, List<Row>> processSheet(HSSFSheet sheet) {
        Map<String, List<Row>> dataMap = new HashMap<>();
        List<Row> lastRows = new ArrayList<>();
        int startRow = 2;
        int endRow;

        for (Row row : sheet) {
            if (row.getPhysicalNumberOfCells() == 1) {
                lastRows.add(row);
            }
        }

        for (Row lastRow : lastRows) {
            endRow = lastRow.getRowNum();
            Row nextRow = sheet.getRow(endRow + 1);
            var num = nextRow.getLastCellNum() - 1;
            Cell cell = nextRow.getCell(num);
            String corretor = pattern.matcher(cell.getStringCellValue()).replaceAll("");
            List<Row> rows = new ArrayList<>();
            for (int j = startRow; j < endRow; j++) {
                rows.add(sheet.getRow(j));
            }
            dataMap.put(corretor, rows);
            startRow = endRow + 3;
        }
        return dataMap;
    }
}