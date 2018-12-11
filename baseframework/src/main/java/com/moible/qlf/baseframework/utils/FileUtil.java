
package com.moible.qlf.baseframework.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * 文件工具类 lml
 *
 * @2015-3-3
 * @actionbartest
 */
@SuppressLint("NewApi")
public class FileUtil {

    public static final String DOC_TYPE_WORD = "docx";

    public static final String DOC_TYPE_DOC = "doc";

    public static final String DOC_TYPE_EXCEL = "xlsx";

    public static final String DOC_TYPE_XLS = "xls";

    public static final String DOC_TYPE_PNG = "png";

    public static final String DOC_TYPE_JPEG = "jpeg";

    public static final String DOC_TYPE_JPG = "jpg";

    public static final String DOC_TYPE_TXT = "txt";

    public static final String DOC_TYPE_XML = "xml";

    public static final String DOC_TYPE_PDF = "pdf";

    public static final String DOC_TYPE_AUDIO = "audio";

    public static final String DOC_TYPE_VIDEO = "video";

    public static final String DOC_TYPE_CHM = "chm";

    public static final String DOC_TYPE_PPT = "ppt";

    private static Context mContext;

    public FileUtil(Context mContext) {
        this.mContext = mContext;
    }

    public static byte[] getImage(final String urlStr) {
        byte[] data = null;


        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
        try {
            // 建立URL
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            InputStream input = conn.getInputStream();
            data = FileUtil.readInputStream(input);
            input.close();

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return data;
    }

    /**
     * 办公文件
     *
     * @param suffix
     * @return
     */
    public static boolean isOffice(String suffix) {
        if (FileUtil.DOC_TYPE_WORD.equals(suffix) || FileUtil.DOC_TYPE_DOC.equals(suffix)
                || FileUtil.DOC_TYPE_PDF.equals(suffix) || FileUtil.DOC_TYPE_XML.equals(suffix)
                || FileUtil.DOC_TYPE_TXT.equals(suffix) || FileUtil.DOC_TYPE_EXCEL.equals(suffix)
                || FileUtil.DOC_TYPE_XLS.equals(suffix)) {
            return true;
        }
        return false;
    }

    public static byte[] readInputStream(InputStream input) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = input.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return output.toByteArray();
    }

    /**
     * 文件生成数组
     *
     * @TODO 2015-5-29 byte[]
     */
    public static byte[] getBytes(File file) {
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * 根据byte数组，生成文件
     */
    public static void getFile(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 图片文件
     *
     * @param suffix
     * @return
     */
    public static boolean isImage(String suffix) {
        if (FileUtil.DOC_TYPE_PNG.equals(suffix) || FileUtil.DOC_TYPE_JPEG.equals(suffix)
                || FileUtil.DOC_TYPE_JPG.equals(suffix)) {
            return true;
        }
        return false;
    }

    /**
     * 生成文件
     *
     * @TODO 2015-5-26 File
     */
    public static File makeFilePath(String filePath, String fileName) {
        File file = null;
        try {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                file = new File(filePath + fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    // 向已创建的文件中写入数据
    public static void writeFile(String str, String filePath, String fileName) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {

            // 生成文件夹之后，再生成文件，不然会出错

            String strFilePath = filePath + fileName;
            fw = new FileWriter(strFilePath, true);//
            // 创建FileWriter对象，用来写入字符流
            bw = new BufferedWriter(fw); // 将缓冲对文件的输出
            bw.write(str); // 写入文件
            bw.newLine();
            bw.flush(); // 刷新该流的缓冲
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                bw.close();
                fw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    // 向已创建的文件中写入数据
    public static void writeFile(String str, String filePath) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            // 生成文件夹之后，再生成文件，不然会出错
            fw = new FileWriter(filePath, true);//
            // 创建FileWriter对象，用来写入字符流
            bw = new BufferedWriter(fw); // 将缓冲对文件的输出
            bw.write(str); // 写入文件
            bw.newLine();
            bw.flush(); // 刷新该流的缓冲
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                bw.close();
                fw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    /**
     * 删除文件
     *
     * @TODO 2015-5-26 void
     */
    public static void deleteFile(String filePath, String fileName) {

        try {
            String strFilePath = filePath + fileName;
            File f = new File(strFilePath); // 输入要删除的文件位置
            if (f.exists())
                f.delete();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * 读文件
     *
     * @TODO 2015-5-26 void
     */
    public static List<String> readFile(String filePath) {
        try {
            List<String> listStr = new ArrayList<String>();
            File file = new File(filePath);// Text文件
            BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
                listStr.add(s);
            }
            br.close();
            return listStr;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<String>();
        }
    }


    /**
     * 删除文件夹下的所有文件
     *
     * @param path
     */
    public static void deleteAllFile(File path) {
        File files[] = path.listFiles();
        if (files != null && files.length > 0)
            for (File f : files) {
                if (f.isDirectory()) { // 判断是否为文件夹
                    deleteAllFile(f);
                    try {
                        f.delete();
                    } catch (Exception e) {
                    }
                } else {
                    if (f.exists()) { // 判断是否存在
                        deleteAllFile(f);
                        try {
                            f.delete();
                        } catch (Exception e) {
                            LogUtil.e("异常", e.toString());
                        }
                    }
                }
            }
    }


    /**
     * @param suffix
     * @return
     */
    public static boolean isVideo(String suffix) {
        if (FileUtil.DOC_TYPE_AUDIO.equals(suffix) || FileUtil.DOC_TYPE_VIDEO.equals(suffix)) {
            return true;
        }
        return false;
    }

    /**
     * 获取文件类型
     *
     * @param url
     * @return
     */
    public static String getFileType(String url) {
        String suffix = "";
        if (!TextUtils.isEmpty(url)) {
            suffix = url.substring(url.lastIndexOf(".") + 1);
        }
        return suffix;
    }

    /**
     * 根据content://格式URL得到File对象
     *
     * @param context
     * @param url
     * @return
     */
    public static File getFileByUrl(Context context, String url) {
        if (!TextUtils.isEmpty(url)) {
            Uri uri = Uri.parse(url);
            String[] proj = {
                    MediaStore.Images.Media.DATA
            };
            Cursor actualimagecursor = ((Activity) context).managedQuery(uri, proj, null, null,
                    null);
            int actual_image_column_index = actualimagecursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            actualimagecursor.moveToFirst();
            String img_path = actualimagecursor.getString(actual_image_column_index);
            File file = new File(img_path);
            return file;
        }
        return null;
    }

    /**
     * 根据content://格式URL得到File名称
     *
     * @param context
     * @param url
     * @return
     */
    public static String getFileName(Context context, String url) {
        if (!TextUtils.isEmpty(url)) {
            Uri uri = Uri.parse(url);
            String[] proj = {
                    MediaStore.Images.Media.DISPLAY_NAME
            };
            Cursor actualimagecursor = ((Activity) context).managedQuery(uri, proj, null, null,
                    null);
            int actual_image_column_index = actualimagecursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);
            actualimagecursor.moveToFirst();
            String fileName = actualimagecursor.getString(actual_image_column_index);
            if (!TextUtils.isEmpty(fileName)) {
                return fileName;
            }
        }
        return null;
    }

    /**
     * 图片获得流
     *
     * @param bm
     * @return InputStream 2015-3-3 Bitmap2IS
     */
    public static InputStream Bitmap2IS(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(CompressFormat.JPEG, 100, baos);
        InputStream sbs = new ByteArrayInputStream(baos.toByteArray());
        try {
            LogUtil.d("FileUtil", "sbs = " + sbs.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sbs;
    }

    /**
     * url获得文件的绝对路径
     *
     * @param context
     * @param uri
     * @return String 2015-3-3 getPath
     */

    // public static String getPath(Context context, Uri uri) {
    //
    // if ("content".equalsIgnoreCase(uri.getScheme())) {
    // String[] projection = { "_data" };
    // Cursor cursor = null;
    //
    // try {
    // cursor = context.getContentResolver().query(uri, projection,
    // null, null, null);
    // int column_index = cursor.getColumnIndexOrThrow("_data");
    // if (cursor.moveToFirst()) {
    // return cursor.getString(column_index);
    // }
    // } catch (Exception e) {
    // }
    // }
    //
    // else if ("file".equalsIgnoreCase(uri.getScheme())) {
    // return uri.getPath();
    // }
    //
    // return null;
    // }

    /**
     * url获得文件的绝对路径
     *
     * @param context
     * @param uri
     * @return String 2015-3-3 getPath
     */
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider

        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {

            // ExternalStorageProvider

            if (isExternalStorageDocument(uri)) {

                final String docId = DocumentsContract.getDocumentId(uri);

                final String[] split = docId.split(":");

                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {

                    return Environment.getExternalStorageDirectory() + "/" + split[1];

                }

                // TODO handle non-primary volumes

            }

            // DownloadsProvider

            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);

                final Uri contentUri = ContentUris.withAppendedId(

                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);

            }

            // MediaProvider

            else if (isMediaDocument(uri)) {

                final String docId = DocumentsContract.getDocumentId(uri);

                final String[] split = docId.split(":");

                final String type = split[0];

                Uri contentUri = null;

                if ("image".equals(type)) {

                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

                } else if ("video".equals(type)) {

                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

                } else if ("audio".equals(type)) {

                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

                }

                final String selection = "_id=?";

                final String[] selectionArgs = new String[]{

                        split[1]

                };

                return getDataColumn(context, contentUri, selection, selectionArgs);

            }

        }

        // MediaStore (and general)

        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            return getDataColumn(context, uri, null, null);

        }

        // File

        else if ("file".equalsIgnoreCase(uri.getScheme())) {

            return uri.getPath();

        }

        return null;

    }

    public static String getDataColumn(Context context, Uri uri, String selection,

                                       String[] selectionArgs) {

        Cursor cursor = null;

        final String column = "_data";

        final String[] projection = {

                column

        };

        try {

            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,

                    null);

            if (cursor != null && cursor.moveToFirst()) {

                final int column_index = cursor.getColumnIndexOrThrow(column);

                return cursor.getString(column_index);

            }

        } finally {

            if (cursor != null)

                cursor.close();

        }

        return null;

    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */

    public static boolean isExternalStorageDocument(Uri uri) {

        return "com.android.externalstorage.documents".equals(uri.getAuthority());

    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */

    public static boolean isDownloadsDocument(Uri uri) {

        return "com.android.providers.downloads.documents".equals(uri.getAuthority());

    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */

    public static boolean isMediaDocument(Uri uri) {

        return "com.android.providers.media.documents".equals(uri.getAuthority());

    }

    /**
     * 创建文件夹
     *
     * @TODO 2015-5-26 void
     */
    public static String createSDCardDir(String fileName) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            // 创建一个文件夹对象，赋值为外部存储器的目录
            File sdcardDir = Environment.getExternalStorageDirectory();
            // 得到一个路径，内容是sdcard的文件夹路径和名字
            String path = sdcardDir.getPath() + "/" + fileName;
            File path1 = new File(path);
            if (!path1.exists()) {
                // 若不存在，创建目录，可以在应用启动的时候创建
                path1.mkdirs();
            }
            return path1.getAbsolutePath();
        } else {
            ToastUtils.toastShort(mContext, "sdcard不存在");
            return "";

        }

    }

    /**
     * @param data     数据
     * @param path     路径
     * @param fileName 文件名
     * @return true成功 false失败
     */
    public static boolean writeToSdcard(byte[] data, String path, String fileName) {
        boolean isSuccess = false;
        FileOutputStream fos = null;
        try {
            // 判断有没有sdCard
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {

                File filePath = new File(path);
                if (!filePath.exists()) {
                    // 创建文件夹
                    filePath.mkdirs();
                }

                // 判断有没有同名的文件
                File file = new File(path + fileName);
                // 有的话，删除
                if (file.exists()) {
                    file.delete();
                }
                // 写文件
                fos = new FileOutputStream(file);
                fos.write(data);
                fos.flush();
                return true;
            }

        } catch (Exception e) {
            return false;
            // TODO: handle exception
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * -------------------------------------------保存照片
     */

    public static String saveFile(Context c, String fileName, Bitmap bitmap) {
        return saveFile(c, "", fileName, bitmap);
    }

    public static String saveFile(Context c, String filePath, String fileName, Bitmap bitmap) {
        byte[] bytes = bitmapToBytes(bitmap);
        return saveFile(c, filePath, fileName, bytes);
    }

    public static byte[] bitmapToBytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    public static String saveFile(Context c, String filePath, String fileName, byte[] bytes) {
        String fileFullName = "";
        FileOutputStream fos = null;
        String dateFolder = new SimpleDateFormat("yyyyMMdd", Locale.CHINA).format(new Date());
        try {
            String suffix = "";
            if (filePath == null || filePath.trim().length() == 0) {
                filePath = Environment.getExternalStorageDirectory() + "/JiaXT/" + dateFolder + "/";
            }
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            File fullFile = new File(filePath, fileName + suffix);
            fileFullName = fullFile.getPath();
            fos = new FileOutputStream(new File(filePath, fileName + suffix));
            fos.write(bytes);
        } catch (Exception e) {
            fileFullName = "";
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    fileFullName = "";
                }
            }
        }
        return fileFullName;
    }


    /**
     * 获取文件名称
     *
     * @return
     */
    public static String getFileName(String suffix) {
        String dateString = DateUtil.getDateString(new Date(), "yyyyMMddHHmmssms");
        return dateString + "." + suffix;
    }

    /**
     * 从Assets中读取图片
     */
    public static ImageView getImageFromAssetsFile(Context context, String fileName) {
        ImageView image = null;

        AssetManager am = context.getResources().getAssets();
        try {
            InputStream is = am.open(fileName);
            image = new ImageView(context);
            image.setImageBitmap(BitmapFactory.decodeStream(is));
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;

    }

    /**
     * 从Assets中读取图片
     */
    public static Bitmap getImageFromAssetsBitmap(Context context, String fileName) {
        Bitmap image = null;

        AssetManager am = context.getResources().getAssets();
        try {

            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;

    }

    /**
     * 从Assets中获取图片的类型
     **/
    public static String getImageType(Context context) {
        AssetManager am = context.getResources().getAssets();
        try {
            String[] assetsStr = am.list("loadad");//   获取/assets目录下所有文件
            return assetsStr[0].substring(assetsStr[0].indexOf("."), assetsStr[0].length());
        } catch (IOException e) {
            return ".png";
        }
    }
}
