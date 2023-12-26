package com.oldmutual.services.sheets;

import com.oldmutual.common.CommonUtils;
import com.oldmutual.dao.PFRBABluePrismSheetDAO;
import com.oldmutual.model.AdvisorRecon;
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

public class AdvisorReconSheetService {

    private static final Logger logger = (Logger) LogManager.getLogger(AdvisorReconSheetService.class);

    public List<AdvisorRecon> getAdvisorRecon(String filePath) throws IOException, InvalidFormatException {
        List<AdvisorRecon> advisorReconList = new ArrayList<>();
        File file = new File(filePath);
        try (Workbook workbook = new XSSFWorkbook(file)) {
            logger.info("File reading process start!");
            Sheet sheet = workbook.getSheetAt(0);
            int lastColumn = sheet.getRow(0).getPhysicalNumberOfCells();
            int count = 0;
            for (Row row : sheet) {
                if (count == 0) {
                    count++;
                    continue;
                }
                if (CommonUtils.isRowEmpty(row)) {
                    continue;
                }
                List<Cell> cells = new ArrayList<>();

                for (int columnNumber = 0; columnNumber < lastColumn; columnNumber++) {
                    Cell cell = row.getCell(columnNumber, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(cell);
                }
                AdvisorRecon advisorRecon = extractInfoFromCell(cells);
                advisorReconList.add(advisorRecon);
            }
        }
        logger.info("File read successfully!");
        return advisorReconList;
    }

    private AdvisorRecon extractInfoFromCell(List<Cell> cellList) {
        AdvisorRecon advisorRecon = new AdvisorRecon();
        advisorRecon.setIdOrPassportNumber(CommonUtils.getCellValue(cellList.get(0)));
        advisorRecon.setInitials(CommonUtils.getCellValue(cellList.get(1)));
        advisorRecon.setFirstName(CommonUtils.getCellValue(cellList.get(2)));
        advisorRecon.setSurname(CommonUtils.getCellValue(cellList.get(3)));
        advisorRecon.setSalesCode(CommonUtils.getCellValue(cellList.get(4)));
        advisorRecon.setBusinessPartnerType(CommonUtils.getCellValue(cellList.get(5)));
        advisorRecon.setChannel(CommonUtils.getCellValue(cellList.get(6)));
        advisorRecon.setEmployeeNumber(CommonUtils.getCellValue(cellList.get(7)));
        advisorRecon.setIdType(CommonUtils.getCellValue(cellList.get(8)));
        advisorRecon.setAdvisorRegisteredOnFSCARegister(CommonUtils.getCellValue(cellList.get(9)));
        advisorRecon.setAdvisorApearsOnPlumblineExtract(CommonUtils.getCellValue(cellList.get(10)));
        advisorRecon.setCpdToFail(CommonUtils.getCellValue(cellList.get(11)));
        advisorRecon.setAdvisorOnRegisterWithNoCPD(CommonUtils.getCellValue(cellList.get(12)));
        advisorRecon.setAdvisorWithZeroCPDHours(CommonUtils.getCellValue(cellList.get(13)));
        advisorRecon.setAdvisorAppearsWorkday(CommonUtils.getCellValue(cellList.get(14)));
        advisorRecon.setProductCategory(CommonUtils.getCellValue(cellList.get(15)));
        advisorRecon.setProductCategoryFoundFSCA(CommonUtils.getCellValue(cellList.get(16)));
        advisorRecon.setProductCategoryFoundWorkday(CommonUtils.getCellValue(cellList.get(17)));
        advisorRecon.setAdvisorWithCOB(CommonUtils.getCellValue(cellList.get(18)));
        advisorRecon.setAdvisorWithRE(CommonUtils.getCellValue(cellList.get(19)));
        advisorRecon.setAdvisorWithQualifications(CommonUtils.getCellValue(cellList.get(20)));
        advisorRecon.setComments(CommonUtils.getCellValue(cellList.get(21)));
        return advisorRecon;
    }
}
