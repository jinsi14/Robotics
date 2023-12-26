package com.oldmutual.services;

import com.oldmutual.dao.AdvisorReconSheetDAO;
import com.oldmutual.model.AdvisorRecon;
import com.oldmutual.services.sheets.AdvisorReconSheetService;

import java.util.List;

public class AdvisorReconService {

    AdvisorReconSheetService reconSheetService = new AdvisorReconSheetService();

    AdvisorReconSheetDAO advisorReconSheetDAO = new AdvisorReconSheetDAO();

    public void saveAdvisorReconReport(String filePath) throws Exception {
        List<AdvisorRecon> advisorReconList = reconSheetService.getAdvisorRecon(filePath);
        advisorReconSheetDAO.save(advisorReconList);
    }
}
