package study.javase.Util;

import java.io.*;

public class FileUtil {
    public void copyFile(String sourcePath, String destPath) throws IOException {
        File sourceFile = new File(sourcePath);
        File destFile = new File(destPath);

        if (!sourceFile.exists() || !sourceFile.isFile()) {
            System.out.println("源文件不存在或不是文件: " + sourcePath);
            return;
        }

        File parent = destFile.getParentFile();
        if (parent != null && !parent.exists()) {
            if (!parent.mkdirs()) {
                throw new IOException("无法创建目标目录: " + parent.getAbsolutePath());
            }
        }

        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(destFile)) {
            byte[] buffer = new byte[8192];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.flush();
        }
    }

    public void cutFile(String sourcePath, String destPath) throws IOException {
        copyFile(sourcePath, destPath);
        File sourceFile = new File(sourcePath);
        if (!sourceFile.delete()) {
            throw new IOException("无法删除源文件: " + sourcePath);
        }
    }
}

