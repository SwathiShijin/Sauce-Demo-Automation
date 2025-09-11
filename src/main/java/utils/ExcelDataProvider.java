package utils;

import org.testng.annotations.DataProvider;
import java.lang.reflect.Method;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class ExcelDataProvider {

    @DataProvider(name = "excelData")
    public static Object[][] getData(Method method) throws IOException {
        String className = method.getDeclaringClass().getSimpleName(); // sheet = class name
        String testCaseId = method.getName(); // get current test method name

        ExcelUtils excel = new ExcelUtils("src/test/resources/testdata.xlsx");

        int rows = excel.getRowCount(className);
        int cols = excel.getColumnCount(className);

        List<HashMap<String, String>> filteredRows = new ArrayList<>();

        for (int i = 1; i < rows; i++) {
            String rowTestCaseId = excel.getCellData(className, i, 0); // assuming first column = TestCaseID
            if (rowTestCaseId.equalsIgnoreCase(testCaseId)) {
                HashMap<String, String> rowMap = new HashMap<>();
                for (int j = 0; j < cols; j++) {
                    String key = excel.getCellData(className, 0, j); // header
                    String value = excel.getCellData(className, i, j);
                    rowMap.put(key, value);
                }
                filteredRows.add(rowMap);
            }
        }

        // Convert List to Object[][]
        Object[][] data = new Object[filteredRows.size()][1];
        for (int i = 0; i < filteredRows.size(); i++) {
            data[i][0] = filteredRows.get(i);
        }

        return data;
    }
}
