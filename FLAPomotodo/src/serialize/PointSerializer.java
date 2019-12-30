package serialize;

import setting.PointSetting;
import template.FileCheck;

public class PointSerializer extends FileCheck{

private static PointSerializer pointFile;
    
    static {
        PointSerializer.pointFile = null;
    }
    
    public static synchronized PointSerializer getInstance() {
        if (PointSerializer.pointFile == null) {
                    PointSerializer.pointFile = new PointSerializer();
                }

        return PointSerializer.pointFile;
    }
    
    public final PointSetting pointReadFile() {
    	PointSetting pointSett;
        if (((PointSetting)(fileRead("point.ser")) == null)) {
        	pointSett = new PointSetting();
            FileCheck.fileWrite("point.ser", pointSett);
        }
        pointSett =  (PointSetting)fileRead("point.ser");
        return pointSett;
    }
    
    public final void pointWriteFile(final PointSetting pointSett) {
        FileCheck.fileWrite("point.ser", pointSett);
    }
	
}
