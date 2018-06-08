package isen_brest.projet_m1.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import isen_brest.projet_m1.model.Sequentiel;


public class JsonUtil {

    // Retourne un JSONObject correspondant à l'objet Sequentiel passé en paramètre
    public static JSONObject toJson (Sequentiel sequentiel){
        try {
            JSONObject sequentielJson = new JSONObject();

            sequentielJson.put("nomSequentiel", sequentiel.getNomSequentiel());
            sequentielJson.put("iconeSequentiel", sequentiel.getIconeSequentiel());
            sequentielJson.put("defilement", sequentiel.getDefilement());
            sequentielJson.put("voix", sequentiel.getVoix());

            JSONArray etapeJsonArray = new JSONArray();

            for (Sequentiel.Etape etape : sequentiel.getEtapeList()){
                JSONObject etapeJson = new JSONObject();

                etapeJson.put("media", etape.getMedia());
                etapeJson.put("nomEtape", etape.getNomEtape());
                etapeJson.put("numEtape", etape.getNumEtape());
                etapeJson.put("minuteur", etape.getMinuteur() == null ? JSONObject.NULL : etape.getMinuteur());
                etapeJson.put("videoVitesse", etape.getVideoVitesse() == null ? JSONObject.NULL : etape.getVideoVitesse());
                etapeJson.put("videoBoucle", etape.getVideoBoucle() == null ? JSONObject.NULL : etape.getVideoBoucle());

                etapeJsonArray.put(etapeJson);
            }

            sequentielJson.put("etapes", etapeJsonArray);

            return sequentielJson;
        }
        catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    // Retourne un objet Sequentiel correspondant au JSONObject passé en paramètre
    public static Sequentiel toSequentiel (JSONObject jsonObject){
        Sequentiel sequentiel = new Sequentiel();

        try {
            sequentiel.setNomSequentiel(jsonObject.getString("nomSequentiel"));
            sequentiel.setIconeSequentiel(jsonObject.getString("iconeSequentiel"));
            sequentiel.setDefilement(jsonObject.getString("defilement"));
            sequentiel.setVoix(jsonObject.getBoolean("voix"));

            List<Sequentiel.Etape> etapeList = new ArrayList<>();
            for (int i = 0; i < jsonObject.getJSONArray("etapes").length(); i++){
                JSONObject etapeJson = jsonObject.getJSONArray("etapes").getJSONObject(i);
                Sequentiel.Etape etape = new Sequentiel.Etape();

                etape.setMedia(etapeJson.getString("media"));
                etape.setNomEtape(etapeJson.getString("nomEtape"));
                etape.setNumEtape(etapeJson.getInt("numEtape"));
                etape.setMinuteur(etapeJson.isNull("minuteur") ? null : etapeJson.getInt("minuteur"));
                etape.setVideoVitesse(etapeJson.isNull("videoVitesse") ? null : etapeJson.getInt("videoVitesse"));
                etape.setVideoBoucle(etapeJson.isNull("videoBoucle") ? null : etapeJson.getBoolean("videoBoucle"));

                etapeList.add(etape);
            }
            sequentiel.setEtapeList(etapeList);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sequentiel;
    }
}
