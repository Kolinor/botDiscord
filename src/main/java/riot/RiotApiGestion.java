package riot;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

public class RiotApiGestion {
    private ApiConfig config;
    private RiotApi api;


    public RiotApiGestion(String apiRiotToken) {
        config = new ApiConfig().setKey(apiRiotToken);
        api = new RiotApi(config);
    }

    public RiotApiGestion getRiotApiGestion() {
        return this;
    }

    public StringBuilder getInformation() throws RiotApiException {
        StringBuilder str = new StringBuilder();
        Summoner summoner = api.getSummonerByName(Platform.EUW, "Kolinor");
        str.append("Name: " + summoner.getName()).append("/n");
        str.append("Summoner ID: " + summoner.getId()).append("/n");
        str.append("Account ID: " + summoner.getAccountId()).append("/n");
        str.append("PUUID: " + summoner.getPuuid()).append("/n");
        str.append("Summoner Level: " + summoner.getSummonerLevel()).append("/n");
        str.append("Profile Icon ID: " + summoner.getProfileIconId()).append("/n");
        return str;
    }
}
