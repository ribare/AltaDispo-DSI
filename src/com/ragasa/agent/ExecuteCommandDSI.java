package com.ragasa.agent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteCommandDSI {
    public ExecuteCommandDSI() {
        super();
    }

    public static void main(String[] args) {
        //SE BORRAN EL O LOS ARCHIVOS GENERADOS ANTERIORMENTE
        try{
            String command = "cmd /C del \"E:\\DSI\\MEP\\dsiMEP82\\Logging\\ThreadPoolStatusReport*.txt\"";
            Process process = Runtime.getRuntime().exec(command);
            String command1 = "cmd /C del \"E:\\DSI\\MEP\\dsiMEP82\\Logging\\DSIPRAG2_DSIAgentCommunicationServer_TraceLogging*.log\"";
            Process process1 = Runtime.getRuntime().exec(command1);
            String command2 = "cmd /C del \"E:\\DSI\\MEP\\dsiMEP82\\Logging\\DSIPRAG2_DSIAgentPrinting_TraceLogging*.log\"";
            Process process2 = Runtime.getRuntime().exec(command2);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        }catch(IOException ioe){
            System.out.print(ioe);
        }
        
        //GENERAR ARCHIVO SI ESTA ACTIVO AGENTE
        try{
            String command = "E:\\DSI\\MEP\\dsiMEP82\\DSICommandLineUtility.exe /agent=DSIAgentCommunicationServer /user=DSI /pass=DSI /logerror=true /action=OUTPUTTHREADSTATUS";
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        }catch(IOException ioe){
            System.out.print(ioe);
        }
    }
}
