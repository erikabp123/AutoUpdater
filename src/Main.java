import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Main {

    public static void main(String[] args) throws IOException {
        int sleepTime = Integer.parseInt(args[0]);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        moveManagerJar();
        moveExe();

        restartManager();
    }

    private static void moveFile(String curPath, String newPath) throws IOException {
        File managerJar = new File(curPath);
        if(managerJar.exists()){
            Files.move(Paths.get(curPath), Paths.get(newPath), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private static void moveManagerJar() throws IOException {
        moveFile("downloads/ClassicAddonManager.jar", "system/ClassicAddonManager.jar");
    }

    private static void moveExe() throws IOException {
        moveFile("downloads/Classic Addon Manager.exe", "Classic Addon Manager.exe");
    }

    private static void restartManager(){
        try {
            Runtime.getRuntime().exec("cmd /c \"system\\jdk-12.0.2\\bin\\javaw.exe -jar system\\ClassicAddonManager.jar\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
