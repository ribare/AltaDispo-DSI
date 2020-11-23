package com.ragasa.agent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StartAgentDSI {
    public StartAgentDSI() {
        super();
    }
    
    public static void main(String args[]) {
        
        try{
            String command = "E:\\DSI\\MEP\\dsiMEP82\\DSICommandLineUtility.exe /action=StartAgent /agent=DSIAgentCommunicationServer /user=DSI /pass=DSI /logerror=true";
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
