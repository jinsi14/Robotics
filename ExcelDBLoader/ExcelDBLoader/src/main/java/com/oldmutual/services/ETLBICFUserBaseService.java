package com.oldmutual.services;

import com.oldmutual.dao.ETLBICFSheetDAO;
import com.oldmutual.dao.ETLBICFUserBaseDAO;
import com.oldmutual.model.ETLBICFINDWizardScenario;
import com.oldmutual.model.ETLBICFUserBase;
import com.oldmutual.services.sheets.ETLBICFSheetService;
import com.oldmutual.services.sheets.ETLBICFUserBaseServiceImpl;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ETLBICFUserBaseService {

    private ETLBICFUserBaseServiceImpl etlbicfUserBaseService = new ETLBICFUserBaseServiceImpl();
    private ETLBICFUserBaseDAO etlbicfUserBaseDAO = new ETLBICFUserBaseDAO();

    public void saveBICFUserBaseData(String filepath) throws IOException, InvalidFormatException, SQLException {
        List<ETLBICFUserBase> etlbicfUserBaseList = etlbicfUserBaseService.getBICFUserBaseList(filepath);
        etlbicfUserBaseDAO.save(etlbicfUserBaseList);
    }
}
