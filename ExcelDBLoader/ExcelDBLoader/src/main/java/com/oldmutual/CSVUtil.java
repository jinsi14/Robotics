package com.oldmutual;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CSVUtil {
    private static final String SAMPLE_CSV_FILE_PATH = "C:\\Users\\pk\\Desktop\\user.csv";

    public static void main(String[] args) throws IOException {
        readCSV();
    }

    private static void readCSV() {
        try  {
            CSVFormat format = CSVFormat.DEFAULT.withDelimiter(',').withFirstRecordAsHeader();
//            FileReader filereader = new FileReader(SAMPLE_CSV_FILE_PATH);
//            CSVParser parser = new CSVParser(filereader, format);
//            final List<CSVRecord> records = parser.getRecords();


            InputStream inputStream = new FileInputStream(SAMPLE_CSV_FILE_PATH);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            CSVParser parser = new CSVParser(inputStreamReader, format);
            final List<CSVRecord> records = parser.getRecords();

            for (CSVRecord csvRecord : records) {
                // Accessing Values by Column Index
                String name = csvRecord.get(0);
                String email = csvRecord.get(1);
                String phone = csvRecord.get(2);
                String country = csvRecord.get(3);


                System.out.println("Record No - " + csvRecord.getRecordNumber());
                System.out.println("---------------");
                System.out.println("Name : " + name);
                System.out.println("Email : " + email);
                System.out.println("Phone : " + phone);
                System.out.println("Country : " + country);
                System.out.println("---------------\n\n");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
