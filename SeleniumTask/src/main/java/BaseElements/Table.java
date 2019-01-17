package BaseElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Table extends BaseElement {
    public Table(String description, String xpath) {
        super(description, xpath);
    }

    public String[] getAllCellsTextInColumn(String columnName) {
        String[] columnText = {};
        int columnIndex = getColumnIndex(columnName);
        List<WebElement> cells = this.getChild("Columns [" + columnName + "] list of cells",
                "/tbody/tr/td[@role='gridcell' and contains(@class,'w-')][" + columnIndex + "]").loadElements();
        for (WebElement cell : cells) {
            columnText = StringUtils.addStringToArray(columnText, cell.getText());
        }
        return columnText;
    }

    public String[] getAllColumnNames() {
        String[] columnNames = {};
        List<WebElement> columns = this.getChild("Get all columns [" + this.controlDescription + "]",
                "//th[@role='columnheader' and not(contains(@id,'clone'))]").loadElements();
        for (WebElement column : columns) {
            columnNames = StringUtils.addStringToArray(columnNames, column.getText());
        }
        return columnNames;
    }

    public String getCellValue(String rowName, String columnName) {
        int colIndex = getColumnIndex(columnName);
        return this.getChild("Get Cell: column[" + columnName + "] and row[" + rowName + "] data",
                "//tbody/tr[contains(.,'" + rowName + "')]//td[" + colIndex + "]").getText();
    }

    public String[] getSortableColumnNames() {
        String[] sortableColumnNames = {};
        String[] columnNames = getAllColumnNames();
        for (String column : columnNames) {
            WebElement element = getDriver().findElement(By.cssSelector("th:contains('" + column +"') > span[class*=ui-sortable-column]"));
            if (element!=null) {
                sortableColumnNames = StringUtils.addStringToArray(columnNames, column);
            }
        }
        return sortableColumnNames;
    }

    public void sortByColumn(String columnName, String sortDirection) {
        String description = "Sort icon of column [" + columnName + "]";
        Button sortIcon = new Button(description, "//th[contains(.,'"+columnName+"')]");
        boolean condition1 = sortIcon.getElementAttribute("aria-label")
                .contains("acs") &
                sortDirection.equals("acs");
        boolean condition2 = sortIcon.getElementAttribute("aria-label")
                .contains("acs")
                & sortDirection.equals("des");
        String expectedIcon = sortDirection.equals("acs") ?
                "des" :"acs";

        if (condition1 || condition2) {
            sortIcon.click();
            description = "Check the sort icon in column [" + columnName + "] changes to the [" + sortDirection + "] icon";
            check(description, sortIcon.getElementAttribute("aria-label").contains(expectedIcon), true);
        }
    }

    public void shouldBeSortedByColumn(String columnName, String sortDirection) {
        String description = "Check column [" + columnName + "] is being sorted [" + sortDirection + "] in [" + controlDescription + "]";
        String[] columnText = getAllCellsTextInColumn(columnName);
        String[] textAfterSort = columnText;

        if (sortDirection.equals("Descending")) {
            Arrays.sort(textAfterSort, Collections.reverseOrder());

        } else {
            Arrays.sort(textAfterSort);
        }

        check(description, Arrays.toString(columnText), Arrays.toString(textAfterSort));
    }

    public void shouldHaveCellValue(String rowName, String columnName, String value) {
        String description = "Check cell has the value [" + value + "] in column [" + columnName + "] and row ["
                + rowName + "] of [" + controlDescription + "]";
        check(description, getCellValue(rowName, columnName), value);
    }

    public void shouldHaveTheSameValueInColumns(String columnName, String value) {
        String description = "Check all cells have the same value [" + value + "] in column [" + columnName + "] of [" + controlDescription + "]";
        String[] columnValues = getAllCellsTextInColumn(columnName);
        check(description, Arrays.stream(columnValues).distinct().count() == 1, true);
        check(description, Arrays.stream(columnValues).findFirst().equals(value), true);
    }

    public void shouldContainValuesInColumn(String columnName, String... values) {
        for (String value : values) {
            String description = "Check all cells contains value [" + value + "] in column [" + columnName + "] of [" + controlDescription + "]";
            String[] columnValues = getAllCellsTextInColumn(columnName);
            check(description, Arrays.stream(columnValues).filter(p -> p.contains(value)), true);
        }
    }

    public void shouldHaveColumns(String... expectedColumnNames) {
        String description = "Check [" + controlDescription + "] shows these columns: " + Arrays.toString(expectedColumnNames);
        String[] actualColumnNames = getAllColumnNames();
        Arrays.sort(actualColumnNames);
        Arrays.sort(expectedColumnNames);
        check(description, Arrays.toString(actualColumnNames), Arrays.toString(expectedColumnNames));
    }

    private int getColumnIndex(String columnName) {
        return StringUtils.getIndexOfArray(getAllColumnNames(), columnName) + 1;
    }

    public void shouldHaveRowCounts(String numberOfRow) {
        String description = "Check  number of row shows : " + numberOfRow;
        BaseElement listRow = new BaseElement("Table Count",
                "//div[contains(@class,'page-segment')]//li[contains(@class,'list-item listing')]//div[@class='listing-info']");

        check(description, Integer.parseInt(numberOfRow), listRow.loadElements().size());
    }

}
