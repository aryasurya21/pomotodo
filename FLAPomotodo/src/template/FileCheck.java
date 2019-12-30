package template;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class FileCheck {
	protected static Object fileRead(final String name) {
        FileInputStream in = null;
        ObjectInputStream objectInputStream = null;
        Object object = null;
        
        try {
            in = new FileInputStream(name);
            object = (objectInputStream = new ObjectInputStream(in)).readObject();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        catch (ClassNotFoundException ex2) {
            System.out.println("Class not found");
            ex2.printStackTrace();
            return null;
        }
        finally {
            try {
                if (in != null) {
                    ((FileInputStream)in).close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            }
            catch (IOException ex3) {
              //  in = (InputStream)ex3;
                ex3.printStackTrace();
            }
        }
        try {
            ((FileInputStream)in).close();
            objectInputStream.close();
        }
        catch (IOException ex4) {
            ex4.printStackTrace();
        }
        return object;
    }
	
	protected static void fileWrite(final String name, final Object obj) {
        FileOutputStream out = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            out = new FileOutputStream(name);
            (objectOutputStream = new ObjectOutputStream(out)).writeObject(obj);
            objectOutputStream.close();
            ((FileOutputStream)out).close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            try {
                if (out != null) {
                    ((FileOutputStream)out).close();
                }
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                return;
            }
            catch (IOException ex2) {
                ex2.printStackTrace();
                return;
            }
        }
        finally {
            try {
                if (out != null) {
                    ((FileOutputStream)out).close();
                }
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            }
            catch (IOException ex3) {
                ex3.printStackTrace();
            }
        }
        try {
            ((FileOutputStream)out).close();
            objectOutputStream.close();
        }
        catch (IOException ex4) {
            ex4.printStackTrace();
        }
    }
}


