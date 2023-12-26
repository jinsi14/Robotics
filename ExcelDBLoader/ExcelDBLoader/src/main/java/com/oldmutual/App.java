package com.oldmutual;

import com.oldmutual.services.AdvisorReconService;
import com.oldmutual.services.ETLBICFService;
import com.oldmutual.services.ETLBICFUserBaseService;
import com.oldmutual.services.PFRBABluePrismService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.Scanner;

public class App {

    private static final Logger log = (Logger) LogManager.getLogger(App.class);

//    static {
//        PropertyConfigurator.configure("log4j.properties");
//    }

    private static final AdvisorReconService advisorReconService = new AdvisorReconService();
    private static final PFRBABluePrismService pfrbaBluePrismService = new PFRBABluePrismService();
    private static final ETLBICFService ETLBICF_SERVICE = new ETLBICFService();
    private static final ETLBICFUserBaseService ETLBICF_USER_BASE_SERVICE = new ETLBICFUserBaseService();

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        log.info("0: insertIntoBluePrismOutput, 1: insertIntoAdvisorReconReport, 2: insertIntoBiCfIndWizardScenarios 3: User Base Service");
        log.info("Enter method number: ");
        String methodName = sc.nextLine();

        log.info("Enter file path: ");
        String filePath = sc.nextLine();

        switch (methodName) {
            case "0":
                try {
                    pfrbaBluePrismService.savePFRBABluePrismServiceReport(filePath);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error(e.getMessage());
                }
                break;
            case "1":
                try {
                    advisorReconService.saveAdvisorReconReport(filePath);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error(e.getMessage());
                }
                break;
            case "2":
                try{
                    ETLBICF_SERVICE.saveBICFReport(filePath);
                } catch (Exception e){
                    e.printStackTrace();
                    log.error(e.getMessage());
                }
                break;
            case "3":
                try{
                    ETLBICF_USER_BASE_SERVICE.saveBICFUserBaseData(filePath);
                } catch (Exception e){
                    e.printStackTrace();
                    log.error(e.getMessage());
                }
                break;
            default:
                log.error("Select valid method");
                throw new Exception("Select valid method");
        }
        System.out.println("Execution Completed Successfully");

    }
}
