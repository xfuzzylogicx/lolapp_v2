package pl.lolapp.static_data;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

@Service
public class VersionService
{
    final String staticDataUrl="https://ddragon.leagueoflegends.com/cdn/dragontail-";
    final String staticDataPath="F:/JavaApps/lolapp_v2/src/main/resources/current_patch";
    final String staticZipedDataPath="F:/JavaApps/lolapp_v2/src/main/resources/dragontail-";
    public boolean fileDownloading=false;

    @Scheduled(fixedRate = 1000*60*5)
    public void checkVersion()
    {
        try {
            URL url = new URL("https://ddragon.leagueoflegends.com/api/versions.json");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            String[] versions = content.toString().replaceAll("[^\\d|\\.\\,]", "").split(",");
            System.out.println("Version "+versions[0]);

            unzipFiles(versions[0]);

            if(!isUpdated(versions[0]) && !fileDownloading)
            {
                getStaticData(versions[0]);
            }
            else System.out.println("Patch up to date");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isUpdated(String version)
    {
        try (Stream<Path> paths = Files.walk(Paths.get("F:/JavaApps/lolapp_v2/src/main/resources")))
        {
          boolean  fileExists= paths
                    .filter(Files::isRegularFile)
                    .anyMatch(s->s.getFileName().endsWith("dragontail-"+version+".tgz"));
          return fileExists;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }
    public void getStaticData(String version)
    {
        fileDownloading=true;
        System.out.println("File downloading");
        try (BufferedInputStream inFile = new BufferedInputStream(new URL(staticDataUrl+version+".tgz").openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("F:/JavaApps/lolapp_v2/src/main/resources/dragontail-"+version+".tgz"))
        {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = inFile.read(dataBuffer, 0, 1024)) != -1)
            {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            fileDownloading=false;
            System.out.println("File downloaded");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void unzipFiles(String version)
    {
            try {
                File inputFile = new File(staticDataPath);
                File fileZip = new File("F:/JavaApps/lolapp_v2/src/main/resources/dragontail-9.10.1.tgz");//new File(staticZipedDataPath+version+".tgz");

                // get the zip file content
                ArchiveInputStream zis = new TarArchiveInputStream(new GZIPInputStream(new FileInputStream(fileZip)));
                // get the zipped file list entry
                ArchiveEntry ze = zis.getNextEntry();

                while (ze != null) {
                    String fileName = ze.getName();
                    if (fileName.contains("version")) {
                        StringBuffer strBuffer = new StringBuffer();
                        while (zis.read(buffer) > 0) {
                            strBuffer.append(new String(buffer));
                        }
                        System.out.println(strBuffer.toString());
                    }
                    ze = zis.getNextEntry();
                }

                zis.close();
            } catch (IOException e)
            {
                System.out.println(e);
            }
    }

    public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());
        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }
}


