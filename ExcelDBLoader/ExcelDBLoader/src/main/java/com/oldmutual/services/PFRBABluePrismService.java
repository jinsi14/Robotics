package com.oldmutual.services;

import com.oldmutual.dao.PFRBABluePrismSheetDAO;
import com.oldmutual.model.PFRBABluePrism;
import com.oldmutual.services.sheets.PFRBABluePrismSheetService;

import java.util.List;

public class PFRBABluePrismService {
    PFRBABluePrismSheetService pfrbaBluePrismSheetService = new PFRBABluePrismSheetService();

    PFRBABluePrismSheetDAO pfrbaBluePrismServiceDAO = new PFRBABluePrismSheetDAO();

    public void savePFRBABluePrismServiceReport(String filePath) throws Exception {
        List<PFRBABluePrism> pfrbaBluePrismList = pfrbaBluePrismSheetService.getPFRBABluePrism(filePath);
        pfrbaBluePrismServiceDAO.save(pfrbaBluePrismList);
    }
}
