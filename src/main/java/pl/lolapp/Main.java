package pl.lolapp;


import com.fasterxml.jackson.databind.ObjectMapper;
import pl.lolapp.champions.ChampionDTO;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws  IOException
    {

       ObjectMapper mapper = new ObjectMapper();

        ChampionDTO championDTO= (mapper.readValue(new File("F:/JavaApps/lolapp_v2/src/main/resources/championFull.json"),ChampionDTO.class));
        System.out.println("");

    }

}
