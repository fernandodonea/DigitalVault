package digital.vault.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class AuditService
{
    private static AuditService instance;
    private static final String FILE_PATH="audit.csv";

    private AuditService()
    {
        File f=new File(FILE_PATH);
        if(!f.exists()){
            try(PrintWriter pw=new PrintWriter(new FileWriter(FILE_PATH))){
                pw.println("action_name,timestamp");
            }catch (IOException e){
                System.err.println("Could not init audit file: "+e.getMessage());
            }
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
        try(PrintWriter pw=new PrintWriter(new FileWriter(FILE_PATH, true)))
        {
            pw.println(actionName+", "+ LocalDateTime.now());

        }
        catch (IOException e)
        {
            System.err.println("Audit log failed: " + e.getMessage());
        }
    }

}
