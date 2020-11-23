package com.ragasa.agent;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindFile {
    public FindFile() {
        super();
    }

    public static void main(String[] args) {
        //SE BUSCA EL ARCHIVO CREADO POR EL COMANDO EN EL SERVIDOR PRIMARIO
        String path = "\\\\DSIPRAG2\\Logging";
        String files;
        String archivo;
        int value = 0;

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                files = listOfFile.getName();
                System.out.println(files);
                //
                archivo = files.substring(0, 22);
                if (!archivo.equals("ThreadPoolStatusReport")) {
                    value = value + 1;
                } else {
                    value = 0;
                }
                archivo = "";
            }
        }
        
        //NO ESTA ACTIVO AGENTE EN SERVIDOR 1 (DSIPRAG2)
        if (value > 0) {
            //VERIFICAR SI YA ESTA ACTIVO EL AGENTE EN SERVIDOR 2 (DSIPRAG)
            try{
                //SE BORRAN EL O LOS ARCHIVOS GENERADOS ANTERIORMENTE
                String command = "cmd /C del \"E:\\DSI\\MEP\\dsiMEP82\\Logging\\ThreadPoolStatusReport*.txt\"";
                Process process = Runtime.getRuntime().exec(command);
                String command1 = "cmd /C del \"E:\\DSI\\MEP\\dsiMEP82\\Logging\\DSIPRAG_DSIAgentCommunicationServer_TraceLogging*.log\"";
                Process process1 = Runtime.getRuntime().exec(command1);
                String command3 = "cmd /C del \"E:\\DSI\\MEP\\dsiMEP82\\Logging\\DSIPRAG_DSIAgentPrinting_TraceLogging*.log\"";
                Process process3 = Runtime.getRuntime().exec(command3);
                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line = null;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
                
                String command2 = "E:\\DSI\\MEP\\dsiMEP82\\DSICommandLineUtility.exe /agent=DSIAgentCommunicationServer /user=DSI /pass=DSI /logerror=true /action=OUTPUTTHREADSTATUS";
                Process process2 = Runtime.getRuntime().exec(command2);
                BufferedReader in2 = new BufferedReader(new InputStreamReader(process2.getInputStream()));
                String line2 = null;
                while ((line2 = in2.readLine()) != null) {
                    System.out.println(line2);
                }
            }catch(IOException ioe){
                System.out.print(ioe);
            }
            //BUSCA ARCHIVO GENERADO
            String pathLocal = "E:\\DSI\\MEP\\dsiMEP82\\Logging";
            String filesLocal;
            String archivoLocal;
            int value2 = 0;

            File folderLocal = new File(pathLocal);
            File[] listOfFilesLocal = folderLocal.listFiles();
            for (File listOfFile : listOfFilesLocal) {
                if (listOfFile.isFile()) {
                    filesLocal = listOfFile.getName();
                    System.out.println(filesLocal);
                    //
                    archivoLocal = filesLocal.substring(0, 22);
                    if (!archivoLocal.equals("ThreadPoolStatusReport")) {
                        value2 = value2 + 1;
                    } else {
                        value2 = 0;
                    }
                    archivoLocal = "";
                }
            }
            //NO ESTA ACTIVO AGENTE EN SERVIDOR 2
            if(value2 > 0){
                //ACTIVAR AGENTE DE COMUNICACION EN DSIPRAG SI NO ESTA ACTIVO
                try {
                    String command = "E:\\DSI\\MEP\\dsiMEP82\\DSICommandLineUtility.exe /action=StartAgent /agent=DSIAgentCommunicationServer /user=DSI /pass=DSI /logerror=true";
                    Process process = Runtime.getRuntime().exec(command);
                    String command2 = "E:\\DSI\\MEP\\dsiMEP82\\DSICommandLineUtility.exe /action=StartAgent /agent=DSIAgentPrinting /user=DSI /pass=DSI /logerror=true";
                    Process process2 = Runtime.getRuntime().exec(command2);
                    BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line = null;
                    while ((line = in.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException ioe) {
                    System.out.print(ioe);
                }
            }else{
                System.out.println("El agente de comunicación ya esta activo");
            }
            
        } else {
            System.out.println("El agente de comunicación esta activo en DSIPRAG2");
        }
    }
}
