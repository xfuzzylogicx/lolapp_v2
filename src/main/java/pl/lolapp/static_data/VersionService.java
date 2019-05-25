package pl.lolapp.static_data;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class VersionService
{
    @Scheduled(fixedRate = 1000*30*1)
    public void getHomepage() {

        final String staticDataPath="https://ddragon.leagueoflegends.com/cdn/dragontail-";
        boolean fileDownloading=false;

        try {
            URL url = new URL("https://ddragon.leagueoflegends.com/api/versions.json");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null)
            {
                content.append(inputLine);
            }
            in.close();
            String[] versions = content.toString().replaceAll("[^\\d|\\.\\,]", "").split(",");

            if(!versions[0].equals("0")&& fileDownloading==false)
            {
                fileDownloading=true;
                System.out.println("File downloading");
                try (BufferedInputStream inFile = new BufferedInputStream(new URL(staticDataPath+versions[0]+".tgz").openStream());
                     FileOutputStream fileOutputStream = new FileOutputStream("/home/seb/Desktop/CodersLab/lolapp_v2/src/main/resources/dragontail-"+versions[0]+".tgz"))
                {
                     byte dataBuffer[] = new byte[1024];
                     int bytesRead;
                     while ((bytesRead = inFile.read(dataBuffer, 0, 1024)) != -1)
                     {
                        fileOutputStream.write(dataBuffer, 0, bytesRead);
                     }
                     fileDownloading=false;
                     System.out.println("plik pobrany");
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
