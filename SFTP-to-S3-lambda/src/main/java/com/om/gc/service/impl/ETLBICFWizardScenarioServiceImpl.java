package com.om.gc.service.impl;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.om.gc.dao.ETLBICFWizardScenarioDAO;
import com.om.gc.model.ETLBICFINDWizardScenario;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ETLBICFWizardScenarioServiceImpl {

    private ETLBICFWizardScenarioDAO ETLBICFWizardScenarioDAO = new ETLBICFWizardScenarioDAO();

    public void save(AmazonS3 s3Client, String s3KeyPrefix, String bucketName, String fileName, LambdaLogger logger){
        List<ETLBICFINDWizardScenario> etlbicfindWizardScenarioList = this.getBICFWizardScenarioListS3(s3Client,s3KeyPrefix,bucketName,fileName,logger);
        ETLBICFWizardScenarioDAO.save(etlbicfindWizardScenarioList,logger);

    }

    private List<ETLBICFINDWizardScenario> getBICFWizardScenarioListS3(AmazonS3 s3Client, String s3KeyPrefix, String bucketName, String fileName, LambdaLogger logger){
        List<ETLBICFINDWizardScenario> etlbicfindWizardScenarioList = new ArrayList<>();
        try {
            CSVFormat format = CSVFormat.DEFAULT.withDelimiter(',').withFirstRecordAsHeader();
            String key = s3KeyPrefix + fileName;
            S3Object object = s3Client.getObject(new GetObjectRequest(bucketName, key));
            InputStream inputStream = object.getObjectContent();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            CSVParser parser = new CSVParser(inputStreamReader, format);
            etlbicfindWizardScenarioList = getETLBCFindWizardScenarioList(parser.getRecords());
            inputStreamReader.close();
            inputStream.close();
        }
        catch (IOException ioException){
            logger.log("Exception generated while processing BI_CF_User_Base file::"+fileName+" Exception::"+ioException.getMessage());
            EmailSend.emailSendOnLambdafailure("Exception generated while processing BI_CF_User_Base file::"+fileName+" Exception::"+ioException.getMessage());
        }
        return etlbicfindWizardScenarioList;
    }

    private List<ETLBICFINDWizardScenario> getBICFListFromLocal(String filePath) {
        List<ETLBICFINDWizardScenario> etlbicfindWizardScenarios = new ArrayList<>();

        try{
            CSVFormat format = CSVFormat.DEFAULT.withDelimiter(',').withFirstRecordAsHeader();
            InputStream inputStream = new FileInputStream(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            CSVParser parser = new CSVParser(inputStreamReader, format);
            final List<CSVRecord> records = parser.getRecords();
            etlbicfindWizardScenarios = getETLBCFindWizardScenarioList(records);
        }
        catch(IOException ioException){
            EmailSend.emailSendOnLambdafailure("Exception :: "+ioException.getMessage());
            ioException.printStackTrace();
        }
        return etlbicfindWizardScenarios;
    }

    private static List<ETLBICFINDWizardScenario> getETLBCFindWizardScenarioList(List<CSVRecord> records) {
        List<ETLBICFINDWizardScenario> etlbicfindWizardScenarios = new ArrayList<>(records.size());
        for (CSVRecord csvRecord : records) {
            ETLBICFINDWizardScenario etlbicfindWizardScenario = new ETLBICFINDWizardScenario();
            etlbicfindWizardScenario.setClientEntityId(csvRecord.get(0));
            etlbicfindWizardScenario.setClientSurname(csvRecord.get(1));
            etlbicfindWizardScenario.setClientFirstName(csvRecord.get(2));
            etlbicfindWizardScenario.setClientInitials(csvRecord.get(3));
            etlbicfindWizardScenario.setClientDateOfBirth(csvRecord.get(4));
            etlbicfindWizardScenario.setClientAdviserXplanId(csvRecord.get(5));
            etlbicfindWizardScenario.setGdmClientSegment(csvRecord.get(6));
            etlbicfindWizardScenario.setGdmAdviceProcess(csvRecord.get(7));
            etlbicfindWizardScenario.setScenarioName(csvRecord.get(8));
            etlbicfindWizardScenario.setCreatedAt(csvRecord.get(9));
            etlbicfindWizardScenario.setUpdatedAt(csvRecord.get(10));
            etlbicfindWizardScenario.setScenarioId(csvRecord.get(11));
            etlbicfindWizardScenario.setCustomerPriorityDeath(csvRecord.get(12));
            etlbicfindWizardScenario.setCustomerPriorityDisability(csvRecord.get(13));
            etlbicfindWizardScenario.setCustomerPrioritySevereIllness(csvRecord.get(14));
            etlbicfindWizardScenario.setCustomerPriorityEmergencyFund(csvRecord.get(15));
            etlbicfindWizardScenario.setCustomerPriorityForRetirement(csvRecord.get(16));
            etlbicfindWizardScenario.setCustomerPrioritySavings(csvRecord.get(17));
            etlbicfindWizardScenario.setAdviserPriorityDeath(csvRecord.get(18));
            etlbicfindWizardScenario.setAdviserPriorityDisability(csvRecord.get(19));
            etlbicfindWizardScenario.setAdviserPrioritySevereIllness(csvRecord.get(20));
            etlbicfindWizardScenario.setAdviserPriorityEmergencyFund(csvRecord.get(21));
            etlbicfindWizardScenario.setAdviserPriorityForRetirement(csvRecord.get(22));
            etlbicfindWizardScenario.setAdviserPrioritySavings(csvRecord.get(23));
            etlbicfindWizardScenario.setBudgetForMyDailyExpenses(csvRecord.get(24));
            etlbicfindWizardScenario.setReduceMyShortTermDebt(csvRecord.get(25));
            etlbicfindWizardScenario.setBankAccountWithAffordableCharges(csvRecord.get(26));
            etlbicfindWizardScenario.setMaximiseMyTaxDeductionOptions(csvRecord.get(27));
            etlbicfindWizardScenario.setEstatePlan(csvRecord.get(28));
            etlbicfindWizardScenario.setProvideLovedOnesSufficientCapital(csvRecord.get(29));
            etlbicfindWizardScenario.setProvideSufficientCapital(csvRecord.get(30));
            etlbicfindWizardScenario.setEnsureThatIHaveFuneralPlans(csvRecord.get(31));
            etlbicfindWizardScenario.setProtectMinorChildrensInheritance(csvRecord.get(32));
            etlbicfindWizardScenario.setProtectMyIncomeIfIBeDisabled(csvRecord.get(33));
            etlbicfindWizardScenario.setProvideALumpSumIfIBeDisabled(csvRecord.get(34));
            etlbicfindWizardScenario.setProvideProtectionAgainstSevereIllness(csvRecord.get(35));
            etlbicfindWizardScenario.setProtectIncomeAgainstRetrenchment(csvRecord.get(36));
            etlbicfindWizardScenario.setProtectAbilityToGetCoverInTheFuture(csvRecord.get(37));
            etlbicfindWizardScenario.setProvideTravelInsurance(csvRecord.get(38));
            etlbicfindWizardScenario.setProtectIncomeMedicalExpenses(csvRecord.get(39));
            etlbicfindWizardScenario.setProtectIncomeSpecialistMedicalExpenses(csvRecord.get(40));
            etlbicfindWizardScenario.setProtectWhatIOwn(csvRecord.get(41));

            etlbicfindWizardScenario.setMakeMostOfMyRetirement(csvRecord.get(43));
            etlbicfindWizardScenario.setEducatingMyself(csvRecord.get(44));
            etlbicfindWizardScenario.setEducatingMyChildRen(csvRecord.get(45));
            etlbicfindWizardScenario.setEducatingSomeoneElse(csvRecord.get(46));
            etlbicfindWizardScenario.setCreateAnEmergencyFund(csvRecord.get(47));
            etlbicfindWizardScenario.setBuyAHome(csvRecord.get(48));
            etlbicfindWizardScenario.setTakingAHoliday(csvRecord.get(49));
            etlbicfindWizardScenario.setBuyACar(csvRecord.get(50));
            etlbicfindWizardScenario.setWhatDoYouWantToDo(csvRecord.get(51));
            etlbicfindWizardScenario.setaNewAdditionToTheFamily(csvRecord.get(52));
            etlbicfindWizardScenario.setStartingABusiness(csvRecord.get(53));
            etlbicfindWizardScenario.setAnythingElse(csvRecord.get(54));
            etlbicfindWizardScenario.setMyCapitalIsFrom(csvRecord.get(55));
            etlbicfindWizardScenario.setTakenAnyRetirementFunds(csvRecord.get(56));
            etlbicfindWizardScenario.setMinimumIncomeAmountYouNeed(csvRecord.get(57));
            etlbicfindWizardScenario.setNeedIncomeInvestmentTo(csvRecord.get(58));
            etlbicfindWizardScenario.setStillHaveSomeRetirementFunds(csvRecord.get(59));
            etlbicfindWizardScenario.setReconsiderWhereILive(csvRecord.get(60));
            etlbicfindWizardScenario.setConsideringPartTimeWork(csvRecord.get(61));
            etlbicfindWizardScenario.setReviewIncomeGeneratingProducts(csvRecord.get(62));
            etlbicfindWizardScenario.setCustomerPriorityIncome(csvRecord.get(63));
            etlbicfindWizardScenario.setBuyAndSellAgreements(csvRecord.get(64));
            etlbicfindWizardScenario.setLifeCoverProtectionForTheCost(csvRecord.get(65));
            etlbicfindWizardScenario.setClientRsaId(csvRecord.get(66));
            etlbicfindWizardScenario.setContingentLiability(csvRecord.get(67));
            etlbicfindWizardScenarios.add(etlbicfindWizardScenario);
        }
        return etlbicfindWizardScenarios;
    }
}

