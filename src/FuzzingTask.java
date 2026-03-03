import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FuzzingTask implements Runnable{
    private final String url;

    private static final Object lock = new Object();

    public FuzzingTask(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        int status = MotorHttp.requestStatus(url);

        if(status != 404 && status != -1){
            synchronized (lock){
                String color = (status == 200) ? "\u001B[32m" : "\u001B[33m";
                String message = "[" + status + "] -> " + url;
                System.out.println(color + message + "\u001B[0m");

                reportTask(message);
            }
        }
    }

    private void reportTask(String text){
        try(FileWriter fw = new FileWriter("report.txt" , true);
            PrintWriter pw = new PrintWriter(fw)){
            pw.println(text);
        }catch (IOException e){
            //Si falla la escritura no detenemos el escaneo
        }
    }

}
