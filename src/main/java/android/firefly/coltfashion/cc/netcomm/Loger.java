package android.firefly.coltfashion.cc.netcomm;

import android.util.Log;

import android.firefly.coltfashion.cc.netcomm.base.FireflyObjectBase;
import android.firefly.coltfashion.cc.netcomm.util.DateUtil;
import android.firefly.coltfashion.cc.netcomm.util.FileDirectoryUtil;
import android.firefly.coltfashion.cc.netcomm.util.StringUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Time;
import java.util.Calendar;


public class Loger extends FireflyObjectBase {

    static private final int KEEPLOG_DATECOUNT = -5;
    static private final String LOGTAG = "firefly-android-app";
    static private Loger _Loger;
    private String mErrorFileName;
    private String mWarnFileName;
    private String mInfoFileName;
    private String mDebugFileName;
    private Level mLevel = Level.debug;
    private boolean mInited;
    private String mLogRootPath = StringUtil.EMPTYSTR;
    private String mLogDate = StringUtil.EMPTYSTR;

    public Loger(String rootPath) {

        mLogRootPath = rootPath;
        initLog();

    }

    static public void init(String logDir) {

        _Loger = new Loger(logDir);
    }

    static public void setLogLevel(Level logLevel) {
        if (_Loger != null) {
            _Loger.setLevel(logLevel);
        }
    }

    static public void error(String codePath, String msg) {

        String prString = String.format("logtag:[%s],codepath:[%s],errormessage:[%s]", LOGTAG, codePath, msg);
        Log.e(LOGTAG, prString);

        if (_Loger != null) {
            _Loger.writeLog(Level.error, prString);
        }
    }

    static public void error(Throwable ex) {

        Log.e(LOGTAG, StringUtil.EMPTYSTR, ex);
        if (_Loger != null) {
            _Loger.writeLog(StringUtil.EMPTYSTR, ex);
        }

    }

    static public void error(String codePath, Throwable ex) {
        Log.e(LOGTAG, codePath, ex);
        if (_Loger != null) {
            _Loger.writeLog(codePath, ex);
        }
    }

    static public void warn(String codePath, String msg) {

        String prString = String.format("logtag:[%s],codepath:[%s],warnmessage:[%s]", LOGTAG, codePath, msg);
        Log.w(LOGTAG, prString);

        if (_Loger != null) {
            _Loger.writeLog(Level.warn, prString);
        }
    }

    static public void info(String codePath, String msg) {

        String prString = String.format("logtag:[%s],codepath:[%s],infomessage:[%s]", LOGTAG, codePath, msg);
        Log.i(LOGTAG, prString);

        if (_Loger != null) {
            _Loger.writeLog(Level.info, prString);
        }
    }

    static public void debug(String codePath, String msg) {
        String prString = String.format("logtag:[%s],codepath:[%s],debugmessage:[%s]", LOGTAG, codePath, msg);
        Log.d(LOGTAG, prString);

        if (_Loger != null) {

            _Loger.writeLog(Level.debug, prString);
        }
    }

    private void cleanFiles(String cleanDir, Calendar cleanDate) {

        File dir = new File(cleanDir);

        if (!dir.exists())
            return;

        File[] files = dir.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {

                String s = files[i].getAbsolutePath();
                int index = s.lastIndexOf("/");
                if ((index >= 0) && (index < s.length() - 1)) {
                    String dirName = s.substring(index + 1);

                    Calendar c = DateUtil.strDateToCalendar(dirName);
                    if ((c != null)
                            && (c.getTimeInMillis() < cleanDate
                            .getTimeInMillis())) {

                        FileDirectoryUtil.deleteDirectory(s);

                    }
                }

            } else if (files[i].isFile()) {

                String s = files[i].getAbsolutePath();

                if (!StringUtil.equals(FileDirectoryUtil.getExtFileName(s),
                        ".log"))
                    continue;

                Calendar c = DateUtil.strDateToCalendar(FileDirectoryUtil
                        .getFileName(s));

                if ((c != null)
                        && (c.getTimeInMillis() < cleanDate.getTimeInMillis())) {

                    FileDirectoryUtil.deleteFile(s);

                }

            }

        }

    }

    private void prepareLog() {
        String logDate = DateUtil.calendarToDateString(Calendar.getInstance());
        if (!StringUtil.equalsIgnoreCase(logDate, mLogDate)) {

            mLogDate = logDate;

            String str1 = String.format("%s/error", mLogRootPath);
            mErrorFileName = String.format("%s/%s.log", str1, mLogDate);

            String str2 = String.format("%s/warn", mLogRootPath);
            mWarnFileName = String.format("%s/%s.log", str2, mLogDate);

            String str3 = String.format("%s/info", mLogRootPath);
            mInfoFileName = String.format("%s/%s.log", str3, mLogDate);

            String str4 = String.format("%s/debug", mLogRootPath);
            mDebugFileName = String.format("%s/%s.log", str4, mLogDate);

        }

    }

    private boolean initLog() {

        if (mInited)
            return mInited;

        if (StringUtil.isNullEmpty(mLogRootPath)) {
            mInited = false;
            return mInited;
        }

        mLogDate = DateUtil.calendarToDateString(Calendar.getInstance());
        Calendar c = DateUtil.strDateToCalendar(mLogDate);
        c.add(Calendar.DAY_OF_MONTH, KEEPLOG_DATECOUNT);

        String str1 = String.format("%s/error", mLogRootPath);
        File file1 = new File(str1);
        if (!file1.exists()) {
            file1.mkdir();
        } else {
            cleanFiles(str1, c);
        }
        mErrorFileName = String.format("%s/%s.log", str1, mLogDate);

        String str2 = String.format("%s/warn", mLogRootPath);
        File file2 = new File(str2);
        if (!file2.exists()) {
            file2.mkdir();
        } else {
            cleanFiles(str2, c);
        }
        mWarnFileName = String.format("%s/%s.log", str2, mLogDate);

        String str3 = String.format("%s/debug", mLogRootPath);
        File file3 = new File(str3);
        if (!file3.exists()) {
            file3.mkdir();
        } else {
            cleanFiles(str3, c);
        }
        mDebugFileName = String.format("%s/%s.log", str3, mLogDate);

        String str4 = String.format("%s/info", mLogRootPath);
        File file4 = new File(str4);
        if (!file4.exists()) {
            file4.mkdir();
        } else {
            cleanFiles(str4, c);
        }
        mInfoFileName = String.format("%s/%s.log", str4, mLogDate);

        mInited = true;

        return mInited;
    }

    public void setLevel(Level level) {
        mLevel = level;
    }

    public synchronized void writeLog(Level logLevel, String msg) {

        if (logLevel.ordinal() <= mLevel.ordinal()) {

            prepareLog();
            String timeString = new Time(System.currentTimeMillis()).toString();
            String logString = String.format("[%s],%s", timeString, msg);

            String fileName = StringUtil.EMPTYSTR;

            switch (logLevel) {
                case error:
                    fileName = mErrorFileName;
                    break;
                case warn:
                    fileName = mWarnFileName;
                    break;
                case info:
                    fileName = mInfoFileName;
                    break;
                case debug:
                    fileName = mDebugFileName;
                    break;
                default:
                    break;
            }

            FileOutputStream fops = null;
            PrintStream ps = null;
            try {
                fops = new FileOutputStream(fileName, true);

                ps = new PrintStream(fops);
                ps.println(logString);

            } catch (Throwable ex) {

            } finally {
                if (ps != null)
                    ps.close();

                if (fops != null) {
                    try {
                        fops.close();
                    } catch (Throwable e) {

                    }
                }
            }
        }
    }

    public synchronized void writeLog(String codePath, Throwable ex) {

        if (Level.error.ordinal() <= mLevel.ordinal()) {

            prepareLog();

            String timeString = new Time(System.currentTimeMillis()).toString();

            String logString = String.format("[%s],logtag:[%s],codepath:[%s],errormessage:[%s]", timeString, LOGTAG, codePath, ex.getMessage());

            FileOutputStream fops = null;
            PrintStream ps = null;
            try {
                fops = new FileOutputStream(mErrorFileName, true);

                ps = new PrintStream(fops);
                ps.println(logString);
                ex.printStackTrace(ps);

            } catch (Throwable e) {

            } finally {

                if (ps != null)
                    ps.close();

                if (fops != null) {
                    try {
                        fops.close();
                    } catch (IOException e) {

                    }
                }
            }
        }

    }

    public enum Level {

        nolog(0, "non"), error(1, "error"), warn(2,
                "warn"), info(3, "info"), debug(4,
                "debug");

        private final int value;
        private final String name;

        Level(int value, String name) {
            this.value = value;
            this.name = name;
        }

        static public Level getEnum(int value) {
            for (Level l : Level.values()) {
                if (l.getValue() == value) {
                    return l;
                }
            }

            return Level.nolog;
        }

        static public Level getEnum(String value) {

            for (Level l : Level.values()) {

                if (l.getName().equals(value)) {
                    return l;
                }

            }

            return Level.nolog;
        }

        public int getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

    }

}
