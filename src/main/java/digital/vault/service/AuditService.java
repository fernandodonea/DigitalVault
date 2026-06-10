package digital.vault.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class AuditService
{
    private static AuditService instance;
    private static final String file_path="audit.csv";

    private AuditService()
    {
        try{
            PrintWriter pw=new PrintWriter(new FileWriter(file_path,true));
        }
        catch (IOException e)
        {
            System.err.println("Could not init audit file");
        }
    }

    public static AuditService getInstance()
    {
        if(instance==null){
            instance=new AuditService();
        }
        return instance;
    }

    public void log(String actionName)
    {
        try(PrintWriter pw=new PrintWriter(new FileWriter(file_path, true)))
        {
            pw.println(actionName+" "+ LocalDateTime.now());

        }
        catch (IOException e)
        {
            System.err.println("Audit log failed: " + e.getMessage());
        }
    }

}
