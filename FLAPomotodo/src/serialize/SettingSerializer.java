package serialize;

import setting.TimeSetting;
import template.FileCheck;

public class SettingSerializer extends FileCheck {

	private static SettingSerializer settFile;
    
    static {
        SettingSerializer.settFile = null;
    }
    
 
    public static SettingSerializer getInstance() {
        if (SettingSerializer.settFile == null) {
            synchronized (SettingSerializer.class) {
                if (SettingSerializer.settFile == null) {
                    SettingSerializer.settFile = new SettingSerializer();
                }
            }
        }
        return SettingSerializer.settFile;
    }
    
    public final TimeSetting settReadFile() {
        TimeSetting timeSett;
        if ((fileRead("setting.ser") == null)) {
            timeSett = new TimeSetting();
           fileWrite("setting.ser", timeSett);
        }
        timeSett = (TimeSetting) (fileRead("setting.ser"));
        return timeSett;
    }
    
    public final void settWriteFile(final TimeSetting timeSett) {
      fileWrite("setting.ser", timeSett);
    }
	
}
