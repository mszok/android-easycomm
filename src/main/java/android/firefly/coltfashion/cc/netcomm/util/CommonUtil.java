package android.firefly.coltfashion.cc.netcomm.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.firefly.coltfashion.cc.netcomm.Loger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.UUID;

public class CommonUtil {

    static public final String TAG = "CommonUtil";

    static private SysCpuInfo _CpuUsage;

    static public String getMethodDescripe(String tag, String methodName) {

        StringBuilder sb = new StringBuilder();
        if (!StringUtil.isNullEmpty(tag))
            sb.append(tag);
        sb.append(".");
        if (!StringUtil.isNullEmpty(methodName))
            sb.append(methodName);

        return sb.toString();

    }

    static private String getHashString(MessageDigest digest) {

        StringBuilder builder = new StringBuilder();

        for (byte b : digest.digest()) {

            builder.append(Integer.toHexString((b >> 4) & 0xf));
            builder.append(Integer.toHexString(b & 0xf));
        }

        return builder.toString();
    }

    static public String getMD5(String content) {
        try {

            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(content.getBytes());
            return getHashString(digest);

        } catch (NoSuchAlgorithmException ex) {
            Loger.error(getMethodDescripe(TAG, "getMD5"), ex);
        }
        return null;
    }

    static public String getSHA1(String content) {
        try {

            MessageDigest digest = MessageDigest.getInstance("SHA1");
            digest.update(content.getBytes());
            return getHashString(digest);

        } catch (NoSuchAlgorithmException ex) {
            Loger.error(getMethodDescripe(TAG, "getSHA1"), ex);

        }
        return null;
    }

    static public long getSdAvailaleSize() {

        File path = Environment.getExternalStorageDirectory();

        return getPathAllSize(path.getPath());


    }

    static public long getSdAllSize() {

        File path = Environment.getExternalStorageDirectory();

        return getPathAvailaleSize(path.getPath());


    }

    static public long getPathAvailaleSize(String path) {

        StatFs stat = new StatFs(path);
        int blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;

    }

    static public long getPathAllSize(String path) {

        StatFs stat = new StatFs(path);
        int blockSize = stat.getBlockSize();
        long availableBlocks = stat.getBlockCount();
        return availableBlocks * blockSize;

    }

    static public String getIpAddress() {
        Enumeration<NetworkInterface> netInterfaces = null;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();

                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    InetAddress ip = ips.nextElement();
                    if (!ip.isLoopbackAddress() && (ip instanceof Inet4Address)) {
                        return ip.getHostAddress();
                    }
                }
            }
        } catch (Throwable ex) {
            Loger.error(getMethodDescripe(TAG, "getIpAddress"), ex);
        }
        return null;
    }

    static public String getLocalMacAddress(Context context) {

        String result = null;

        WifiManager wifi = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);

        WifiInfo info = wifi.getConnectionInfo();
        result = info.getMacAddress();

        if (StringUtil.isNullEmpty(result)
                || result.equals("00:00:00:00:00:00")) {
            String[] cmds = {"/system/bin/netcfg"};
            String cmdResult = cmdRun(cmds);
            cmdResult = StringUtil.spaceRepeatString(cmdResult);

            String[] ars = cmdResult.split("\n");

            for (String s : ars) {

                String[] ars1 = s.split(" ");

                if (ars1.length == 5) {
                    if (ars1[0].contains("eth")) {
                        result = ars1[4];
                        if (!result.equals("00:00:00:00:00:00"))
                            break;
                        else
                            result = StringUtil.EMPTYSTR;
                        continue;
                    }
                }
            }
        }

        if (StringUtil.isNullEmpty(result) || result.equals("00:00:00:00:00:00")) {

            String macSerial = null;

            try {
                Process pp = Runtime.getRuntime().exec(
                        "cat /sys/class/net/eth0/address");
                InputStreamReader ir = new InputStreamReader(
                        pp.getInputStream());
                LineNumberReader input = new LineNumberReader(ir);

                String str = StringUtil.EMPTYSTR;
                for (; null != str; ) {
                    str = input.readLine();
                    if (str != null) {
                        macSerial = str.trim();
                        break;
                    }
                }
            } catch (IOException ex) {

                Loger.error(getMethodDescripe(TAG, "getLocalMacAddress"), ex);
            }

            result = macSerial;
        }

        if (StringUtil.isNullEmpty(result) || result.equals("00:00:00:00:00:00")) {

            String macSerial = null;

            try {
                Process pp = Runtime.getRuntime().exec(
                        "cat /sys/class/net/wlan0/address");
                InputStreamReader ir = new InputStreamReader(
                        pp.getInputStream());
                LineNumberReader input = new LineNumberReader(ir);
                String str = StringUtil.EMPTYSTR;
                for (; null != str; ) {
                    str = input.readLine();
                    if (str != null) {
                        macSerial = str.trim();
                        break;
                    }
                }
            } catch (IOException ex) {

                Loger.error(getMethodDescripe(TAG, "getLocalMacAddress"), ex);

            }

            result = macSerial;
        }


        return result;
    }

    static public String getMachineSerialCode(Context context) {

        String machineTag = getLocalMacAddress(context);

        if (machineTag.equals("00:00:00:00:00:00"))
            machineTag = StringUtil.EMPTYSTR;

        if (StringUtil.isNullEmpty(machineTag)) {

            machineTag = Secure.getString(context.getContentResolver(),
                    Secure.ANDROID_ID);

        }

        boolean flag = false;
        if (StringUtil.isNullEmpty(machineTag)) {
            machineTag = UUID.randomUUID().toString();
            flag = true;
        }

        machineTag = getMD5(machineTag);

        if (flag)
            machineTag = "RAN" + machineTag;

        if (machineTag.length() > 20)
            machineTag = machineTag.substring(0, 20);

        return machineTag.toUpperCase();
    }

    static public String getOsInformation() {

//        return "android";
        return String.format("android (module:%s,version:%s,sdkversion:%d)",
                android.os.Build.MODEL, android.os.Build.VERSION.RELEASE,
                android.os.Build.VERSION.SDK_INT);

    }

    // Secure.getString(context.getContentResolver(),
    // Secure.ANDROID_ID);

    // TelephonyManager.getDeviceId()

    // android.os.Build.SERIAL

    static public int isMachineIdValid(Context context, String machineId) {

        int re = 0;
        String machineTag = getLocalMacAddress(context);

        if (StringUtil.isNullEmpty(machineTag) || machineTag.equals("00:00:00:00:00:00")) {

            if (!machineId.startsWith("RAN"))
                re = -1;

        } else {

            machineTag = getMD5(machineTag);
            if (machineTag.length() > 20)
                machineTag = machineTag.substring(0, 20);
            machineTag = machineTag.toUpperCase();

            if (!StringUtil.equals(machineId, machineTag))
                re = -2;
        }

        return re;
    }

    static public PackageInfo getPackageInfo(Context context) {
        try {
            if (context != null) {
                return context.getPackageManager().getPackageInfo(
                        context.getPackageName(), 0);

            }
        } catch (NameNotFoundException ex) {
            Loger.error(getMethodDescripe(TAG, "getPackageInfo"), ex);
        }

        return null;

    }

    public static int getVerCode(Context context) {


        PackageInfo pinfo = getPackageInfo(context);
        if (pinfo != null) {
            return pinfo.versionCode;
        }

        return -1;
    }

    public static String getVerName(Context context) {
        PackageInfo pinfo = getPackageInfo(context);
        if (pinfo != null) {
            return pinfo.versionName;
        }

        return StringUtil.EMPTYSTR;

    }

    static public synchronized String cmdRun(String[] cmds) {

        String result = StringUtil.EMPTYSTR;
        InputStream in = null;
        try {
            ProcessBuilder builder = new ProcessBuilder(cmds);

            Process process = builder.start();
            in = process.getInputStream();
            byte[] re = new byte[1024];
            while (in.read(re) > 0) {
                result = result + new String(re);
            }

        } catch (Throwable ex) {
            Loger.error(getMethodDescripe(TAG, "cmdRun"), ex);
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (Throwable e) {

                }
        }
        return result;
    }



    static public void sleepThread(long msecond) {

        try {
            Thread.sleep(msecond);
        } catch (InterruptedException ex) {
            Loger.error(getMethodDescripe(TAG, "sleepThread"), ex);
        }
    }


    static public class SysMemInfo {

        public long totalSize;

        public long aviableSize;

        public long bufferSize;

        public long cacheSize;
    }

    static public class SysCpuInfo {

        public long busyTime;

        public long idleTime;

        public long snapTime;
    }
}
