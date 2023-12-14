import java.io.*;

public class FileProcess {

    public static void readFile(StringBuffer buffer, String filePath){
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fileReader);
            String temp;
            while((temp=br.readLine())!=null){
                buffer.append(temp);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void writeFile(String str){
        try {
            File file = new File("right_output.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(str);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean clearFile() {

        try {
            File file = new File("right_output.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("");
            bw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }

}
