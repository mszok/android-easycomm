package android.firefly.coltfashion.cc.netcomm.util;

import android.firefly.coltfashion.cc.netcomm.Loger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileDirectoryUtil {

    static public final String TAG = "FileDirectoryUtil";

    static public boolean filePathExist(String file) {

        File f = new File(file);
        return f.exists();

    }

    static public boolean createFile(String file, boolean createDir,
                                     boolean createNew) {

        File f = new File(file);
        if (!f.exists()) {

            File parentFile = f.getParentFile();
            if (!parentFile.exists()) {
                if (createDir) {

                    if (!parentFile.mkdirs())
                        return false;

                } else {
                    return false;
                }

            }
            try {
                return f.createNewFile();
            } catch (Exception e) {
                return false;
            }

        } else {

            if (!createNew)
                return true;

            f.delete();

            try {
                return f.createNewFile();
            } catch (IOException e) {

                return false;
            }
        }
    }

    static public boolean createDir(String dir) {

        File f = new File(dir);
        if (!f.exists()) {
            return f.mkdirs();
        }

        return true;
    }

    static public boolean reNameFile(String sourceFilePath, String destFilePath) {
        File f = new File(sourceFilePath);
        File destFile = new File(destFilePath);
        return f.renameTo(destFile);
    }

    static public boolean deleteFile(String fileName) {

        File file = new File(fileName);

        if (file.isFile() && file.exists()) {

            return file.delete();

        } else {

            return false;
        }
    }

    static public boolean clearDirectory(String dir) {

        File dirFile = new File(dir);

        if (!dirFile.exists() || !dirFile.isDirectory()) {

            return false;
        }

        boolean flag = true;

        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {

            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            } else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }

        return flag;

    }

    static public boolean deleteDirectory(String dir) {

        boolean flag = clearDirectory(dir);
        if (flag) {
            File dirFile = new File(dir);
            return dirFile.delete();
        }

        return false;

    }

    static public void copyDirectory(String sourceDir, String destDir,
                                     boolean overWrite) {

        File sourceDirFile = new File(sourceDir);

        if (!sourceDirFile.exists()) {
            return;
        }

        createDir(destDir);

        File[] files = sourceDirFile.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {

                String str1 = files[i].getAbsolutePath();
                String str2 = String.format("%s/%s", destDir,
                        files[i].getName());

                copyDirectory(str1, str2, overWrite);

            } else if (files[i].isFile()) {

                File f = new File(destDir, files[i].getName());

                if (f.exists()) {
                    if (overWrite) {

                        f.delete();
                        reNameFile(files[i].getPath(), f.getPath());

                    }
                } else {
                    reNameFile(files[i].getPath(), f.getPath());

                }

            }
        }

    }

    static public void copyDirectory2(String sourceDir, String destDir,
                                      boolean overWrite) {

        File sourceDirFile = new File(sourceDir);

        if (!sourceDirFile.exists()) {
            return;
        }

        File destDirFile = new File(destDir);
        if (!destDirFile.exists()) {
            destDirFile.mkdirs();
        }

        File[] files = sourceDirFile.listFiles();

        for (int i = 0; i < files.length; i++) {

            if (files[i].isDirectory()) {

                String str1 = files[i].getAbsolutePath();
                String str2 = String.format("%s/%s", destDir,
                        files[i].getName());

                copyDirectory2(str1, str2, overWrite);

            } else if (files[i].isFile()) {

                File f = new File(destDir, files[i].getName());

                copyFile(files[i].getAbsolutePath(), f.getAbsolutePath(),
                        overWrite);

            }
        }

    }

    static public boolean copyFile(String oldPath, String newPath,
                                   boolean overWrite) {

        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        File tmpDestFile = null;
        try {
            int byteread = 0;

            File oldfile = new File(oldPath);
            if (!oldfile.exists()) {

                return false;
            }

            File newfile = new File(newPath);
            if (newfile.exists() && (!overWrite)) {

                return true;
            }

            File newParentFile = newfile.getParentFile();
            if (!newParentFile.exists()) {
                newParentFile.mkdirs();
            }
            tmpDestFile = new File(newParentFile, String.format("%s.tmp", UUID
                    .randomUUID().toString()));

            inStream = new FileInputStream(oldfile); // 读入原文件

            outStream = new FileOutputStream(tmpDestFile);

            byte[] buffer = new byte[4 * 1024];

            while ((byteread = inStream.read(buffer)) != -1) {

                outStream.write(buffer, 0, byteread);
            }

            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {

                }
            }

            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {

                }
            }

            newfile.delete();
            tmpDestFile.renameTo(newfile);
            return true;

        } catch (Exception ex) {

            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {

                }
            }

            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {

                }
            }

            if (tmpDestFile != null)
                tmpDestFile.delete();

            Loger.error(ex);

            return false;

        }

    }

    static public String getExtFileName(String fileName) {

        if (!StringUtil.isNullEmpty(fileName)) {
            int index = fileName.lastIndexOf(".");
            if ((index >= 0) && (index + 1 < fileName.length()))
                return fileName.substring(index);
        }
        return null;
    }


    static public String getFileName(String filePath) {

        File file = new File(filePath);
        String fileName = file.getName();
        if (!StringUtil.isNullEmpty(fileName)) {

            int index = fileName.lastIndexOf(".");

            return index < 0 ? fileName : fileName.substring(0, index);
        }
        return null;
    }

    static public String changeFileName(String absPath, String fileName) {


        File file = new File(absPath);
        if (file.isAbsolute()) {
            File file1 = new File(file.getParentFile(), fileName);
            return file1.getAbsolutePath();
        } else {
            return null;
        }


    }

    static public void createParentDir(String filePath) {

        File f = new File(filePath);
        if ((f.isFile()) && (!f.exists())) {

            File f1 = f.getParentFile();

            if (f1.isDirectory() && (!f1.exists())) {

                f1.mkdirs();
            }
        }

    }

    static public String removePathString(String path, String removeString) {

        int index = path.indexOf(removeString);
        if (index >= 0)
            return path.substring(index + removeString.length());
        else
            return path;

    }


    static public void writeTextFile(String filePath, List<String> fileContent) {


        createParentDir(filePath);

        OutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {

            out = new FileOutputStream(filePath);
            osw = new OutputStreamWriter(out, StringUtil.defaultCharset());
            bw = new BufferedWriter(osw);

            for (int i = 0; i < fileContent.size(); i++) {

                String line = fileContent.get(i);
                if (i > 0) bw.newLine();
                bw.write(line);
            }

            bw.flush();

        } catch (Throwable ex) {

            Loger.error(CommonUtil.getMethodDescripe(TAG, "writeTextFile"), ex);

        } finally {

            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {

                }

            }

            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {

                }
            }


            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {

                }
            }

        }

    }

    static public List<String> readTextFile(String filePath) {

        if (!FileDirectoryUtil.filePathExist(filePath)) return null;

        InputStream in = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {

            List<String> resultStrings = new ArrayList<String>();

            in = new FileInputStream(filePath);

            isr = new InputStreamReader(in, StringUtil.defaultCharset());
            br = new BufferedReader(isr);

            String str = null;
            while ((str = br.readLine()) != null) {

                resultStrings.add(str);

            }

            return resultStrings;

        } catch (Throwable ex) {

            Loger.error(CommonUtil.getMethodDescripe(TAG, "readTextFile"), ex);

            return null;

        } finally {

            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {

                }
            }

            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {

                }
            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {

                }
            }

        }

    }
}
