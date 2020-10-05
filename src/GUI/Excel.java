/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 *
 * @author KMB1604
 */
public class Excel {
    public boolean ExportExcel(JTable tab) {
        boolean result = false;
        JFileChooser chooser = new JFileChooser();
        int i = chooser.showSaveDialog(chooser);
        if (i == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            WritableWorkbook workbook;
            try {
                workbook = Workbook.createWorkbook(new File(file + ".xls"));
                WritableSheet sheet1 = workbook.createSheet("Excel", 0);
                DefaultTableModel model = (DefaultTableModel) tab.getModel();
                for (int j = 0; j < model.getColumnCount(); j++) {
                    sheet1.addCell(new Label(j, 0, (String) tab.getColumnName(j)));
                }
                int rowBegin = 1;
                int colBegin = 0;

                for (int row = rowBegin, j = 0; row < model.getRowCount() + rowBegin; row++, j++) {
                    for (int col = colBegin, k = 0; col < model.getColumnCount() + colBegin; col++, k++) {
                        Object data = model.getValueAt(j, k);
                        sheet1.addCell(new Label(col, row, (String) data.toString()));
                    }
                }
                workbook.write();
                workbook.close();
            } catch (IOException e) {
                System.out.println("Error create file\n" + e.toString());
            } catch (RowsExceededException e) {
                System.out.println("Error write file\n" + e.toString());
            } catch (WriteException e) {
                System.out.println("Error write file\n" + e.toString());
            }
            result = true;
        }
        return result;
    }
    
    
    
}
