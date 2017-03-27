package android.firefly.coltfashion.cc.netcomm.xml;

import android.util.Xml;

import android.firefly.coltfashion.cc.netcomm.Loger;
import android.firefly.coltfashion.cc.netcomm.util.StringUtil;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public abstract class XmlFileHandleBase extends XmlHandleBase {

    static public final String TAG = "XmlFileHandleBase";

    private String mFilePath;

    private boolean mCreated;


    protected boolean createInstance(InputStream fileStream, boolean created) {

        mCreated = created;

        if (!mCreated) {

            return (loadFromFile(fileStream));

        } else {

            loadFromFile(fileStream);
        }

        return true;

    }

    protected boolean createInstance(String fileName, boolean created) {

        mCreated = created;

        if (!mCreated) {

            if (loadFromFile(fileName)) {
                mFilePath = fileName;
                return true;
            } else
                return false;

        } else {
            mFilePath = fileName;
            return loadFromFile(fileName);
        }


    }

    private boolean loadFromFile(String fileName) {

        File pathFile = new File(fileName);
        if (!pathFile.exists()) {
            return false;
        }

        FileInputStream fileInputStream = null;
        try {

            fileInputStream = new FileInputStream(pathFile);

            return loadFromFile(fileInputStream);

        } catch (Throwable ex) {

            Loger.error(getDescripe(TAG,"loadFromFile1"), ex);

            return false;

        } finally {

            try {
                if (fileInputStream != null)
                    fileInputStream.close();
            } catch (Throwable e) {

            }
        }

    }

    private boolean loadFromFile(InputStream fileStream) {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser reader = factory.newSAXParser();

            reader.parse(fileStream, this);

            return true;

        } catch (Throwable ex) {

            Loger.error(getDescripe(TAG,"loadFromFile2"), ex);

            return false;

        }

    }

    public void saveToFile() {

        saveToFile(mFilePath);

    }

    protected boolean currentPathEqual(String path) {

        if (StringUtil.isNullEmpty(getXmlRootTag())) {
            return mPathString.equalsIgnoreCase(path.trim());
        } else {

            String path1 = String.format("%s%s%s", SPLITXML, getXmlRootTag(), path);
            return mPathString.equalsIgnoreCase(path1.trim());
        }
    }

    protected boolean currentPathStartWith(String path) {

        if (StringUtil.isNullEmpty(path)) return false;

        String standPath =  mPathString.toLowerCase();

        if (StringUtil.isNullEmpty(getXmlRootTag())) {

            return standPath.startsWith(path.toLowerCase());

        } else {

            String path1 = String.format("%s%s%s", SPLITXML, getXmlRootTag(), path);
            return standPath.startsWith(path1.trim().toLowerCase());
        }
    }

    protected void saveToFile(String fileName) {

        XmlSerializer xmlSerializer = Xml.newSerializer();

        FileOutputStream fileOutputStream = null;

        try {

            fileOutputStream = new FileOutputStream(fileName);

            xmlSerializer.setOutput(fileOutputStream, XML_ENCODE);

            xmlSerializer.startDocument(XML_ENCODE, true);

            if (!StringUtil.isNullEmpty(getXmlRootTag())) {

                xmlSerializer.startTag(NODE_NAMESPACE, getXmlRootTag());
                saveToFile(xmlSerializer);
                xmlSerializer.endTag(NODE_NAMESPACE, getXmlRootTag());
            } else {
                saveToFile(xmlSerializer);
            }


            xmlSerializer.endDocument();
            fileOutputStream.getFD().sync();
            fileOutputStream.flush();

        } catch (Exception ex) {

            Loger.error(getDescripe(TAG,"saveToFile"), ex);

        } finally {

            if (fileOutputStream != null)
                try {
                    fileOutputStream.close();

                } catch (IOException e) {

                }

        }

    }

    protected abstract void saveToFile(XmlSerializer xmlSir)
            throws IllegalArgumentException, IllegalStateException, IOException;

    protected String getXmlRootTag() {
        return StringUtil.EMPTYSTR;
    }

    public String getFileName() {

        return mFilePath;

    }
}
