package com.sannong.infrastructure.export;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

import com.sannong.domain.applications.Questionnaire;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.sannong.domain.applications.Application;
/**
 * Created by Vidor Chan on 10/30/14.
 * Modified by William Zhang on 11/14/14
 */
@Component
public class CsvExporter {

	private static final Logger logger = Logger.getLogger(CsvExporter.class);
	
	public static String export(List<Application> applications, int questionSum) throws Exception {

        logger.info("start to call export in CsvExporter.java");

        BufferedWriter csvFileOutputStream = null;
        String currentPath = CsvExporter.class.getResource("/").getPath().replaceAll("/WEB-INF/classes/", "").replaceAll("%20", " ");
        String fileName = "answers" + System.currentTimeMillis();
        String filePath = currentPath + "/csvfiles/" + fileName + ".csv";
        String returnFilePath = "/sannong/csvfiles/" + fileName + ".csv";

        logger.info("restore csv file path: " + filePath);
        logger.info("return file path: " + returnFilePath);

        File csvFile = new File(filePath);
        File parent = csvFile.getParentFile();

        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        csvFile.createNewFile();

        csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(csvFile), "GB2312"), 1024);

        //edit title
        StringBuffer header = new StringBuffer();

        for (int number = 1; number <= questionSum; number++) {
            header.append("问题" + number + ",");
        }
        String title = "编号,姓名,手机号码,工作单位," + header.toString();
        csvFileOutputStream.write(title);
        csvFileOutputStream.newLine();

        //edit contents
        int i = 1;

        for (Application applicationRow : applications) {
            String Baseinfo = i + "," + applicationRow.getUser().getRealName() + ","
                    + "=\"" + applicationRow.getUser().getMobilePhone() + "\"" + "," + applicationRow.getUser().getCompanyName() + ",";
            StringBuffer answerRow = new StringBuffer();
            String dataRow = "";

            if (applicationRow.getQuestionnaires().size() > 0) {
                List<Questionnaire> questionnaireList = applicationRow.getQuestionnaires();
                Iterator it = questionnaireList.iterator();
                while (it.hasNext()) {
                    Questionnaire questionnaire = (Questionnaire) it.next();
                    String questionnaireAnswer = questionnaire.getConcatenatedAnswers();
                    String[] answerArray = questionnaireAnswer.split(";");
                    buildAnswerRow(answerRow, answerArray);
                }
                String answerInfo = answerRow.toString().substring(0,
                        answerRow.toString().length() - 1);
                dataRow = Baseinfo + answerInfo;
            }
			csvFileOutputStream.write(dataRow);
			csvFileOutputStream.newLine();
			i++;
		}
		csvFileOutputStream.flush();
		csvFileOutputStream.close();
		
		logger.info("end to call export in CsvExporter.java");
		return returnFilePath;
	}

    private static void buildAnswerRow (StringBuffer answerRow, String[]answerArray){
            for (String answers : answerArray) {
                StringBuffer answerStringBuffer = new StringBuffer();
                String[] singleAnswerArray = answers.split(",");

                for (String answerStr : singleAnswerArray) {
                    answerStr = String.valueOf(answerStr
                            .charAt(answerStr.length() - 1));
                    answerStringBuffer.append(answerStr);
                }
                answerRow.append(answerStringBuffer.toString() + ",");
            }
        }
 }
