package pl.lolapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import pl.lolapp.champions.Champion;
import pl.lolapp.champions.ChampionDTO;
import pl.lolapp.static_data.StaticDataService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HomePageController {

    private ChampionDTO championDTO;
    private StaticDataService staticDataService;
    private Champion[] championsComparison= new Champion[3];

    @Autowired
    public HomePageController(StaticDataService staticDataService)
    {
        this.staticDataService=staticDataService;
        staticDataService.checkVersion();
        String version =StaticDataService.getCurrentVersion();
        ClassLoader classLoader = getClass().getClassLoader();
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            this.championDTO= (mapper.readValue(new File(classLoader.getResource("current_patch/dragontail-"+version+"/"+version+"/data/pl_PL/championFull.json").getFile()), ChampionDTO.class));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @ModelAttribute("championList")
    public Map<String,String> returnAllChampions()
    {
            return championDTO.keys;
    }
    @GetMapping(path= "/")
    public String getHomepage(Model model)
    {
        return "redirect:/championComp";
    }
    @GetMapping(path= "/championComp")
    public String getChampionComp(Model model)
    {
        return "championComp";
    }
    @GetMapping(path= "/championComp/{champion}")
    public String compareChampions(@PathVariable(name = "champion") String champion, Model model)
    {
        List<String> test=new ArrayList<>();
        championsComparison[2]=championsComparison[1];
        championsComparison[1]=championsComparison[0];
        championsComparison[0]=(Champion)championDTO.data.get(champion);
        //model.addAttribute("champ1","dragontail-"+StaticDataService.getCurrentVersion()+"/img/champion/loading/"+championsComparison[0].getName()+"_0.jpg");
        return "redirect:/championComp";
    }
}
