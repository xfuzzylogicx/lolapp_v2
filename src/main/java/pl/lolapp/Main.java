package pl.lolapp;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import pl.lolapp.champions.Champion;
import pl.lolapp.champions.ChampionDTO;
import pl.lolapp.champions.Image;


import java.io.*;

public class Main {
    public static void main(String[] args) throws  IOException {

       ObjectMapper mapper = new ObjectMapper();

        ChampionDTO championDTO= (mapper.readValue(new File("src\\main\\resources\\championFull.json"),ChampionDTO.class));
        System.out.println("");
    }
}
