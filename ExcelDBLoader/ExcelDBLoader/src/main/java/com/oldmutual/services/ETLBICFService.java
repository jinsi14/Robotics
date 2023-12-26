package com.oldmutual.services;

import com.oldmutual.dao.ETLBICFSheetDAO;
import com.oldmutual.model.ETLBICFINDWizardScenario;
import com.oldmutual.services.sheets.ETLBICFSheetService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ETLBICFService {
    private ETLBICFSheetService ETLBICFSheetService = new ETLBICFSheetService();

    private ETLBICFSheetDAO ETLBICFSheetDAO = new ETLBICFSheetDAO();

    public void saveBICFReport(String filepath) throws IOException, InvalidFormatException, SQLException {
//        List<ETLBICFINDWizardScenarios> bicfList = ETLBICFSheetService.getBICF(filepath);
        List<ETLBICFINDWizardScenario> bicfList = ETLBICFSheetService.getBICFListFromLocal(filepath);
        ETLBICFSheetDAO.save(bicfList);
    }
}
