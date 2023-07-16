package TrashClass;

import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class TrashRecorder {
    String filePath = "output.csv";
    public void clear(){
        File file=new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }
    public void write(String[] strings){
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(filePath,true));
            writer.writeNext(strings,true);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

