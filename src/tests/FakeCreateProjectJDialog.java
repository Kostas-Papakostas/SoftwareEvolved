package tests;

import gui.dialogs.CreateProjectJDialog;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FakeCreateProjectJDialog extends CreateProjectJDialog {

    /**
     * Launch the application.
     *
     * @param projectName
     * @param datasetTxt
     * @param inputCsv
     * @param ass1
     * @param ass2
     * @param transXml
     */

    public FakeCreateProjectJDialog(String projectName, String datasetTxt, String inputCsv, String ass1, String ass2, String transXml) {

        super(projectName, datasetTxt, inputCsv, ass1, ass2, transXml);
    }

    public void writeInFile(String projectName, String datasetTxt, String inputCsv, String ass1, String ass2, String transXml){
        confirm=true;
        setVisible(false);

        String toWrite="Project-name:"+projectName+"\n";
        toWrite=toWrite+"Dataset-txt:"+datasetTxt+"\n";
        toWrite=toWrite+"Input-csv:"+inputCsv+"\n";
        toWrite=toWrite+"Assessement1-output:"+ass1+"\n";
        toWrite=toWrite+"Assessement2-output:"+ass2+"\n";
        toWrite=toWrite+"Transition-xml:"+transXml;

        System.out.println(toWrite);

        fileToCreate = new File("filesHandler/inis/"+textFieldProjectName.getText()+".ini");

        System.out.print(fileToCreate.getAbsolutePath());

        // if file doesnt exists, then create it
        if (!fileToCreate.exists()) {

            try {
                fileToCreate.createNewFile();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }

        FileWriter fw;
        BufferedWriter bw;
        try {
            fw = new FileWriter(fileToCreate.getAbsoluteFile());
            bw = new BufferedWriter(fw);
            bw.write(toWrite);
            bw.close();


        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
    }


}