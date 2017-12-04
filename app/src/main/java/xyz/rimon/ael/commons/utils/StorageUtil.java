package xyz.rimon.ael.commons.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import xyz.rimon.ael.domains.Event;

/**
 * Created by SAyEM on 3/11/17.
 */

public class StorageUtil {
    private StorageUtil() {
    }

    private static String uniqueID = UUID.randomUUID().toString();
    public static String FILE_NAME = "ael.events";
    public static String TEMP_FILE_NAME = "temp.events";

    public static void writeObjects(Context context, String fileName, List<Event> objectList) {
        // check permission
        if (!PermissionUtil.hasPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE))
            PermissionUtil.askForPermission((Activity) context, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        Log.i("EVENT_LIST_WRITE", objectList.size() + "");
        File rootPath = Environment.getExternalStorageDirectory();
        File objectDir = new File(rootPath.getAbsolutePath() + "/" + context.getPackageName() + "/ael");
        if (!objectDir.exists()) objectDir.mkdirs();
        File objectsFile = new File(objectDir, fileName);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(objectsFile);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(objectList);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) oos.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static List<Event> readObjects(Context context, String fileName) {
        // check permission
        if (!PermissionUtil.hasPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE))
            PermissionUtil.askForPermission((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE);

        List<Event> objectList = new ArrayList<>();
        File rootPath = Environment.getExternalStorageDirectory();
        File cmedDir = new File(rootPath.getAbsolutePath() + "/" + context.getPackageName() + "/ael");
        if (!cmedDir.exists())
            cmedDir.mkdirs();
        File objectsFile = new File(cmedDir, fileName);
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(objectsFile);
            ois = new ObjectInputStream(fis);
            objectList = (List<Event>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Log.e("CAN\'T READ OBJECTS ", e.toString());
        } finally {
            try {
                if (ois != null) ois.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Log.i("EVENT_LIST_READ", objectList.size() + "");
        return objectList;
    }

    public static void writeObject(Context context, String fileName, Event event) {
        List<Event> objectList = StorageUtil.readObjects(context, fileName);
        if (objectList == null)
            objectList = new ArrayList<>();
        objectList.add(event);
        StorageUtil.writeObjects(context, fileName, objectList);
        Log.i("OFFLINE_EVENTS", String.valueOf(objectList.size()));
    }

    public static void clearObjects(Context context, String fileName) {
        writeObjects(context, fileName, new ArrayList<Event>());
    }
}
