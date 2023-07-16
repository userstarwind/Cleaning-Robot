package StartFrameTool;

import java.io.IOException;

public class PythonRunner {
    public void run(){
        try {
            Runtime.getRuntime().exec("C:\\Users\\starwind\\anaconda3\\python.exe F:\\pythonProject\\turtletest.py");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
