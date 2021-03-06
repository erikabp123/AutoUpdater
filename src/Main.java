import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Main {

    public static boolean JAVA_INSTALLED = false;
    public static boolean DEFAULT_SLEEP_LENGTH;

    public static void main(String[] args) throws IOException {
        int sleepTime = Integer.parseInt(args[0]);
        if(args.length > 1) JAVA_INSTALLED = Boolean.parseBoolean(args[1]);

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
        File file = new File(curPath);
        if(file.exists()){
            Files.move(Paths.get(curPath), Paths.get(newPath), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private static void moveManagerJar() throws IOException {
        moveFile("downloads/ClassicAddonManager.jar", "system/ClassicAddonManager.jar");
        moveFile("downloads/CHANGELOG.txt", "system/CHANGELOG.txt");
    }

    private static void moveExe() throws IOException {
        moveFile("downloads/Classic Addon Manager.exe", "Classic Addon Manager.exe");
    }

    private static void restartManager(){
        try {
            if(JAVA_INSTALLED){
                Runtime.getRuntime().exec("cmd /c \"java -jar system\\ClassicAddonManager.jar updated\"");
            } else {
                Runtime.getRuntime().exec("cmd /c \"system\\jdk-12.0.2\\bin\\javaw.exe -jar system\\ClassicAddonManager.jar updated\"");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
