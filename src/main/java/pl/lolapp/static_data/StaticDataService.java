package pl.lolapp.static_data;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.io.IOUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.zip.*;

@Service
public class StaticDataService {
    final String staticDataUrl = "https://ddragon.leagueoflegends.com/cdn/dragontail-";
    final String staticDataPath = "F:/JavaApps/lolapp_v2/src/main/resources/current_patch";
    final String LinuxStaticDataPath = "/home/seb/Desktop/CodersLab/lolapp_v2/src/main/resources/current_path";
    final String staticZipedDataPath = "F:/JavaApps/lolapp_v2/src/main/resources/";
    final String LinuxStaticZipedDataPath = "/home/seb/Desktop/CodersLab/lolapp_v2/src/main/resources/";

    public boolean fileDownloading = false;

    @Scheduled(fixedRate = 1000 * 60 * 5)
    public void checkVersion() {
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
            System.out.println("Version " + versions[0]);

            unzipFiles(versions[0],true);

            if (isUpdated(versions[0])) {
                getStaticData(versions[0]);
            } else System.out.println("Patch up to date");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isUpdated(String version) {
        try (Stream<Path> paths = Files.walk(Paths.get(LinuxStaticZipedDataPath))) {
            boolean fileNotExists = paths
                    .filter(Files::isRegularFile)
                    .noneMatch(s -> s.getFileName().endsWith("dragontail-" + version + ".tgz"));
            return fileNotExists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void getStaticData(String version) {

        File tgzFile = new File(LinuxStaticZipedDataPath+"/dragontail-"+version+".tgz");

        System.out.println("File downloading");
        try (BufferedInputStream inFile = new BufferedInputStream(new URL(staticDataUrl + version + ".tgz").openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(tgzFile)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = inFile.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            System.out.println("File downloaded");
            //unzipFiles(tgzFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void untarFiles(File tarFile) {
        try
        {
            System.out.println("Untaring file");
            TarArchiveInputStream myTarFile = new TarArchiveInputStream(new FileInputStream(tarFile));
            TarArchiveEntry entry = null;
            int offset;
            FileOutputStream outputFile = null;
            //czyta wszystkie wpisy w pliku tar
            while ((entry = myTarFile.getNextTarEntry()) != null) {
                String fileName = tarFile.getName().substring(0, tarFile.getName().lastIndexOf('.'));
                //File outputDir = new File(tarFile.getParent() + "/" + fileName + "/" + entry.getName());
                File outputDir = new File(LinuxStaticDataPath+ "/" + fileName + "/" + entry.getName());
                if (!outputDir.getParentFile().exists()) {
                    outputDir.getParentFile().mkdirs();
                }
                //if the entry in the tar is a directory, it needs to be created, only files can be extracted
                if (entry.isDirectory()) {
                    outputDir.mkdirs();
                } else {
                    byte[] content = new byte[(int) entry.getSize()];
                    offset = 0;
                    myTarFile.read(content, offset, content.length - offset);
                    outputFile = new FileOutputStream(outputDir);
                    IOUtils.write(content, outputFile);
                    outputFile.close();
                }
            }
            //zamkniecie streamu i usuniecie pliku tar
            myTarFile.close();
            tarFile.delete();
            System.out.println("File unpacked");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    public void unzipFiles(String version,boolean unTar)
    {
        File tgzFile = new File(LinuxStaticZipedDataPath+"/dragontail-"+version+".tgz");//to do argumentu
        File tarFile = new File(tgzFile.toString().substring(0,tgzFile.toString().lastIndexOf("."))+".tar");
        try {
            System.out.println("Unziping file");
            GZIPInputStream ginstream = new GZIPInputStream(new FileInputStream(tgzFile));
            FileOutputStream outstream = new FileOutputStream(tarFile);
            byte[] buf = new byte[1024];
            int len;
            while ((len = ginstream.read(buf)) > 0) {
                outstream.write(buf, 0, len);
            }
            System.out.println("Unziped file");
            if(unTar==true)
            {
                untarFiles(tarFile);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}





