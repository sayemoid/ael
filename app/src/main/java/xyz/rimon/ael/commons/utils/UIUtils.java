package xyz.rimon.ael.commons.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by SAyEM on 4/11/17.
 */

public class UIUtils {
    public static void showToast(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
