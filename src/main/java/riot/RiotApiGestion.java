package riot;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

public class RiotApiGestion {

    public StringBuilder getInformation() throws RiotApiException {
        ApiConfig config = new ApiConfig().setKey("RGAPI-685059cc-faf3-4619-b5fd-59df63b01ed3");
        RiotApi api = new RiotApi(config);

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
