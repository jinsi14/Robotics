package com.oldmutual.services.sheets;

import com.oldmutual.common.CommonUtils;
import com.oldmutual.model.PFRBABluePrism;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PFRBABluePrismSheetService {

    private static final Logger logger = (Logger) LogManager.getLogger(PFRBABluePrismSheetService.class);

    public List<PFRBABluePrism> getPFRBABluePrism(String filePath) throws IOException, InvalidFormatException {
        List<PFRBABluePrism> pfrbaBluePrismList = new ArrayList<>();
        File file = new File(filePath);
        try(Workbook workbook = new XSSFWorkbook(file)) {
            Sheet sheet = workbook.getSheetAt(0);
            logger.info("File reading process start!");
            int count = 0;
            for (Row row : sheet) {
                if(count == 0){
                    count++;
                    continue;
                }
                if (CommonUtils.isRowEmpty(row)) {
                    continue;
                }
                List<Cell> cells = new ArrayList<>();
                int lastColumn = sheet.getRow(0).getPhysicalNumberOfCells();
                for (int columnNumber = 0; columnNumber < lastColumn; columnNumber++) {
                    Cell cell = row.getCell(columnNumber, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(cell);
                }
                PFRBABluePrism pfrbaBluePrism = extractInfoFromCell(cells);
                pfrbaBluePrismList.add(pfrbaBluePrism);
            }
        }
        logger.info("File read successfully!");
        return pfrbaBluePrismList;
    }

    private PFRBABluePrism extractInfoFromCell(List<Cell> cellList) {
        PFRBABluePrism pfrbaObj = new PFRBABluePrism();

//        pfrbaObj.setTransactionDate(CommonUtils.getCellValue(cellList.get(0)));
//        pfrbaObj.setIntermediaryNumber(CommonUtils.getCellValue(cellList.get(1)));
//        pfrbaObj.setClientSurname(CommonUtils.getCellValue(cellList.get(2)));
//        pfrbaObj.setClientName(CommonUtils.getCellValue(cellList.get(3)));
//        pfrbaObj.setClientID(CommonUtils.getCellValue(cellList.get(4)));
//        pfrbaObj.setClientInitials(CommonUtils.getCellValue(cellList.get(5)));
//        pfrbaObj.setProductProvider(CommonUtils.getCellValue(cellList.get(6)));
//        pfrbaObj.setProductCode(CommonUtils.getCellValue(cellList.get(7)));
//        pfrbaObj.setContractNumber(CommonUtils.getCellValue(cellList.get(8)));
//        pfrbaObj.setBusinessException(CommonUtils.getCellValue(cellList.get(9)));

        //For loading from Athenkosi sample Dataset
        pfrbaObj.setTransactionDate(CommonUtils.getCellValue(cellList.get(0)));
        pfrbaObj.setIntermediaryNumber(CommonUtils.getCellValue(cellList.get(1)));
        pfrbaObj.setClientSurname(CommonUtils.getCellValue(cellList.get(2)));
        pfrbaObj.setClientName(CommonUtils.getCellValue(cellList.get(3)));
        pfrbaObj.setClientID(CommonUtils.getCellValue(cellList.get(4)));
        pfrbaObj.setClientInitials(CommonUtils.getCellValue(cellList.get(5)));
        pfrbaObj.setProductProvider(CommonUtils.getCellValue(cellList.get(6)));
        pfrbaObj.setProductCode(CommonUtils.getCellValue(cellList.get(7)));
        pfrbaObj.setContractNumber(CommonUtils.getCellValue(cellList.get(8)));
        pfrbaObj.setBusinessException(CommonUtils.getCellValue(cellList.get(9)));
        pfrbaObj.setIntermediaryChannel(CommonUtils.getCellValue(cellList.get(10)));



//        //For loading from Andrew sample Dataset
//        pfrbaObj.setTransactionDate(CommonUtils.getCellValue(cellList.get(1)));
//        pfrbaObj.setIntermediaryNumber(CommonUtils.getCellValue(cellList.get(2)));
//        pfrbaObj.setClientSurname(CommonUtils.getCellValue(cellList.get(3)));
//        pfrbaObj.setClientName(CommonUtils.getCellValue(cellList.get(4)));
//        pfrbaObj.setClientID(CommonUtils.getCellValue(cellList.get(5)));
//        pfrbaObj.setClientInitials(CommonUtils.getCellValue(cellList.get(6)));
//        pfrbaObj.setProductProvider(CommonUtils.getCellValue(cellList.get(7)));
//        pfrbaObj.setProductCode(CommonUtils.getCellValue(cellList.get(8)));
//        pfrbaObj.setContractNumber(CommonUtils.getCellValue(cellList.get(9)));
//        pfrbaObj.setIntermediaryChannel(CommonUtils.getCellValue(cellList.get(10)));
//        pfrbaObj.setBusinessException(CommonUtils.getCellValue(cellList.get(11)));

//        pfrbaObj.setTransactionDate(CommonUtils.getCellValue(cellList.get(0)));
//        pfrbaObj.setIntermediaryNumber(CommonUtils.getCellValue(cellList.get(1)));
//        pfrbaObj.setClientSurname(CommonUtils.getCellValue(cellList.get(2)));
//        pfrbaObj.setClientName(CommonUtils.getCellValue(cellList.get(3)));
//        pfrbaObj.setClientID(CommonUtils.getCellValue(cellList.get(4)));
//        pfrbaObj.setClientInitials(CommonUtils.getCellValue(cellList.get(5)));
//        pfrbaObj.setProductProvider(CommonUtils.getCellValue(cellList.get(6)));
//        pfrbaObj.setProductCode(CommonUtils.getCellValue(cellList.get(7)));
//        pfrbaObj.setContractNumber(CommonUtils.getCellValue(cellList.get(8)));
//        pfrbaObj.setIntermediaryChannel(CommonUtils.getCellValue(cellList.get(9)));
//        pfrbaObj.setBusinessException(CommonUtils.getCellValue(cellList.get(10)));

        return pfrbaObj;
    }
}
