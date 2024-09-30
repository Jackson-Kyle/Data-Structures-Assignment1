import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Queue;

public class reader
{


    public static void main(String[] args)
    {
        String line = "";
        String splitBy = ",";
        queue myQueue = new queue();
        stack myStack = new stack();
        int errorCount = 0;
        int warningCount = 0;
        int infoCount = 0;
        int memoryWarningCount = 0;
        String recentErrors[] = new String[100];

        try
        {
//parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("src\\log-data.csv"));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                 String[] row = line.split(splitBy);
                 for(String index : row){
                    myQueue.enqueue(index);
                 }
            }
            myQueue.dequeue();
            while (!myQueue.isEmpty()){
                String check = myQueue.dequeue();
                if(check.charAt(22) == 'E'){
                    errorCount++;
                    myStack.push(check.substring(28, check.length()));
                }
                else if(check.charAt(22) == 'W'){
                    warningCount++;
                   String memoryCheck = myQueue.dequeue();
                   if(check.charAt(27) == 'M'){
                       memoryWarningCount++;
                   }
                }
                else if(check.charAt(22) == 'I'){
                    infoCount++;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println("Error Count:" + errorCount);
        System.out.println("Warning Count:" + warningCount);
        System.out.println("Info Count:" + infoCount);
        System.out.println("Memory Warnings:" + memoryWarningCount);
        for(int i = 0; i < recentErrors.length; i++){
            recentErrors[i] = myStack.pop();
        }
    }
}
